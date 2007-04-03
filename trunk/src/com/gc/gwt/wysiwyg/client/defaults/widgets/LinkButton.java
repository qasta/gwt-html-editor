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
