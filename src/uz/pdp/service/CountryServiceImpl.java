package uz.pdp.service;

import uz.pdp.entity.Country;
import uz.pdp.entity.Region;
import uz.pdp.payload.CountryDTO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountryServiceImpl implements CountryService {

    List<Country> countries = Collections.synchronizedList(new ArrayList<>());
    private static CountryServiceImpl instance;
    List<CountryDTO> list = Collections.synchronizedList(new ArrayList<>());

    public static Lock lock = new ReentrantLock();

    public CountryServiceImpl() {
    }

    public static CountryServiceImpl getInstance() {

        if (Objects.isNull(instance)) {

            lock.lock();

            if (Objects.isNull(instance)) {

                instance = new CountryServiceImpl();

                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public List<Region> getByRegion(Integer countryId) {

        Optional<CountryDTO> country = Optional.ofNullable(countries
                .stream()
                .filter(country1 -> Objects.equals(country1.getId(), countryId)).map(country1 -> toDo(country1)).findFirst().orElseThrow(() -> new RuntimeException("bunaqa id yoq")));

        List<Region> region = country.get().getRegions();

        return region;
    }

    private CountryDTO toDo(Country country1) {

        return new CountryDTO(country1.getId(), country1.getName(), country1.getUrl(), country1.getFilePath(), country1.getEstablishetmentDate(), country1.getSquare(), country1.getRegions());


    }

    @Override
    public CountryDTO getById(Integer id) {

        return countries
                .stream()
                .filter(country -> Objects.equals(country.getId(), id)).map(this::toDo).findFirst().orElseThrow(() -> new RuntimeException("xato id"));

    }

    @Override
    public CountryDTO add(CountryDTO countryDTO) {

        int id = countries.size() + 1;

        RegionServiceImpl regionService = RegionServiceImpl.getInstance();

        Region region = regionService.regions
                .stream()
                .filter(region1 -> Objects.equals(region1.getId(), countryDTO.getId())).findFirst().orElseThrow(() -> new RuntimeException("Xato id"));

        Country country = new Country(countryDTO.getId(), countryDTO.getName(), countryDTO.getUrl(), countryDTO.getFilePath(), countryDTO.getEstablishetmentDate(), countryDTO.getSquare(), countryDTO.getRegions());

        countries.add(country);

        return toDo(country);
    }

    @Override
    public CountryDTO edit(Integer id, CountryDTO countryDTO) {

        Country country = countries.stream()
                .filter(country1 -> Objects.equals(country1.getId(), id)).findFirst().orElseThrow(() -> new RuntimeException("Xato kod"));

        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        country.setEstablishetmentDate(countryDTO.getEstablishetmentDate());
        country.setSquare(countryDTO.getSquare());
        country.setFilePath(countryDTO.getFilePath());
        country.setUrl(countryDTO.getUrl());

        RegionService regionService = RegionServiceImpl.getInstance();

        Region region = regionService.getByIdOrElseThrow(countryDTO.getId());

        country.setRegions((List<Region>) region);

        return toDo(country);
    }

    @Override
    public boolean delete(Integer id) {


        Country country = countries.stream()
                .filter(country1 -> Objects.equals(country1.getId(), id)).findFirst().orElseThrow(() -> new RuntimeException("Xato id"));

        countries.remove(country);

        return true;
    }

    @Override
    public String read(Integer id) {

        Country country = countries
                .stream()
                .filter(country1 -> Objects.equals(country1.getId(), id)).findFirst().orElseThrow(() -> new RuntimeException("Xato son"));

        Path path = Paths.get(String.valueOf(country.getFilePath()));

        return readFileViaFileInputStream(path);

    }

    @Override
    public boolean serialize() {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("db/file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean deserialize() {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("db/file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private String readFileViaFileReader(Path path) {

        try (Reader reader = new FileReader(path.toFile())) {

            StringBuilder stringBuilder = new StringBuilder();

            int number = reader.read();

            while (number != -1) {

                stringBuilder.append((char) number);

                number = reader.read();

            }

            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private String readFileViaFileInputStream(Path path) {
        try(InputStream inputStream = new FileInputStream(path.toFile())) {

            byte[] bytes = inputStream.readAllBytes();

            return new String(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
