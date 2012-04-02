import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yin
 * @author sst8696
 * @author peter
 */
public class FileSearch implements Callable<Found> {

    private final InputStream currentStream;
    private final String patternString;
    private final ArrayList<String> list = new ArrayList<String>();
    private final Found result;

    /**
     * @param currentFile
     * @param patternString
     * @throws FileNotFoundException
     */
    public FileSearch(File currentFile, String patternString)
            throws FileNotFoundException {
        this.currentStream = new FileInputStream(currentFile);
        this.result = new Found();
        this.result.setName(currentFile.getName());
        this.patternString = patternString;
    }

    /**
     * @param input
     * @param patternString
     */
    public FileSearch(InputStream input, String patternString) {
        this.currentStream = input;
        this.result = new Found();
        this.patternString = patternString;
        this.result.setName("-");
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Found call() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                currentStream));

        // The line currently being read in the file.
        String currentLine;

        // The current line number.
        long lineCount = 0;

        Pattern expression = Pattern.compile(patternString);

        while ((currentLine = reader.readLine()) != null) {
            Matcher matcher = expression.matcher(currentLine);

            if (matcher.find()) {
                list.add(lineCount + " " + currentLine);
            }

            lineCount++;
        }

        result.setEntries(list);

        return result;
    }
}