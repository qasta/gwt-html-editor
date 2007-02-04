package com.gc.gwt.wysiwyg.client.defaults;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.defaults.widgets.*;
import com.google.gwt.user.client.ui.Widget;

public class DefaultEditorToolbarWidgetsFactory {
  
  private Editor editor;
  
  public DefaultEditorToolbarWidgetsFactory(Editor editor) {
    this.editor = editor;
  }
  
  public Widget getRemoveFormattingWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_DELETE, "RemoveFormat");
  }
  
  public Widget getUndoWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNDO, "Undo");
  }
  
  public Widget getRedoWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_REDO, "Redo");
  }
  
  public Widget getBoldWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_BOLD, "Bold");
  }
  
  public Widget getItalicWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_ITALIC, "Italic");
  }
  
  public Widget getUnderlineWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNDERLINE, "Underline");
  }
  
  public Widget getSubscriptWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_SUBSCRIPT, "Subscript");
  }
  
  public Widget getSuperscriptWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_SUPERSCRIPT, "Superscript");
  }
  
  public Widget getJustifyLeftWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNLEFT, "JustifyLeft");
  }
  
  public Widget getJustifyCenterWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNCENTER, "JustifyCenter");
  }
  
  public Widget getJustifyRightWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNRIGHT, "JustifyRight");
  }
  
  public Widget getJustifyFullWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNJUSTIFY, "JustifyFull");
  }

  public Widget getOrderedListWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_OL, "InsertOrderedList");
  }

  public Widget getUnorderedListWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_UL, "InsertUnorderedList");
  }

  public Widget getLinkWidget() {
    return new LinkButton(editor);
  }
  
  public Widget getUnlinkWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNLINK, "UnLink");
  }

  public Widget getInsertImageWidget() {
    return new InsertImageButton(editor);
  }
  
  public Widget getForegroundColorWidget() {
    return new ForegroundColorButton(editor);
  }

  public Widget getBackgroundColorWidget() {
    return new BackgroundColorButton(editor);
  }

  public Widget getFontStyleWidget() {
    return new FontStyleButton(editor);
  }

  public Widget getFontSizeWidget() {
    return new FontSizesCombo(editor);
  }

  public Widget getShowSourceWidget() {
    return new ShowSourceButton(editor);
  }
  
  public Widget getBackToRichTextWidget() {
    return new BackToRichTextButton(editor);
  }
  
}
