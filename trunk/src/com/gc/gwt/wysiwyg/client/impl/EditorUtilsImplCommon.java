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

package com.gc.gwt.wysiwyg.client.impl;

import com.gc.gwt.wysiwyg.client.EditorLoadListener;
import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;

public abstract class EditorUtilsImplCommon extends EditorUtilsImpl {
  
  public void enableDesignMode(final Element oIframe, final EditorLoadListener loadListener) {
    System.out.println("EditorUtilsImplCommon.enableDesignMode");
    new Timer() {
      public void run() {
        System.out.println("EditorUtilsImplCommon.enableDesignMode call _enableDesignMode");
        _enableDesignMode(oIframe, loadListener);
        new Timer() {
          public void run() {
            System.out.println("EditorUtilsImplCommon.enableDesignMode call loadListener.onLoad()");
            loadListener.onLoad();
          }
        }.schedule(500);
      }
    }.schedule(500);
  }
  
  private native void _enableDesignMode(Element oIframe, EditorLoadListener loadListener) /*-{
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
    if (!oDoc) {
      $wnd.alert("bug _enableDesignMode");
    }
    if (oDoc.document) {
      oDoc = oDoc.document;
    }
    oDoc.designMode = 'On';
  }-*/;
  
  public void doRemoveFormat(Element oIframe) {
    EditorUtils.execCommand(oIframe, "RemoveFormat", false, null);
  }
  
  public void doUndo(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Undo", false, null);
  }
  
  public void doRedo(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Redo", false, null);
  }
  
  public void doBold(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Bold", false, null);
  }
  
  public void doItalic(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Italic", false, null);
  }
  
  public void doUnderline(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Underline", false, null);
  }
  
  public void doSubscript(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Subscript", false, null);
  }
  
  public void doSuperscript(Element oIframe) {
    EditorUtils.execCommand(oIframe, "Superscript", false, null);
  }
  
  public void doJustifyLeft(Element oIframe) {
    EditorUtils.execCommand(oIframe, "JustifyLeft", false, null);
  }
  
  public void doJustifyRight(Element oIframe) {
    EditorUtils.execCommand(oIframe, "JustifyRight", false, null);
  }
  
  public void doJustifyCenter(Element oIframe) {
    EditorUtils.execCommand(oIframe, "JustifyCenter", false, null);
  }
  
  public void doJustifyFull(Element oIframe) {
    EditorUtils.execCommand(oIframe, "JustifyFull", false, null);
  }
  
  public void doInsertOrderedList(Element oIframe) {
    EditorUtils.execCommand(oIframe, "InsertOrderedList", false, null);
  }
  
  public void doInsertUnorderedList(Element oIframe) {
    EditorUtils.execCommand(oIframe, "InsertUnorderedList", false, null);
  }
  
  public void doUnLink(Element oIframe) {
    EditorUtils.execCommand(oIframe, "UnLink", false, null);
  }
  
  public void doCreateLink(Element oIframe, String url) {
    EditorUtils.restoreSelection(oIframe);
    EditorUtils.execCommand(oIframe, "CreateLink", false, url);
  }
  
  public void doInsertImage(Element oIframe, String url) {
    EditorUtils.restoreSelection(oIframe);
    EditorUtils.execCommand(oIframe, "InsertImage", false, url);
  }
  
  public void doForeColor(Element oIframe, String color) {
    EditorUtils.execCommand(oIframe, "ForeColor", false, color);
  }

  /**
   * overridden by IE impl
   */
  public void doBackgroundColor(Element oIframe, String color) {
    EditorUtils.execCommand(oIframe, "hilitecolor", false, color);
  }
  
  public void doFontSize(Element oIframe, String size) {
    EditorUtils.restoreSelection(oIframe);
    EditorUtils.execCommand(oIframe, "FontSize", false, size);
  }
  
  public void doFontStyle(Element oIframe, String style) {
    EditorUtils.restoreSelection(oIframe);
    EditorUtils.execCommand(oIframe, "FormatBlock", false, style);
  }
  
  public void doFontName(Element oIframe, String name) {
    EditorUtils.restoreSelection(oIframe);
    EditorUtils.execCommand(oIframe, "FontName", false, name);
  }
}
