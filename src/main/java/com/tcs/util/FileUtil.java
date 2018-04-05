package com.tcs.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component//will create singleton instance and instance name is fileUtil
public class FileUtil {
	@Autowired
	private HttpSession httpSession;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	//private static String imageDirectory = "G:\\eclipse oxy\\391\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\images";
	 private static String rootPath = System.getProperty("user.dir");

//private static final String imageDirectory="ShoppingCartImages";
//private static String rootPath=System.getProperty("catalina.home");
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
public  boolean fileCopyNIO(MultipartFile file,String fileName) {
	File dest=new File(rootPath+File.separator  + fileName);
	//System.out.println("where it is uploading?"+dest.getAbsolutePath());
	
	/*if(!dest.exists()) {
		dest.mkdirs();
	}*/
	try {
		file.transferTo(dest);
		return true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return false;
}
}
