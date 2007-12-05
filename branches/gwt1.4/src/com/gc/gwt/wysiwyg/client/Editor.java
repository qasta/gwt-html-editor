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

/**
 * Rich Text Editor Widget.
 *
 * @author pavel.jbanov
 */
public class Editor extends FCKEditor {

  public Editor(final String width, final String height) {
    super(new FCKEditorConfig() {
      {
        setWidth(width);
        setHeight(height);
      }
    });
  }
  
  public Editor(final String width, final String height, final String toolbarSet) {
    super(new FCKEditorConfig() {
      {
        setWidth(width);
        setHeight(height);
        setToolbarSet(toolbarSet);
      }
    });
  }
}
