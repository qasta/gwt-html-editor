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
import com.gc.gwt.wysiwyg.client.EditorColorPicker;
import com.gc.gwt.wysiwyg.client.EditorColorSelectListener;
import com.gc.gwt.wysiwyg.client.EditorToolbar;
import com.gc.gwt.wysiwyg.client.EditorToolbarButton;
import com.gc.gwt.wysiwyg.client.EditorToolbarSelect;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Default editor toolbar implementation. 
 *
 * @author pavel.jbanov
 */
public class DefaultEditorToolbar extends EditorToolbar {

  /* Fields */
  private Editor editor;

  /* Constructors */
  public DefaultEditorToolbar(Editor edt) {
    super();

    this.editor = edt;

    EditorToolbarButton removeFormat = new SimpleCommandButton(editor, DefaultConstants.BUTTON_DELETE, "RemoveFormat");

    EditorToolbarButton undo = new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNDO, "Undo");
    EditorToolbarButton redo = new SimpleCommandButton(editor, DefaultConstants.BUTTON_REDO, "Redo");

    EditorToolbarButton bold = new SimpleCommandButton(editor, DefaultConstants.BUTTON_BOLD, "Bold");
    EditorToolbarButton italic = new SimpleCommandButton(editor, DefaultConstants.BUTTON_ITALIC, "Italic");
    EditorToolbarButton underlined = new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNDERLINE, "Underline");

    EditorToolbarButton subscript = new SimpleCommandButton(editor, DefaultConstants.BUTTON_SUBSCRIPT, "Subscript");
    EditorToolbarButton superscript = new SimpleCommandButton(editor, DefaultConstants.BUTTON_SUPERSCRIPT, "Superscript");

    EditorToolbarButton justifyLeft = new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNLEFT, "JustifyLeft");
    EditorToolbarButton justifyCenter = new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNCENTER, "JustifyCenter");
    EditorToolbarButton justifyRight = new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNRIGHT, "JustifyRight");
    EditorToolbarButton justifyJustify = new SimpleCommandButton(editor, DefaultConstants.BUTTON_ALIGNJUSTIFY, "JustifyFull");

    EditorToolbarButton ol = new SimpleCommandButton(editor, DefaultConstants.BUTTON_OL, "InsertOrderedList");
    EditorToolbarButton ul = new SimpleCommandButton(editor, DefaultConstants.BUTTON_UL, "InsertUnorderedList");

    EditorToolbarButton link = new EditorToolbarButton(editor, DefaultConstants.BUTTON_LINK);
    link.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        new SimpleOneFieldPromptBox(DefaultEditorToolbar.this.editor, "CreateLink", "Create Link", "Link URL: ", "Create Link").show(editor);
      }
    });

    EditorToolbarButton unlink = new SimpleCommandButton(editor, DefaultConstants.BUTTON_UNLINK, "UnLink");
    EditorToolbarButton image = new EditorToolbarButton(editor, DefaultConstants.BUTTON_IMAGE);
    image.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        new SimpleOneFieldPromptBox(DefaultEditorToolbar.this.editor, "InsertImage", "Insert Image", "Image URL: ", "Insert Image").show(editor);
      }
    });

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


    EditorToolbarButton source = new EditorToolbarButton(editor, DefaultConstants.BUTTON_SOURCE);
    source.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        editor.getEditorWYSIWYG().toggleView();
      }
    });
    this.addEditorToolbarWidget(source);

    this.addSpacer();

    this.addEditorToolbarWidget(removeFormat);

    this.addSpacer();

    this.addEditorToolbarWidget(undo);
    this.addEditorToolbarWidget(redo);

    this.addSpacer();

    this.addEditorToolbarWidget(bold);
    this.addEditorToolbarWidget(italic);
    this.addEditorToolbarWidget(underlined);

    this.addSpacer();

    this.addEditorToolbarWidget(subscript);
    this.addEditorToolbarWidget(superscript);

    this.addSpacer();

    this.addEditorToolbarWidget(justifyLeft);
    this.addEditorToolbarWidget(justifyCenter);
    this.addEditorToolbarWidget(justifyRight);
    this.addEditorToolbarWidget(justifyJustify);

    this.addSpacer();

    this.addEditorToolbarWidget(ol);
    this.addEditorToolbarWidget(ul);

    this.addSpacer();

    this.addEditorToolbarWidget(link);
    this.addEditorToolbarWidget(unlink);
    this.addEditorToolbarWidget(image);

    this.addSpacer();

    this.addEditorToolbarWidget(foreColor);
    this.addEditorToolbarWidget(bgColor);

    this.addSpacer();

    this.addEditorToolbarWidget(styles);
    this.addEditorToolbarWidget(fontSizes);

    EditorToolbarButton source2 = new EditorToolbarButton(editor, DefaultConstants.BUTTON_BACK_TO_FULL_TOOLBAR);
    source2.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        editor.getEditorWYSIWYG().toggleView();
      }
    });

    this.addSourceEditorToolbarWidget(source2);
  }
}
