package fr.geeklink.reader.window;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import fr.geeklink.api.GLApi;

public class GLRMainPanel extends JPanel{

	private static final long serialVersionUID = 2321691707787002460L;
	
	private GLRenderPanel render;
	private GLRBorderPanel border;

	public GLRMainPanel(GLRFrame frame, GLRenderPanel panel) {
		setLayout(new BorderLayout());
		render = panel;
        add(panel, BorderLayout.CENTER);
        border = new GLRBorderPanel(frame);
        add(border, BorderLayout.PAGE_START);
        setBorder(new LineBorder(GLApi.getFrameColor(), 5));
	}
	
	public GLRBorderPanel getBorderPanel() {
		return border;
	}
	
	public GLRenderPanel getRenderPanel() {
		return render;
	}
}
