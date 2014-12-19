package fr.geeklink.reader.window;

import fr.geeklink.api.GLApi;

public class GLRenderPanel extends GLRPanel {

	private static final long serialVersionUID = -6761483974410505869L;
	
	public GLRenderPanel() {
		setBackground(GLApi.getBackgroundColor());
	}

}
