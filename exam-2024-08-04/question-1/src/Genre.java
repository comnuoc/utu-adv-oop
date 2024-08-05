import java.util.Objects;

/*
 * @.classInvariant:
 *     getId() != null
 *     && getName() != null
 */
public class Genre {
    public record GenreId(Object id) {
        public GenreId {
            Objects.requireNonNull(id, "Genre Id should not be null");
        }
    }

    private final GenreId id;
    private NotEmptyString name;

    /*
     * @.pre: true
     * @.post: Genre object is constructed, throws NullPointerException if one of id, name is null.
     */
    public Genre(GenreId id, NotEmptyString name) {
        this.id = Objects.requireNonNull(id, "Genre Id should not be null");

        setName(name);
    }

    /*
     * @.pre: true
     * @.post: RESULT != null
     */
    public GenreId getId() {
        return id;
    }

    /*
     * @.pre: true
     * @.post: RESULT != null
     */
    public NotEmptyString getName() {
        return name;
    }

    /*
     * @.pre: true
     * @.post: data is set,
     *      throws NullPointerException if name is null.
     */
    private void setName(NotEmptyString name) {
        this.name = Objects.requireNonNull(name, "Genre name should not be null");
    }
}
