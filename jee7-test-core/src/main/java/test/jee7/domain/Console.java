package test.jee7.domain;


import javax.persistence.*;

/**
 * Date: 8/15/11
 * Time: 8:10 PM
 */
@Entity
@Table(name = "CONSOLES")
public class Console {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "FULL_NAME")
    private String fullName;
    private String description;
    private Double price;
    @Column(name = "MAX_PLAYERS")
    private Integer maxPlayers;
    private boolean wireless;
    private boolean network;
    private boolean hd;
    private String media;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public boolean isNetwork() {
        return network;
    }

    public void setNetwork(boolean network) {
        this.network = network;
    }

    public boolean isHd() {
        return hd;
    }

    public void setHd(boolean hd) {
        this.hd = hd;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Console console = (Console) o;

        if (hd != console.hd) return false;
        if (network != console.network) return false;
        if (wireless != console.wireless) return false;
        if (description != null ? !description.equals(console.description) : console.description != null) return false;
        if (company != null ? !company.equals(console.company) : console.company != null) return false;
        if (fullName != null ? !fullName.equals(console.fullName) : console.fullName != null) return false;
        if (id != null ? !id.equals(console.id) : console.id != null) return false;
        if (maxPlayers != null ? !maxPlayers.equals(console.maxPlayers) : console.maxPlayers != null) return false;
        if (media != null ? !media.equals(console.media) : console.media != null) return false;
        if (name != null ? !name.equals(console.name) : console.name != null) return false;
        if (price != null ? !price.equals(console.price) : console.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (maxPlayers != null ? maxPlayers.hashCode() : 0);
        result = 31 * result + (wireless ? 1 : 0);
        result = 31 * result + (network ? 1 : 0);
        result = 31 * result + (hd ? 1 : 0);
        result = 31 * result + (media != null ? media.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", maxPlayers=" + maxPlayers +
                ", wireless=" + wireless +
                ", network=" + network +
                ", hd=" + hd +
                ", media='" + media + '\'' +
                ", company=" + company +
                '}';
    }
}
