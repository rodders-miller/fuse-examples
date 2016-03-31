package co.uk.sussexsoftware.command;

import co.uk.sussexsoftware.Worker;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.felix.gogo.commands.*;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.log4j.Logger;


@Command(scope = "example", name = "execute", description="Example Command Execute")
public class ExecuteOperation extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(ExecuteOperation.class);
    private Thread theThread = null;
    
    @Argument(index = 0, name = "argument", 
              description = "An argument to the command", 
              required = true, multiValued = false)
    int arg;
    private final Worker worker;

    public ExecuteOperation(Worker worker)
    {
        this.worker = worker;
    }
    

    @Override
    protected Object doExecute() throws Exception {

        // Execute the command as aysnchronusly to allow it to return to the caller
        // but check that it has not already been called before
        
        java.util.logging.Logger.getLogger(ExecuteOperation.class.getName()).log(Level.INFO, "Executing Command with value" + arg);
                
        System.out.println(worker.doWork(arg));
        
        
        // TODO :- add handling here for the Karaf terminal Scirpt etc.
        
        return null;
    }

}
