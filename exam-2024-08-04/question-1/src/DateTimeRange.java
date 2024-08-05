import java.time.LocalDateTime;
import java.util.Objects;

/*
 * @.classInvariant:
 *     start() != null
 *     && end() != null
 *     && end() > start()
 */
public record DateTimeRange(LocalDateTime start, LocalDateTime end) {
    /*
     * @.pre: true
     * @.post: Object is constructed,
     *     throws NullPointerException if one of start, end is null
     *     throws InvalidDateTimeRangeException if end <= start
     */
    public DateTimeRange {
        Objects.requireNonNull(start, "Start time should not be null");
        Objects.requireNonNull(end, "End time should not be null");

        if (end.isBefore(start) || end.isEqual(start)) {
            throw new InvalidDateTimeRangeException("Invalid date time range");
        }
    }
}

class InvalidDateTimeRangeException extends RuntimeException {
    public InvalidDateTimeRangeException(String message) {
        super(message);
    }
}