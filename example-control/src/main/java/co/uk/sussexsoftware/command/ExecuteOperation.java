/*
 * Copyright 2016 Sussex Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and

*/

package co.uk.sussexsoftware.command;

import co.uk.sussexsoftware.Worker;
import java.util.logging.Level;
import org.apache.felix.gogo.commands.*;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.log4j.Logger;

/**
 *
 *  Example Operation that exposes a command to the Karaf Console
 * 
 *  This command delegates to a worker that is executes the command
 * 
 * @author rodm
 */
@Command(scope = "example", name = "execute", description="Example Command Execute")
public class ExecuteOperation extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(ExecuteOperation.class);
    
    /**
     *  The Argument is passed by the framework before calling doExecute
     */
    @Argument(index = 0, name = "argument", 
              description = "An argument to the command", 
              required = true, multiValued = false)
    int arg;
    private final Worker worker;

    /**
     *
     * @param worker
     */
    public ExecuteOperation(Worker worker)
    {
        this.worker = worker;
    }
    
    /**
     *
     * The entry point for the command to be executed from the Karaf CLI
     * 
     * The argument is passed to the call by the framework using the @Argument annotation
     * 
     * @return
     * @throws Exception
     */
    @Override
    protected Object doExecute() throws Exception {
       
        java.util.logging.Logger.getLogger(ExecuteOperation.class.getName()).log(Level.INFO, "Executing Command with value" + arg);
                
        System.out.println(worker.doWork(arg));
        
        return null;
    }

}