/*
 * Copyright 2006 Pavel Jbanov.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gc.gwt.wysiwyg.client.defaults;

import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class SimpleCommandButton extends EditorToolbarButton {
  
  public SimpleCommandButton(String buttonId, final EditorCommand command) {
    this(buttonId, command, false, null);
  }

  public SimpleCommandButton(String buttonId, final EditorCommand command, final boolean ui, String value) {
    super(buttonId);
    
    this.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        command.exec(null);
      }
    });
  }
}
