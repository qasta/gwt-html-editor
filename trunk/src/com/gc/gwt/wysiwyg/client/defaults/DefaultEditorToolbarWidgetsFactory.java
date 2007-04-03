package com.gc.gwt.wysiwyg.client.defaults;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.gc.gwt.wysiwyg.client.defaults.widgets.*;
import com.google.gwt.user.client.ui.Widget;

public class DefaultEditorToolbarWidgetsFactory {
  
  private Editor editor;
  
  public DefaultEditorToolbarWidgetsFactory(Editor editor) {
    this.editor = editor;
  }
  
  public Widget getRemoveFormattingWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_DELETE, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doRemoveFormat(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getUndoWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UNDO, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doUndo(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getRedoWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_REDO, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doRedo(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getBoldWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_BOLD, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doBold(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getItalicWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ITALIC, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doItalic(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getUnderlineWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UNDERLINE, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doUnderline(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getSubscriptWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_SUBSCRIPT, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doSubscript(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getSuperscriptWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_SUPERSCRIPT, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doSuperscript(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getJustifyLeftWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNLEFT, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doJustifyLeft(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getJustifyCenterWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNCENTER, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doJustifyCenter(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getJustifyRightWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNRIGHT, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doJustifyRight(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }
  
  public Widget getJustifyFullWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNJUSTIFY, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doJustifyFull(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }

  public Widget getOrderedListWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_OL, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doInsertOrderedList(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }

  public Widget getUnorderedListWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UL, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doInsertUnorderedList(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
  }

  public Widget getLinkWidget() {
    return new LinkButton(editor);
  }
  
  public Widget getUnlinkWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UNLINK, new EditorCommand() {
      public void exec(String[] params) {
        EditorUtils.doUnLink(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
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
