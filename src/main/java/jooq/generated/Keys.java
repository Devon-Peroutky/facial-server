/**
 * This class is generated by jOOQ
 */
package jooq.generated;

/**
 * A class modelling foreign key relationships between tables of the <code>facial</code> 
 * schema
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.UniqueKey<jooq.generated.tables.records.ImagesRecord> KEY_IMAGES_PRIMARY = UniqueKeys0.KEY_IMAGES_PRIMARY;
	public static final org.jooq.UniqueKey<jooq.generated.tables.records.StarsRecord> KEY_STARS_PRIMARY = UniqueKeys0.KEY_STARS_PRIMARY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.ForeignKey<jooq.generated.tables.records.ImagesRecord, jooq.generated.tables.records.StarsRecord> IMAGES_IBFK_1 = ForeignKeys0.IMAGES_IBFK_1;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class UniqueKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.UniqueKey<jooq.generated.tables.records.ImagesRecord> KEY_IMAGES_PRIMARY = createUniqueKey(jooq.generated.tables.Images.IMAGES, jooq.generated.tables.Images.IMAGES.NAME, jooq.generated.tables.Images.IMAGES.IMG);
		public static final org.jooq.UniqueKey<jooq.generated.tables.records.StarsRecord> KEY_STARS_PRIMARY = createUniqueKey(jooq.generated.tables.Stars.STARS, jooq.generated.tables.Stars.STARS.NAME);
	}

	private static class ForeignKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.ForeignKey<jooq.generated.tables.records.ImagesRecord, jooq.generated.tables.records.StarsRecord> IMAGES_IBFK_1 = createForeignKey(jooq.generated.Keys.KEY_STARS_PRIMARY, jooq.generated.tables.Images.IMAGES, jooq.generated.tables.Images.IMAGES.NAME);
	}
}
