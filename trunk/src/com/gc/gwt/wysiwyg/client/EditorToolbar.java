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

import java.util.HashMap;
import java.util.Map;

import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorToolbar extends Composite {

  /* Inner Classes */
  
  private static class EditorToolbarFlowPanel extends FlowPanel {
    public void insert(Widget w, int beforeIndex) {
      super.insert(w, beforeIndex);
      DOM.insertChild(getElement(), w.getElement(), beforeIndex);
    }
  }
  
  /* Fields */
  
  private final EditorToolbarFlowPanel fullToolbar;
  private final Map<String, Widget> widgetMap = new HashMap<String, Widget>();
  private final Map<Widget, String> widgetIDMap = new HashMap<Widget, String>();
  private final FlowPanel sourceToolbar;
  private final CellPanel topContainer;
  private int spacerCounter = 0;
  
  /* Constructors */
  public EditorToolbar() {
    topContainer = new VerticalPanel();
    topContainer.setStyleName("Editor-Toolbar");
    topContainer.setWidth("100%");

    fullToolbar = new EditorToolbarFlowPanel();
  
    sourceToolbar = new FlowPanel();

    topContainer.add(fullToolbar);
    topContainer.add(sourceToolbar);

    fullToolbar.setVisible(true);
    sourceToolbar.setVisible(false);
    
    initWidget(topContainer);
    setWidth("100%");
  }
  

  /* Methods */

  public void addSpacer() {
    this.putWidgetLast(new EditorToolbarButton(DefaultConstants.SPACER), "SPACER_" + (++spacerCounter));
  }
  
  public int getWidgetIndex(Widget widget) {
    return fullToolbar.getWidgetIndex(widget);
  }
  
  public int getWidgetCount() {
    return widgetMap.size();
  }
  
  public String getWidgetID(Widget widget) {
    return (String) widgetIDMap.get(widget);
  }
  
  public Widget getWidget(int index) {
    return fullToolbar.getWidget(index);
  }
  
  public Widget getWidget(String widgetID) {
    return (Widget) widgetMap.get(widgetID);
  }
  
  public void putWidget(Widget widget, int index, String widgetID) {
    if (widgetMap.containsKey(widgetID)) {
      throw new RuntimeException("Cannot put widget with duplicate widget ID '" +
          widgetID + "'. Use replaceWidget(...) method instead.");
    }
    
    fullToolbar.insert(widget, index);
    widgetMap.put(widgetID, widget);
    widgetIDMap.put(widget, widgetID);
  }
  
  public void putWidgetLast(Widget widget, String widgetID) {
    putWidget(widget, getWidgetCount(), widgetID);
  }
  
  public void putWidgetFirst(Widget widget, String widgetID) {
    putWidget(widget, 0, widgetID);
  }
  
  public void replaceWidget(Widget original, Widget widget) {
    replaceWidget(original, widget, getWidgetID(original));
  }
  
  public void replaceWidget(Widget original, Widget widget, String widgetID) {
    Widget idOwner = getWidget(widgetID);
    if (idOwner != null && idOwner != original) {
      throw new RuntimeException("Provided widgetID is owned by a widget" +
            " other that the widget that is being replaced.");
    }
    
    // Remove
    int indexOfTheOriginal = getWidgetIndex(original);
    removeWidget(indexOfTheOriginal);
    
    // Add
    putWidget(widget, indexOfTheOriginal, widgetID);
  }
  
  public void removeWidget(int index) {
    Widget widget = getWidget(index);
    String widgetID = getWidgetID(widget);
    
    fullToolbar.remove(index);
    widgetMap.remove(widgetID);
    widgetIDMap.remove(widget);
  }
  
  public void removeWidget(String widgetID) {
    Widget widget = getWidget(widgetID);
    int index = getWidgetIndex(widget);
    
    fullToolbar.remove(index);
    widgetMap.remove(widgetID);
    widgetIDMap.remove(widget);
  }
  
  public void removeWidget(Widget widget) {
    String widgetID = getWidgetID(widget);
    int index = getWidgetIndex(widget);
    
    fullToolbar.remove(index);
    widgetMap.remove(widgetID);
    widgetIDMap.remove(widget);
  }
  
  public void addSourceToolbarWidget(Widget widget) {
    sourceToolbar.add(widget);
  }

  public void setWidth(String width) {
    topContainer.setWidth(width);
  }

  public String getWidth() {
    return DOM.getStyleAttribute(topContainer.getElement(), "width");
  }

  public void switchToSource() {
    fullToolbar.setVisible(false);
    sourceToolbar.setVisible(true);
  }

  public void switchToFull() {
    fullToolbar.setVisible(true);
    sourceToolbar.setVisible(false);
  }
  
}
