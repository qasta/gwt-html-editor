/*
 * Copyright 2006 Pavel Jbanov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gc.gwt.wysiwyg.client.defaults;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.defaults.widgets.BackToRichTextButton;
import com.gc.gwt.wysiwyg.client.defaults.widgets.BackgroundColorButton;
import com.gc.gwt.wysiwyg.client.defaults.widgets.FontSizesCombo;
import com.gc.gwt.wysiwyg.client.defaults.widgets.FontStyleButton;
import com.gc.gwt.wysiwyg.client.defaults.widgets.ForegroundColorButton;
import com.gc.gwt.wysiwyg.client.defaults.widgets.InsertImageButton;
import com.gc.gwt.wysiwyg.client.defaults.widgets.LinkButton;
import com.gc.gwt.wysiwyg.client.defaults.widgets.ShowSourceButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.RichTextArea.Justification;

public class DefaultEditorToolbarWidgetsFactory {
  
  private Editor editor;
  
  public DefaultEditorToolbarWidgetsFactory(Editor editor) {
    this.editor = editor;
  }
  
  public Widget getRemoveFormattingWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_DELETE, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getExtendedFormatter().removeFormat();
      }
    });
  }
//  
//  public Widget getUndoWidget() {
//    return new SimpleCommandButton(DefaultConstants.BUTTON_UNDO, new EditorCommand() {
//      public void exec(String[] params) {
//        editor.getRichTextArea().getExtendedFormatter().
//        EditorUtils.doUndo(editor.getEditorWYSIWYG().getFrame().getElement());
//      }
//    });
//  }
//  
//  public Widget getRedoWidget() {
//    return new SimpleCommandButton(DefaultConstants.BUTTON_REDO, new EditorCommand() {
//      public void exec(String[] params) {
//        EditorUtils.doRedo(editor.getEditorWYSIWYG().getFrame().getElement());
//      }
//    });
//  }
  
  public Widget getBoldWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_BOLD, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().toggleBold();
      }
    });
  }
  
  public Widget getItalicWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ITALIC, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().toggleItalic();
      }
    });
  }
  
  public Widget getUnderlineWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UNDERLINE, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().toggleUnderline();
      }
    });
  }
  
  public Widget getSubscriptWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_SUBSCRIPT, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().toggleSubscript();
      }
    });
  }
  
  public Widget getSuperscriptWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_SUPERSCRIPT, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().toggleSuperscript();
      }
    });
  }
  
  public Widget getJustifyLeftWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNLEFT, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().setJustification(Justification.LEFT);
      }
    });
  }
  
  public Widget getJustifyCenterWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNCENTER, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().setJustification(Justification.CENTER);
      }
    });
  }
  
  public Widget getJustifyRightWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNRIGHT, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getBasicFormatter().setJustification(Justification.RIGHT);
      }
    });
  }
//  
//  public Widget getJustifyFullWidget() {
//    return new SimpleCommandButton(DefaultConstants.BUTTON_ALIGNJUSTIFY, new EditorCommand() {
//      public void exec(String[] params) {
//        editor.getRichTextArea().getExtendedFormatter().
//        EditorUtils.doJustifyFull(editor.getEditorWYSIWYG().getFrame().getElement());
//      }
//    });
//  }

  public Widget getOrderedListWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_OL, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getExtendedFormatter().insertOrderedList();
      }
    });
  }

  public Widget getUnorderedListWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UL, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getExtendedFormatter().insertUnorderedList();
      }
    });
  }

  public Widget getLinkWidget() {
    return new LinkButton(editor);
  }
  
  public Widget getUnlinkWidget() {
    return new SimpleCommandButton(DefaultConstants.BUTTON_UNLINK, new EditorCommand() {
      public void exec(String[] params) {
        editor.getRichTextArea().getExtendedFormatter().removeLink();
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
