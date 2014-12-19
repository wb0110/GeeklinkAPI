package fr.geeklink.api.save;

import java.io.File;

public class DirectoryManager {
	
	public static File getDirectory() {
		String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN"))
			return new File(System.getenv("APPDATA"),"/.geeklink");
		else if (OS.contains("MAC"))
			return new File(System.getProperty("user.home") + "/Library/Application Support","/.geeklink");
		else if (OS.contains("NUX"))
			return new File(System.getProperty("user.home"),"/.geeklink");
		return new File(System.getProperty("user.dir"),"/.geeklink");
	}
	
	public static File getDirectory(String name) {
		File d = new File(getDirectory(), name);
		if(!d.exists()) {
			d.mkdirs();
		}
		return d;
	}
}
