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

import com.gc.gwt.wysiwyg.client.fck.FCKEditor;
import com.gc.gwt.wysiwyg.client.fck.FCKEditorConfig;
import com.gc.gwt.wysiwyg.client.fck.FCKEditorLoadListener;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LoadListener;

/**
 * Rich Text Editor Widget.
 *
 * @author pavel.jbanov
 */
public class Editor extends Composite {

  private FCKEditor editor;
  
  public Editor(final String width, final String height) {
    editor = new FCKEditor(new FCKEditorConfig() {
      {
        setWidth(width);
        setHeight(height);
      }
    });
    initWidget(editor);
  }
  
  public Editor(final String width, final String height, final String toolbarSet) {
    editor = new FCKEditor(new FCKEditorConfig() {
      {
        setWidth(width);
        setHeight(height);
        setToolbarSet(toolbarSet);
      }
    });
    initWidget(editor);
  }

  /**
   * Set editor height.
   *
   * @param height new height
   */
  public void setHeight(String height) {
    editor.setHeight(height);
  }

  /**
   * Get editor height.
   *
   * @return editor height
   */
  public String getHeight() {
    return DOM.getStyleAttribute(editor.getElement(), "height");
  }

  /**
   * Attach a LoadListener.
   *
   * @param listener Load Listener to attach
   */
  public void addLoadListener(final LoadListener listener) {
    editor.addLoadListener(new FCKEditorLoadListener() {
      public void onLoad() {
        listener.onLoad(editor);
      }
    });
  }


  /**
   * @return HTML
   */
  public String getHTML() {
    return editor.getHTML();
  }

  /**
   * set editor HTML.
   *
   * @param _html HTML
   */
  public void setHTML(String _html) {
    editor.setHTML(_html);
  }
}
