package test.jee7.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Had to rename @ManyToMany relationship properties to "game" and "console" instead of "games" or "gameList" and so on.
 * Because eclipselink is so moron that ignores the annotation @JoinColumn(name = "") and tries to build the foreign key
 * name using the property name. So the current property names gives "game_id" and "console_id" which are the right values.
 * But if we insist in using "games" as property and @JoinColumn(name = "game_code") eclipselinks ignores this and instead
 * uses "games_id" as the foreign key column. Or "consoles and @JoinColumn(name = "console_id") eclipselink uses "consoles_id"
 * or even "consolelist_id". Why does it have to ignore @JoinColumn?
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Game> game;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Console> console;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
