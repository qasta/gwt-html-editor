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

import com.gc.gwt.wysiwyg.client.EditorUtils;
import com.google.gwt.user.client.Element;

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
