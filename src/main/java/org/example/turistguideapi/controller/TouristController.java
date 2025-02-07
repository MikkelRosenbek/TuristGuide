package org.example.turistguideapi.controller;

import org.example.turistguideapi.model.TouristAttraction;
import org.example.turistguideapi.repository.TouristRepository;
import org.example.turistguideapi.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public ResponseEntity<List<TouristAttraction>> getAllAttractions(){
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@RequestParam String name){
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        return new ResponseEntity<>(touristAttraction,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction touristAttraction){
        TouristAttraction newTouristAttraction = touristService.addAttraction(touristAttraction);
        return new ResponseEntity<>(newTouristAttraction, HttpStatus.CREATED);
    }
    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateAttraction(@RequestBody String name){
        TouristAttraction touristAttraction = touristService.updateAttraction(name);
        return new ResponseEntity<>(touristAttraction,HttpStatus.OK);
    }


}
