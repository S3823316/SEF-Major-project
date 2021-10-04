package model.Shows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Object for TVShows, based off of Movie class
public class TVShow extends Movie {
    private int seasons;
    private int episodes;

    public TVShow(){}

    public TVShow(Integer id, Integer pcoid, String title, String description, Integer runningTime, Date releaseDate, String language, String country, String genre, List<List<String>> actors, int rating, String image, int seasons, int episodes, boolean approved) {
        super(id, pcoid, title, description, runningTime, releaseDate, language, country, genre, actors, rating, image, approved);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "TVShows{" + super.toString() +
                "seasons=" + seasons +
                ", episodes=" + episodes +
                '}';
    }
}
