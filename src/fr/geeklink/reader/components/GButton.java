package fr.geeklink.reader.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import fr.geeklink.api.GLApi;

public class GButton extends JButton{
	
	private static final long serialVersionUID = -7801892872886230626L;

	public GButton() {
		super();
	}

	public GButton(Action a) {
		super(a);
	}

	public GButton(Icon icon) {
		super(icon);
	}

	public GButton(String text, Icon icon) {
		super(text, icon);
	}

	public GButton(String text) {
		super(text);
	}
	
	@Override
	public void paint(Graphics g) {
		Color back = getModel().isPressed() ? GLApi.getFrameColor().darker() :
			getModel().isRollover() ? GLApi.getFrameColor().brighter() : GLApi.getFrameColor();
			
		back = getModel().isEnabled() ? back : GLApi.getFrameColor().darker().darker();
		g.setColor(back);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(GLApi.getBackgroundColor());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		g.drawString(getText(), getWidth()/2-metrics.stringWidth(getText())/2, getHeight()/2+metrics.getHeight()/2);
	}

}
