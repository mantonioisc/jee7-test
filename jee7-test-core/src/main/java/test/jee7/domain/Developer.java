package test.jee7.domain;

import javax.persistence.*;

/**
 * Date: 8/15/11
 * Time: 8:13 PM
 */
@Entity
@Table(name = "DEVELOPERS")
public class Developer {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String country;
    private String url;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (country != null ? !country.equals(developer.country) : developer.country != null) return false;
        if (id != null ? !id.equals(developer.id) : developer.id != null) return false;
        if (name != null ? !name.equals(developer.name) : developer.name != null) return false;
        if (url != null ? !url.equals(developer.url) : developer.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
