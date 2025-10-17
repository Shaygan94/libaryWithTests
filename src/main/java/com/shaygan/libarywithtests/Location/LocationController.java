package com.shaygan.libarywithtests.Location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping()
    public ResponseEntity<Location> save(@RequestBody Location location){
        return ResponseEntity.ok(locationService.save(location));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@PathVariable Long id){
        var result = locationService.getById(id);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        locationService.deleteById(id);
        return ResponseEntity.ok("Location with id: " + id + " got deleted");
    }
}
