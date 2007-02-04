package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ShowSourceButton extends EditorToolbarButton implements ClickListener {
  
  Editor editor;
  
  public ShowSourceButton(Editor editor) {
    super(DefaultConstants.BUTTON_SOURCE);
    this.editor = editor;
    this.addClickListener(this);
  }
  
  public void onClick(Widget sender) {
    editor.getEditorWYSIWYG().toggleView();
  }

}
