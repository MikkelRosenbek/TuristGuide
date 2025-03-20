package org.example.turistguideapi.controller;

import org.example.turistguideapi.model.Tag;
import org.example.turistguideapi.model.TouristAttraction;
import org.example.turistguideapi.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> allAttractions = touristService.getAllAttractions();
        model.addAttribute("allAttractions", allAttractions);
        return "listAttractions";
    }

    @GetMapping("/attractions/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        return "detailsAttraction";
    }

    @GetMapping("/attractions/tags/{name}")
    public String getTagsByName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        return "tags";
    }

    @GetMapping("/attractions/add")
    public String showAddForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("tagsList", Tag.values());
        return "addAttraction";
    }

    @PostMapping("/attractions/save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/update/{name}")
    public String showUpdateForm(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        model.addAttribute("tagList", List.of(Tag.values()));
        return "addAttraction";
    }

    @PostMapping("/attractions/update/")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttaction) {
        touristService.updateAttraction(touristAttaction);
        return "redirect:/attractions";
    }

    //Not sure if this gets used anywhere...
    @GetMapping("/attractions/delete/{name}")
    public String showDeleteConfirmation(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/attractions/delete/")
    public String deleteAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.deleteAttraction(touristAttraction);
        return "redirect:/attractions";
    }

}
