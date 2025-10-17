package com.shaygan.libarywithtests.Location;

import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    public Location save(Location location){
        return locationRepo.save(location);
    }

    public Location getById(Long id){
        return locationRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        locationRepo.deleteById(id);
    }
}
