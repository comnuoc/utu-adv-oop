import java.util.List;
import java.util.Objects;

public class ShowHostService {
    private final UserProvider userProvider;
    private final ShowHostRepository showHostRepository;

    /*
     * @.pre: true
     * @.post: object is constructed,
     *      throws NullPointerException if one of userProvider, showHostRepository is null.
     */
    public ShowHostService(UserProvider userProvider, ShowHostRepository showHostRepository) {
        this.userProvider = Objects.requireNonNull(userProvider, "User provider should not be null");;
        this.showHostRepository = Objects.requireNonNull(showHostRepository, "Show host repository should not be null");;
    }

    /*
     * @.pre: true
     * @.post: Show hosts are returned based on the search criteria,
     *      throws SecurityException if user is not allowed to search.
     */
    public List<ShowHost> search(ShowHost.ShowHostDto criteria) {
        Objects.requireNonNull(criteria, "Criteria should not be null");

        User user = userProvider.getUser();

        if (!user.canSearch()) {
            throw new SecurityException("User is not allowed to search show hosts.");
        }

        return showHostRepository.search(criteria);
    }

    /*
     * @.pre: true
     * @.post: Show host is updated,
     *      throws SecurityException if user is not allowed to update.
     */
    public void update(ShowHost.ShowHostDto dto) {
        Objects.requireNonNull(dto, "Show host data should not be null");
        Objects.requireNonNull(dto.id(), "Show host ID should not be null");

        ShowHost showHost = showHostRepository.find(dto.id());
        User user = userProvider.getUser();

        if (!user.canUpdate(showHost)) {
            throw new SecurityException("User is not allowed to update show host.");
        }

        showHost.updateData(dto);

        showHostRepository.update(showHost);
    }
}
