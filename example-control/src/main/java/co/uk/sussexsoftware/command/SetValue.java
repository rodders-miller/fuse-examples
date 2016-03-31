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
@Command(scope = "example", name = "set", description="Example Set Value")
public class SetValue extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(SetValue.class);
    private Thread theThread = null;
    
    @Argument(index = 0, name = "value", 
              description = "The value to set", 
              required = true, multiValued = false)
    String value;
    private final Worker worker;

    /**
     *
     * @param worker
     */
    public SetValue(Worker worker)
    {
        this.worker = worker;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Object doExecute() throws Exception {
               
        worker.setValue(value);
        
        return null;
    }

}
