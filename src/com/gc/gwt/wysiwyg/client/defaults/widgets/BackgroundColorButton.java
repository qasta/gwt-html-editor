package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.gc.gwt.wysiwyg.client.defaults.EditorColorPicker;
import com.gc.gwt.wysiwyg.client.defaults.EditorColorSelectListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class BackgroundColorButton
    extends EditorToolbarButton
    implements EditorColorSelectListener, ClickListener {

  private Editor editor;
  private EditorColorPicker bgPicker;
  
  public BackgroundColorButton(Editor editor) {
    super(DefaultConstants.BUTTON_TEXTBACKGROUNDCOLOR);
    this.editor = editor;
    init();
  }
  
  public void colorSelected(String rgb) {
    EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    editor.execCommand(EditorUtils.isIE() ? "backcolor" : "hilitecolor", false, rgb);
    EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
  }
  
  public void onClick(Widget sender) {
    EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    bgPicker.show();
    bgPicker.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
  }

  private void init() {
    this.bgPicker = new EditorColorPicker("Select Background Color");
    this.bgPicker.addSelectListener(this);
    this.addClickListener(this);
  }
  
}
