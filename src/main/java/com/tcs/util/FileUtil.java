package com.tcs.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
private static final String imageDirectory="ShoppingCartImages";
private static String rootPath=System.getProperty("catalina.home");
/*	public static boolean copyFile(MultipartFile file,String fileName) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + fileName);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	
		
	}*/
public static boolean fileCopyNIO(MultipartFile file,String fileName) {
	File dest=new File(rootPath + File.separator +imageDirectory+File.separator+fileName);
	
	if(!dest.exists()) {
		dest.mkdirs();
	}
	try {
		file.transferTo(dest);
		return true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return false;
}
}
