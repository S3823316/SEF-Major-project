package model.Shows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Object for Movies
// TVShow will extend off of this class
public class Movie {
    private Integer id;
    private Integer pcoid;
    private String title;
    private String description;
    private Integer runTime;
    private Date releaseDate;
    private String language;
    private String country;
    private String genre;
    private List<List<String>> actors;
    private int rating;
    private String image;
    private boolean approved;

    public Movie() {
    }

    public Movie(Integer id, Integer pcoid, String title, String description, Integer runTime, Date releaseDate, String language, String country, String genre, List<List<String>> actors, int rating, String image, boolean approved) {
        this.id = id;
        this.pcoid = pcoid;
        this.title = title;
        this.actors = actors;
        this.description = description;
        this.runTime = runTime;
        this.releaseDate = releaseDate;
        this.language = language;
        this.country = country;
        this.genre = genre;
        this.rating = rating;
        this.image = image;
        this.approved = approved;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id){this.id = id;}

    public Integer getPCoID(){ return pcoid; }

    public void setPCoID(Integer pcoid){this.pcoid = pcoid;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<List<String>> getActors() {
        return actors;
    }

    public void setActors(List<List<String>> actors) {
        this.actors = actors;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){this.rating = rating;}

    public String getImage(){ return image; }

    public void setImage(String image){this.image = image;}

    public boolean getApproved(){ return approved; }

    public void setApproved(boolean approved){this.approved = approved;}
    
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", runningTime=" + runTime +
                ", releaseDate=" + releaseDate +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", genre=" + genre +
                ", actors=" + actors +
                '}';
    }
}