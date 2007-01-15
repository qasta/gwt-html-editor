package com.jpavel.gwt.wysiwyg.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DefaultEditorToolbar extends EditorToolbar {
  
  /* Fields */
  
  private Editor editor;

  /* Nested Classes */
  
  private class SimpleCommandButton extends EditorToolbarButton {
    public SimpleCommandButton(String buttonId, final String command) {
      this(buttonId, command, false, null);
    }

    public SimpleCommandButton(String buttonId, final String command, final boolean ui, String value) {
      super(buttonId);

      this.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          // EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
          EditorUtils.execCommand(editor.getEditorWYSIWYG().getFrame().getElement(), command, ui, null);
        }
      });
    }
  }

  private class SimpleOneFieldPromptPanel {
    public SimpleOneFieldPromptPanel(String command, String title, String fieldLabel, String buttonLabel) {
      final EditorPromptPanelWidget widget = new EditorPromptPanelWidget();

      VerticalPanel container = new VerticalPanel();
      container.setWidth("300px");
      final TextBox urlTextBox = new TextBox();

      HorizontalPanel hz = new HorizontalPanel();
      hz.setSpacing(5);
      hz.setWidth("100%");
      Label linkLabel = new Label(fieldLabel);
      linkLabel.setWordWrap(false);
      hz.add(linkLabel);
      hz.setCellWidth(linkLabel, "70px");
      hz.setCellVerticalAlignment(linkLabel, HasAlignment.ALIGN_MIDDLE);
      hz.setCellHorizontalAlignment(linkLabel, HasAlignment.ALIGN_RIGHT);
      hz.add(urlTextBox);
      hz.setCellVerticalAlignment(urlTextBox, HasAlignment.ALIGN_MIDDLE);
      urlTextBox.setWidth("100%");
      container.add(hz);

      HorizontalPanel hzButtons = new HorizontalPanel();
      Button b = new Button(buttonLabel);
      b.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          widget.getPrompt().complete(urlTextBox.getText());
        }
      });

      Button c = new Button("Cancel");
      c.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          widget.getPrompt().complete(null);
        }
      });

      hzButtons.add(b);
      hzButtons.add(c);
      hzButtons.setSpacing(4);

      container.add(hzButtons);
      container.setCellHorizontalAlignment(hzButtons, HasAlignment.ALIGN_CENTER);

      widget.setWidget(container);

      new AdvancedPromptPanel(command, title, widget);

      urlTextBox.setFocus(true);
    }
  }

  private class AdvancedPromptPanel extends EditorPromptPanel {
    public AdvancedPromptPanel(final String command, String title, EditorPromptPanelWidget widget) {
      super(title, widget);

      this.addPopupListener(new PopupListener() {
        public void onPopupClosed(final PopupPanel sender, boolean autoClosed) {
          String value = ((EditorPromptPanel) sender).getValue();
          EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame().getElement());
          if (value != null) {
            editor.execCommand(command, false, value);
            EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame().getElement());
          }
        }
      });

      EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame().getElement());
      super.show(editor);
    }
  }

  /* Constructors */
  
  public DefaultEditorToolbar(Editor edt) {
    super();
    
    this.editor = edt;

    EditorToolbarButton removeFormat = new SimpleCommandButton(EditorToolbarButton.BUTTON_DELETE, "RemoveFormat");

    EditorToolbarButton undo = new SimpleCommandButton(EditorToolbarButton.BUTTON_UNDO, "Undo");
    EditorToolbarButton redo = new SimpleCommandButton(EditorToolbarButton.BUTTON_REDO, "Redo");

    EditorToolbarButton bold = new SimpleCommandButton(EditorToolbarButton.BUTTON_BOLD, "Bold");
    EditorToolbarButton italic = new SimpleCommandButton(EditorToolbarButton.BUTTON_ITALIC, "Italic");
    EditorToolbarButton underlined = new SimpleCommandButton(EditorToolbarButton.BUTTON_UNDERLINE, "Underline");

    EditorToolbarButton subscript = new SimpleCommandButton(EditorToolbarButton.BUTTON_SUBSCRIPT, "Subscript");
    EditorToolbarButton superscript = new SimpleCommandButton(EditorToolbarButton.BUTTON_SUPERSCRIPT, "Superscript");

    EditorToolbarButton justifyLeft = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNLEFT, "JustifyLeft");
    EditorToolbarButton justifyCenter = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNCENTER, "JustifyCenter");
    EditorToolbarButton justifyRight = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNRIGHT, "JustifyRight");
    EditorToolbarButton justifyJustify = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNJUSTIFY, "JustifyFull");

    EditorToolbarButton ol = new SimpleCommandButton(EditorToolbarButton.BUTTON_OL, "InsertOrderedList");
    EditorToolbarButton ul = new SimpleCommandButton(EditorToolbarButton.BUTTON_UL, "InsertUnorderedList");

    EditorToolbarButton link = new EditorToolbarButton(EditorToolbarButton.BUTTON_LINK);
    link.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        new SimpleOneFieldPromptPanel("CreateLink", "Create Link", "Link URL: ", "Create Link");
      }
    });
    
    EditorToolbarButton unlink = new SimpleCommandButton(EditorToolbarButton.BUTTON_UNLINK, "UnLink");
    EditorToolbarButton image = new EditorToolbarButton(EditorToolbarButton.BUTTON_IMAGE);
    image.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        new SimpleOneFieldPromptPanel("InsertImage", "Insert Image", "Image URL: ", "Insert Image");
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

    EditorToolbarButton foreColor = new EditorToolbarButton(EditorToolbarButton.BUTTON_TEXTCOLOR);
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
    EditorToolbarButton bgColor = new EditorToolbarButton(EditorToolbarButton.BUTTON_TEXTBACKGROUNDCOLOR);
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


    EditorToolbarButton source = new EditorToolbarButton(EditorToolbarButton.BUTTON_SOURCE);
    source.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        editor.getEditorWYSIWYG().toggleView();
      }
    });
    this.addEditorToolbarWidget(source);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(removeFormat);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(undo);
    this.addEditorToolbarWidget(redo);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(bold);
    this.addEditorToolbarWidget(italic);
    this.addEditorToolbarWidget(underlined);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(subscript);
    this.addEditorToolbarWidget(superscript);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(justifyLeft);
    this.addEditorToolbarWidget(justifyCenter);
    this.addEditorToolbarWidget(justifyRight);
    this.addEditorToolbarWidget(justifyJustify);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(ol);
    this.addEditorToolbarWidget(ul);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(link);
    this.addEditorToolbarWidget(unlink);
    this.addEditorToolbarWidget(image);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(foreColor);
    this.addEditorToolbarWidget(bgColor);

    this.addEditorToolbarWidget(EditorToolbarButton.getSpacer());

    this.addEditorToolbarWidget(styles);
    this.addEditorToolbarWidget(fontSizes);

    EditorToolbarButton source2 = new EditorToolbarButton(EditorToolbarButton.BUTTON_BACK_TO_FULL_TOOLBAR);
    source2.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        editor.getEditorWYSIWYG().toggleView();
      }
    });

    this.addSourceEditorToolbarWidget(source2);
    
  }
  
}
