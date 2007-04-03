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
import com.google.gwt.user.client.Element;

public abstract class EditorUtilsImpl {
  public abstract void saveSelection(Element oIframe);

  public abstract void restoreSelection(Element oIframe);
  
  public abstract void enableDesignMode(Element oIframe, EditorLoadListener loadListener);
  
  // Editing stuff

  public abstract void doRemoveFormat(Element oIframe);

  public abstract void doUndo(Element oIframe);

  public abstract void doRedo(Element oIframe);

  public abstract void doBold(Element oIframe);

  public abstract void doItalic(Element oIframe);

  public abstract void doUnderline(Element oIframe);

  public abstract void doSubscript(Element oIframe);

  public abstract void doSuperscript(Element oIframe);

  public abstract void doJustifyLeft(Element oIframe);

  public abstract void doJustifyCenter(Element oIframe);

  public abstract void doJustifyRight(Element oIframe);

  public abstract void doJustifyFull(Element oIframe);

  public abstract void doInsertOrderedList(Element oIframe);

  public abstract void doInsertUnorderedList(Element oIframe);

  public abstract void doUnLink(Element oIframe);

  public abstract void doCreateLink(Element oIframe, String url);

  public abstract void doInsertImage(Element oIframe, String url);

  public abstract void doForeColor(Element oIframe, String color);

  public abstract void doBackgroundColor(Element oIframe, String color);
  
  public abstract void doFontStyle(Element oIframe, String style);
  
  public abstract void doFontSize(Element oIframe, String size);
}