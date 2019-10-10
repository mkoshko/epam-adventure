package by.koshko.cyberwikia.bean;

import java.io.Serializable;

/**
 * Superclass for all entity classes of this project. All subclasses are
 * following the java bean convention.
 */
public abstract class Entity implements Serializable {

    /**
     * Entities id.
     */
    private long id;

    /**
     * Returns the id value of the entity.
     *
     * @return the id value of the entity.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the {@link #id} value.
     *
     * @param entityId New value to be set to {@link #id}.
     */
    public void setId(final long entityId) {
        id = entityId;
    }
}
