import java.util.List;

public interface ShowHostRepository {
    /*
     * @.pre: id != null
     * @.post: RESULT != null,
     *      throws ShowHostNotFoundException if show host is not found
     */
    ShowHost find(ShowHost.ShowHostId id) throws ShowHostNotFoundException;

    /*
     * @.pre: criteria != null
     * @.post: Show hosts are returned based on the search criteria
     */
    List<ShowHost> search(ShowHost.ShowHostDto criteria);

    /*
     * @.pre: showHost != null
     * @.post: showHost is updated
     */
    void update(ShowHost showHost);
}

class ShowHostNotFoundException extends RuntimeException {
    public ShowHostNotFoundException(String message) {
        super(message);
    }
}
