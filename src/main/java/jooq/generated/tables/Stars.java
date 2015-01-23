/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables;

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
public class Stars extends org.jooq.impl.TableImpl<jooq.generated.tables.records.StarsRecord> {

	private static final long serialVersionUID = -681928117;

	/**
	 * The reference instance of <code>facial.stars</code>
	 */
	public static final jooq.generated.tables.Stars STARS = new jooq.generated.tables.Stars();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.StarsRecord> getRecordType() {
		return jooq.generated.tables.records.StarsRecord.class;
	}

	/**
	 * The column <code>facial.stars.name</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>facial.stars.bio</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.String> BIO = createField("bio", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * The column <code>facial.stars.twitter</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.String> TWITTER = createField("twitter", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>facial.stars.website</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.String> WEBSITE = createField("website", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>facial.stars.recent_work</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.String> RECENT_WORK = createField("recent_work", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>facial.stars.latina</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.Byte> LATINA = createField("latina", org.jooq.impl.SQLDataType.TINYINT.defaulted(true), this, "");

	/**
	 * The column <code>facial.stars.white</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.Byte> WHITE = createField("white", org.jooq.impl.SQLDataType.TINYINT.defaulted(true), this, "");

	/**
	 * The column <code>facial.stars.asian</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.Byte> ASIAN = createField("asian", org.jooq.impl.SQLDataType.TINYINT.defaulted(true), this, "");

	/**
	 * The column <code>facial.stars.black</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.StarsRecord, java.lang.Byte> BLACK = createField("black", org.jooq.impl.SQLDataType.TINYINT.defaulted(true), this, "");

	/**
	 * Create a <code>facial.stars</code> table reference
	 */
	public Stars() {
		this("stars", null);
	}

	/**
	 * Create an aliased <code>facial.stars</code> table reference
	 */
	public Stars(java.lang.String alias) {
		this(alias, jooq.generated.tables.Stars.STARS);
	}

	private Stars(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.StarsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Stars(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.StarsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Facial.FACIAL, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.StarsRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_STARS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.StarsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.StarsRecord>>asList(jooq.generated.Keys.KEY_STARS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Stars as(java.lang.String alias) {
		return new jooq.generated.tables.Stars(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Stars rename(java.lang.String name) {
		return new jooq.generated.tables.Stars(name, null);
	}
}
