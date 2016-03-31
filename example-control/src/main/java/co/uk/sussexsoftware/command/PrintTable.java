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

import java.io.ByteArrayOutputStream;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.karaf.shell.table.ShellTable;

/**
 *
 * @author rodm
 */

@Command(scope = "example", name = "print-table", description="Example of Printing a Table")
public class PrintTable extends OsgiCommandSupport {
    
    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Object doExecute() throws Exception {

        // Execute the command as aysnchronusly to allow it to return to the caller
        // but check that it has not already been called before
        ShellTable table = new ShellTable();
        table.separator("|");
        table.column("col1");
        table.column("col2");
        table.addRow().addContent("my first column value", "Some value here");
        table.addRow().addContent("my second column value", "Another Value here");
        table.addRow().addContent("my thrid column value", "Yet another value here");        
        table.size(50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        table.print(System.out);
        
        return null;
    }

    
    
}
