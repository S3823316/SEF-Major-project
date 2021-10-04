package interfaces;

import model.Account.Account;

// create abstract methods for use by PCoServiceImpl()
public interface PCoService extends AccountService {

    public abstract void createMovie();//parameter:Movie

    public abstract void editMovie();//parameter:Movie

    public abstract void deleteMovie();//parameter:Movie

    public abstract boolean createTVShow(String pcoid, String title, String description, String runtime, String releasedate, String language, String country, String genre, String image);//parameter:TVShow

    public abstract boolean editTVShow(int id, String title, String description, String runtime, String releasedate, String language, String country, String genre, String image);//parameter:TVShow

    public abstract boolean deleteTVShow(int id);//parameter:TVShow

    public abstract void addRole();//parameter:Role

    public abstract void editRole();//parameter:Role

    public abstract void removeRole();//parameter:Role

    public abstract void addTag();

    public abstract void RemoveRole();
}
