/*
 * @.classInvariant:
 *      month() > 0  && month() <= 12
 */

public record Month(int month) {
    /*
     * @.pre: true
     * @.post: Object is constructed
     *      throws InvalidMonthException if month <= 0 || month > 12
     */
    public Month {
        if (month <= 0 || month > 12) {
            throw new InvalidMonthException("Invalid month");
        }
    }
}

class InvalidMonthException extends RuntimeException {
    public InvalidMonthException(String message) {
        super(message);
    }
}