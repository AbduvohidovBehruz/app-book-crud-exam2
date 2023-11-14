package uz.pdp.entity;

public class Region {

    private Integer id;
    private String name;
    private String countr;

    public Region(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.countr = country;
    }

    public Integer getId() {
        return id;
    }

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
        return countr;
    }

    public void setCountry(String country) {
        this.countr = country;
    }
}
