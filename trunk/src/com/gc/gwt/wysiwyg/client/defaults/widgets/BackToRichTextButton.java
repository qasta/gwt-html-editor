package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class BackToRichTextButton extends EditorToolbarButton implements ClickListener {

  Editor editor;
  
  public BackToRichTextButton(Editor editor) {
    super(DefaultConstants.BUTTON_BACK_TO_FULL_TOOLBAR);
    this.editor = editor;
    this.addClickListener(this);
  }
  
  public void onClick(Widget sender) {
    editor.getEditorWYSIWYG().toggleView();
  }

}
