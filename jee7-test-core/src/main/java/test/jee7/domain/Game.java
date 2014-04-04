package test.jee7.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Date: 8/15/11
 * Time: 9:29 PM
 */
@Entity
@Table(name = "GAMES")
public class Game {
    @Id
    @Column(name = "code")
    private String code;//no generated value since it's a natural key
    private String title;
    private String description;
    private String media;
    @Column(name = "RELEASED_YEAR")
    private Integer releasedYear;
    private Double price;
    @Column(name = "PLAYERS_NUMBER")
    private Integer playersNumber;
    @Column(name = "STORAGE_SPACE")
    private String storageSpace;
    private String rate;
    @Column(name = "AVERAGE_RATING")
    private Double averageRating;
    @ManyToOne(fetch = FetchType.EAGER)
    private Developer developer;//a developer can have many games

    public String getCode() {
        return code;
    }

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

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Integer getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(Integer playersNumber) {
        this.playersNumber = playersNumber;
    }

    public String getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(String storageSpace) {
        this.storageSpace = storageSpace;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (averageRating != null ? !averageRating.equals(game.averageRating) : game.averageRating != null)
            return false;
        if (code != null ? !code.equals(game.code) : game.code != null) return false;
        if (description != null ? !description.equals(game.description) : game.description != null) return false;
        if (developer != null ? !developer.equals(game.developer) : game.developer != null) return false;
        if (media != null ? !media.equals(game.media) : game.media != null) return false;
        if (playersNumber != null ? !playersNumber.equals(game.playersNumber) : game.playersNumber != null)
            return false;
        if (price != null ? !price.equals(game.price) : game.price != null) return false;
        if (rate != null ? !rate.equals(game.rate) : game.rate != null) return false;
        if (releasedYear != null ? !releasedYear.equals(game.releasedYear) : game.releasedYear != null) return false;
        if (storageSpace != null ? !storageSpace.equals(game.storageSpace) : game.storageSpace != null) return false;
        if (title != null ? !title.equals(game.title) : game.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (media != null ? media.hashCode() : 0);
        result = 31 * result + (releasedYear != null ? releasedYear.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (playersNumber != null ? playersNumber.hashCode() : 0);
        result = 31 * result + (storageSpace != null ? storageSpace.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (averageRating != null ? averageRating.hashCode() : 0);
        result = 31 * result + (developer != null ? developer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", media='" + media + '\'' +
                ", releasedYear=" + releasedYear +
                ", price=" + price +
                ", playersNumber=" + playersNumber +
                ", storageSpace='" + storageSpace + '\'' +
                ", rate='" + rate + '\'' +
                ", averageRating=" + averageRating +
                ", developer=" + developer +
                '}';
    }
}
