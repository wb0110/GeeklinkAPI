package fr.geeklink.reader.window.button;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class GLRButton {

	private Point location;
	private Dimension size;
	protected boolean relativeToRight = false;
	private boolean enabled = true;
	
	public GLRButton(int x, int y, int width, int height) {
		location = new Point(x, y);
		size = new Dimension(width, height);
	}
	
	public GLRButton(int x, int y, int width, int height, boolean relativeToRight) {
		this(x, y, width, height);
		this.relativeToRight = relativeToRight;
	}
	
	public Dimension getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public boolean isRelativeToRight() {
		return relativeToRight;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setRelativeToRight(boolean relative) {
		relativeToRight = relative;
	}
	
	public void paint(Graphics g, int x, int y, boolean hover) {}
	
	public void onClicked() {}
}
