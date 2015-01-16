package code;

import static jooq.generated.Tables.IMAGES;
import static jooq.generated.Tables.STARS;

import java.util.ArrayList;

import jsonObjects.Star;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;


public class QueryService {
	
	public static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	
	public static Star getStar(String name) {
		Result<Record> result = create.select().from(STARS).where(STARS.NAME.equal(name)).fetch();
		if (result.size() == 1) {
			Record r = result.get(0);
			return new Star(r.getValue(STARS.NAME),
					r.getValue(STARS.BIO),
					r.getValue(STARS.TWITTER),
					r.getValue(STARS.WEBSITE),
					r.getValue(STARS.RECENT_WORK),
					null);
		}
		return new Star();
	}
	
	public static void createStar(Star star) {
		if (getStar(star.name).name == null) {
			create.insertInto(STARS, STARS.NAME, STARS.BIO, STARS.TWITTER, STARS.WEBSITE, STARS.RECENT_WORK)
			.values(star.name, star.bio, star.twitter, star.website, star.recentWork)
			.returning().fetchOne();
		}
		
	}
	
	public static ArrayList<String> getImages(String name) {
		Result<Record> result = create.select().from(IMAGES).where(IMAGES.NAME.equal(name)).fetch();
		ArrayList<String> retVal = new ArrayList<String>();
		for (Record r : result) {
			retVal.add(r.getValue(IMAGES.IMG));
		}
		return retVal;
	}
	
	public static void createImages(String name, ArrayList<String> images) {
		ArrayList<String> existingImages = getImages(name);
		for (String image : images) {
			if (!existingImages.contains(image)) {
				create.insertInto(IMAGES, IMAGES.NAME, IMAGES.IMG)
				.values(name, image)
				.returning().fetchOne();
			}
		}
	}
	
	public static void main(String[] args) {
		
	
		System.out.println(QueryService.getStar("Capri Anderson"));
		
	}
}
