public interface UserProvider {
    /*
     * @.pre: true
     * @.post: RESULT != null,
     *      throws UserNotFoundException if there is no user
     */
    User getUser() throws UserNotFoundException;
}

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}