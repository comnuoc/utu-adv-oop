import java.util.Objects;

/*
 * @.classInvariant:
 *     getId() != null
 *     && getName() != null
 *     && getShowName() != null
 *     && getAirTime() != null
 *     && getGenre() != null
 */
public class ShowHost {
    public record ShowHostId(Object id) {
        /*
         * @.pre: true
         * @.post: Object is constructed, throws NullPointerException if id is null
         */
        public ShowHostId {
            Objects.requireNonNull(id, "Show host ID should not be null");
        }
    }

    public record ShowHostDto(ShowHostId id, NotEmptyString name, NotEmptyString showName, DateTimeRange airTime, Genre genre) {

    }

    private final ShowHostId id;
    private NotEmptyString name;
    private NotEmptyString showName;
    private DateTimeRange airTime;
    private Genre genre;

    /*
     * @.pre: true
     * @.post: object is constructed,
     *      throws NullPointerException if one of id, name, showName, airTime, genre is null.
     */
    public ShowHost(ShowHostId id, NotEmptyString name, NotEmptyString showName, DateTimeRange airTime, Genre genre) {
        this.id = Objects.requireNonNull(id, "Id should not be null");

        this.setName(name);
        this.setShowName(showName);
        this.setAirTime(airTime);
        this.setGenre(genre);
    }

    /*
     * @.pre: true
     * @.post: RESULT != null
     */
    public ShowHostId getId() {
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
     * @.post: RESULT != null
     */
    public NotEmptyString getShowName() {
        return showName;
    }

    /*
     * @.pre: true
     * @.post: RESULT != null
     */
    public DateTimeRange getAirTime() {
        return airTime;
    }

    /*
     * @.pre: true
     * @.post: RESULT != null
     */
    public Genre getGenre() {
        return genre;
    }

    /*
     * @.pre: true
     * @.post: data is updated if one of name, show name, airtime, genre is not null.
     */
    void updateData(ShowHostDto data) {
        Objects.requireNonNull(data, "Show host data should not be null");

        if (data.name() != null) {
            this.setName(data.name());
        }

        if (data.showName() != null) {
            this.setShowName(data.showName());
        }

        if (data.airTime() != null) {
            this.setAirTime(data.airTime());
        }

        if (data.genre() != null) {
            this.setGenre(data.genre());
        }
    }

    /*
     * @.pre: true
     * @.post: data is set, throws NullPointerException if name is null.
     */
    private void setName(NotEmptyString name) {
        this.name = Objects.requireNonNull(name, "Name should not be null");;
    }

    /*
     * @.pre: true
     * @.post: data is set, throws NullPointerException if showName is null.
     */
    private void setShowName(NotEmptyString showName) {
        this.showName = Objects.requireNonNull(showName, "Show name should not be null");;
    }

    /*
     * @.pre: true
     * @.post: data is set, throws NullPointerException if airTime is null.
     */
    private void setAirTime(DateTimeRange airTime) {
        this.airTime = Objects.requireNonNull(airTime, "Airtime should not be null");;
    }

    /*
     * @.pre: true
     * @.post: data is set, throws NullPointerException if genre is null.
     */
    private void setGenre(Genre genre) {
        this.genre = Objects.requireNonNull(genre, "Genre should not be null");;
    }
}
