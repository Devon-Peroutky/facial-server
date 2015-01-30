package opencv;

import java.io.IOException;
import java.util.ArrayList;

import jsonObjects.Star;
import code.ImageService;
import code.QueryService;

public class DownloadStarImages {
	public static void main(String[] args) throws IOException {
		ArrayList<Star> stars = QueryService.loadStars();
		for (Star star : stars) {
			for (int i = 0; i < star.images.size(); i++) {
				// should do .png for faces
				// .jpg now to save space
				ImageService.writeFileFromUrl(star.images.get(i), "images/stars/" + star.name + "/full/"+i+".jpg");
			}
			System.out.println("created images for " + star.name);
		}
	}
}
