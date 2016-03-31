package co.uk.sussexsoftware.command;

import co.uk.sussexsoftware.Worker;
import org.apache.felix.gogo.commands.*;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.log4j.Logger;


@Command(scope = "example", name = "get", description="Example Get Value")
public class GetValue extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(GetValue.class);
    private final Worker worker;

    
    public GetValue(Worker worker)
    {
        this.worker = worker;
    }
    

    @Override
    protected Object doExecute() throws Exception {
               
        System.out.println(worker.getValue());
        
        return null;
    }

}
