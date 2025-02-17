package org.example.turistguideapi.repository;

import org.example.turistguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractionList = new ArrayList<>();



    public TouristRepository() {
        TouristAttraction tivoli = new TouristAttraction("Tivoli", "Forlystelsespark i København");
        tivoli.setAttractionId(UUID.randomUUID());
        TouristAttraction denLilleHavfrue = new TouristAttraction("Den Lille Havfrue", "Kendt statue på Langelinje, København");
        denLilleHavfrue.setAttractionId(UUID.randomUUID());
        TouristAttraction skraldebjerget = new TouristAttraction("Skraldebjerg", "Bakke lokaliseret i Herning");
        skraldebjerget.setAttractionId(UUID.randomUUID());

        attractionList.add(tivoli);
        attractionList.add(denLilleHavfrue);
        attractionList.add(skraldebjerget);

    }

    public List<TouristAttraction> getAllAttractions(){
        return attractionList;
    }

    public TouristAttraction getAttractionByName(String name) {
        for (TouristAttraction attraction : attractionList) {
            if (attraction.getName().equalsIgnoreCase(name)){
                return attraction;
            }
        }
        return null;
    }
    public TouristAttraction getAttractionById(UUID id) {
        for (TouristAttraction attraction : attractionList) {
            if (attraction.getAttractionId().equals(id)){
                return attraction;
            }
        }
        return null;
    }

    public void addAttraction(TouristAttraction touristAttraction){
        touristAttraction.setAttractionId(UUID.randomUUID());
         attractionList.add(touristAttraction);
    }

    public void updateAttraction(TouristAttraction newTourAttraction, TouristAttraction oldTourAttraction){
        attractionList.remove(oldTourAttraction);
        attractionList.add(newTourAttraction);
    }

    public void deleteAttraction(TouristAttraction touristAttraction) {
        TouristAttraction toDelete = getAttractionByName(touristAttraction.getName());
        attractionList.remove(toDelete);
    }



}
