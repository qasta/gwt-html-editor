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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorToolbar extends Composite {
	
	final private Editor editor;
	
	private FlowPanel fullToolbar;
	private FlowPanel shortToolbar;
	private VerticalPanel topContainer;
	
	public EditorToolbar(Editor _editor) {
		
		topContainer = new VerticalPanel();
		topContainer.setStyleName("Editor-Toolbar");
		
		this.editor = _editor;
		
		fullToolbar = new FlowPanel();
		
		shortToolbar = new FlowPanel();
		
		EditorToolbarButton source = new EditorToolbarButton(EditorToolbarButton.BUTTON_SOURCE);
		source.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				editor.getEditorWYSIWYG().toggleView();
			}
		});

		EditorToolbarButton removeFormat = new SimpleCommandButton(EditorToolbarButton.BUTTON_DELETE, "RemoveFormat");

		EditorToolbarButton undo = new SimpleCommandButton(EditorToolbarButton.BUTTON_UNDO, "Undo");
		EditorToolbarButton redo = new SimpleCommandButton(EditorToolbarButton.BUTTON_REDO, "Redo");
		
		EditorToolbarButton bold = new SimpleCommandButton(EditorToolbarButton.BUTTON_BOLD, "Bold");
		EditorToolbarButton italic = new SimpleCommandButton(EditorToolbarButton.BUTTON_ITALIC, "Italic");
		EditorToolbarButton underlined = new SimpleCommandButton(EditorToolbarButton.BUTTON_UNDERLINE, "Underline"); 

		EditorToolbarButton subscript = new SimpleCommandButton(EditorToolbarButton.BUTTON_SUBSCRIPT, "Subscript"); 
		EditorToolbarButton superscript = new SimpleCommandButton(EditorToolbarButton.BUTTON_SUPERSCRIPT, "Superscript"); 

		EditorToolbarButton justifyLeft = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNLEFT, "JustifyLeft"); 
		EditorToolbarButton justifyCenter = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNCENTER, "JustifyCenter"); 
		EditorToolbarButton justifyRight = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNRIGHT, "JustifyRight"); 
		EditorToolbarButton justifyJustify = new SimpleCommandButton(EditorToolbarButton.BUTTON_ALIGNJUSTIFY, "JustifyFull");
		
		EditorToolbarButton ol = new SimpleCommandButton(EditorToolbarButton.BUTTON_OL, "InsertOrderedList");
		EditorToolbarButton ul = new SimpleCommandButton(EditorToolbarButton.BUTTON_UL, "InsertUnorderedList");

		EditorToolbarButton link = new EditorToolbarButton(EditorToolbarButton.BUTTON_LINK);
		link.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				
				final EditorPromptPanelWidget widget = new EditorPromptPanelWidget();
				
				VerticalPanel container = new VerticalPanel();
				final TextBox urlTextBox = new TextBox();
				
				HorizontalPanel hz = new HorizontalPanel();
				hz.add(new Label("Link URL: "));
				hz.add(urlTextBox);
				container.add(hz);
				
				HorizontalPanel hzButtons = new HorizontalPanel();
				Button b = new Button("Create Link");
				b.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						widget.getPrompt().complete(urlTextBox.getText());
					}
				});
				
				Button c = new Button("Cancel");
				c.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						widget.getPrompt().complete(null);
					}
				});
				
				hzButtons.add(b);
				hzButtons.add(c);
				
				container.add(hzButtons);
				
				widget.setWidget(container);
				
				new AdvancedPromptPannel("CreateLink", "Create Link", widget);
				
				urlTextBox.setFocus(true);
			}
		});
		
		EditorToolbarButton unlink = new SimpleCommandButton(EditorToolbarButton.BUTTON_UNLINK, "UnLink"); 
		EditorToolbarButton image = new EditorToolbarButton(EditorToolbarButton.BUTTON_IMAGE);
		image.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				final EditorPromptPanelWidget widget = new EditorPromptPanelWidget();
				
				VerticalPanel container = new VerticalPanel();
				final TextBox urlTextBox = new TextBox();
				
				HorizontalPanel hz = new HorizontalPanel();
				hz.add(new Label("Image URL: "));
				hz.add(urlTextBox);
				container.add(hz);
				
				HorizontalPanel hzButtons = new HorizontalPanel();
				Button b = new Button("Insert Image");
				b.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						widget.getPrompt().complete(urlTextBox.getText());
					}
				});
				
				Button c = new Button("Cancel");
				c.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						widget.getPrompt().complete(null);
					}
				});
				
				hzButtons.add(b);
				hzButtons.add(c);
				
				container.add(hzButtons);
				
				widget.setWidget(container);
				
				new AdvancedPromptPannel("InsertImage", "Insert Image", widget);
				
				urlTextBox.setFocus(true);
			}
		});
		
		EditorToolbarButton foreColor = new EditorToolbarButton(EditorToolbarButton.BUTTON_TEXTCOLOR); 
		foreColor.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				final EditorPromptPanelWidget widget = new EditorPromptPanelWidget();
				
				VerticalPanel container = new VerticalPanel();
				final TextBox urlTextBox = new TextBox();
				
				HorizontalPanel hz = new HorizontalPanel();
				hz.add(new Label("Text color RGB #"));
				hz.add(urlTextBox);
				container.add(hz);
				
				HorizontalPanel hzButtons = new HorizontalPanel();
				Button b = new Button("Set Color");
				b.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						widget.getPrompt().complete(urlTextBox.getText());
					}
				});
				
				Button c = new Button("Cancel");
				c.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						widget.getPrompt().complete(null);
					}
				});
				
				hzButtons.add(b);
				hzButtons.add(c);
				
				container.add(hzButtons);
				
				widget.setWidget(container);
				
				new AdvancedPromptPannel("ForeColor", "Text Color", widget);
				
				urlTextBox.setFocus(true);
			}
		});
		
		EditorToolbarSelect styles = new EditorToolbarSelect();
		styles.addItem("Style", "");
		String[][] formats = EditorUtils.getSupportedFormats();
		for (int i = 0; i < formats.length; i++) {
			styles.addItem(formats[i][0], formats[i][1]);
		}
		
		styles.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox subj = ((ListBox) sender);
				editor.execCommand("formatblock", false, subj.getValue(subj.getSelectedIndex()));
				subj.setSelectedIndex(0);
				subj.setFocus(false);
			}
		});

		EditorToolbarSelect fontSizes = new EditorToolbarSelect();
		fontSizes.addItem("Size", "");
		for (int i = 1; i < 8; i++) {
			fontSizes.addItem("Size " + i, "" + i);
		}
		
		fontSizes.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox subj = ((ListBox) sender);
				editor.execCommand("fontsize", false, subj.getValue(subj.getSelectedIndex()));
				subj.setSelectedIndex(0);
				subj.setFocus(false);
			}
		});

		

		fullToolbar.add(source);

		fullToolbar.add(EditorToolbarButton.getSpacer());
		
		fullToolbar.add(removeFormat);
		
		fullToolbar.add(EditorToolbarButton.getSpacer());
		
		fullToolbar.add(undo);
		fullToolbar.add(redo);
		
		fullToolbar.add(EditorToolbarButton.getSpacer());
		
		fullToolbar.add(bold);
		fullToolbar.add(italic);
		fullToolbar.add(underlined);
		
		fullToolbar.add(EditorToolbarButton.getSpacer());

		fullToolbar.add(subscript);
		fullToolbar.add(superscript);

		fullToolbar.add(EditorToolbarButton.getSpacer());
		
		fullToolbar.add(justifyLeft);
		fullToolbar.add(justifyCenter);
		fullToolbar.add(justifyRight);
		fullToolbar.add(justifyJustify);

		fullToolbar.add(EditorToolbarButton.getSpacer());

		fullToolbar.add(ol);
		fullToolbar.add(ul);
		
		fullToolbar.add(EditorToolbarButton.getSpacer());
		
		fullToolbar.add(link);
		fullToolbar.add(unlink);
		fullToolbar.add(image);
		
		fullToolbar.add(EditorToolbarButton.getSpacer());
		
		fullToolbar.add(foreColor);
		
		fullToolbar.add(EditorToolbarButton.getSpacer());

		fullToolbar.add(styles);
		fullToolbar.add(fontSizes);
		
		
		EditorToolbarButton source2 = new EditorToolbarButton(EditorToolbarButton.BUTTON_NEW2);
		source2.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				editor.getEditorWYSIWYG().toggleView();
			}
		});
		
		shortToolbar.add(source2);
		
		topContainer.add(fullToolbar);
		topContainer.add(shortToolbar);
		
		fullToolbar.setVisible(true);
		shortToolbar.setVisible(false);

		initWidget(topContainer);
	}
	
	private class SimpleCommandButton extends EditorToolbarButton {
		public SimpleCommandButton(String buttonId, final String command) {
			this(buttonId, command, false, null);
		}

		public SimpleCommandButton(String buttonId, final String command, final boolean ui, String value) {
			super(buttonId);
			
			this.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					editor.execCommand(command, ui, null);
				}
			});
		}
	}
	
	private class SimplePromptPannel extends EditorToolbarButton {
		public SimplePromptPannel(String buttonId, final String command, final String question) {
			super(buttonId);
			
			this.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					String value = EditorUtils.prompt(question);
					if (value != null) {
						editor.execCommand(command, false, value);
					}
				}
			});
		}
	}
	
	private class AdvancedPromptPannel extends EditorPromptPanel {
		public AdvancedPromptPannel(final String command, String title, EditorPromptPanelWidget widget) {
			super(title, widget);
			
			this.addPopupListener(new PopupListener() {
				public void onPopupClosed(final PopupPanel sender, boolean autoClosed) {
					String value = ((EditorPromptPanel) sender).getValue();
					EditorUtils.restoreSelection(editor.getEditorWYSIWYG().getFrame());
					EditorUtils.doFocus(editor.getEditorWYSIWYG().getFrame());
					if (value != null) {
						editor.execCommand(command, false, value);
					}
				}
			});
			
			EditorUtils.saveSelection(editor.getEditorWYSIWYG().getFrame());
			super.show(editor);
		}
	}
	
	public void setWidth(String width) {
		topContainer.setWidth(width);
	}
	
	public String getWidth() {
		return DOM.getStyleAttribute(topContainer.getElement(), "width");
	}
	
	public void switchToSmall() {
		fullToolbar.setVisible(false);
		shortToolbar.setVisible(true);
	}
	
	public void switchToFull() {
		fullToolbar.setVisible(true);
		shortToolbar.setVisible(false);
	}
}
