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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: javadocs.
 *
 * @author pavel.jbanov
 */
public abstract class EditorPromptPanel extends DialogBox {

  private SimplePanel container;

  /**
   * TODO: javadocs.
   *
   * @param title
   */
  public EditorPromptPanel(String title) {

    container = new SimplePanel();

    container.setStyleName("Editor-DialogBox-Content");

    this.setWidget(container);
    this.setText(title);

    this.setStyleName("Editor-DialogBox");
  }

  /**
   * TODO: javadocs.
   *
   * @return Widget
   */
  public abstract Widget initWidget();

  /**
   * TODO: javadocs.
   *
   * @param editor
   */
  public void show(Editor editor) {
    this.setPopupPosition(editor.getAbsoluteLeft() + 50, editor.getAbsoluteTop() + 50);
    show();
  }

  /**
   * TODO: javadocs.
   */
  public void show() {
    container.setWidget(initWidget());
    super.show();
  }

  /**
   * TODO: javadocs.
   *
   * @param value
   */
  public void submit(String value) {
    this.hide();
    fireSubmitEvent(value);
  }

  private List submitListeners = new ArrayList();

  /**
   * TODO: javadocs.
   *
   * @param listener
   */
  public void addEditorPromptPanelSubmitListener(EditorPromptPanelSubmitListener listener) {
    submitListeners.add(listener);
  }

  /**
   * TODO: javadocs.
   *
   * @param listener
   */
  public void removeEditorPromptPanelSubmitListener(EditorPromptPanelSubmitListener listener) {
    submitListeners.remove(listener);
  }

  /**
   * TODO: javadocs.
   *
   * @param value
   */
  private void fireSubmitEvent(String value) {
    for (Iterator iter = submitListeners.iterator(); iter.hasNext();) {
      ((EditorPromptPanelSubmitListener) iter.next()).onSubmit(value);
    }
  }
}
