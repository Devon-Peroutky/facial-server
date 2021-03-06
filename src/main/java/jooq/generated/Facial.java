/**
 * This class is generated by jOOQ
 */
package jooq.generated;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Facial extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -1432422572;

	/**
	 * The reference instance of <code>facial</code>
	 */
	public static final Facial FACIAL = new Facial();

	/**
	 * No further instances allowed
	 */
	private Facial() {
		super("facial");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			jooq.generated.tables.Images.IMAGES,
			jooq.generated.tables.InstagramUsers.INSTAGRAM_USERS,
			jooq.generated.tables.RecognitionImages.RECOGNITION_IMAGES,
			jooq.generated.tables.Stars.STARS);
	}
}
