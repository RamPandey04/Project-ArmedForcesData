package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.RegimentDTO;
import Project.rampandey.MilitaryVerse.Service.RegimentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegimentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RegimentService regimentService;

    @InjectMocks
    private RegimentController regimentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(regimentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateRegiment() throws Exception {
        RegimentDTO regimentDTO = new RegimentDTO();
        regimentDTO.setId(1L);
        regimentDTO.setName("Rajput Regiment");
        regimentDTO.setMotto("Victory or Valhalla");
        regimentDTO.setEstablishedYear(1775);
        regimentDTO.setHistory("Historic regiment");

        when(regimentService.create(any(RegimentDTO.class))).thenReturn(regimentDTO);

        mockMvc.perform(post("/regiments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regimentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Rajput Regiment"))
                .andExpect(jsonPath("$.motto").value("Victory or Valhalla"));
    }

    @Test
    void testGetAllRegiments() throws Exception {
        RegimentDTO regiment1 = new RegimentDTO();
        regiment1.setId(1L);
        regiment1.setName("Rajput Regiment");

        RegimentDTO regiment2 = new RegimentDTO();
        regiment2.setId(2L);
        regiment2.setName("Sikh Regiment");

        List<RegimentDTO> regiments = Arrays.asList(regiment1, regiment2);

        when(regimentService.getAll()).thenReturn(regiments);

        mockMvc.perform(get("/regiments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Rajput Regiment"))
                .andExpect(jsonPath("$[1].name").value("Sikh Regiment"));
    }

    @Test
    void testFindById_Found() throws Exception {
        RegimentDTO regiment = new RegimentDTO();
        regiment.setId(1L);
        regiment.setName("Rajput Regiment");

        when(regimentService.findByID(1L)).thenReturn(regiment);

        mockMvc.perform(get("/regiments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Rajput Regiment"));
    }

    @Test
    void testFindById_NotFound() throws Exception {
        when(regimentService.findByID(99L)).thenReturn(null);

        mockMvc.perform(get("/regiments/99"))
                .andExpect(status().isNotFound());
    }
}
