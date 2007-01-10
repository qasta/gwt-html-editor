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

package com.jpavel.gwt.wysiwyg.client;

import com.google.gwt.user.client.Element;

/**
 * just utilities
 * 
 * PS: This is how it worked before...
 * 	var frameName = frame.@com.google.gwt.user.client.ui.NamedFrame::getName()();
 *	var oIframe = $wnd.document.getElementsByName(frameName)[0];
 *
 * @author pavel.jbanov
 */
public class EditorUtils {
  
  public static native String getSelection() /*-{
    var txt = '';
    if ($wnd.getSelection) {
      txt = $wnd.getSelection();
    } else if ($wnd.document.getSelection) {
      txt = $wnd.document.getSelection();
    } else if ($wnd.document.selection) {
      txt = $wnd.document.selection.createRange().text;
    } else {
      return;
    }
    return txt;
  }-*/;

  public static native String replaceSelection(String text) /*-{
    if ($wnd.document.selection) { // IE
      var sel = $wnd.document.selection.createRange();
      sel.text = text;
    } else {
      var sel = $wnd.getSelection();
      var range = sel.getRangeAt(0);
      sel.removeAllRanges();
      range.deleteContents();
      var oldContent = $wnd.document.body.innerHTML;
      $wnd.alert(oldContent);
      var inTag = false;	
      var insertPos = 0;
      for (var foo = 0, pos = 0; foo < oldContent.length; foo++) {
        var aChar = oldContent.substr(foo, 1);
        if (aChar == "<") {
          inTag = true;
        }
        if (!inTag) {
          pos++;
          if (pos == range.startOffset) {
            insertPos = foo + 1;
          }
        }
        if (aChar == ">") {
          inTag = false;
        }
      }
      $wnd.alert(insertPos);
      
      $wnd.document.body.innerHTML = oldContent.substr(0, insertPos) + text + oldContent.substr(insertPos, oldContent.length);
    }
  }-*/;

//	public static native void _execCommand(Element oIframe, String command, boolean ui, String value)/*-{
////		$wnd.alert(.apply);
////		@com.jpavel.gwt.wysiwyg.client.EditorUtils::execCommand(Lcom.google.gwt.user.client.Element;Ljava/lang/String;ZLjava/lang/String;).apply(this);
// 
//	    var exec = function(frame1, command1, ui1, value1) {
//	    	@com.jpavel.gwt.wysiwyg.client.EditorUtils::_execCommand(Lcom.google.gwt.user.client.Element;Ljava/lang/String;ZLjava/lang/String;)(frame1, command1, ui1, value1);
//	    };
//	    exec.apply(frame, new Array(frame, command, ui, value));
//	}-*/;

  public static native void execCommand(Element oIframe, String command, boolean ui, String value) /*-{
    oIframe.contentWindow.focus();
    oIframe.contentWindow.document.execCommand(command, ui, value);
  }-*/;

  public static native void oldExecCommand(Element oIframe, String command, boolean ui, String value) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
    oDoc.execCommand(command, ui, value);
  }-*/;


  public static native void doFocus(Element oIframe) /*-{
    oIframe.contentWindow.focus();
  }-*/;

  public static native void oldDoFocus(Element oIframe) /*-{
    if (@com.jpavel.gwt.wysiwyg.client.EditorUtils::isIE()() || 
      @com.jpavel.gwt.wysiwyg.client.EditorUtils::isGecko()() || 
      @com.jpavel.gwt.wysiwyg.client.EditorUtils::isSafari()()) {
      oIframe.contentWindow.focus();
    } else {
      oIframe.focus();
    }
  }-*/;

  public static native void doBlur(Element oIframe)/*-{
    if (@com.jpavel.gwt.wysiwyg.client.EditorUtils::isIE()() || @com.jpavel.gwt.wysiwyg.client.EditorUtils::isGecko()()) {
      oIframe.contentWindow.blur();
    } else {
      oIframe.blur();
    }
  }-*/;

  public static native String prompt(String question)/*-{
    return $wnd.prompt(question, "");
  }-*/;

  public static native void alert(String question) /*-{
    $wnd.alert(question);
  }-*/;

  public static native boolean isIE() /*-{
    var agt = navigator.userAgent.toLowerCase();
    return ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
  }-*/;

  public static native boolean isGecko() /*-{
    if(navigator.userAgent.toLowerCase().indexOf("firefox")!=-1){
      var versionindex=navigator.userAgent.indexOf("Firefox")+8
      if (parseInt(navigator.userAgent.charAt(versionindex))>=1) {
        return true;
      }
    }
    //var agt=navigator.userAgent.toLowerCase();
    //return (agt.indexOf('gecko') != -1);
    return false;
  }-*/;

  public static native boolean isOpera() /*-{
    var agt=navigator.userAgent.toLowerCase();
    return (agt.indexOf("opera") != -1);
  }-*/;

  public static native boolean isSafari() /*-{
    var agt = navigator.vendor;
    if (agt) {
      return (agt.indexOf("Apple") != -1);
    } 
    return false; 
  }-*/;

  public static native void saveSelection(Element oIframe) /*-{
    // Save the selection, works around a problem with IE/Safari where the 
    // selection in the iframe gets lost.
    
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
           
    if (@com.jpavel.gwt.wysiwyg.client.EditorUtils::isIE()()) {
      var currange = oDoc.selection.createRange();
      oDoc._previous_range = currange;
    }
    
    if (@com.jpavel.gwt.wysiwyg.client.EditorUtils::isSafari()()) {
      var oWin = oIframe.contentWindow;
      var sel = oWin.getSelection();
      
      oDoc._previous_range = new Object();
      oDoc._previous_range.baseNode = sel.baseNode;
      oDoc._previous_range.baseOffset = sel.baseOffset;
      oDoc._previous_range.extentNode = sel.extentNode;
      oDoc._previous_range.extentOffset = sel.extentOffset;
    }
  }-*/;

  public static native void restoreSelection(Element oIframe) /*-{
    // re-selects the previous selection.
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
    if (oDoc._previous_range) {
      if (@com.jpavel.gwt.wysiwyg.client.EditorUtils::isIE()()) {
        try {
          oDoc._previous_range.select();
          oDoc.focus();
        } catch (e) {
          alert("Error placing back selection");
        };
      }
      if (@com.jpavel.gwt.wysiwyg.client.EditorUtils::isSafari()()) {
        var oWin = oIframe.contentWindow;
        var sel = oWin.getSelection();
        sel.setBaseAndExtent(oDoc._previous_range.baseNode, oDoc._previous_range.baseOffset,
            oDoc._previous_range.extentNode, oDoc._previous_range.extentOffset);
      }
      oDoc._previous_range = null;
    }
  }-*/;

  public static native int parseInt(String s) /*-{
    return parseInt(s);
  }-*/;

  public static String[][] getSupportedFormats() {
    return new String[][]{
        {"Normal", "<P>"},
        {"Heading 1", "<H1>"},
        {"Heading 2", "<H2>"},
        {"Heading 3", "<H3>"},
        {"Heading 4", "<H4>"},
        {"Heading 5", "<H5>"},
        {"Heading 6", "<H6>"},
        {"Preformatted", "<PRE>"},
        {"Address", "<ADDRESS>"}};		
    //if (isIE()) {
    //  return new String[][]{{"Normal", "Normal"}, {"Heading 1", "Heading 1"}, {"Heading 2", "Heading 2"}, {"Heading 3", "Heading 3"}, {"Heading 4", "Heading 4"}, {"Heading 5", "Heading 5"}, {"Heading 6", "Heading 6"}};		
    //} else {
    //  return new String[][]{{"Normal", "P"}, {"Heading 1", "H1"}, {"Heading 2", "H2"}, {"Heading 3", "H3"}, {"Heading 4", "H4"}, {"Heading 5", "H5"}, {"Heading 6", "H6"}};		
    //}
  }
  
}
