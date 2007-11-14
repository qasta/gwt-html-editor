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
   *
   * @param question prompt text.
   * @return value
   */
  public static native String prompt(String question)/*-{
    return $wnd.prompt(question, "");
  }-*/;

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
        {"Arial", "Arial"},
        {"Times New Roman", "Times New Roman"},
        {"Verdana", "Verdana"},
        {"Helvetica", "Helvetica"},
        {"Tahoma", "Tahoma"},
        {"Courier New", "Courier New"}
      };
  }
}
