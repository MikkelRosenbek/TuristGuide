package org.example.turistguideapi.service;

import org.example.turistguideapi.model.Tag;
import org.example.turistguideapi.model.TouristAttraction;
import org.example.turistguideapi.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction getAttractionByName(String name){
        return touristRepository.getAttractionByName(name);
    }

    public void addAttraction(TouristAttraction touristAttraction){
        touristRepository.insertAttraction(touristAttraction);
    }

    public void updateAttraction(TouristAttraction newTourAttraction){
        if (newTourAttraction != null){
            touristRepository.updateAttraction(newTourAttraction);
        }
    }
    public void deleteAttraction(TouristAttraction touristAttraction){
        touristRepository.deleteAttraction(touristAttraction);
    }

}
