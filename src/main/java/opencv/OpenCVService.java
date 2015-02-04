package opencv;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacpp.opencv_highgui.*;

public class OpenCVService {

	public static CascadeClassifier faceDetector;

	public static void main(String[] args) throws IOException {
		
		// Mat image = Imgcodecs.imread("bradpitt.jpg");
		// Imgcodecs.imwrite(filename, face);
		
		
		

	}
	
	public static void initialize() {
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		faceDetector = new CascadeClassifier(
				"haarcascades/haarcascade_frontalface_alt.xml");

		if (faceDetector.empty()) {
			System.out.println("FaceDetector is empty");
		}
	}

	public static Mat getFace(Mat orig, Rect rect) {
		Mat ret = new Mat(rect.height(), rect.width(), org.bytedeco.javacpp.opencv_core.CV_8UC3);
		Raster bi = orig.getBufferedImage().getData();
		for (int row = 0; row < rect.height(); row++) {
			for (int col = 0; col < rect.width(); col++) {
				//ret.put(row, col, orig.get(rect.y() + row, rect.x() + col));
			}
		}
		return ret;
	}

	public static Rect[] detectFaces(Mat image) {
		
		//MatOfRect faceDetections = new MatOfRect();
		//faceDetector.detectMultiScale(image, faceDetections);
		//return faceDetections.toArray();
		return null;
	}
}