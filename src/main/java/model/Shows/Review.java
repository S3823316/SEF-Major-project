package model.Shows;


import java.sql.Date;

public class Review {
    private int reviewid;
    private int showid;
    private int userID;
    private String review;
    private int rating;
    private Date date;
    private boolean approved;

    public Review(int reviewid, int showid, int userID, String review, int rating, Date date, boolean approved) {
        this.reviewid = reviewid;
        this.showid = showid;
        this.userID = userID;
        this.review = review;
        this.rating = rating;
        this.date = date;
        this.approved = approved;
    }

    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewid=" + reviewid +
                ", showid=" + showid +
                ", userID=" + userID +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", approved=" + approved +
                '}';
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
