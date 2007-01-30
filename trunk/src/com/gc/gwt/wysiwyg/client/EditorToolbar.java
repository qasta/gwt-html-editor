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

import com.gc.gwt.wysiwyg.client.defaults.DefaultConstants;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorToolbar extends Composite {

  /* Fields */
  
  private static final Image SPACER_IMAGE = new Image("spacer.gif");

  private final Panel fullToolbar;

  private final Panel sourceToolbar;

  private final CellPanel topContainer;

  
  /* Constructors */
  
  public EditorToolbar() {
    topContainer = new VerticalPanel();
    topContainer.setStyleName("Editor-Toolbar");
    topContainer.setWidth("100%");

    fullToolbar = new FlowPanel();
  
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
    this.addEditorToolbarWidget(new EditorToolbarButton(null, DefaultConstants.SPACER));
  }
  
  public void addEditorToolbarWidget(Widget widget) {
    fullToolbar.add(widget);
  }
  
  public void addSourceEditorToolbarWidget(Widget widget) {
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
