package co.uk.sussexsoftware.command;

import co.uk.sussexsoftware.Worker;
import org.apache.felix.gogo.commands.*;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.log4j.Logger;


@Command(scope = "example", name = "set", description="Example Set Value")
public class SetValue extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(SetValue.class);
    private Thread theThread = null;
    
    @Argument(index = 0, name = "value", 
              description = "The value to set", 
              required = true, multiValued = false)
    String value;
    private final Worker worker;

    
    public SetValue(Worker worker)
    {
        this.worker = worker;
    }
    

    @Override
    protected Object doExecute() throws Exception {
               
        worker.setValue(value);
        
        return null;
    }

}
