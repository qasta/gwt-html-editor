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
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorColorPicker extends DialogBox {
	//18x12
	private static String[] colors = new String[] { "000000", "000033",
			"000066", "000099", "0000CC", "0000FF", "330000", "330033",
			"330066", "330099", "3300CC", "3300FF", "660000", "660033",
			"660066", "660099", "6600CC", "6600FF", "990000", "990033",
			"990066", "990099", "9900CC", "9900FF", "CC0000", "CC0033",
			"CC0066", "CC0099", "CC00CC", "CC00FF", "FF0000", "FF0033",
			"FF0066", "FF0099", "FF00CC", "FF00FF", "003300", "003333",
			"003366", "003399", "0033CC", "0033FF", "333300", "333333",
			"333366", "333399", "3333CC", "3333FF", "663300", "663333",
			"663366", "663399", "6633CC", "6633FF", "993300", "993333",
			"993366", "993399", "9933CC", "9933FF", "CC3300", "CC3333",
			"CC3366", "CC3399", "CC33CC", "CC33FF", "FF3300", "FF3333",
			"FF3366", "FF3399", "FF33CC", "FF33FF", "006600", "006633",
			"006666", "006699", "0066CC", "0066FF", "336600", "336633",
			"336666", "336699", "3366CC", "3366FF", "666600", "666633",
			"666666", "666699", "6666CC", "6666FF", "996600", "996633",
			"996666", "996699", "9966CC", "9966FF", "CC6600", "CC6633",
			"CC6666", "CC6699", "CC66CC", "CC66FF", "FF6600", "FF6633",
			"FF6666", "FF6699", "FF66CC", "FF66FF", "009900", "009933",
			"009966", "009999", "0099CC", "0099FF", "339900", "339933",
			"339966", "339999", "3399CC", "3399FF", "669900", "669933",
			"669966", "669999", "6699CC", "6699FF", "999900", "999933",
			"999966", "999999", "9999CC", "9999FF", "CC9900", "CC9933",
			"CC9966", "CC9999", "CC99CC", "CC99FF", "FF9900", "FF9933",
			"FF9966", "FF9999", "FF99CC", "FF99FF", "00CC00", "00CC33",
			"00CC66", "00CC99", "00CCCC", "00CCFF", "33CC00", "33CC33",
			"33CC66", "33CC99", "33CCCC", "33CCFF", "66CC00", "66CC33",
			"66CC66", "66CC99", "66CCCC", "66CCFF", "99CC00", "99CC33",
			"99CC66", "99CC99", "99CCCC", "99CCFF", "CCCC00", "CCCC33",
			"CCCC66", "CCCC99", "CCCCCC", "CCCCFF", "FFCC00", "FFCC33",
			"FFCC66", "FFCC99", "FFCCCC", "FFCCFF", "00FF00", "00FF33",
			"00FF66", "00FF99", "00FFCC", "00FFFF", "33FF00", "33FF33",
			"33FF66", "33FF99", "33FFCC", "33FFFF", "66FF00", "66FF33",
			"66FF66", "66FF99", "66FFCC", "66FFFF", "99FF00", "99FF33",
			"99FF66", "99FF99", "99FFCC", "99FFFF", "CCFF00", "CCFF33",
			"CCFF66", "CCFF99", "CCFFCC", "CCFFFF", "FFFF00", "FFFF33",
			"FFFF66", "FFFF99", "FFFFCC", "FFFFFF" };
	
	private List selectListeners = new ArrayList();
	
	public EditorColorPicker(String title) {
		this.setStyleName("Editor-DialogBox");
		this.setText(title);
		
		VerticalPanel vp = new VerticalPanel();
		
		Grid grid = new Grid(12, 18);
		grid.setCellPadding(0);
		grid.setCellSpacing(1);
		
		for (int j = 0; j < 12; j++) {
			for (int i = 0; i < 18; i++) {
				grid.setWidget(j, i, new HTML("<div style='width:12px;height:12px;cursor:pointer;background-color:#" + colors[j*12 + i] + "'></div>"));
			}
		}
		
		grid.addTableListener(new TableListener() {
			public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
				fireColorSelected(colors[row*12 + cell]);
				hide();
			}
		});
		
		vp.setStyleName("Editor-DialogBox-Content");
		vp.add(grid);
		
		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				hide();
			}
		});
		
		vp.add(cancelButton);
		vp.setCellHorizontalAlignment(cancelButton, HasAlignment.ALIGN_CENTER);
		
		setWidget(vp);
	}
	
	public void addSelectListener(EditorColorSelectListener listener) {
		selectListeners.add(listener);
	}
	
	public void removeSelectListener(EditorColorSelectListener listener) {
		selectListeners.remove(listener);
	}
	
	private void fireColorSelected(String rgb) {
		for (Iterator i = selectListeners.iterator(); i.hasNext(); ) {
			((EditorColorSelectListener) i.next()).colorSelected(rgb);
		}
	}
}
