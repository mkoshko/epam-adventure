package by.koshko.cyberwikia.bean;

/**
 * Country entity class.
 */
public final class Country extends Entity {

    private static final long serialVersionUID = 1948645834600028453L;
    /**
     * Name of the country.
     */
    private String name;
    /**
     * ISO code of the country.
     */
    private String code;
    /**
     * Path to flag image file.
     */
    private String flag;

    /**
     * Returns the name of the country.
     *
     * @return the name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country.
     *
     * @param countryName Name to be set to {@link #name}.
     */
    public void setName(final String countryName) {
        name = countryName;
    }

    /**
     * Returns the iso code of the country.
     *
     * @return the iso code of the country.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the iso code of the country.
     *
     * @param countryCode Code to be set to {@link #code}.
     */
    public void setCode(final String countryCode) {
        code = countryCode;
    }

    /**
     * Returns the path to the flag image file.
     *
     * @return the path to the flag image file.
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets the path to the flag image file.
     *
     * @param countryFlag Path to be set to {@link #flag}.
     */
    public void setFlag(final String countryFlag) {
        flag = countryFlag;
    }

    public Country() {
    }

    public Country(final long id) {
        super(id);
    }
}
