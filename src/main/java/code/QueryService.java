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
	
	public static ArrayList<String> getImages(String name) {
		Result<Record> result = create.select().from(IMAGES).where(IMAGES.NAME.equal(name)).fetch();
		ArrayList<String> retVal = new ArrayList<String>();
		for (Record r : result) {
			retVal.add(r.getValue(IMAGES.IMG));
		}
		return retVal;
	}
	

	
	public static void main (String[] args) {
		
		
	}
}
