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
import java.util.List;

import com.gc.gwt.wysiwyg.client.defaults.DefaultEditorToolbar;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LoadListener;
import com.google.gwt.user.client.ui.SourcesLoadEvents;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Rich Text Editor Widget.
 *
 * @author pavel.jbanov
 */
public class Editor extends Composite implements SourcesLoadEvents {

  private EditorToolbar toolbar;

  private EditorWYSIWYG wysiwyg;

  private VerticalPanel container;
  
  // internal status
  private boolean initialized = false;

  // listeners
  private List loadListeners = new ArrayList();

  public Editor() {
    this(null);
  }

  /**
   * TODO: javadocs.
   *
   * @param tlBar
   */
  public Editor(EditorToolbar tlBar) {
    if (tlBar == null) {
      toolbar = new DefaultEditorToolbar(this);
    } else {
      toolbar = tlBar;
    }

    wysiwyg = new EditorWYSIWYG(this);
    wysiwyg.setWidth("100%");

    addLoadListener(new LoadListener() {
      public void onLoad(Widget sender) {
        initialized = true;

        if (tmpHTMLStorage != null) {
          getEditorWYSIWYG().setHTML(tmpHTMLStorage);
          tmpHTMLStorage = null;
        }
      }

      public void onError(Widget sender) {
        // TODO Implement error handling
      }
    });

    container = new VerticalPanel();
    container.setStyleName("Editor");
    container.add(toolbar);
    container.add(wysiwyg);
    initWidget(container);
  }

  /**
   * do not override it!
   */
  protected void onLoad() {
    super.onLoad();
    
    if (!initialized) {
      wysiwyg.initFrame(wysiwyg.getFrame().getElement());

      new Timer() {
        public void run() {
          wysiwyg.enableDesignMode(new EditorLoadListener() {
            public void onLoad() {
              notifyLoadListeners();
            }
          });
        }
      }.schedule(50);
      
    } else if (tmpHTMLStorage != null) {
      getEditorWYSIWYG().setHTML(tmpHTMLStorage);
      tmpHTMLStorage = null;
    }
  }

  /**
   * Set editor width.
   *
   * @param width width
   */
  public void setWidth(String width) {
    container.setWidth(width);
  }

  /**
   * @return editor width
   */
  public String getWidth() {
    return DOM.getStyleAttribute(container.getElement(), "width");
  }

  /**
   * Set editor height.
   *
   * @param height new height
   */
  public void setHeight(String height) {
    container.setHeight(height);
    wysiwyg.setHeight("" + (EditorUtils.parseInt(height)
        - toolbar.getOffsetHeight()) + "px");
  }

  /**
   * Get editor height.
   *
   * @return editor height
   */
  public String getHeight() {
    return DOM.getStyleAttribute(container.getElement(), "height");
  }

  /**
   * @return editor toolbar
   */
  public EditorToolbar getEditorToolbar() {
    return toolbar;
  }

  /**
   * @return editor WYSIWYG widget
   */
  public EditorWYSIWYG getEditorWYSIWYG() {
    return wysiwyg;
  }

  /**
   * Attach a LoadListener.
   *
   * @param listener Load Listener to attach
   */
  public void addLoadListener(LoadListener listener) {
    loadListeners.add(listener);
  }

  /**
   * Remove load listener.
   *
   * @parame listener Load Listener to remove
   */
  public void removeLoadListener(LoadListener listener) {
    loadListeners.remove(listener);
  }

  /**
   * fire load listener event.
   */
  private void notifyLoadListeners() {
    for (int i = 0; i < loadListeners.size(); i++) {
      ((LoadListener) loadListeners.get(i)).onLoad(this);
    }
  }

  /**
   * @return HTML
   */
  public String getHTML() {
    return getEditorWYSIWYG().getHTML();
  }

  private String tmpHTMLStorage = null;

  /**
   * set editor HTML.
   *
   * @param _html HTML
   */
  public void setHTML(String _html) {
    if (isAttached()) {
      getEditorWYSIWYG().setHTML(_html);
    } else {
      tmpHTMLStorage = _html;
    }
  }
}
