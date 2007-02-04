package com.gc.gwt.wysiwyg.client.defaults.widgets;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbarSelect;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class FontSizesCombo extends EditorToolbarSelect implements ChangeListener {

  Editor editor;

  public FontSizesCombo(Editor editor) {
    this.editor = editor;
    init();
  }

  public void onChange(Widget sender) {
    ListBox subj = ((ListBox) sender);
    String value = subj.getValue(subj.getSelectedIndex());
    subj.setSelectedIndex(0);
    editor.execCommand("FontSize", false, value);
    EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
  }

  private void init() {
    this.addItem("Size", "");
    for (int i = 1; i < 8; i++) {
      this.addItem("Size " + i, "" + i);
    }
    this.addChangeListener(this);
  }
  
}
