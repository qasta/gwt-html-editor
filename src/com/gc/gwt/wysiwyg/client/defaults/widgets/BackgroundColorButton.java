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
import com.gc.gwt.wysiwyg.client.defaults.EditorColorPicker;
import com.gc.gwt.wysiwyg.client.defaults.EditorColorSelectListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class BackgroundColorButton
    extends EditorToolbarButton
    implements EditorColorSelectListener, ClickHandler {

  private Editor editor;
  private EditorColorPicker bgPicker;
  
  public BackgroundColorButton(Editor editor) {
    super(DefaultConstants.BUTTON_TEXTBACKGROUNDCOLOR);
    this.editor = editor;
    init();
  }
  
  public void colorSelected(String rgb) {
    EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    EditorUtils.doBackgroundColor(editor.getEditorWYSIWYG().getFrame().getElement(), rgb);
    EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
  }
  
  public void onClick(ClickEvent event) {
    EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    bgPicker.show();
    bgPicker.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
  }

  private void init() {
    this.bgPicker = new EditorColorPicker("Select Background Color");
    this.bgPicker.addSelectListener(this);
    this.addClickHandler(this);
  }
}
