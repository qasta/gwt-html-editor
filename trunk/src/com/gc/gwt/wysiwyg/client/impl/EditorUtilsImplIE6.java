package com.gc.gwt.wysiwyg.client.impl;

import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

class EditorUtilsImplIE6 extends EditorUtilsImplCommon {
  public EditorUtilsImplIE6() {
    //Window.alert("EditorUtilsImplIE6");
  }

  public native void saveSelection(Element oIframe) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
   
    var currange = oDoc.selection.createRange();
    oDoc._previous_range = currange;
  }-*/;

  public native void restoreSelection(Element oIframe) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
   
    oDoc._previous_range.select();
    oDoc.focus();
  }-*/;
  
  public void doBackgroundColor(Element oIframe, String color) {
    EditorUtils.execCommand(oIframe, "backcolor", false, color);
  }
}
