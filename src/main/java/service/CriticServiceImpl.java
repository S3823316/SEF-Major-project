package service;

import interfaces.CriticService;
import model.Account.Account;
import model.Account.Critic;
import org.springframework.dao.DataAccessException;

// class for running actions as Critic user
// placeholders only as not implemented
public class CriticServiceImpl extends AccountServiceImpl implements CriticService {


    @Override
    public void rate(int rate) {

    }

    @Override
    public Account signup(String accountType, String username, String password, String email, String country,
                          String gender, String firstName, String lastName, String organisation, String phone) {
        boolean approved = false;
        String sql = "INSERT INTO Account(accounttype, username, password, email, country, gender, firstname, lastname, approved,organisation,phone) values(?,?,?,?,?,?,?,?,?,?,?)";
        if (!usernameExist(username)){
            int count = template.update(sql, accountType, username, password, email, country, gender, firstName, lastName, approved,organisation,phone);
            if(count > 0){
                Account newAccount = login(accountType, username, password);
                return newAccount;
            }
        }
        return null;
    }

    @Override
    public void review(String review) {

    }

}
