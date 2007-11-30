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
  private boolean isLoaded;
  
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
    addLoadListener(new FCKEditorLoadListener() {
      public void onLoad() {
        isLoaded = true;
      }
    });
  }
  
  public void setHTML(final String html) {
    if (!isLoaded) {
      addLoadListener(new FCKEditorLoadListener() {
        public void onLoad() {
          _setHTML(html);
        }
      });
    } else {
      _setHTML(html);
    }
  }
  
  private native void _setHTML(String html)/*-{
    var oEditor = $wnd.FCKeditorAPI.GetInstance(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId) ;
    oEditor.SetHTML(html);
  }-*/;
  
  public native String getHTML()/*-{
    var oEditor = $wnd.FCKeditorAPI.GetInstance(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId) ;
    return oEditor.GetHTML();
  }-*/;
  
  private native JavaScriptObject initEditor()/*-{
    var oFCKeditor = new $wnd.FCKeditor(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId);
    var config = this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::config;
    
    oFCKeditor.Width = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getWidth()();
    oFCKeditor.Height = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getHeight()();
    oFCKeditor.ToolbarSet = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getToolbarSet()();
    oFCKeditor.BasePath = "fckeditor/";
    oFCKeditor.ReplaceTextarea();
    return oFCKeditor;
  }-*/;

  public native void addLoadListener(FCKEditorLoadListener listener)/*-{
    $wnd.__FCK_addLoadListener(function() {
      listener.@com.gc.gwt.wysiwyg.client.fck.FCKEditorLoadListener::onLoad()();
    });
  }-*/;;
}

