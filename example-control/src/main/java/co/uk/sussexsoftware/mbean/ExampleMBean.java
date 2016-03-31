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

package co.uk.sussexsoftware.mbean;

import co.uk.sussexsoftware.Worker;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 *
 *  Example MBean that exposes via the Karaf Platform MBean Server
 *  
 *  In this case the MBean is created by extending the StandardMBean and
 *  exposing an interface that is registered with the StandardMBean
 * 
 * @author rodm
 */
public class ExampleMBean extends StandardMBean implements ExampleMBeanInterface {
    private final Worker worker;
    
    /**
     *
     * @param worker
     * @throws NotCompliantMBeanException
     */
    public ExampleMBean(Worker worker) throws NotCompliantMBeanException {
        /* Using the StandardMBean as a parrent we must tell it which of the interfaces represents the Mbean */
        super(ExampleMBeanInterface.class);
        
        this.worker = worker;
    }

    /**
     *
     * @param arg
     * @return
     */
    @Override
    public String exampleOperation(int arg) {
        return worker.doWork(arg);
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return worker.getValue();
    }

    /**
     *
     * @param Value
     */
    public void setValue(String Value) {
        worker.setValue(Value);
    }
}
