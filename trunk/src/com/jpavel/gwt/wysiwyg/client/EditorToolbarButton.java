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

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class EditorToolbarButton extends Image {

	// Predefined set of buttons
	public static final String BUTTON_SOURCE = "Source";
	public static final String BUTTON_SOMETHING1 = "Something1";
	public static final String BUTTON_SAVE = "Save";
	public static final String BUTTON_NEW = "New";
	public static final String BUTTON_PREVIEW = "Preview";
	public static final String BUTTON_NEW2 = "New2";
	public static final String BUTTON_CUT = "Cut";
	public static final String BUTTON_COPY = "Copy";
	public static final String BUTTON_PASTE = "Paste";
	public static final String BUTTON_PASTETEXTONLY = "PasteTextOnly";
	public static final String BUTTON_PASTERICHTEXT = "PasteRichText";
	public static final String BUTTON_PRINT = "Print";
	public static final String BUTTON_SPELLCHECK = "SpellCheck";
	public static final String BUTTON_UNDO = "Undo";
	public static final String BUTTON_REDO = "Redo";
	public static final String BUTTON_FIND = "Find";
	public static final String BUTTON_REPLACE = "Replace";
	public static final String BUTTON_SOMETHING2 = "Something2";
	public static final String BUTTON_BOLD = "Bold";
	public static final String BUTTON_DELETE = "Delete";
	public static final String BUTTON_ITALIC = "Italic";
	public static final String BUTTON_UNDERLINE = "Underline";
	public static final String BUTTON_STRIKETHROUGH = "StrikeThrough";
	public static final String BUTTON_SUBSCRIPT = "Subscript";
	public static final String BUTTON_SUPERSCRIPT = "Superscript";
	public static final String BUTTON_OL = "OL";
	public static final String BUTTON_UL = "UL";
	public static final String BUTTON_INDENTLEFT = "IndentLeft";
	public static final String BUTTON_INDENTRIGHT = "IndentRight";
	public static final String BUTTON_ALIGNLEFT = "AlignLeft";
	public static final String BUTTON_ALIGNCENTER = "AlignCenter";
	public static final String BUTTON_ALIGNRIGHT = "AlignRight";
	public static final String BUTTON_ALIGNJUSTIFY = "AlignJustify";
	public static final String BUTTON_LINK = "Link";
	public static final String BUTTON_UNLINK = "UnLink";
	public static final String BUTTON_ANCHORE = "Anchore";
	public static final String BUTTON_IMAGE = "Image";
	public static final String BUTTON_FLASH = "Flash";
	public static final String BUTTON_TABLE = "Table";
	public static final String BUTTON_HR = "HR";
	public static final String BUTTON_SMILE = "Smile";
	public static final String BUTTON_SPECIALCHARACTER = "SpecialCharacter";
	public static final String BUTTON_PAGEBREAK = "PageBreak";
	public static final String BUTTON_KEYBOARD = "Keyboard";
	public static final String BUTTON_TEXTCOLOR = "TextColor";
	public static final String BUTTON_TEXTBACKGROUNDCOLOR = "TextBackgroundColor";
	public static final String BUTTON_HELP = "Help";
	public static final String BUTTON_MOVIE = "Movie";
	public static final String BUTTON_CHECKBOX = "Checkbox";
	public static final String BUTTON_RADIO = "Radio";
	public static final String BUTTON_TEXTFIELD = "TextField";
	public static final String BUTTON_TEXTAREA = "TextArea";
	public static final String BUTTON_SCROLLBAR = "ScrollBar";
	public static final String BUTTON_BUTTON = "Button";
	public static final String BUTTON_IMAGEFIELD = "ImageField";
	public static final String BUTTON_TEXTLAYER = "TextLayer";
	public static final String BUTTON_TABLE1 = "Table1";
	public static final String BUTTON_TABLEINSERTROW = "TableInsertRow";
	public static final String BUTTON_TABLEDELETEROW = "TableDeleteRow";
	public static final String BUTTON_TABLE2 = "Table2";
	public static final String BUTTON_TABLE3 = "Table3";
	public static final String BUTTON_TABLE4 = "Table4";
	public static final String BUTTON_TABLE5 = "Table5";
	public static final String BUTTON_TABLE6 = "Table6";
	public static final String BUTTON_TABLE7 = "Table7";
	public static final String BUTTON_FULLSCREEN = "FullScreen";

	public static final String SPACER = "spacer";
	
	private String buttonId;
	
	public EditorToolbarButton(String buttonId) {
		super("spacer.gif");
		
		this.buttonId = buttonId;
		
		this.addMouseListener(new MyMouseListener(this));
		
		this.setStyleName("Editor-Toolbar-Button Editor-Toolbar-Button-" + buttonId);
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	
	public static EditorToolbarButton getSpacer() {
		return new EditorToolbarButton(EditorToolbarButton.SPACER);
	}

	private class MyMouseListener implements MouseListener {
		
		private EditorToolbarButton button; 
		
		public MyMouseListener(EditorToolbarButton button) {
			this.button = button;
		}
		
		public void onMouseEnter(Widget sender) {
			button.setStyleName("Editor-Toolbar-Button Editor-Toolbar-Button-hover Editor-Toolbar-Button-" + button.getButtonId());
		}
		
		public void onMouseLeave(Widget sender) {
			button.setStyleName("Editor-Toolbar-Button Editor-Toolbar-Button-" + button.getButtonId());
		}

		public void onMouseDown(Widget sender, int x, int y) {
			// TODO Auto-generated method stub
		}
		
		public void onMouseMove(Widget sender, int x, int y) {
			// TODO Auto-generated method stub
		}
		
		public void onMouseUp(Widget sender, int x, int y) {
			// TODO Auto-generated method stub
		}
	}
}
