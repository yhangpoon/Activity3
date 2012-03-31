import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author sst8696
 *
 */
public class FileSearch implements Callable<Found> {
	
	private final InputStream currentStream;
	private final String patternString;
	private ArrayList<String> list = new ArrayList<String>();
	private Found result;
	
	public FileSearch(File currentFile, String patternString) throws FileNotFoundException{
		this.currentStream = new FileInputStream(currentFile);
		this.result=new Found();
		this.result.setName(currentFile.getName());
		this.patternString=patternString;
	}
	
	public FileSearch(InputStream input,String patternString){
		this.currentStream=input;
		this.result=new Found();
		this.result.setName("Console");
		this.patternString=patternString;
	}

	
	public Found call() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(currentStream));
		
		//The line currently being read in the file.
		String currentLine;
		
		//
		long lineCount=0;
		
		Pattern expression = Pattern.compile(patternString);
		
		while((currentLine=reader.readLine())!=null){
			Matcher matcher = expression.matcher(currentLine);
			if(matcher.find()){
				list.add(lineCount +" "+ currentLine);
			}
			lineCount++;
		}
		result.setEntries(list);
		
		
		
		return result;
	}
}