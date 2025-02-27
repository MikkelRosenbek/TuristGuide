package org.example.turistguideapi.repository;

import org.example.turistguideapi.model.Tag;
import org.example.turistguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractionList = new ArrayList<>();



    public TouristRepository() {
        TouristAttraction tivoli = new TouristAttraction("Tivoli", "Forlystelsespark i København","København",List.of("Børnevenlig"));
        TouristAttraction denLilleHavfrue = new TouristAttraction("Den Lille Havfrue", "Kendt statue på Langelinje, København","København",List.of("Kunst"));
        TouristAttraction skraldebjerget = new TouristAttraction("Skraldebjerget", "Bakke lokaliseret i Herning","Herning",List.of("Børnevenlig","Natur","Gratis"));

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
    public List<Tag> getAttractionsTagByName(String name){
        for (TouristAttraction attraction : attractionList){
            if (attraction.getName().equalsIgnoreCase(name)){
                return attraction.getTags();
            }
        }
        return Collections.emptyList();
    }

    public void addAttraction(TouristAttraction touristAttraction){
         attractionList.add(touristAttraction);
    }

    public void updateAttraction(TouristAttraction newTourAttraction){
        TouristAttraction existing = getAttractionByName(newTourAttraction.getName());
        existing.setDescription(newTourAttraction.getDescription());
        existing.setLokation(newTourAttraction.getLokation());
        existing.setTags(newTourAttraction.getTags());
    }

    public void deleteAttraction(TouristAttraction touristAttraction) {
        TouristAttraction toDelete = getAttractionByName(touristAttraction.getName());
        attractionList.remove(toDelete);
    }



}
