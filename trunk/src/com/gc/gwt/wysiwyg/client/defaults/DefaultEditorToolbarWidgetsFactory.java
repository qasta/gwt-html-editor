package com.gc.gwt.wysiwyg.client.defaults;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorColorPicker;
import com.gc.gwt.wysiwyg.client.EditorColorSelectListener;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.EditorToolbarSelect;
import com.gc.gwt.wysiwyg.client.EditorToolbarWidgetsFactory;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class DefaultEditorToolbarWidgetsFactory implements EditorToolbarWidgetsFactory {
  
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
    EditorToolbarButton link = new EditorToolbarButton(editor, DefaultConstants.BUTTON_LINK);
    link.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        new SimpleOneFieldPromptBox(editor, "CreateLink", "Create Link", "Link URL: ", "Create Link").show(editor);
      }
    });
    return link;
  }

  public Widget getUnlinkWidget() {
    return new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNLINK, "UnLink");
  }

  public Widget getInsertImageWidget() {
    EditorToolbarButton image = new EditorToolbarButton(editor, DefaultConstants.BUTTON_IMAGE);
    image.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        new SimpleOneFieldPromptBox(editor, "InsertImage", "Insert Image", "Image URL: ", "Insert Image").show(editor);
      }
    });
    return image;
  }

  public Widget getForegroundColorWidget() {
    final EditorColorPicker fgPicker = new EditorColorPicker("Select Text Color");
    fgPicker.addSelectListener(new EditorColorSelectListener() {
      public void colorSelected(String rgb) {
        EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
        editor.execCommand("ForeColor", false, rgb);
        EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });

    EditorToolbarButton foreColor = new EditorToolbarButton(editor, DefaultConstants.BUTTON_TEXTCOLOR);
    foreColor.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
        fgPicker.show();
        fgPicker.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
      }
    });
    return foreColor;
  }

  public Widget getBackgroundColorWidget() {
    final EditorColorPicker bgPicker = new EditorColorPicker("Select Background Color");
    bgPicker.addSelectListener(new EditorColorSelectListener() {
      public void colorSelected(String rgb) {
        EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
        editor.execCommand(EditorUtils.isIE() ? "backcolor" : "hilitecolor", false, rgb);
        EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
    
    EditorToolbarButton bgColor = new EditorToolbarButton(editor, DefaultConstants.BUTTON_TEXTBACKGROUNDCOLOR);
    bgColor.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
        bgPicker.show();
        bgPicker.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
      }
    });
    
    return bgColor;
  }

  public Widget getFontStyleWidget() {
    EditorToolbarSelect styles = new EditorToolbarSelect();
    styles.addItem("Style", "");
    String[][] formats = EditorUtils.getSupportedFormats();
    for (int i = 0; i < formats.length; i++) {
      styles.addItem(formats[i][0], formats[i][1]);
    }
  
    styles.addChangeListener(new ChangeListener() {
      public void onChange(Widget sender) {
        ListBox subj = ((ListBox) sender);
        String value = subj.getValue(subj.getSelectedIndex());
        subj.setSelectedIndex(0);
        EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
        editor.execCommand("FormatBlock", false, value);
      }
    });
    
    return styles;
  }

  public Widget getFontSizeWidget() {
    EditorToolbarSelect fontSizes = new EditorToolbarSelect();
    fontSizes.addItem("Size", "");
    for (int i = 1; i < 8; i++) {
      fontSizes.addItem("Size " + i, "" + i);
    }
  
    fontSizes.addChangeListener(new ChangeListener() {
      public void onChange(Widget sender) {
        ListBox subj = ((ListBox) sender);
        String value = subj.getValue(subj.getSelectedIndex());
        subj.setSelectedIndex(0);
        editor.execCommand("FontSize", false, value);
        EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
      }
    });
    
    return fontSizes;
  }

  public Widget getShowSourceWidget() {
    EditorToolbarButton source = new EditorToolbarButton(editor, DefaultConstants.BUTTON_SOURCE);
    source.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        editor.getEditorWYSIWYG().toggleView();
      }
    });
    
    return source;
  }

  public Widget getBackToRichTextWidget() {
    EditorToolbarButton source = new EditorToolbarButton(editor, DefaultConstants.BUTTON_BACK_TO_FULL_TOOLBAR);
    source.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        editor.getEditorWYSIWYG().toggleView();
      }
    });
    
    return source;
  }
}
