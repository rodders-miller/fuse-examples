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

/**
 *
 * Example Interface for an MBean that exposes both operations
 * and attributes to the MBean Server
 * 
 * @author rodm
 */
public interface ExampleMBeanInterface 
{

    /*  
        A Method that allows us to expose an MBean Operation
    */     

    /**
     *
     * @param arg
     * @return
     */
         
    String exampleOperation(int arg);
    
    /* Getter / Setter pair that allows us to expose an
       MBean Attribute of Value
    */ 

    /**
     *
     * @return
     */
     
    String getValue();

    /**
     *
     * @param Value
     */
    void setValue(String Value);
}
