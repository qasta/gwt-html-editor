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
import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.gc.gwt.wysiwyg.client.defaults.EditorColorPicker;
import com.gc.gwt.wysiwyg.client.defaults.EditorColorSelectListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ForegroundColorButton
    extends EditorToolbarButton
    implements EditorColorSelectListener, ClickListener {

  private Editor editor;
  private EditorColorPicker fgPicker;
  
  public ForegroundColorButton(Editor editor) {
    super(DefaultConstants.BUTTON_TEXTCOLOR);
    this.editor = editor;
    init();
  }

  public void colorSelected(String rgb) {
    editor.getRichTextArea().setFocus(true);
    editor.getRichTextArea().getBasicFormatter().setForeColor(rgb);
  }

  public void onClick(Widget sender) {
    fgPicker.show();
    fgPicker.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
  }

  private void init() {
    this.fgPicker = new EditorColorPicker("Select Text Color");
    this.fgPicker.addSelectListener(this);
    this.addClickListener(this);
  }
}
