package fr.geeklink.reader.window.button;

import java.awt.Color;
import java.awt.Graphics;

public class GLRColorButton extends GLRButton{

	private Color color,hover;
	
	public GLRColorButton(int x, int y, int width, int height, Color color, Color hover) {
		super(x, y, width, height);
		this.hover = hover;
		this.color = color;
	}
	
	public GLRColorButton(int x, int y, int width, int height, boolean relativeToRight, Color color, Color hover) {
		this(x, y, width, height, color, hover);
		super.relativeToRight = relativeToRight;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Color getHover() {
		return hover;
	}
	
	public void paint(Graphics g, int x, int y, boolean hover) {
		g.setColor(hover ? this.hover : color);
		g.fillRect(x, y, getSize().width, getSize().height);
	}
}
