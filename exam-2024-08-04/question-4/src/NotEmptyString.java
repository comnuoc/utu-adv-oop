/*
 * @.classInvariant:
 *     str() != null && !str().trim().isEmpty()
 */
public record NotEmptyString(String str) {
    /*
     * @.pre: true
     * @.post: Object is constructed, throws NotEmptyStringException if string is empty
     */
    public NotEmptyString {
        if (null == str || str.trim().isEmpty()) {
            throw new NotEmptyStringException("String is null or empty.");
        }
    }

    @Override
    public String toString() {
        return str;
    }
}

class NotEmptyStringException extends RuntimeException {
    public NotEmptyStringException(String message) {
        super(message);
    }
}
