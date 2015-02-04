package code;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_highgui;

public class ImageService {
	public static void writeFileFromUrl(String urlString, String dest) throws IOException {
		URL url = new URL(urlString);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
		   out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		
		makeDirs(dest);
		
		FileOutputStream fos = new FileOutputStream(dest);
		fos.write(response);
		fos.close();
		
	}
	
	/**
	 * Removes file extension if it exists
	 * @param file
	 */
	public static void makeDirs(String file) {
		String dest = file;
		if (file.contains(".") && file.contains("/")) {
			int slashIndex = getLastSlash(file);
			dest = file.substring(0, slashIndex);
		}
		File createDirs = new File(dest);
		createDirs.mkdirs();
	}
	
	private static int getLastSlash(String s) {
		for (int i = s.length() - 1; i>= 0; i--) {
			if (s.charAt(i) == '/' || s.charAt(i) == '\\') {
				return i;
			}
		}
		return -1;
	}
	
	public static void writeFile(String path, Mat m) {
		makeDirs(path);
		org.bytedeco.javacpp.opencv_highgui.imwrite(path, m);
	}
}
