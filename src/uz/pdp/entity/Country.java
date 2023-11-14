package uz.pdp.entity;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class Country {

    private Integer id;
    private String name;

    private URL url;

    private File filePath;

    private LocalDate establishetmentDate;

    private Double square;

    private List<Region> regions;

    public Country(Integer id, String name, URL url, File filePath, LocalDate establishetmentDate, Double square, List<Region> regions) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.filePath = filePath;
        this.establishetmentDate = establishetmentDate;
        this.square = square;
        this.regions = regions;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public File getFilePath() {
        return filePath;
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public LocalDate getEstablishetmentDate() {
        return establishetmentDate;
    }

    public void setEstablishetmentDate(LocalDate establishetmentDate) {
        this.establishetmentDate = establishetmentDate;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
