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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorWYSIWYG extends Composite {
	
	private Editor editor;
	
	private VerticalPanel vp;
	private EditorIframe frame;
	private TextArea textArea;
	
	private boolean showFrame = true;
	
	public EditorWYSIWYG(Editor _editor, String _id) {
		
		this.editor = _editor;
		
		vp = new VerticalPanel();
		vp.setWidth("100%");

		frame = new EditorIframe(_id + "_wysiwyg_Frame");
		frame.setUrl("about:blank");
		frame.setStyleName("Editor-IFrame");
		frame.setWidth("100%");
		frame.setHeight(editor.getHeight());
		
		// this is done on purpose...
		frame.addMouseOverListener(new EditorMouseOverListener() {
			public void onMouseOver(Widget widget) {
				enableDesignMode();
			}
		});
		
		textArea = new TextArea();
		textArea.setWidth("100%");
		
		frame.setVisible(true);
		textArea.setVisible(false);
		
		vp.add(frame);
		vp.add(textArea);
		
		initWidget(vp);
	}
	
	public native void initFrame(Element oIframe)/*-{
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
		oDoc.write("<html><body style='border:none; margin:0px; padding: 5px; font-family: sans-serif; font-size: 12px;'><p>&nbsp;</p></body></html>");
		oDoc.close();
	}-*/;


	private boolean editModeOn = false; 
	
	public void enableDesignMode() {
		if (!editModeOn) {
			//String _html = getHTML();
			_enableDesignMode(frame.getElement());
			//setHTML(_html);
			editModeOn = true;
		}
	}
	
	private native void _enableDesignMode(Element oIframe)/*-{
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    oDoc.designMode = 'On';
	}-*/;

	public void setHTML(String _html) {
		textArea.setText(_html);
		_setHTML(frame.getElement(), _html);
	}
	
	private native void _setHTML(Element oIframe, String _html)/*-{
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    oDoc.body.innerHTML = "";
	    oDoc.write(_html);
	    oDoc.close();
	}-*/;

	public String getHTML() {
		return _getHTML(frame.getElement());
	}
	
	public native String _getHTML(Element oIframe)/*-{
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    return oDoc.body.innerHTML;
	}-*/;

	public EditorIframe getFrame() {
		return frame;
	}
	
	public void toggleView() {
		if (this.showFrame) {
			textArea.setText(getHTML());

			textArea.setHeight("" + frame.getOffsetHeight() + "px");
			textArea.setWidth("" + frame.getOffsetWidth() + "px");
			
			textArea.setVisible(true);
			frame.setVisible(false);
			
			this.showFrame = false;
			
			editor.getEditorToolbar().switchToSmall();
		} else {
			
			String newHtml = textArea.getText();
			
			textArea.setVisible(false);
			frame.setVisible(true);

			setHTML(newHtml);

			this.showFrame = true;
			
			editor.getEditorToolbar().switchToFull();
		}
	}
}
