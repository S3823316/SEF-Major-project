package interfaces;

import model.Account.Account;
import model.Shows.Movie;
import model.Shows.Review;

import java.util.List;

// create abstract methods for use by AdminServiceImpl()
public interface AdminService extends AccountService {
    public abstract boolean deleteUser(int id);

    public abstract void addUser(Account user);

    public abstract void editUser(Account user);

    public abstract boolean upgradeUser(int id);

    public abstract boolean downgradeUser(int id);

    public abstract List<Account> getUnapprovedUsers();

    public abstract List<Account> getApprovedUsers();

    public abstract Account getUsers(int userid);

    public abstract boolean approveUser(int id);

    public abstract boolean disableUser(int id);

    public abstract boolean approveReview(int reviewid);

    public abstract boolean disableReview(int reviewid);

    public abstract boolean deleteReview(int reviewid);

    public abstract List<Review> getUnapprovedReview();

    public abstract List<Review> getApprovedReview();

    public abstract Movie getShow(int showid);

    public abstract boolean approveShow(int id);

    public abstract boolean disableShow(int id);

    public abstract boolean deleteShow(int id);
}
