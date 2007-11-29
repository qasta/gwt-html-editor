package com.gc.gwt.wysiwyg.client.fck;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;

public class FCKEditor extends Composite {

  private String instanceId;
  private TextArea textarea;
  private JavaScriptObject editorInstance;
  private FCKEditorConfig config;
  
  public FCKEditor(FCKEditorConfig config) {
    this.config = config;
    
    textarea = new TextArea();
    textarea.setWidth(config.getWidth());
    textarea.setHeight(config.getHeight());
    instanceId = "FCKEditor" + System.currentTimeMillis();
    DOM.setElementAttribute(textarea.getElement(), "id", instanceId);
    initWidget(textarea);
    DeferredCommand.addCommand(new Command() {
      public void execute() {
        editorInstance = initEditor();
      }
    });
  }
  
  private native JavaScriptObject initEditor()/*-{
    var oFCKeditor = new $wnd.FCKeditor(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId);
    var config = this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::config;
    oFCKeditor.Width = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getWidth()();
    oFCKeditor.Height = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getHeight()();
    oFCKeditor.ToolbarSet = 'Default';
    oFCKeditor.BasePath = "fckeditor/";
    oFCKeditor.ReplaceTextarea();
    return oFCKeditor;  
  }-*/;
}
