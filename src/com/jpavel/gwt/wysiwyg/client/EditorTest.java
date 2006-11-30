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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.LoadListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EditorTest implements EntryPoint {
	
	public void onModuleLoad() {
		buildSamplePage();
	}
	
	public void buildSamplePage() {
		final Editor editor = new Editor();
		editor.addLoadListener(new LoadListener() {
			public void onLoad(Widget sender) {
				// TODO Auto-generated method stub
			}
			
			public void onError(Widget sender) {
				// TODO Auto-generated method stub
			}
		});
		editor.setWidth("100%");
		editor.setHeight("300px");
		
		editor.load();
		
		Button button = new Button("Get HTML");
		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Window.alert(editor.getHTML());
			}
		});
		
		RootPanel.get().add(editor);
		//RootPanel.get().add(button);
	}
}
