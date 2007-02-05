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

  /* Constructors */
  public DefaultEditorToolbar(Editor edt) {
    super();

    DefaultEditorToolbarWidgetsFactory toolbarWidgetsFactory = new DefaultEditorToolbarWidgetsFactory(edt);
    
    this.putWidgetLast(toolbarWidgetsFactory.getShowSourceWidget(), DefaultConstants.BUTTON_SOURCE);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getRemoveFormattingWidget(), DefaultConstants.BUTTON_DELETE);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getUndoWidget(), DefaultConstants.BUTTON_UNDO);
    this.putWidgetLast(toolbarWidgetsFactory.getRedoWidget(), DefaultConstants.BUTTON_REDO);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getBoldWidget(), DefaultConstants.BUTTON_BOLD);
    this.putWidgetLast(toolbarWidgetsFactory.getItalicWidget(), DefaultConstants.BUTTON_ITALIC);
    this.putWidgetLast(toolbarWidgetsFactory.getUnderlineWidget(), DefaultConstants.BUTTON_UNDERLINE);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getSubscriptWidget(), DefaultConstants.BUTTON_SUBSCRIPT);
    this.putWidgetLast(toolbarWidgetsFactory.getSuperscriptWidget(), DefaultConstants.BUTTON_SUPERSCRIPT);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getJustifyLeftWidget(), DefaultConstants.BUTTON_ALIGNLEFT);
    this.putWidgetLast(toolbarWidgetsFactory.getJustifyCenterWidget(), DefaultConstants.BUTTON_ALIGNCENTER);
    this.putWidgetLast(toolbarWidgetsFactory.getJustifyRightWidget(), DefaultConstants.BUTTON_ALIGNRIGHT);
    this.putWidgetLast(toolbarWidgetsFactory.getJustifyFullWidget(), DefaultConstants.BUTTON_ALIGNJUSTIFY);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getOrderedListWidget(), DefaultConstants.BUTTON_OL);
    this.putWidgetLast(toolbarWidgetsFactory.getUnorderedListWidget(), DefaultConstants.BUTTON_UL);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getLinkWidget(), DefaultConstants.BUTTON_LINK);
    this.putWidgetLast(toolbarWidgetsFactory.getUnlinkWidget(), DefaultConstants.BUTTON_UNLINK);
    this.putWidgetLast(toolbarWidgetsFactory.getInsertImageWidget(), DefaultConstants.BUTTON_IMAGE);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getForegroundColorWidget(), DefaultConstants.BUTTON_TEXTCOLOR);
    this.putWidgetLast(toolbarWidgetsFactory.getBackgroundColorWidget(), DefaultConstants.BUTTON_TEXTBACKGROUNDCOLOR);

    this.addSpacer();

    this.putWidgetLast(toolbarWidgetsFactory.getFontStyleWidget(), DefaultConstants.BUTTON_FONT_STYLE);
    this.putWidgetLast(toolbarWidgetsFactory.getFontSizeWidget(), DefaultConstants.BUTTON_FONT_SIZE);

    this.addSourceToolbarWidget(toolbarWidgetsFactory.getBackToRichTextWidget());
  }

}
