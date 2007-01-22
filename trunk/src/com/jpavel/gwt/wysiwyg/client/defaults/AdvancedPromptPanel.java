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

package com.jpavel.gwt.wysiwyg.client.defaults;

import com.jpavel.gwt.wysiwyg.client.Editor;
import com.jpavel.gwt.wysiwyg.client.EditorPromptPanel;
import com.jpavel.gwt.wysiwyg.client.EditorPromptPanelSubmitListener;
import com.jpavel.gwt.wysiwyg.client.EditorUtils;

public abstract class AdvancedPromptPanel extends EditorPromptPanel {
  
  protected Editor editor;
  
  public AdvancedPromptPanel(Editor editor, String command, String title) {
    super(title);

    this.editor = editor;
    
    this.addEditorPromptPanelSubmitListener(new AdvancedPromptPanelPopupListener(command));

    EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
  }
  
  private class AdvancedPromptPanelPopupListener implements EditorPromptPanelSubmitListener {
    
    private String command;
    
    public AdvancedPromptPanelPopupListener(String command) {
      this.command = command;
    }

    public void onSubmit(String value) {
      EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
      if (value != null) {
        editor.execCommand(command, false, value);
        EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    }
  }
  
}
