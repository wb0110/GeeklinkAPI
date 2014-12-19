package fr.geeklink.reader.window;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import fr.geeklink.api.GLApi;
import fr.geeklink.reader.window.button.GLRButton;

public class GLRFrame extends JFrame implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = -1301410681649007194L;

	private int resizeType = Cursor.DEFAULT_CURSOR;
	private Point locationBefore;
	private Point whereStarted;
	private Dimension sizeBefore;
	private GLRMainPanel mainPanel;

	public GLRFrame(String title, Dimension size, boolean resizable, GLRenderPanel panel) {
		setTitle(title);
		setPreferredSize(size);
		setIconImage(GLApi.getIconTexture());
		setUndecorated(true);
		setResizable(resizable);
		setMinimumSize(getPreferredSize());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2-size.width/2, screenSize.height/2-size.height/2);

		addMouseListener(this);
		addMouseMotionListener(this);

		this.mainPanel = new GLRMainPanel(this, panel);
		add(mainPanel);

		setSize(getPreferredSize());
		setVisible(false);
		setEnabled(true);

		new Thread("RepaintFrame-"+title){
			public void run() {
				while(isEnabled()) {
					try {
						Thread.sleep(100l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if(isVisible()) {
						repaint();
					}
				}
			};
		}.start();
	}

	public GLRenderPanel getRenderPanel() {
		return mainPanel.getRenderPanel();
	}
	
	public GLRBorderPanel getBorderPanel() {
		return mainPanel.getBorderPanel();
	}

	public GLRFrame(String title, Dimension size, boolean resizable) {
		this(title, size, resizable, new GLRenderPanel());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {
		resizeType = getCursor(e.getPoint());
		whereStarted = e.getLocationOnScreen();
		sizeBefore = getSize();
		locationBefore = getLocation();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isResizable() && resizeType != Cursor.DEFAULT_CURSOR) {
			Dimension newSize = getSize();
			Point location = getLocation();

			if(resizeType == Cursor.NW_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width+(whereStarted.x-e.getXOnScreen()), sizeBefore.height+(whereStarted.y-e.getYOnScreen()));
				location = new Point(e.getXOnScreen()+(locationBefore.x-whereStarted.x), e.getYOnScreen()+(locationBefore.y-whereStarted.y));
			} else if(resizeType == Cursor.SW_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width+(whereStarted.x-e.getXOnScreen()), sizeBefore.height+(e.getYOnScreen()-whereStarted.y));
				location = new Point(e.getXOnScreen()+(locationBefore.x-whereStarted.x), locationBefore.y);
			} else if(resizeType == Cursor.SE_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width+(e.getXOnScreen()-whereStarted.x), sizeBefore.height+(e.getYOnScreen()-whereStarted.y));
			} else if(resizeType == Cursor.NE_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width+(e.getXOnScreen()-whereStarted.x), sizeBefore.height+(whereStarted.y-e.getYOnScreen()));
				location = new Point(locationBefore.x, e.getYOnScreen()+(locationBefore.y-whereStarted.y));
			} else if(resizeType == Cursor.E_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width+(e.getXOnScreen()-whereStarted.x), sizeBefore.height);
			} else if(resizeType == Cursor.S_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width, sizeBefore.height+(e.getYOnScreen()-whereStarted.y));
			} else if(resizeType == Cursor.N_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width, sizeBefore.height+(whereStarted.y-e.getYOnScreen()));
				location = new Point(locationBefore.x, e.getYOnScreen()+(locationBefore.y-whereStarted.y));
			} else if(resizeType == Cursor.W_RESIZE_CURSOR) {
				newSize = new Dimension(sizeBefore.width+(whereStarted.x-e.getXOnScreen()), sizeBefore.height);
				location = new Point(e.getXOnScreen()+(locationBefore.x-whereStarted.x), locationBefore.y);
			}

			if(newSize.width < getMinimumSize().width) {
				newSize.width = getMinimumSize().width;
				location.x = getLocation().x;
			}
			if(newSize.height < getMinimumSize().height) {
				newSize.height = getMinimumSize().height;
				location.y = getLocation().y;
			}

			setSize(newSize);
			setLocation(location);
			setCursor(new Cursor(resizeType));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setCursor(new Cursor(getCursor(e.getPoint())));
	}

	public int getCursor(Point p) {
		if(isResizable()) {
			if(isBetween(p, new Point(0, 0), new Point(10, 10))) {
				return Cursor.NW_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(10, 0), new Point(getWidth()-10, 10))) {
				return Cursor.N_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(getWidth()-10, 0), new Point(getWidth(), 10))) {
				return Cursor.NE_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(getWidth()-10, 10), new Point(getWidth(), getHeight()-10))) {
				return Cursor.E_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(getWidth()-10, getHeight()-10), new Point(getWidth(), getHeight()))) {
				return Cursor.SE_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(10, getHeight()-10), new Point(getWidth()-10, getHeight()))) {
				return Cursor.S_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(0, getHeight()-10), new Point(10, getHeight()))) {
				return Cursor.SW_RESIZE_CURSOR;
			} else if(isBetween(p, new Point(0, 10), new Point(10, getHeight()-10))) {
				return Cursor.W_RESIZE_CURSOR;
			}
		}
		return Cursor.DEFAULT_CURSOR;
	}

	public boolean isBetween(Point p, Point p1, Point p2) {
		return p.x > p1.x && p.x < p2.x && p.y > p1.y && p.y < p2.y;
	}
	
	public void addButton(GLRButton button) {
		getBorderPanel().buttons.add(button);
	}
	
	public void notify(String title, String text) {
		new GLNotifyFrame(title, text);
	}

}
