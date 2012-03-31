import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Yin
 * @author sst8696
 * @author peter
 */
public class Found {

    private String name;
    private List<String> entries;

    public Found() {
        this.setName(null);
        this.setEntries(new ArrayList<String>());
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for entries.
     * 
     * @return the entries
     */
    public List<String> getEntries() {
        return entries;
    }

    /**
     * Setter for entries.
     * 
     * @param entries
     *            the entries to set
     */
    public void setEntries(List<String> entries) {
        this.entries = entries;
    }
    
    /**
     * Checks to see if this file has any occurrences of the pattern
     * 
     * @return true if the size of the entries list is not zero
     */
    public boolean hasFoundOccurence(){
    	return this.entries.size() != 0;
    }
    
    /**
     * Generates a string to be printed to the screen
     * 
     * @return the string to be printed
     */
    public String toString(){
    	String ret = name + "/n/t";
    	for (int i=0; i<entries.size(); i++){
    		ret = ret + " " + entries.get(i) + "/n/t";
    	}
    	return ret;
    }
}
