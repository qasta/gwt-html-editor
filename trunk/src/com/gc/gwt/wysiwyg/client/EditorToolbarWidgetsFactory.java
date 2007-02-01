package com.gc.gwt.wysiwyg.client;

import com.google.gwt.user.client.ui.Widget;


public interface EditorToolbarWidgetsFactory {
  public Widget getRemoveFormattingWidget();
  
  public Widget getUndoWidget();
  
  public Widget getRedoWidget();
  
  public Widget getBoldWidget();
  
  public Widget getItalicWidget();
  
  public Widget getUnderlineWidget();
  
  public Widget getSubscriptWidget();
  
  public Widget getSuperscriptWidget();
  
  public Widget getJustifyLeftWidget();
  
  public Widget getJustifyCenterWidget();
  
  public Widget getJustifyRightWidget();
  
  public Widget getJustifyFullWidget();

  public Widget getOrderedListWidget();

  public Widget getUnorderedListWidget();

  public Widget getLinkWidget();

  public Widget getUnlinkWidget();

  public Widget getInsertImageWidget();

  public Widget getForegroundColorWidget();

  public Widget getBackgroundColorWidget();

  public Widget getFontStyleWidget();

  public Widget getFontSizeWidget();

  public Widget getShowSourceWidget();

  public Widget getBackToRichTextWidget();
}
