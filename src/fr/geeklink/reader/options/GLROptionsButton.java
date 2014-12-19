package fr.geeklink.reader.options;

import java.awt.Color;
import java.awt.Graphics;

import fr.geeklink.reader.window.GLRFrame;
import fr.geeklink.reader.window.button.GLRColorButton;

public class GLROptionsButton extends GLRColorButton{

	private GLRFrame frame;
	
	public GLROptionsButton(int x, int y, int width, int height, Color color, Color hover) {
		super(x, y, width, height, color, hover);
	}
	
	public GLROptionsButton(int x, int y, int width, int height, boolean relativeToRight, Color color, Color hover) {
		super(x, y, width, height, relativeToRight, color, hover);
	}
	
	public void setFrame(GLRFrame frame) {
		this.frame = frame;
	}
	
	public boolean isEnabled() {
		return frame == null || !frame.isVisible();
	}
	
	public void paint(Graphics g, int x, int y, boolean hover) {
		if(isEnabled()) {
			g.setColor(hover ? getHover() : getColor());
		} else {
			g.setColor(Color.GRAY);
		}
		g.fillRect(x, y, getSize().width, getSize().height);
	}
}
