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

package co.uk.sussexsoftware;

/**
 * 
 * Simple Worker Bean that holds the state and performs work for the
 * Command or Mbean Interface
 * 
 * Note is synchronized to make it thread safe
 * 
 * @author rodm
 */
public class Worker {
    
    private String value = "Not Yet Set!";
    
    /**
     *
     * @param Arg
     * @return
     */
    public String doWork(int Arg) {
        return "Executed command "+Arg;
    }

    /**
     * @return the value
     */
    synchronized public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    synchronized public void setValue(String value) {
        this.value = value;
    }

}
