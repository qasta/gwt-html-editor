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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Frame;

public class EditorIframe extends Frame {
  public EditorIframe() {
    this.setStyleName("Editor-IFrame");
    // this.setUrl("about:blank");

    sinkEvents(Event.MOUSEEVENTS);
  }

  private List mouseOverListeners = new ArrayList();

  public void addMouseOverListener(EditorMouseOverListener listener) {
    mouseOverListeners.add(listener);
  }

  public void removeMouseOverListener(EditorMouseOverListener listener) {
    mouseOverListeners.remove(listener);
  }

  public void onBrowserEvent(Event event) {
    if (DOM.eventGetType(event) == Event.ONMOUSEOVER) {
      for (Iterator i = mouseOverListeners.iterator(); i.hasNext();) {
        ((EditorMouseOverListener) i.next()).onMouseOver(this);
      }
    }
  }
}
