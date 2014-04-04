package test.jee7.domain;

import javax.persistence.*;

/**
 * Date: 8/15/11
 * Time: 7:49 PM
 */
@Entity
@Table(name = "COMPANIES")
public class Company {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "FULL_NAME")
    private String fullName;
    private String country;
    private String url;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

        Company company = (Company) o;

        if (country != null ? !country.equals(company.country) : company.country != null) return false;
        if (fullName != null ? !fullName.equals(company.fullName) : company.fullName != null) return false;
        if (id != null ? !id.equals(company.id) : company.id != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (url != null ? !url.equals(company.url) : company.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
