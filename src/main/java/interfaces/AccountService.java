package interfaces;


import model.Account.Account;
import model.Shows.TVShow;

import java.util.List;
import java.util.Map;

// create abstract methods for use by AccountServiceImpl()
public interface AccountService {
    public abstract void search();

    public abstract Account login(String accountType, String username, String password);

    public abstract void logout();

    public abstract List<TVShow> view(String column, String search);

    public abstract List<Map<String, Object>> viewReviews(String column, int search);

    public abstract Account signup(String pcoid, String accountType, String username, String password, String email, String country, String gender, String firstName, String lastName, String organisation, String phone);

    public abstract boolean review(int userID, int showID, int rating, String review, String date,boolean approved);

    public abstract String viewPCo(int id);

    public abstract List<Map<String, Object>> viewAllPCo();

    public abstract List<TVShow> getUnapprovedShows();

    public abstract List<TVShow> getApprovedShows();
}
