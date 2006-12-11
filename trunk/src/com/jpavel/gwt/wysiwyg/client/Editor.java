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

package com.jpavel.gwt.wysiwyg.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LoadListener;
import com.google.gwt.user.client.ui.SourcesLoadEvents;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Editor extends Composite implements SourcesLoadEvents {
	
	private EditorToolbar toolbar;
	private EditorWYSIWYG wysiwyg;

	private VerticalPanel container;
	
	// listeners
	private List loadListeners = new ArrayList();
	
	public Editor() {
		container = new VerticalPanel();
		
		container.setStyleName("Editor");
		
		initWidget(container);
	}
	
	public void load() {
		EditorToolbar toolbar = getEditorToolbar();
		toolbar.setWidth("100%");
		container.add(toolbar);
		
		final EditorWYSIWYG wysiwyg = getEditorWYSIWYG();
		wysiwyg.setWidth("100%");
		container.add(wysiwyg);
		
		// Firefox hack...
		new Timer() {
			public void run() {
				wysiwyg.initFrame(wysiwyg.getFrame().getElement());
				notifyLoadListeners();
			}
		}.schedule(10);
	}

	public void setWidth(String width) {
		container.setWidth(width);
	}
	
	public String getWidth() {
		return DOM.getStyleAttribute(container.getElement(), "width");
	}

	public void setHeight(String height) {
		container.setHeight(height);
	}

	public String getHeight() {
		return DOM.getStyleAttribute(container.getElement(), "height");
	}
	
	public EditorToolbar getEditorToolbar() {
		if (toolbar == null) {
			toolbar = new EditorToolbar(this);
		}
		return toolbar;
	}
	
	public EditorWYSIWYG getEditorWYSIWYG() {
		if (wysiwyg == null) {
			wysiwyg = new EditorWYSIWYG(this, "" + (int)(Math.random() * 10000));
		}
		return wysiwyg;
	}
	
	public void addLoadListener(LoadListener listener) {
		loadListeners.add(listener);
	}
	
	public void removeLoadListener(LoadListener listener) {
		loadListeners.remove(listener);
	}
	
	private void notifyLoadListeners() {
		for (int i = 0; i < loadListeners.size(); i++) {
			((LoadListener) loadListeners.get(i)).onLoad(this);
		}
	}
	
	public String getHTML(){
		return getEditorWYSIWYG().getHTML();
	}
	
	public void setHTML(String _html){
		getEditorWYSIWYG().setHTML(_html);
	}
	
	public void execCommand(String command, boolean ui, String value) {
		EditorUtils.execCommand(this.getEditorWYSIWYG().getFrame().getElement(), command, ui, value);
	}
}
