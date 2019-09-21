package by.koshko.cyberwikia.bean;

import java.util.Objects;

/**
 * Game entity class.
 */
public final class Game extends Entity {
    /**
     * Title of the game.
     */
    private String title;
    /**
     * Path to the icon image file.
     */
    private String iconFile;

    /**
     * Returns the title of the game.
     *
     * @return the title of the game.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the game.
     *
     * @param gameTitle Title to be set to {@link #title}.
     */
    public void setTitle(final String gameTitle) {
        title = gameTitle;
    }

    /**
     * Returns the path to the game icon image.
     *
     * @return path to the game icon image.
     */
    public String getIconFile() {
        return iconFile;
    }

    /**
     * Sets the path to the game icon image.
     *
     * @param iconGameFile path to be set to {@link #iconFile}.
     */
    public void setIconFile(final String iconGameFile) {
        iconFile = iconGameFile;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return getId() == game.getId()
               && getTitle().equals(game.getTitle())
               && Objects.equals(getIconFile(), game.getIconFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getIconFile(), getId());
    }

    @Override
    public String toString() {
        return "Game{"
               + "title='" + title + '\''
               + ", iconFile='" + iconFile + '\''
               + '}';
    }
}