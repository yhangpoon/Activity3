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

}
