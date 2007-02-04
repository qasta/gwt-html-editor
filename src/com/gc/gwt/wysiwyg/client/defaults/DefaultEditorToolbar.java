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

package com.gc.gwt.wysiwyg.client.defaults;

import com.gc.gwt.wysiwyg.client.Editor;
import com.gc.gwt.wysiwyg.client.EditorToolbar;

/**
 * Default editor toolbar implementation. 
 *
 * @author pavel.jbanov
 * @author yegor.jbanov
 */
public class DefaultEditorToolbar extends EditorToolbar {

  /* Fields */
  
  private DefaultEditorToolbarWidgetsFactory toolbarWidgetsFactory;
  
  /* Constructors */
  public DefaultEditorToolbar(Editor edt) {
    super();

    this.setToolbarWidgetsFactory(new DefaultEditorToolbarWidgetsFactory(edt));
    
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getShowSourceWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getRemoveFormattingWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getUndoWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getRedoWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getBoldWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getItalicWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getUnderlineWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getSubscriptWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getSuperscriptWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getJustifyLeftWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getJustifyCenterWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getJustifyRightWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getJustifyFullWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getOrderedListWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getUnorderedListWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getLinkWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getUnlinkWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getInsertImageWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getForegroundColorWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getBackgroundColorWidget());

    this.addSpacer();

    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getFontStyleWidget());
    this.addEditorToolbarWidget(getToolbarWidgetsFactory().getFontSizeWidget());

    this.addSourceEditorToolbarWidget(getToolbarWidgetsFactory().getBackToRichTextWidget());
  }

  protected DefaultEditorToolbarWidgetsFactory getToolbarWidgetsFactory() {
    return toolbarWidgetsFactory;
  }


  protected void setToolbarWidgetsFactory(DefaultEditorToolbarWidgetsFactory toolbarWidgetsFactory) {
    this.toolbarWidgetsFactory = toolbarWidgetsFactory;
  }

}
