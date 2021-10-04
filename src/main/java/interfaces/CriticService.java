package interfaces;

import model.Account.Account;
import model.Account.Critic;

// create abstract methods for use by CriticServiceImpl()
public interface CriticService extends AccountService{


    public abstract void rate(int rate);
    public abstract Account signup(String accountType, String username, String password, String email, String country,
                                  String gender, String firstName, String lastName,  String organisation, String phone);

    public abstract void review(String review);
}
