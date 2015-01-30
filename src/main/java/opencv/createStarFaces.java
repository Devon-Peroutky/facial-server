package opencv;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import code.ImageService;

public class createStarFaces {
	
	public static CascadeClassifier faceDetector;
	
	public static void main(String[] args) {
		OpenCVService.initialize();
		
		File starsDir = new File("images/stars");
		int mffCount = 0;
		int nffCount = 0;
		for (File starPath : starsDir.listFiles()) {
			File fullPics = new File(starPath + "/full");
			int i = 0;
			for (File image : fullPics.listFiles()) {
				Mat orig = Imgcodecs.imread(image.getPath());
				Rect[] faces = OpenCVService.detectFaces(orig);
				if (faces.length > 1) {
					System.out.println("MFF;" + faces.length + ";" + image.getPath());
					mffCount++;
				} else if (faces.length == 0) {
					System.out.println("NFF;" + image.getPath());
					nffCount++;
				} else if (faces.length == 1){
					Mat face = OpenCVService.getFace(orig, faces[0]);
					String path = starPath.getPath() + "/faces/"+ i + ".png";
					ImageService.writeFile(path, face);
				}
				
				
				
				i++;
			}
		}
		System.out.println("MFF:" + mffCount);
		System.out.println("NFF:" + nffCount);
	}
	
	
	
}
