package com.gc.gwt.wysiwyg.client.fck;

import java.util.ArrayList;
import java.util.List;

public class FCKEditorToolbar {
  
  public static final String SOURCE = "Source";
  public static final String DOCPROPS = "DocProps";
  public static final String SAVE = "Save";
  public static final String NEWPAGE = "NewPage";
  public static final String PREVIEW = "Preview";
  public static final String TEMPLATES = "Templates";
  public static final String CUT = "Cut";
  public static final String COPY = "Copy";
  public static final String PASTE = "Paste";
  public static final String PASTETEXT = "PasteText";
  public static final String PASTEWORD = "PasteWord";
  public static final String PRINT = "Print";
  public static final String SPELLCHECK = "SpellCheck";
  public static final String UNDO = "Undo";
  public static final String REDO = "Redo";
  public static final String FIND = "Find";
  public static final String REPLACE = "Replace";
  public static final String SELECTALL = "SelectAll";
  public static final String REMOVEFORMAT = "RemoveFormat";
  public static final String FORM = "Form";
  public static final String CHECKBOX = "Checkbox";
  public static final String RADIO = "Radio";
  public static final String TEXTFIELD = "TextField";
  public static final String TEXTAREA = "Textarea";
  public static final String SELECT = "Select";
  public static final String BUTTON = "Button";
  public static final String IMAGEBUTTON = "ImageButton";
  public static final String HIDDENFIELD = "HiddenField";
  public static final String BOLD = "Bold";
  public static final String ITALIC = "Italic";
  public static final String UNDERLINE = "Underline";
  public static final String STRIKETHROUGH = "StrikeThrough";
  public static final String SUBSCRIPT = "Subscript";
  public static final String SUPERSCRIPT = "Superscript";
  public static final String ORDEREDLIST = "OrderedList";
  public static final String UNORDEREDLIST = "UnorderedList";
  public static final String OUTDENT = "Outdent";
  public static final String INDENT = "Indent";
  public static final String JUSTIFYLEFT = "JustifyLeft";
  public static final String JUSTIFYCENTER = "JustifyCenter";
  public static final String JUSTIFYRIGHT = "JustifyRight";
  public static final String JUSTIFYFULL = "JustifyFull";
  public static final String LINK = "Link";
  public static final String UNLINK = "Unlink";
  public static final String ANCHOR = "Anchor";
  public static final String IMAGE = "Image";
  public static final String FLASH = "Flash";
  public static final String TABLE = "Table";
  public static final String RULE = "Rule";
  public static final String SMILEY = "Smiley";
  public static final String SPECIALCHAR = "SpecialChar";
  public static final String PAGEBREAK = "PageBreak";
  public static final String STYLE = "Style";
  public static final String FONTFORMAT = "FontFormat";
  public static final String FONTNAME = "FontName";
  public static final String FONTSIZE = "FontSize";
  public static final String TEXTCOLOR = "TextColor";
  public static final String BGCOLOR = "BGColor";
  public static final String FITWINDOW = "FitWindow";
  public static final String ABOUT = "About";
  
  private String name;
  private List toolbar = new ArrayList();
  private List currentBar = null;
  
  public FCKEditorToolbar() {
    this("Toolbar" + System.currentTimeMillis());
  }
  
  public FCKEditorToolbar(String name) {
    this.name = name;
  }
  
  public void addButton(String name) {
    if (currentBar == null) {
      currentBar = new ArrayList();
      toolbar.add(currentBar);
    }
    currentBar.add(name);
  }
  
  public void addSeparator() {
    addButton("-");
  }
  
  public void addLineBreak() {
    toolbar.add("/");
    currentBar = null;
  }
  
  public void startNewBar() {
    currentBar = null;
  }

  public List getToolbar() {
    return toolbar;
  }

  public String getName() {
    return name;
  }
}
