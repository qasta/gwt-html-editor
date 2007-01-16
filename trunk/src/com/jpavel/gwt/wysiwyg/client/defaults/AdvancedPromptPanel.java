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

import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.jpavel.gwt.wysiwyg.client.Editor;
import com.jpavel.gwt.wysiwyg.client.EditorPromptPanel;
import com.jpavel.gwt.wysiwyg.client.EditorPromptPanelWidget;
import com.jpavel.gwt.wysiwyg.client.EditorUtils;

public class AdvancedPromptPanel extends EditorPromptPanel {
  
  protected Editor editor;
  
  public AdvancedPromptPanel(Editor editor, String command, String title, EditorPromptPanelWidget widget) {
    super(title, widget);

    this.editor = editor;
    
    this.addPopupListener(new AdvancedPromptPanelPopupListener(command));

    EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    super.show(editor);
  }
  
  private class AdvancedPromptPanelPopupListener implements PopupListener {
    
    private String command;
    
    public AdvancedPromptPanelPopupListener(String command) {
      this.command = command;
    }
    
    public void onPopupClosed(final PopupPanel sender, boolean autoClosed) {
      String value = ((EditorPromptPanel) sender).getValue();
      EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
      if (value != null) {
        editor.execCommand(command, false, value);
        EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    }
  }
  
}
