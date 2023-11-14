package uz.pdp.service;

import uz.pdp.entity.Region;
import uz.pdp.payload.CountryDTO;

import java.util.List;

public interface CountryService {

    List<Region> getByRegion(Integer RegionId);

    CountryDTO getById(Integer id);

    CountryDTO add(CountryDTO countryDTO);

    CountryDTO edit(Integer id, CountryDTO countryDTO);

    boolean delete(Integer id);

    String read(Integer id);

    boolean serialize();

    boolean deserialize();


}
