package org.example.turistguideapi.controller;

import org.example.turistguideapi.model.Location;
import org.example.turistguideapi.model.Tag;
import org.example.turistguideapi.model.TouristAttraction;
import org.example.turistguideapi.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

@WebMvcTest(TouristController.class)
class TouristControllerTest {
    private List<TouristAttraction> mockList;
    private TouristAttraction attraction1;
    private TouristAttraction attraction2;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
       attraction1 = new TouristAttraction("Sankt Jørgen","Byens bedste hotel",Location.HERNING,List.of(Tag.BØRNEVENLIG));
       attraction2 = new TouristAttraction("Skraldebjerget","Dejlig bakke",Location.HERNING,List.of(Tag.BØRNEVENLIG));
        mockList = List.of(attraction1,attraction2);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testGetAllAttractions() throws Exception {
        when(touristService.getAllAttractions()).thenReturn(mockList);

        mockMvc.perform(get("/attractions")).andExpect(status().isOk())
                .andExpect(view().name("attractions"))
                .andExpect(model().attributeExists("allAttractions"))
                .andExpect(model().attribute("allAttractions",mockList));

        verify(touristService, times(1)).getAllAttractions();
    }
    /*@Test
    void testShowAddForm() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addAttraction"));
    }

     */

    /*@Test
    void testAddAttraction() throws Exception {
        mockMvc.perform(post("/add")
                .param("name","Tivoli")
                .param("description","Hundepark i Valby")
                .param("lokation","Valby")
                .param("tag","Børnevenlig"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));
        verify(touristService,times(1)).addAttraction(any(TouristAttraction.class));
    }

     */


    @Test
    void testGetAttractionByName() throws Exception{
        String name = "Sankt Jørgen";
        when(touristService.getAttractionByName(name)).thenReturn(attraction1);
        mockMvc.perform(get("/attractions/{name}", name))
                .andExpect(status().isOk());
    }

    @Test
    void testShowUpdateForm() throws Exception{
        String name = "Sankt Jørgen";
        when(touristService.getAttractionByName(name)).thenReturn(attraction1);
        mockMvc.perform(get("/attractions/update/{name}", name))
                .andExpect(status().isOk())
                .andExpect(view().name("updateAttraction"));
    }

//    @Test
//    void testUpdateAttraction() throws Exception {
//        mockMvc.perform(post("/attractions/update/")
//                        .param("name","Sankt Jørgen")
//                        .param("description","Hundepark")
//                        .param("lokation","Herning")
//                        .param("tag","Gratis","børnevenlig"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/attractions"));
//        verify(touristService,times(1)).updateAttraction(any(TouristAttraction.class));
//    }

    @Test
    void testShowDeleteConfirmation() throws Exception{
        String name = "Sankt Jørgen";
        when(touristService.getAttractionByName(name)).thenReturn(attraction1);
        mockMvc.perform(get("/attractions/delete/{name}",name))
                .andExpect(status().isOk())
                .andExpect(view().name("deleteAttraction"));
    }
//    @Test
//    void testDeleteAttraction() throws Exception{
//        mockMvc.perform(post("/attractions/delete/")
//                .param("name","Sankt Jørgen")
//                .param("description","Byens bedste hotel")
//                .param("lokation","Herning")
//                .param("tag","Børnevenlig"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/attractions"));
//        verify(touristService, times(1)).deleteAttraction(any(TouristAttraction.class));
//    }


}