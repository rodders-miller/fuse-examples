package co.uk.sussexsoftware;

/**
 * 
 * Simple Worker Bean that holds the state and performs work for the
 * Command or Mbean Interface
 * 
 * Note is synchronized to make it thread safe
 * 
 * @author rodm
 */
public class Worker {
    
    private String value = "Not Yet Set!";
    
        
    public String doWork(int Arg) {
        return "Executed command "+Arg;
    }

    /**
     * @return the value
     */
    synchronized public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    synchronized public void setValue(String value) {
        this.value = value;
    }

}
