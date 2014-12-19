package fr.geeklink.api;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class GLApi {

	private static Color frameColor = new Color(0x453ea0);
	private static Color backgroundColor = Color.WHITE;
	private static Font font;
	
	public static Color getFrameColor() {
		return frameColor;
	}
	
	public static Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public static Image getTexture(String texture) {
		Image image = Toolkit.getDefaultToolkit().getImage(GLApi.class.getResource("/ressources/textures/" + texture + ".png"));
        return image;
    }
	
	public static Image getTrayIconTexture() {
        return getTexture("app_favicon_20x20");
    }
	
	public static Image getIconTexture() {
        return getTexture("app_favicon_64x64");
    }
	
	public static Font getFont(int size) {
        if (font == null) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, GLApi.class.getResource("/ressources/fonts/BlackRose.TTF").openStream());
            } catch (Exception e) {
                font = new Font("TimesRoman", Font.BOLD, size);
            }
        }

        return font.deriveFont(font.getStyle(), size);
    }
}
