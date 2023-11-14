package uz.pdp.service;

import uz.pdp.entity.Country;
import uz.pdp.entity.Region;
import uz.pdp.payload.RegionDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class RegionServiceImpl implements RegionService {

    private static RegionServiceImpl instance;

    private RegionServiceImpl() {
    }

   private static Lock lock = new ReentrantLock();

    public static RegionServiceImpl getInstance(){

        if (Objects.isNull(instance)){

            lock.lock();

            if (Objects.isNull(instance)){

                instance = new RegionServiceImpl();

                lock.unlock();

            }


        }

        return instance;
    }

public List<Region> regions = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<RegionDTO> all() {

        return regions
                .stream()
                .map(region -> new RegionDTO(region.getId(),region.getName(),region.getCountry())).collect(Collectors.toList());

    }

    @Override
    public RegionDTO add(RegionDTO regionDTO) {

        int id = regions.size() + 1;

        Region region = new Region(id,regionDTO.getName(),regionDTO.getCountry());

        regions.add(region);

        regionDTO.setId(id);

        return regionDTO;
    }

    @Override
    public RegionDTO edit(Integer id, RegionDTO regionDTO) {

       Region region =  regions
                .stream()
                .filter(region1 -> Objects.equals(regionDTO.getId(),id)).findFirst().orElseThrow(()->new RuntimeException("Xato id"));

      region.setName(regionDTO.getName());

      regionDTO.setId(region.getId());

      return regionDTO;
    }

    @Override
    public String delete(Integer id) {

        regions.removeIf(region -> Objects.equals(region.getId(),id));

        return "o'chirildi";
    }

    @Override
    public Region getByIdOrElseThrow(Integer id) {

        return regions
                .stream()
                .filter(region -> Objects.equals(region.getId(),id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Xato id"));

    }


}
