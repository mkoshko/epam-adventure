package by.koshko.cyberwikia.bean;

import java.util.Objects;

/**
 * Tournament tier entity class.
 */
public final class TournamentTier extends Entity {
    /**
     * Tier name.
     */
    private String name;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TournamentTier that = (TournamentTier) o;
        return Objects.equals(that.getId(), getId())
               && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getId());
    }
}
