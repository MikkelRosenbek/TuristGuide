package org.example.turistguideapi.controller;

import org.example.turistguideapi.model.TouristAttraction;
import org.example.turistguideapi.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> allAttractions = touristService.getAllAttractions();
        model.addAttribute("allAttractions", allAttractions);
        return "attractions";
    }

    @GetMapping("attraction/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        return "attractionDetails";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        return "addAttraction";
    }

    @PostMapping("/add")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionById(id);
        model.addAttribute("touristAttraction", touristAttraction);
        return "updateAttraction";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttaction) {
        touristService.updateAttraction(touristAttaction);
        return "/redirect:/attractions";
    }

@GetMapping("/delete/{id}")
public String showDeleteConfirmation(@PathVariable UUID id, Model model){
        TouristAttraction touristAttraction = touristService.getAttractionById(id);
        model.addAttribute("touristAttraction",touristAttraction);
        return "deleteAttraction";
}

    @PostMapping("/delete")
    public String deleteAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.deleteAttraction(touristAttraction);
        return "redirect:/attractions";
    }

}
