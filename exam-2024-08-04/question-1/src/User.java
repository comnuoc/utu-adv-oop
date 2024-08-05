public interface User {
    /*
     * @.pre: true
     * @.post: whether user is a staff or not
     */
    boolean isStaff();

    /*
     * @.pre: true
     * @.post: RESULT == true
     */
    default boolean canSearch() {
        return true;
    }

    /*
     * @.pre: true
     * @.post: RESULT == true if user is a staff, else RESULT == false
     */
    default boolean canUpdate(ShowHost showHost) {
        return this.isStaff();
    }
}
