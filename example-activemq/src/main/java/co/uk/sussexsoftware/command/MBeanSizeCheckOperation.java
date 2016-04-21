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

//import co.uk.sussexsoftware.Worker;
import javax.management.MBeanServer;
import javax.management.ObjectName;
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
@Command(scope = "buffering", name = "size-check", description="checks the size of a buffer")
public class MBeanSizeCheckOperation extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(MBeanSizeCheckOperation.class);
    private final  javax.management.MBeanServer mBeanServer;
    
    /**
     *  The Argument is passed by the framework before calling doExecute
     */
    @Argument(index = 0, name = "argument", 
              description = "The destination name to check", 
              required = true, multiValued = false)
    String destination;
   // private final Worker worker;

    /**
     *
     * @param mBeanServer
     */
    public MBeanSizeCheckOperation(MBeanServer mBeanServer)
    {
       this.mBeanServer = mBeanServer;
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
        retriveDetails();
        long endTime = System.nanoTime();

        long duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.     

        System.out.println("execution tock " + duration);   

        return null;
    }

    private void retriveDetails() throws Exception {

    // Assuming the RuntimeMXBean has been registered in mbs
    System.out.println("org.apache.activemq:type=Broker,brokerName=amq,destinationType=Queue,destinationName=inbound.TEST3");
    ObjectName oname = new ObjectName("org.apache.activemq:type=Broker,brokerName=amq,destinationType=Queue,destinationName=" +destination);
    // Check if this MXBean contains Sun's extension

        // Get platform-specific attribute "TotalPhysicalMemorySize"
        long size = (Long) mBeanServer.getAttribute(oname, "QueueSize");
        long messageSize = (Long) mBeanServer.getAttribute(oname, "AverageMessageSize");
        long EnqueueCount = (Long) mBeanServer.getAttribute(oname, "EnqueueCount");
        long storeUseage  = size * messageSize;
     
        System.out.println( "Message Size " + messageSize);
        System.out.println( "Size " + size);        
        System.out.println( "Store Usage " + storeUseage);
        System.out.println( "Enqueue Count " + EnqueueCount);
}
    
}