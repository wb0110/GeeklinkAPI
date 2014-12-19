package fr.geeklink.reader.window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import fr.geeklink.reader.components.GButton;

public class GLNotifyFrame extends GLRFrame{

	private static final long serialVersionUID = -4776049386066746743L;

	public GLNotifyFrame(String title, String text) {
		super(title, new Dimension(300,200), false);
		setAlwaysOnTop(true);
		
		getRenderPanel().add(new JLabel(text));
		
		final GLNotifyFrame frame = this;
		JButton button = new GButton("Ok");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		getRenderPanel().add(button);
		setVisible(true);
		setEnabled(true);
	}

}
