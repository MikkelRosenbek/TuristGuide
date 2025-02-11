package org.example.turistguideapi.service;

import org.example.turistguideapi.model.TouristAttraction;
import org.example.turistguideapi.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionByName(String name){
        return touristRepository.getAttractionByName(name);
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction){
        return touristRepository.addAttraction(touristAttraction);
    }

    public TouristAttraction updateAttraction(TouristAttraction newTourAttraction){
        TouristAttraction old = touristRepository.getAttractionByName(newTourAttraction.getName());
        if (old != null){
            return touristRepository.updateAttraction(newTourAttraction, old);
        }
        return null;
    }



}
