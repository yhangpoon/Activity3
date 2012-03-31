import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.Callable;



public class FileSearch implements Callable<Found> {
	
	private final File currentFile;
	
	public FileSearch(File currentFile){
		this.currentFile=currentFile;
	}

	public Found call() throws Exception{
		FileReader fstream = new FileReader(currentFile);
		BufferedReader reader = new BufferedReader(fstream);
		Found result = new Found();
		return result;
	}
}
