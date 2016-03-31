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

import java.util.List;
import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

/**
 *
 *  An example command completer that completes the strings for the 
 *  user of the command as required
 *
 * @author rodm
 */
public class ExecuteOperationCompleter implements Completer {
 /**
* @param buffer it's the beginning string typed by the user
* @param cursor it's the position of the cursor
* @param candidates the list of completions proposed to the user
     * @return 
*/
 @Override
 public int complete(String buffer, int cursor, List candidates) {
  StringsCompleter delegate = new StringsCompleter();
  delegate.getStrings().add("10");
  return delegate.complete(buffer, cursor, candidates);
 }

}
