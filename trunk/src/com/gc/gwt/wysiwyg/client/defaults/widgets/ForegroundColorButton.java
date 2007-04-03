package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.EditorUtils;
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
    EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    EditorUtils.doForeColor(editor.getEditorWYSIWYG().getFrame().getElement(), rgb);
    EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
  }

  public void onClick(Widget sender) {
    EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
    fgPicker.show();
    fgPicker.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
  }

  private void init() {
    this.fgPicker = new EditorColorPicker("Select Text Color");
    this.fgPicker.addSelectListener(this);
    this.addClickListener(this);
  }
  
}
