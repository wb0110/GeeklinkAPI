package fr.geeklink.reader.window;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

import fr.geeklink.reader.window.button.GLRButton;

public class GLRPanel extends JPanel{

	private static final long serialVersionUID = 8234426169765923138L;
	protected Point lastClick = new Point(-1, -1);
	protected Point lastLocation = new Point(-1, -1);
	protected ArrayList<GLRButton> buttons = new ArrayList<>();

	public GLRPanel() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				lastClick = e.getPoint();

				for(GLRButton button : buttons) {
					if(button == null) return;

					int x;
					if(button.isRelativeToRight()) {
						x = getWidth()-button.getLocation().x-button.getSize().width;
					} else {
						x = button.getLocation().x;
					}
					boolean hover = isBetween(lastLocation, new Point(x, button.getLocation().y),
							new Point(x+button.getSize().width, button.getLocation().y+button.getSize().height));
					if(hover) {
						button.onClicked();
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lastLocation = e.getPoint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lastLocation = new Point(-1, -1);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lastLocation = e.getPoint();

				boolean hover = false;
				for(GLRButton button : buttons) {
					if(button == null) return;

					int x;
					if(button.isRelativeToRight()) {
						x = getWidth()-button.getLocation().x-button.getSize().width;
					} else {
						x = button.getLocation().x;
					}
					if(button.isEnabled() && isBetween(lastLocation, new Point(x, button.getLocation().y),
							new Point(x+button.getSize().width, button.getLocation().y+button.getSize().height))) {
						hover = true;
					}
				}
				
				if(hover) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		super.paint(g);
	}
	
	public void drawButtons(Graphics g) {
		for(GLRButton button : buttons) {
			if(button == null) return;

			int x;
			if(button.isRelativeToRight()) {
				x = getWidth()-button.getLocation().x-button.getSize().width;
			} else {
				x = button.getLocation().x;
			}
			boolean hover = isBetween(lastLocation, new Point(x, button.getLocation().y),
					new Point(x+button.getSize().width, button.getLocation().y+button.getSize().height));
			button.paint(g.create(), x, button.getLocation().y, hover);
		}
	}
	
	public ArrayList<GLRButton> getButtons() {
		return buttons;
	}

	public boolean isBetween(Point p, Point p1, Point p2) {
		return p.x > p1.x && p.x < p2.x && p.y > p1.y && p.y < p2.y;
	}
}
