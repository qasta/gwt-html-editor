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

package com.gc.gwt.wysiwyg.client;

import com.gc.gwt.wysiwyg.client.impl.EditorUtilsImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 * just utilities
 *
 * PS: This is how it worked before...
 *    var frameName =
 *      frame.@com.google.gwt.user.client.ui.NamedFrame::getName()();
 *    var oIframe = $wnd.document.getElementsByName(frameName)[0];
 *
 * @author pavel.jbanov
 */
public final class EditorUtils {

  /**
   * we don't need a public or default constructor.
   */
  private EditorUtils() {

  }
  
  private static EditorUtilsImpl impl;
  
  static {
    impl = (EditorUtilsImpl) GWT.create(EditorUtilsImpl.class);
  }
  
  public static void doRemoveFormat(Element oIframe) {
    impl.doRemoveFormat(oIframe);
  }

  public static void doUndo(Element oIframe) {
    impl.doUndo(oIframe);
  }

  public static void doRedo(Element oIframe) {
    impl.doRedo(oIframe); 
  }

  public static void doBold(Element oIframe) {
    impl.doBold(oIframe); 
  }

  public static void doItalic(Element oIframe) {
    impl.doItalic(oIframe);
  }

  public static void doUnderline(Element oIframe) {
    impl.doUnderline(oIframe); 
  }

  public static void doSubscript(Element oIframe) {
    impl.doSubscript(oIframe);    
  }

  public static void doSuperscript(Element oIframe) {
    impl.doSuperscript(oIframe);
  }

  public static void doJustifyLeft(Element oIframe) {
    impl.doJustifyLeft(oIframe);
  }

  public static void doJustifyCenter(Element oIframe) {
    impl.doJustifyCenter(oIframe);
  }

  public static void doJustifyRight(Element oIframe) {
    impl.doJustifyRight(oIframe);
  }

  public static void doJustifyFull(Element oIframe) {
    impl.doJustifyFull(oIframe);
  }

  public static void doInsertOrderedList(Element oIframe) {
    impl.doInsertOrderedList(oIframe);
  }

  public static void doInsertUnorderedList(Element oIframe) {
    impl.doInsertUnorderedList(oIframe);
  }

  public static void doUnLink(Element oIframe) {
    impl.doUnLink(oIframe);    
  }

  public static void doCreateLink(Element oIframe, String url) {
    impl.doCreateLink(oIframe, url); 
  }

  public static void doInsertImage(Element oIframe, String url) {
    impl.doInsertImage(oIframe, url); 
  }

  public static void doForeColor(Element oIframe, String color) {
    impl.doForeColor(oIframe, color);
  }

  public static void doBackgroundColor(Element oIframe, String color) {
    impl.doBackgroundColor(oIframe, color);
  }
  
  public static void doFontStyle(Element oIframe, String style) {
    impl.doFontStyle(oIframe, style);
  }
  
  public static void doFontSize(Element oIframe, String size) {
    impl.doFontSize(oIframe, size);
  }

  public static void enableDesignMode(Element oIframe, EditorLoadListener loadListener) {
    impl.enableDesignMode(oIframe, loadListener);
  }
  
  /**
   * Exec Midas command.
   *
   * @param oIframe target IFrame
   * @param command Midas command
   * @param ui show browser native UI
   * @param value command value
   */
  public static native void execCommand(Element oIframe, String command,
      boolean ui, String value) /*-{
    oIframe.contentWindow.document.execCommand(command, ui, value);
  }-*/;

  /**
   * Exec Midas command.
   *
   * @param oIframe target IFrame
   * @param command Midas command
   * @param ui show browser native UI
   * @param value command value
   * @deprecated
   */
  public static native void oldExecCommand(Element oIframe, String command,
      boolean ui, String value) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
    oDoc.execCommand(command, ui, value);
  }-*/;

  /**
   * Focus target IFrame.
   *
   * @param oIframe target IFrame.
   */
  public static native void doFocus(Element oIframe) /*-{
    oIframe.contentWindow.focus();
  }-*/;

  /**
   * Focus target IFrame.
   *
   * @param oIframe target IFrame.
   * @deprecated
   */
  public static native void oldDoFocus(Element oIframe) /*-{
    if (@com.gc.gwt.wysiwyg.client.EditorUtils::isIE()() ||
      @com.gc.gwt.wysiwyg.client.EditorUtils::isGecko()() ||
      @com.gc.gwt.wysiwyg.client.EditorUtils::isSafari()()) {
      oIframe.contentWindow.focus();
    } else {
      oIframe.focus();
    }
  }-*/;

  /**
   * Blur target IFrame.
   *
   * @param oIframe target IFrame.
   */
  public static native void doBlur(Element oIframe)/*-{
    if (@com.gc.gwt.wysiwyg.client.EditorUtils::isIE()() ||
        @com.gc.gwt.wysiwyg.client.EditorUtils::isGecko()()) {
      oIframe.contentWindow.blur();
    } else {
      oIframe.blur();
    }
  }-*/;

  /**
   *
   * @param question prompt text.
   * @return value
   */
  public static native String prompt(String question)/*-{
    return $wnd.prompt(question, "");
  }-*/;

  /**
   * temporaty replacement for Window.alert().
   *
   * @param question text to alert
   * @deprecated
   */
  public static native void alert(String question) /*-{
    $wnd.alert(question);
  }-*/;

  /**
   * is current browser IE.
   *
   * @return true/false
   */
  public static native boolean isIE() /*-{
    var agt = navigator.userAgent.toLowerCase();
    return ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
  }-*/;

  /**
   * is current browser Gecko based (i.e. Firefox, Epiphany, ect.).
   *
   * @return true/false
   */
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

  /**
   * is current browser Opera.
   *
   * @return true/false
   */
  public static native boolean isOpera() /*-{
    var agt=navigator.userAgent.toLowerCase();
    return (agt.indexOf("opera") != -1);
  }-*/;

  /**
   * is current browser Safari.
   *
   * @return true/false
   */
  public static native boolean isSafari() /*-{
    var agt = navigator.vendor;
    if (agt) {
      return (agt.indexOf("Apple") != -1);
    }
    return false;
  }-*/;

  /**
   * Save seection in target IFRame.
   *
   * @param oIframe target IFrame
   */
  public static void saveSelection(Element oIframe) {
    impl.saveSelection(oIframe);
  }

  /**
   * Restore selection in oIframe.
   *
   * @param oIframe target IFrame
   */
  public static void restoreSelection(Element oIframe) {
    impl.restoreSelection(oIframe);
  }

  /**
   * JavaScript parseInt style Integer parser.
   *
   * @param s to-parse string.
   * @return pasrsed integer value
   */
  public static native int parseInt(String s) /*-{
    return parseInt(s);
  }-*/;

  /**
   *
   * @return supported FontStyle values.
   */
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
        {"Address", "<ADDRESS>"}
      };
  }

  /**
   * CRAP.
   *
   * @return selection.
   */
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

  /**
   * CRAP.
   *
   * @param text replacement text
   * @return have no idea!
   */
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

      $wnd.document.body.innerHTML =
        oldContent.substr(0, insertPos) +
        text + oldContent.substr(insertPos, oldContent.length);
    }
  }-*/;
}
