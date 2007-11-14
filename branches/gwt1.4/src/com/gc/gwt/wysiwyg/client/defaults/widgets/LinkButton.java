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

package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.gc.gwt.wysiwyg.client.defaults.EditorCommand;
import com.gc.gwt.wysiwyg.client.defaults.SimpleOneFieldPromptBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class LinkButton extends EditorToolbarButton implements ClickListener {
  
  private Editor editor;
  
  public LinkButton(Editor editor) {
    super(DefaultConstants.BUTTON_LINK);
    this.editor = editor;
    this.addClickListener(this);
  }
  
  public void onClick(Widget sender) {
    new SimpleOneFieldPromptBox(editor, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
        EditorUtils.doCreateLink(editor.getEditorWYSIWYG().getFrame().getElement(), params[0]);
      }
    }, "Create Link", "Link URL: ", "Create Link").show(editor);
  }
  
}
