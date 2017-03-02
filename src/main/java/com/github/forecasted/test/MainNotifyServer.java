package com.github.forecasted.test;

import java.io.File;
import java.io.IOException;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;

public class MainNotifyServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		args = new String[] { "/tmp/tmp" };
		String dir = new File(args.length == 0 ? "." : args[0]).getCanonicalFile().getAbsolutePath();
		JNotify.addWatch(dir, JNotify.FILE_ANY, true, new JNotifyListener() {
			public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
				System.out.println("renamed " + rootPath + " : " + oldName + " -> " + newName);
			}

			public void fileModified(int wd, String rootPath, String name) {
				System.out.println("modified " + rootPath + " : " + name);
			}

			public void fileDeleted(int wd, String rootPath, String name) {
				System.out.println("deleted " + rootPath + " : " + name);
			}

			public void fileCreated(int wd, String rootPath, String name) {
				System.out.println("created " + rootPath + " : " + name);
			}
		});

		System.out.println("Monitoring " + dir + ", ctrl+c to stop");
		while (true) {
			Thread.sleep(10000);
		}
	}
}
