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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.NamedFrame;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditorWYSIWYG extends Composite {
	
	private Editor editor;
	
	private VerticalPanel vp;
	private NamedFrame frame;
	private TextArea textArea;
	
	private boolean showFrame = true;
	
	public EditorWYSIWYG(Editor _editor, String _id) {
		
		this.editor = _editor;
		
		vp = new VerticalPanel();

		frame = new NamedFrame(_id + "_wysiwyg_Frame");
		frame.setUrl("about:blank");
		frame.setStyleName("Editor-IFrame");
		frame.setWidth("100%");
		frame.setHeight(editor.getHeight());
		
		textArea = new TextArea();
		
		frame.setVisible(true);
		textArea.setVisible(false);
		
		vp.add(frame);
		vp.add(textArea);
		
		initWidget(vp);
	}
	
	public void setWidth(String width) {
		vp.setWidth(width);
	}
	
	public native void initFrame()/*-{
		var frameName = this.@com.jpavel.gwt.wysiwyg.client.EditorWYSIWYG::frame.@com.google.gwt.user.client.ui.NamedFrame::getName()();
		var oIframe = $wnd.document.getElementsByName(frameName)[0];
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    oDoc.designMode = 'On';
		oDoc.write("<html><body style='border:none; margin:0px; padding: 5px; font-family: sans-serif; font-size: 12px;'><p>&nbsp;</p></body></html>");
		oDoc.close();
	}-*/;
	
	public void setHTML(String _html) {
		textArea.setText(_html);
		_setHTML(_html);
	}
	
	private native void _setHTML(String _html)/*-{
		var frameName = this.@com.jpavel.gwt.wysiwyg.client.EditorWYSIWYG::frame.@com.google.gwt.user.client.ui.NamedFrame::getName()();
		var oIframe = $wnd.document.getElementsByName(frameName)[0];
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    oDoc.body.innerHTML = _html;
	    oDoc.designMode = 'On';
	}-*/;
	
	public native String getHTML()/*-{
		var frameName = this.@com.jpavel.gwt.wysiwyg.client.EditorWYSIWYG::frame.@com.google.gwt.user.client.ui.NamedFrame::getName()();
		var oIframe = $wnd.document.getElementsByName(frameName)[0];
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    return oDoc.body.innerHTML;
	}-*/;

	public NamedFrame getFrame() {
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

			_setHTML(newHtml);

			this.showFrame = true;
			
			editor.getEditorToolbar().switchToFull();
		}
	}
}
