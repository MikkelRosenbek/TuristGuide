package org.example.turistguideapi.repository;

import org.example.turistguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractionList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    public TouristRepository() {
        attractionList.add(new TouristAttraction("Tivoli", "Forlystelsespark i København"));
        attractionList.add(new TouristAttraction("Den Lille Havfrue", "Kendt statue på Langelinje, København"));
    }

    public List<TouristAttraction> getAllAttractions(){
        return attractionList;
    }

    public TouristAttraction getAttractionByName(String name) {
       TouristAttraction current = null;
        for (TouristAttraction attraction : attractionList) {
            if (attraction.getName().equals(name)){
                current = attraction;
            }
        }
        return current;
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction){
         attractionList.add(touristAttraction);
         return touristAttraction;
    }

    public TouristAttraction updateAttraction(String name){
        TouristAttraction attractionToUpdate = getAttractionByName(name);
        System.out.println("Nyt navn");
        attractionToUpdate.setName(scanner.nextLine());
        System.out.println("Ny beskrivelse");
        attractionToUpdate.setDescription(scanner.nextLine());

        System.out.println(attractionToUpdate.getName() + "\n" + attractionToUpdate.getDescription());
        return attractionToUpdate;
    }
}
