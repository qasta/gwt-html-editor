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

package com.gc.gwt.ext.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Always visible panel.
 *
 * @author pavel.jbanov
 */
public class AlwaysVisiblePanel extends SimplePanel {
  
  public final static int DOCK_DEFAULT = 0;
  public final static int DOCK_TOP_LEFT = 1;
  public final static int DOCK_TOP_CENTER = 2;
  public final static int DOCK_TOP_RIGHT = 3;
  public final static int DOCK_MIDDLE_LEFT = 4;
  public final static int DOCK_MIDDLE_CENTER = 5;
  public final static int DOCK_MIDDLE_RIGHT = 6;
  public final static int DOCK_BOTTOM_LEFT = 7;
  public final static int DOCK_BOTTOM_CENTER = 8;
  public final static int DOCK_BOTTOM_RIGHT = 9;
  
  private int docking = DOCK_DEFAULT;
  
  /**
   * Create new AlwaysVisiblePanel with default docking -- inline. Ex:
   *   awp = new AlwaysVisiblePanel();
   */
  public AlwaysVisiblePanel() {
    DOM.addEventPreview(new EventPreview() {
      public boolean onEventPreview(Event event) {
        adjustPosition();
        return true;
      }
    });
    sinkEvents(Event.ONSCROLL);
  }
  /**
   * Create new AlwaysVisiblePanel. Ex:
   *   awp = new AlwaysVisiblePanel(AlwaysVisiblePanel.DOCK_BOTTOM_RIGHT);
   *
   * @param docking docking position
   */
  public AlwaysVisiblePanel(int docking) {
    this();
    setDocking(docking);
  }
  
  public void onBrowserEvent(Event event) {
    if (DOM.eventGetType(event) == Event.ONSCROLL) {
      adjustPosition();
    }
  }
  
  protected void onLoad() {
    super.onLoad();
    
    adjustPosition();
    
    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        adjustPosition();
      }
    });
  }
  
  /**
   * Set panel docking.
   *
   * @param docking
   */
  public void setDocking(int docking) {
    this.docking = docking;
    
    if (isAttached()) {
      adjustPosition();
    }
  }
  
  /**
   * Set panel position on the screen.  
   */
  private void adjustPosition() {
    if (docking == DOCK_DEFAULT) {
      DOM.setStyleAttribute(getElement(), "position", "");
    } else {
      DOM.setStyleAttribute(getElement(), "position", "absolute");
    }

    if (docking == DOCK_TOP_LEFT) {
      setOffsetLeft(0);
      setOffsetTop(0);
    } else if (docking == DOCK_TOP_CENTER) {
      setOffsetLeft(Window.getClientWidth()/2 + getScrollXOffset() - getOffsetWidth()/2);
      setOffsetTop(0);
    } else if (docking == DOCK_TOP_RIGHT) {
      setOffsetLeft(Window.getClientWidth() + getScrollXOffset() - getOffsetWidth());
      setOffsetTop(0);
    } else if (docking == DOCK_MIDDLE_LEFT) {
      setOffsetLeft(0);
      setOffsetTop(Window.getClientHeight()/2 + getScrollYOffset() - getOffsetHeight());
    } else if (docking == DOCK_MIDDLE_CENTER) {
      setOffsetLeft(Window.getClientWidth()/2 + getScrollXOffset() - getOffsetWidth()/2);
      setOffsetTop(Window.getClientHeight()/2 + getScrollYOffset() - getOffsetHeight());
    } else if (docking == DOCK_MIDDLE_RIGHT) {
      setOffsetLeft(Window.getClientWidth() + getScrollXOffset() - getOffsetWidth());
      setOffsetTop(Window.getClientHeight()/2 + getScrollYOffset() - getOffsetHeight());
    } else if (docking == DOCK_BOTTOM_LEFT) {
      setOffsetLeft(0);
      setOffsetTop(Window.getClientHeight() + getScrollYOffset() - getOffsetHeight());
    } else if (docking == DOCK_BOTTOM_CENTER) {
      setOffsetLeft(Window.getClientWidth()/2 + getScrollXOffset() - getOffsetWidth()/2);
      setOffsetTop(Window.getClientHeight() + getScrollYOffset() - getOffsetHeight());
    } else if (docking == DOCK_BOTTOM_RIGHT) {
      setOffsetLeft(Window.getClientWidth() + getScrollXOffset() - getOffsetWidth());
      setOffsetTop(Window.getClientHeight() + getScrollYOffset() - getOffsetHeight());
    }
  }
  
  private void setOffsetLeft(int left) {
    DOM.setStyleAttribute(getElement(), "left", left + "px");
  }
  
  private void setOffsetTop(int top) {
    DOM.setStyleAttribute(getElement(), "top", top + "px");
  }
  
  private static native int getScrollYOffset() /*-{
    var scrOfY = 0;
  
    if( typeof( $wnd.pageYOffset ) == 'number' ){
            scrOfY = $wnd.pageYOffset;
    } else if( $doc.body && ( $doc.body.scrollLeft || $doc.body.scrollTop) ){
            scrOfY = $doc.body.scrollTop;
    } else {
            scrOfY = $doc.documentElement.scrollTop;
    }
    return scrOfY;
  }-*/; 
  
  private static native int getScrollXOffset() /*-{
    var scrOfX = 0;
  
    if( typeof( $wnd.pageXOffset ) == 'number' ){
            scrOfX = $wnd.pageXOffset;
    } else if( $doc.body && ( $doc.body.scrollLeft || $doc.body.scrollTop) ){
            scrOfX = $doc.body.scrollLeft;
    } else {
            scrOfX = $doc.documentElement.scrollLeft;
    }
    return scrOfX;
  }-*/; 
}
