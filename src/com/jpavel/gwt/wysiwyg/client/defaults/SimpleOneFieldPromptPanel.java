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

package com.jpavel.gwt.wysiwyg.client.defaults;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jpavel.gwt.wysiwyg.client.Editor;
import com.jpavel.gwt.wysiwyg.client.EditorPromptPanelWidget;

public class SimpleOneFieldPromptPanel {
  
  public SimpleOneFieldPromptPanel(Editor editor, String command, String title, String fieldLabel, String buttonLabel) {
    final EditorPromptPanelWidget widget = new EditorPromptPanelWidget();

    VerticalPanel container = new VerticalPanel();
    container.setWidth("300px");
    final TextBox urlTextBox = new TextBox();

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
    b.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        widget.getPrompt().complete(urlTextBox.getText());
      }
    });

    Button c = new Button("Cancel");
    c.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        widget.getPrompt().complete(null);
      }
    });

    hzButtons.add(b);
    hzButtons.add(c);
    hzButtons.setSpacing(4);

    container.add(hzButtons);
    container.setCellHorizontalAlignment(hzButtons, HasAlignment.ALIGN_CENTER);

    widget.setWidget(container);

    // TODO What's the purpose of the next statement?
    // It is the only statement that uses editor. If it's not
    // necessary, we could get rid of it and the 'editor' parameter
    // in the constructor
    new AdvancedPromptPanel(editor, command, title, widget);

    urlTextBox.setFocus(true);
  }
  
}
