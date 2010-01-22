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
import com.gc.gwt.wysiwyg.client.EditorToolbarSelect;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

public class FontSizesCombo extends EditorToolbarSelect implements ChangeHandler {

  Editor editor;

  public FontSizesCombo(Editor editor) {
    this.editor = editor;
    init();
  }

  public void onChange(ChangeEvent event) {
    ListBox subj = ((ListBox) event.getSource());
    String value = subj.getValue(subj.getSelectedIndex());
    subj.setSelectedIndex(0);
    EditorUtils.doFontSize(editor.getEditorWYSIWYG().getFrame().getElement(), value);
    EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
  }

  private void init() {
    this.addItem("Size", "");
    for (int i = 1; i < 8; i++) {
      this.addItem("Size " + i, "" + i);
    }
    this.addChangeHandler(this);
  }
}
