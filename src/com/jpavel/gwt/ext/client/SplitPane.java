package com.jpavel.gwt.ext.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SplitPane extends Composite {

	public static final int VERTICAL_SPLIT = 0;
	public static final int HORIZONTAL_SPLIT = 1;

	private int splitOrientation;
	
	private SimplePanel mainContainer;
	private VerticalPanel verticalPanel;
	private HorizontalPanel horizontalPanel;
	private SimplePanel firstWidget;
	private SimplePanel secondWidget;
	private Divider divider;
	
	private int originalWidth;
	private int originalHeight;

	/**
	 * 
	 * 
	 */
	public SplitPane() {
		splitOrientation = VERTICAL_SPLIT;

		init();
	}

	/**
	 * 
	 * @param split
	 */
	public SplitPane(int splitOrientation) {
		this.splitOrientation = splitOrientation;

		init();
	}

	private void init() {

		mainContainer = new SimplePanel();
		mainContainer.setStyleName("SplitPane");

		Panel panel;

		if (splitOrientation == VERTICAL_SPLIT) {
			horizontalPanel = new HorizontalPanel();
			horizontalPanel.setWidth("100%");
			horizontalPanel.setHeight("100%");

			mainContainer.setWidget(horizontalPanel);
			panel = horizontalPanel;
		} else {
			verticalPanel = new VerticalPanel();
			verticalPanel.setWidth("100%");
			verticalPanel.setHeight("100%");
			
			mainContainer.setWidget(verticalPanel);
			panel = verticalPanel;
		}

		firstWidget = new SimplePanel();
		DOM.setStyleAttribute(firstWidget.getElement(), "overflow", "hidden");
		firstWidget.setWidth("100%");
		firstWidget.setHeight("100%");
		panel.add(firstWidget);

		divider = new Divider(splitOrientation);
		panel.add(divider);

		secondWidget = new SimplePanel();
		DOM.setStyleAttribute(secondWidget.getElement(), "overflow", "hidden");
		secondWidget.setWidth("100%");
		secondWidget.setHeight("100%");
		panel.add(secondWidget);

		initWidget(mainContainer);
	}
	
	protected void onLoad() {
		super.onLoad();
		
		this.originalWidth = this.getOffsetWidth();
		this.originalHeight = this.getOffsetHeight();
	}

	private class Divider extends Image implements MouseListener {

		private boolean dragging = false;

		public Divider(int splitOrientation) {
			super("spacer.gif");

			setStyleName("SplitPaneDivider");
			if (splitOrientation == HORIZONTAL_SPLIT) {
				DOM.setStyleAttribute(this.getElement(), "cursor", "n-resize");

				setWidth("100%");
				setHeight("5px");
			} else {
				DOM.setStyleAttribute(this.getElement(), "cursor", "e-resize");

				setWidth("5px");
				setHeight("100%");
			}

			addMouseListener(this);
		}

		protected void onLoad() {
			super.onLoad();

			CellPanel cPanel = (CellPanel) mainContainer.getWidget();

			if (splitOrientation == VERTICAL_SPLIT) {
				cPanel.setCellWidth(firstWidget, (originalWidth - 5)/2 + "px");
				cPanel.setCellWidth(divider, "5px");
				cPanel.setCellWidth(secondWidget, (originalWidth - 5)/2 + "px");
			} else {
				cPanel.setCellHeight(firstWidget, (originalHeight - 5)/2 + "px");
				cPanel.setCellHeight(divider, "5px");
				cPanel.setCellHeight(secondWidget, (originalHeight - 5)/2 + "px");
			}
		}

		public void onMouseDown(Widget sender, int x, int y) {
			dragging = true;
			DOM.setCapture(this.getElement());
		}

		public void onMouseUp(Widget sender, int x, int y) {
			dragging = false;
			DOM.releaseCapture(this.getElement());
		}

		public void onMouseEnter(Widget sender) {
			// TODO Auto-generated method stub

		}

		public void onMouseLeave(Widget sender) {
			// TODO Auto-generated method stub

		}

		public void onMouseMove(Widget sender, int x, int y) {
			if (dragging) {
				int newFirstWidth;
				if (splitOrientation == VERTICAL_SPLIT) {
					newFirstWidth = divider.getAbsoluteLeft() + x - 2 - mainContainer.getAbsoluteLeft();

					if (newFirstWidth >= originalWidth) {
						newFirstWidth = originalWidth - 1;
					}
				} else {
					newFirstWidth = divider.getAbsoluteTop() + y - 2 - mainContainer.getAbsoluteTop();

					if (newFirstWidth >= originalHeight) {
						newFirstWidth = originalHeight - 1;
					}
				}

				if (newFirstWidth < 0) {
					newFirstWidth = 0;
				}

				CellPanel cPanel = (CellPanel) mainContainer.getWidget();

				if (splitOrientation == VERTICAL_SPLIT) {
					cPanel.setCellWidth(firstWidget, (newFirstWidth - 5) + "px");
					cPanel.setCellWidth(secondWidget, (originalWidth - newFirstWidth) + "px");
				} else {
					cPanel.setCellHeight(firstWidget, (newFirstWidth - 5) + "px");
					cPanel.setCellHeight(secondWidget, (originalHeight - newFirstWidth) + "px");
				}
			}
		}
		
	    /**
	     * Overriden to block the browser's default behaviour.
	     */
	    public void onBrowserEvent(Event event) {
	        super.onBrowserEvent(event);
	        //This is required to prevent a Drag & Drop of the Image in the edit text.
	        DOM.eventPreventDefault(event);
	    }

	}

	public void setLeftWidget(Widget widget) {
		if (splitOrientation != VERTICAL_SPLIT) {
			throw new RuntimeException("you're not using VERTICAL_SPLIT");
		}

		firstWidget.setWidget(widget);
	}

	public void setRightWidget(Widget widget) {
		if (splitOrientation != VERTICAL_SPLIT) {
			throw new RuntimeException("you're not using VERTICAL_SPLIT");
		}

		secondWidget.setWidget(widget);
	}

	public void setTopWidget(Widget widget) {
		if (splitOrientation != HORIZONTAL_SPLIT) {
			throw new RuntimeException("you're not using HORIZONTAL_SPLIT");
		}

		firstWidget.setWidget(widget);
	}

	public void setBottomWidget(Widget widget) {
		if (splitOrientation != HORIZONTAL_SPLIT) {
			throw new RuntimeException("you're not using HORIZONTAL_SPLIT");
		}

		secondWidget.setWidget(widget);
	}
}
