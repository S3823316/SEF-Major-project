package service;

import interfaces.PCoService;
import model.Account.Account;
import org.springframework.dao.DataAccessException;

// class for running actions as production company user
// placeholders only as not implemented
public class PCoServiceImpl extends AccountServiceImpl implements PCoService {

    @Override
    public void createMovie() {

    }

    @Override
    public void editMovie() {

    }

    @Override
    public void deleteMovie() {

    }

    @Override
    public boolean createTVShow(String pcoid, String title, String description, String runtime, String releasedate, String language, String country, String genre, String image) {
        String sql = "INSERT INTO shows(pcoid, title, description, runtime, releasedate, language, country, genre, image, approved) values(?,?,?,?,?,?,?,?,?,?)";
        int count = template.update(sql, pcoid, title, description, runtime, releasedate, language, country, genre, image, false);
        return count > 0;
    }

    @Override
    public boolean editTVShow(int id, String title, String description, String runtime, String releasedate, String language, String country, String genre, String image) {
        String sql = "UPDATE shows SET title = ?, description = ?, runtime = ?, releasedate = ?, language = ?, country = ?, genre = ?, image = ? WHERE id = " + id;
        int count = template.update(sql, title, description, runtime, releasedate, language, country, genre, image);
        return count > 0;
    }

    @Override
    public boolean deleteTVShow(int id){
        String sql = "DELETE FROM shows WHERE id = " + id;
        int count = template.update(sql);
        return count > 0;
    }

    @Override
    public void addRole() {

    }

    @Override
    public void editRole() {

    }

    @Override
    public void removeRole() {

    }

    @Override
    public void addTag() {

    }

    @Override
    public void RemoveRole() {

    }
}
