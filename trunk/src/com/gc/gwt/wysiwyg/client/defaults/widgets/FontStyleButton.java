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
    EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
    editor.execCommand("FormatBlock", false, value);
  }
  
  private void init() {
    this.addItem("Style", "");
    String[][] formats = EditorUtils.getSupportedFormats();
    for (int i = 0; i < formats.length; i++) {
      this.addItem(formats[i][0], formats[i][1]);
    }
    this.addChangeListener(this);
  }
  
}
