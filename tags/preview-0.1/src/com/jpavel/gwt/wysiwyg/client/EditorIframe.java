package com.jpavel.gwt.wysiwyg.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.NamedFrame;

public class EditorIframe extends NamedFrame {
	public EditorIframe(String name) {
		super(name);
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
		if (DOM.eventGetType(event) == Event.ONMOUSEOVER){
			for (Iterator i = mouseOverListeners.iterator(); i.hasNext(); ) {
				((EditorMouseOverListener) i.next()).onMouseOver(this);
			}
		}
	}
}
