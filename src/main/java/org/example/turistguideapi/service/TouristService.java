package org.example.turistguideapi.service;

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
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionByName(String name){
        return touristRepository.getAttractionByName(name);
    }

    public void addAttraction(TouristAttraction touristAttraction){
        touristRepository.addAttraction(touristAttraction);
    }

    public void updateAttraction(TouristAttraction newTourAttraction){
        TouristAttraction old = touristRepository.getAttractionById(newTourAttraction.getAttractionId());
        if (old != null){
            touristRepository.updateAttraction(newTourAttraction, old);
        }
    }
    public TouristAttraction getAttractionById(UUID id){
        return touristRepository.getAttractionById(id);
    }

    public void deleteAttraction(TouristAttraction touristAttraction){
        touristRepository.deleteAttraction(touristAttraction);
    }


}
