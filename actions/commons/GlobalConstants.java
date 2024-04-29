package commons;

import java.io.File;

public class GlobalConstants {
	public static final String WEB_ADMIN_URL =  "https://admin-demo.nopcommerce.com/login";
	public static final String WEB_USER_URL = "https://demo.nopcommerce.com/";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final String ADMIN_USERNAME = "admin@yourstoer.com";
	public static final String ADMIN_PASSWORD = "admin";
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "Download";
	public static final String DOWNLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "Upload";
	
}
