package code;

import static jooq.generated.Tables.IMAGES;
import static jooq.generated.Tables.STARS;
import static jooq.generated.Tables.INSTAGRAM_USERS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import jsonObjects.Star;
import jsonObjects.StarImage;
import logging.InstagramUser;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.google.common.collect.Sets;


public class QueryService {
	
	public static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	
	
	// STARS SECTION
	public static Star getStar(String name) {
		Result<Record> result = create.select().from(STARS).where(STARS.NAME.equal(name)).fetch();
		if (result.size() == 1) {
			Record r = result.get(0);
			return getStar(r);
		}
		return new Star();
	}
	
	public static Star getStar(Record r) {
		return new Star(r.getValue(STARS.NAME),
				r.getValue(STARS.BIO),
				r.getValue(STARS.TWITTER),
				r.getValue(STARS.WEBSITE),
				r.getValue(STARS.RECENT_WORK),
				null);
	}
	
	public static HashSet<Star> loadStars() {
		Result<Record> result = create.select().from(STARS).fetch();
		HashSet<Star> stars = Sets.newHashSet();
		for (Record r : result) {
			Star s = getStar(r);
			s.images = getImages(s.name);
			stars.add(s);
		}
		return stars;
	}
	
	public static void createStar(Star star) {
		if (getStar(star.name).name == null) {
			create.insertInto(STARS, STARS.NAME, STARS.BIO, STARS.TWITTER, STARS.WEBSITE, STARS.RECENT_WORK)
			.values(star.name, star.bio, star.twitter, star.website, star.recentWork)
			.returning().fetchOne();
		}
		
	}
	
	// Image Section
	
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
	
	public static HashSet<StarImage> loadStarImages() {
		HashSet<StarImage> starImages = Sets.newHashSet();
		Result<Record> records = create.select().from(IMAGES).where(IMAGES.FACE_ID.charLength().greaterThan(0)).fetch();
		for (Record r : records) {
			starImages.add(getStarImage(r));
		}
		return starImages;
	}
	
	public static StarImage getStarImage(String faceId) {
		Result<Record> records = create.select().from(IMAGES).where(IMAGES.FACE_ID.equal(faceId)).fetch();
		if (records.size() == 1) {
			return getStarImage(records.get(0));
		}
		return null;
	}
	
	public static StarImage getStarImage(Record r) {
		return new StarImage(r.getValue(IMAGES.NAME),
				r.getValue(IMAGES.IMG),
				r.getValue(IMAGES.FACE_ID));
	}
	
	
	// InstagramUser Section
	public static void logInstagramUser(InstagramUser instagramUser) {
		create.insertInto(INSTAGRAM_USERS, INSTAGRAM_USERS.USERNAME, INSTAGRAM_USERS.VISITS)
		.values(instagramUser.username, 1)
		.onDuplicateKeyUpdate()
		.set(INSTAGRAM_USERS.VISITS, INSTAGRAM_USERS.VISITS.add(1))
		.execute();
	}
	
	public static void main(String[] args) {
		
	
		System.out.println(QueryService.getStar("Capri Anderson"));
		
	}
}
