package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.gc.gwt.wysiwyg.client.defaults.SimpleOneFieldPromptBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class InsertImageButton extends EditorToolbarButton implements ClickListener {
  
  private Editor editor;
  
  public InsertImageButton(Editor editor) {
    super(DefaultConstants.BUTTON_IMAGE);
    this.editor = editor;
    this.addClickListener(this);
  }
  
  public void onClick(Widget sender) {
    new SimpleOneFieldPromptBox(editor, "InsertImage", "Insert Image", "Image URL: ", "Insert Image").show(editor);
  }

}
