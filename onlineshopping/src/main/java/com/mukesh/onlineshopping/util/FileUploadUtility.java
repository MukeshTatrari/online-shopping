package com.mukesh.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtility.class);

	private static final String ABS_PATH = "C:/Users/mukeshtr/git/online-shopping/onlineshopping/src/main/webapp/assets/images/";
	private static String REAL_PATH = "";

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {

		// to calculate the relative path

		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");

		LOGGER.info("REAL_PATH ::" + REAL_PATH);

		// to check all the directory exists.
		// if not then please create a directory for me.

		if (!new File(ABS_PATH).exists()) {
			// create a new directory
			new File(ABS_PATH).mkdirs();
		}

		if (!new File(REAL_PATH).exists()) {
			// create a new directory
			new File(REAL_PATH).mkdirs();
		}

		try {

			// server upload.
			file.transferTo(new File(REAL_PATH + code + ".jpg"));

			// project directory upload

			file.transferTo(new File(ABS_PATH + code + ".jpg"));

		} catch (IOException ex) {
			LOGGER.error("exception in uploading the file", ex);
		}

	}

}
