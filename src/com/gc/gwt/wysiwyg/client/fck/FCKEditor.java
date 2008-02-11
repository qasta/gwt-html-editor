package com.gc.gwt.wysiwyg.client.fck;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.LoadListener;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.SourcesLoadEvents;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class FCKEditor extends Composite implements SourcesLoadEvents, SourcesFocusEvents {

  private static int instanceCounter = 0;
  
  private String instanceId;
  private TextArea textarea;
  private JavaScriptObject editorInstance;
  private FCKEditorConfig config;
  private boolean isLoaded;
  
  private List focusListeners = new ArrayList();
  private List loadListeners = new ArrayList();
  
  public FCKEditor() {
    this(new FCKEditorConfig());
  }
  
  public FCKEditor(FCKEditorConfig config) {
    this.config = config;
    
    textarea = new TextArea();
    if (config != null) {
      textarea.setWidth(config.getWidth());
      textarea.setHeight(config.getHeight());
    }
    
    instanceCounter++;
    instanceId = "FCKEditor" + System.currentTimeMillis() + "-" + instanceCounter;
    
    initLoadListener(this);
    initFocusListener(this);

    DOM.setElementAttribute(textarea.getElement(), "id", instanceId);
    initWidget(textarea);
    
    new Timer() {
      public void run() {
        editorInstance = initEditor();
      }
    }.schedule(500);
    
    addLoadListener(new LoadListener() {
      public void onLoad(Widget sender) {
        isLoaded = true;
      }
      
      public void onError(Widget sender) {}
    });
  }

  public void setWidth(String width) {
    textarea.setWidth(width);
  }
  
  public void setHeight(String height) {
    textarea.setHeight(height);
  }
  
  public void setHTML(final String html) {
    if (!isLoaded) {
      addLoadListener(new LoadListener() {
        
        public void onLoad(Widget sender) {
          new Timer() {
            public void run() {
              _setHTML(html);
            }
          }.schedule(100);
        }
        
        public void onError(Widget sender) {}
      });
    } else {
      _setHTML(html);
    }
  }
  
  private native void _setHTML(String html)/*-{
    var oEditor = $wnd.FCKeditorAPI.GetInstance(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId);
    
    oEditor.SetHTML(html);
  }-*/;
  
  public native String getHTML()/*-{
    var oEditor = $wnd.FCKeditorAPI.GetInstance(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId) ;
    return oEditor.GetHTML();
  }-*/;
  
  public void setFocus(boolean focus) {
    if (focus) {
      new Timer() {
        public void run() {
          Focus();
        }
      }.schedule(100);
    }
  }
  
  private native void Focus()/*-{
    var oEditor = $wnd.FCKeditorAPI.GetInstance(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId) ;
    oEditor.Focus();
  }-*/;
  
  private native JavaScriptObject initEditor()/*-{
    var oFCKeditor = new $wnd.FCKeditor(this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId);
    var config = this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::config;
    
    oFCKeditor.Name = this.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId;
    oFCKeditor.Width = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getWidth()();
    oFCKeditor.Height = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getHeight()();
    oFCKeditor.ToolbarSet = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getToolbarSet()();
    oFCKeditor.BasePath = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getBasePath()();
    
    oFCKeditor.Config['CustomConfigurationsPath'] = config.@com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig::getCustomConfigurationsPath()();
    
    oFCKeditor.ReplaceTextarea();
    return oFCKeditor;
  }-*/;

  
  private native void initLoadListener(FCKEditor editor)/*-{
    $wnd.__FCK_addLoadListener(function(editorInstance) {
      if (editorInstance.Name == editor.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId) {
        editor.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::fireLoadListeners()();
      }
    });
  }-*/;
  
  private native void initFocusListener(FCKEditor editor)/*-{
    $wnd.__FCK_addFocusListener({
      Name: editor.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId,
      Callback: function() {
        editor.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::fireFocusLisneters_onFocus()();
      }
    });
    
    $wnd.__FCK_addBlurListener({
      Name: editor.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::instanceId,
      Callback: function() {
        editor.@com.gc.gwt.wysiwyg.client.fck.FCKEditor::fireFocusLisneters_onLostFocus()();
      }
    });
    
  }-*/;
  
  private void fireFocusLisneters_onFocus() {
    for (int i = 0; i < focusListeners.size(); i++) {
      ((FocusListener) focusListeners.get(i)).onFocus(this);
    }
  }
  
  private void fireFocusLisneters_onLostFocus() {
    for (int i = 0; i < focusListeners.size(); i++) {
      ((FocusListener) focusListeners.get(i)).onLostFocus(this);
    }
  }
  
  private void fireLoadListeners() {
    for (int i = 0; i < loadListeners.size(); i++) {
      ((LoadListener) loadListeners.get(i)).onLoad(this);
    }
  }
  
  public void addFocusListener(FocusListener listener) {
    focusListeners.add(listener);
  }
  
  public void removeFocusListener(FocusListener listener) {
    focusListeners.remove(listener);
  }

  public void addLoadListener(LoadListener listener) {
    loadListeners.add(listener);
  }
  
  public void removeLoadListener(LoadListener listener) {
    loadListeners.remove(listener);
  }
}

