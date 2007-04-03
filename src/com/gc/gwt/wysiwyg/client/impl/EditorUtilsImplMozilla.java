package com.gc.gwt.wysiwyg.client.impl;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

class EditorUtilsImplMozilla extends EditorUtilsImplCommon {
  
  public EditorUtilsImplMozilla() {
    //Window.alert("EditorUtilsImplMozilla");
  }
  
  public native void saveSelection(Element oIframe) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
   
    var currange = oIframe.contentWindow.getSelection();
    oDoc._previous_range = currange;
  }-*/;

  public native void restoreSelection(Element oIframe) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
  }-*/;
}
