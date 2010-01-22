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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SimpleOneFieldPromptBox extends AdvancedPromptBox {
  
  private String fieldLabel;
  private String buttonLabel;
  
  private TextBox urlTextBox;
  
  public SimpleOneFieldPromptBox(Editor editor, EditorCommand command, String title, String fieldLabel, String buttonLabel) {
    super(editor, command, title);
    this.fieldLabel = fieldLabel;
    this.buttonLabel = buttonLabel;
  }

  public Widget initWidget() {
    VerticalPanel container = new VerticalPanel();
    container.setWidth("300px");
    urlTextBox = new TextBox();

    HorizontalPanel hz = new HorizontalPanel();
    hz.setSpacing(5);
    hz.setWidth("100%");
    Label linkLabel = new Label(fieldLabel);
    linkLabel.setWordWrap(false);
    hz.add(linkLabel);
    hz.setCellWidth(linkLabel, "70px");
    hz.setCellVerticalAlignment(linkLabel, HasAlignment.ALIGN_MIDDLE);
    hz.setCellHorizontalAlignment(linkLabel, HasAlignment.ALIGN_RIGHT);
    hz.add(urlTextBox);
    hz.setCellVerticalAlignment(urlTextBox, HasAlignment.ALIGN_MIDDLE);
    urlTextBox.setWidth("100%");
    container.add(hz);

    HorizontalPanel hzButtons = new HorizontalPanel();
    Button b = new Button(buttonLabel);
    b.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        submit(urlTextBox.getText());
      }
    });

    Button c = new Button("Cancel");
    c.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        submit(null);
      }
    });

    hzButtons.add(b);
    hzButtons.add(c);
    hzButtons.setSpacing(4);

    container.add(hzButtons);
    container.setCellHorizontalAlignment(hzButtons, HasAlignment.ALIGN_CENTER);
    
    return container;
  }
  
  protected void onLoad() {
    super.onLoad();
    urlTextBox.setFocus(true);
  }
}
