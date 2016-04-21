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

package co.uk.sussexsoftware.buffer;

import java.util.Dictionary;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

/**
 *
 * @author rodm
 */
public class MBeanSizeCheck implements ManagedService {
    
    private final String mBeanName;
    private final MBeanServer mBeanServer;
    private Dictionary<String, ?> bufferSizes;
    
    /**
     *
     *  Note:  this class uses the Mbean server within Karaf, which has an MBean guard requiring RBAC
     *  Therefore to access from with the process the MBean must be white listed in \auth\jmx.acl.whiitelist.cfg
     * 
     * @param mBeanServer
     * @param amqBrokerName
     */
    public MBeanSizeCheck(MBeanServer mBeanServer, String amqBrokerName)
    {
       this.mBeanServer = mBeanServer;
       
       // Build the Object Name for the mBean as "org.apache.activemq:type=Broker,brokerName=<amqBrokerName>,destinationType=Queue,destinationName="
       // name of destination is added later by the retriveBufferSize method
       StringBuilder mBeanNameBuilder = new StringBuilder();
       mBeanNameBuilder.append("org.apache.activemq:type=Broker,brokerName=");
       mBeanNameBuilder.append(amqBrokerName);
       mBeanNameBuilder.append(",destinationType=Queue,destinationName=");
       mBeanName=mBeanNameBuilder.toString();
    }
    
    /**
     * 
     * @param bufferName
     * @return
     * @throws Exception
     */
    public boolean isBufferFull(String bufferName) throws Exception
    {     
        long maxBufferSize = Long.parseLong((String) bufferSizes.get(bufferName+".size"));
        long bufferSize = retriveBufferSize(bufferName);
        return (bufferSize > maxBufferSize);
    }
    
    
    private long retriveBufferSize(String bufferName) throws Exception
    {    
        // Assuming the RuntimeMXBean has been registered in mbs
        String objectName = mBeanName+bufferName;
        ObjectName oname = new ObjectName(objectName);

        // Get platform-specific attribute "TotalPhysicalMemorySize"
        return (Long) mBeanServer.getAttribute(oname, "QueueSize");
    }

    /**
     * This implements the ManagedService interface for obtaining a copy of 
     * property set that contains the buffer sizing values
     * 
     * @param dctnr
     * @throws ConfigurationException
     */
    public void updated(Dictionary<String, ?> dctnr) throws ConfigurationException 
    {    
        this.bufferSizes = dctnr;
    }
}
