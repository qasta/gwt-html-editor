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

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorPromptBox;
import com.gc.gwt.wysiwyg.client.EditorPromptBoxSubmitListener;

/**
 * TODO: javadocs.
 *
 * @author pavel.jbanov
 */
public abstract class AdvancedPromptBox extends EditorPromptBox {
  
  protected Editor editor;
  
  /**
   * TODO: javadocs.
   *
   * @param editor editor
   * @param command Midas command
   * @param title dialog caption
   */
  public AdvancedPromptBox(Editor editor, EditorCommand command, String title) {
    super(title);

    this.editor = editor;

    this.addEditorPromptPanelSubmitListener(new AdvancedPromptPanelPopupListener(command));
  }

  /**
   * TODO: javadocs.
   *
   * @author pavel.jbanov
   */
  private class AdvancedPromptPanelPopupListener implements EditorPromptBoxSubmitListener {

    private EditorCommand command;

    /**
     * TODO: javadocs.
     *
     * @param command
     */
    public AdvancedPromptPanelPopupListener(EditorCommand command) {
      this.command = command;
    }

    public void onSubmit(String value) {
      if (value != null) {
        command.exec(new String[]{value});
      }
    }
  }
}
