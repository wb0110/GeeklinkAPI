package fr.geeklink.reader.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import fr.geeklink.api.GLApi;
import fr.geeklink.reader.window.button.GLRColorButton;

public class GLRBorderPanel extends GLRPanel{

	private static final long serialVersionUID = 8234426169765923138L;
	private Point locationBeforeResize;
	private Dimension sizeBeforeResize;
	private GLRFrame frame;

	public GLRBorderPanel(final GLRFrame frame) {
		this.frame = frame;
		setBackground(GLApi.getFrameColor());
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setPreferredSize(new Dimension(0, 30));

		buttons.add(new GLRColorButton(0, 6, 13, 13, true, new Color(0xA85B5B), new Color(0xDB7070)) {
			@Override
			public void onClicked() {
				frame.dispose();
				if(frame instanceof MainFrame || frame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE) {
					System.exit(0);
				}
			}
		});

		if(frame.isResizable()) {
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					frame.setLocation(e.getXOnScreen() - lastClick.x, e.getYOnScreen() - lastClick.y);
				}
			});

			buttons.add(new GLRColorButton(18, 6, 13, 13, true, new Color(0xA3A1C9), new Color(0xCBC9F0)) {
				@Override
				public void onClicked() {
					if(sizeBeforeResize != null) {
						frame.setSize(sizeBeforeResize);
						frame.setLocation(locationBeforeResize);
						sizeBeforeResize = null;
						locationBeforeResize = null;
						frame.setResizable(true);
					} else {
						sizeBeforeResize = frame.getSize();
						locationBeforeResize = frame.getLocation();
						frame.setLocation(0, 0);
						frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
						frame.setResizable(false);
					}
				}
			});

			buttons.add(new GLRColorButton(36, 6, 13, 13, true, new Color(0xC9C99D), new Color(0xF0EFB9)) {
				@Override
				public void onClicked() {
					frame.setExtendedState(JFrame.ICONIFIED);
				}
			});
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setFont(GLApi.getFont(23));
		g.setColor(GLApi.getBackgroundColor());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		g.drawString(frame.getTitle(), getWidth()/2-metrics.stringWidth(frame.getTitle())/2, 18);

		super.drawButtons(g);
	}
}
