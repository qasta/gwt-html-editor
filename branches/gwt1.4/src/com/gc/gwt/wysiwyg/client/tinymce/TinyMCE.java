package com.gc.gwt.wysiwyg.client.tinymce;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LoadListener;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.SourcesLoadEvents;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class TinyMCE extends Composite implements SourcesLoadEvents, SourcesChangeEvents {

  private TextArea textArea;
  private String editorId;
  private boolean isLoaded = false;
  private JavaScriptObject editorInstance;

  private List loadListeners;
  private List changeListeners;

  public TinyMCE() {
      editorId = "tinyMCE-" + System.currentTimeMillis();

      loadListeners = new ArrayList();
      changeListeners = new ArrayList();

      textArea = new TextArea();
      textArea.setWidth("90%");
      DOM.setElementAttribute(textArea.getElement(), "id", editorId);
      initWidget(textArea);
      DeferredCommand.addCommand(new Command() {

          public void execute() {
              initEditor();
          }
      });
  }

  private native void initEditor()/*-{
      var editor = new $wnd.tinymce.Editor(this.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::editorId, {
          theme : "advanced",
          skin : "o2k7",
//          width : "90%",
//          height : 100,
          plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
      
          theme_advanced_buttons1 : "bold,italic,underline,|,bullist,numlist,|,paste,pastetext,pasteword,|,fullscreen,|,code",
          theme_advanced_buttons2 : "",
          theme_advanced_buttons3 : "",
          theme_advanced_toolbar_location : "top",
          theme_advanced_toolbar_align : "left",
          theme_advanced_statusbar_location : "none",
          theme_advanced_resizing : true
                                
      });
    
      this.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::editorInstance = editor;
    
      var self = this;
      editor.onInit.add(function(ed) {
          self.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::isLoaded = true;
          self.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::fireLoadEvent()();
      });
      editor.onKeyUp.add(function(ed, l) {
          self.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::fireChangeEvent()();
      });
    
      editor.render();
  }-*/;

  public native void focus()/*-{
      this.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::editorInstance.focus(false);
  }-*/;

  private void fireLoadEvent() {
      for (int i = 0; i < loadListeners.size(); i++) {
          ((LoadListener) loadListeners.get(i)).onLoad(this);
      }
  }

  private void fireChangeEvent() {
      for (int i = 0; i < changeListeners.size(); i++) {
          ((ChangeListener) changeListeners.get(i)).onChange(this);
      }
  }

  public void setHTML(final String html) {
      if (!isLoaded) {
          addLoadListener(new LoadListener() {
              public void onLoad(Widget sender) {
                  DeferredCommand.addCommand(new Command() {

                      public void execute() {
                          _setHTML(html);
                      }
                  });
              }

              public void onError(Widget sender) {
              }
          });
      } else {
          _setHTML(html);
      }
  }

  private native void _setHTML(String html)/*-{
      this.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::editorInstance.setContent(html);
  }-*/;

  public native String getHTML()/*-{
      return this.@com.gc.gwt.wysiwyg.client.tinymce.TinyMCE::editorInstance.getContent();
  }-*/;

  public void addLoadListener(LoadListener listener) {
      loadListeners.add(listener);
  }

  public void removeLoadListener(LoadListener listener) {
      loadListeners.remove(listener);
  }

  public void addChangeListener(ChangeListener listener) {
      changeListeners.add(listener);
  }

  public void removeChangeListener(ChangeListener listener) {
      changeListeners.remove(listener);
  }
}
