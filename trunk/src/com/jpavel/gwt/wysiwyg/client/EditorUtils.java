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

import com.google.gwt.user.client.ui.NamedFrame;


public class EditorUtils {
	public static native String getSelection() /*-{
		var txt = '';
		if ($wnd.getSelection)
		{
			txt = $wnd.getSelection();
		}
		else if ($wnd.document.getSelection)
		{
			txt = $wnd.document.getSelection();
		}
		else if ($wnd.document.selection)
		{
			txt = $wnd.document.selection.createRange().text;
		}
		else return;
		return txt;
	}-*/;

	public static native String replaceSelection(String text) /*-{
		if ($wnd.document.selection) { // IE
	        var sel = $wnd.document.selection.createRange();
	        sel.text = text;
	    } else {
			var sel = $wnd.getSelection();
			var range = sel.getRangeAt(0);
            sel.removeAllRanges();
            range.deleteContents();
            var oldContent = $wnd.document.body.innerHTML;
            $wnd.alert(oldContent);
            var inTag = false;	
            var insertPos = 0;
            for (var foo = 0, pos = 0; foo < oldContent.length; foo++) {
                var aChar = oldContent.substr(foo, 1);
                if (aChar == "<") {
                    inTag = true;
                }
                if (!inTag) {
                    pos++;
                    if (pos == range.startOffset) {
                        insertPos = foo + 1;
                    }
                }
                if (aChar == ">") {
                    inTag = false;
                }
            }
            $wnd.alert(insertPos);
            
            $wnd.document.body.innerHTML = oldContent.substr(0, insertPos) + text + oldContent.substr(insertPos, oldContent.length);
		}
	}-*/;
	
	public static native void execCommand(NamedFrame frame, String command, boolean ui, String value)/*-{
		var frameName = frame.@com.google.gwt.user.client.ui.NamedFrame::getName()();
		var oIframe = $wnd.document.getElementsByName(frameName)[0];
	    var oDoc = oIframe.contentWindow || oIframe.contentDocument;
	    if (oDoc.document) {
	        oDoc = oDoc.document;
	    }
	    oDoc.execCommand(command, ui, value);
	}-*/;
	
	
	public static native String prompt(String question)/*-{
		return $wnd.prompt(question, "");
	}-*/;

	public static native String alert(String question)/*-{
		return $wnd.alert(question);
	}-*/;
	
	private static native boolean isIE() /*-{
		var agt=navigator.userAgent.toLowerCase();
		return ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
	}-*/;
	
	private static native boolean isGecko() /*-{
		var agt=navigator.userAgent.toLowerCase();
		return (agt.indexOf('gecko') != -1);
	}-*/;

	
	public static String[][] getSupportedFormats() {
		if (isIE()) {
			return new String[][]{{"Normal", "Normal"}, {"Heading 1", "Heading 1"}, {"Heading 2", "Heading 2"}, {"Heading 3", "Heading 3"}, {"Heading 4", "Heading 4"}, {"Heading 5", "Heading 5"}, {"Heading 6", "Heading 6"}};		
		}
		
		if (isGecko()) {
			return new String[][]{{"Normal", "P"}, {"Heading 1", "H1"}, {"Heading 2", "H2"}, {"Heading 3", "H3"}, {"Heading 4", "H4"}, {"Heading 5", "H5"}, {"Heading 6", "H6"}};		
		}
		
		return new String[][]{{"Normal", "P"}};
	}
}
