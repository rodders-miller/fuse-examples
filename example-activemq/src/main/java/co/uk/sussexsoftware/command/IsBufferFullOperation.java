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

import co.uk.sussexsoftware.buffer.MBeanSizeCheck;
import org.apache.felix.gogo.commands.*;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.log4j.Logger;

/**
 *
 *  Example Operation that exposes a command to the Karaf Console
 * 
 *  This command utilises the MBeanSizeChecker to check if their is space 
 *  available in the buffer
 * 
 * @author rodm
 */
@Command(scope = "buffering", name = "is-buffer-full", description="checks the size of a buffer")
public class IsBufferFullOperation extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(IsBufferFullOperation.class);
    
    private final  MBeanSizeCheck sizeChecker;
    
    /**
     *  The Argument is passed by the framework before calling doExecute
     */
    @Argument(index = 0, name = "argument", 
              description = "The destination name to check", 
              required = true, multiValued = false)
    String destination;

    /**
     *
     * @param sizeChecker
     */
    public IsBufferFullOperation(MBeanSizeCheck sizeChecker)
    {  
        this.sizeChecker = sizeChecker;
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
        
        long startTime = System.nanoTime();
            boolean isBufferFull = sizeChecker.isBufferFull(destination);
        long endTime = System.nanoTime();

        long duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.     

        String res =  isBufferFull? "Buffer is full" : "Buffer is not full"; 
        
        System.out.println(res);   
        System.out.println("execution tock " + duration);   

        return null;
    }    
}