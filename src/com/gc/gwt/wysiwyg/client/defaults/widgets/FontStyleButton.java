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
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class FontStyleButton extends EditorToolbarSelect implements ChangeListener {

  private Editor editor;
  
  public FontStyleButton(Editor editor) {
    super();
    this.editor = editor;
    init();
  }
  
  public void onChange(Widget sender) {
    ListBox subj = ((ListBox) sender);
    String value = subj.getValue(subj.getSelectedIndex());
    subj.setSelectedIndex(0);
    editor.getRichTextArea().getBasicFormatter().setFontName(value);
    editor.getRichTextArea().setFocus(true);
  }
  
  private void init() {
    this.addItem("Font", "");
    String[][] formats = EditorUtils.getSupportedFormats();
    for (int i = 0; i < formats.length; i++) {
      this.addItem(formats[i][0], formats[i][1]);
    }
    this.addChangeListener(this);
  }
}
