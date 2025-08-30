package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.EquipmentDTO;
import Project.rampandey.MilitaryVerse.Service.EquipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EquipmentController.class)
public class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentService equipmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEquipment() throws Exception {
        EquipmentDTO requestDto = new EquipmentDTO();
        requestDto.setName("INS Vikrant");
        requestDto.setType("Aircraft Carrier");
        requestDto.setDescription("India’s first indigenous aircraft carrier");
        requestDto.setInServiceSince(2022);

        EquipmentDTO responseDto = new EquipmentDTO();
        responseDto.setId(1L);
        responseDto.setName("INS Vikrant");
        responseDto.setType("Aircraft Carrier");
        responseDto.setDescription("India’s first indigenous aircraft carrier");
        responseDto.setInServiceSince(2022);

        when(equipmentService.create(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("INS Vikrant"))
                .andExpect(jsonPath("$.type").value("Aircraft Carrier"));
    }

    @Test
    void testGetById_Found() throws Exception {
        EquipmentDTO responseDto = new EquipmentDTO();
        responseDto.setId(1L);
        responseDto.setName("INS Vikrant");
        responseDto.setType("Aircraft Carrier");
        responseDto.setDescription("India’s first indigenous aircraft carrier");
        responseDto.setInServiceSince(2022);

        when(equipmentService.findById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/equipment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("INS Vikrant"))
                .andExpect(jsonPath("$.type").value("Aircraft Carrier"));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(equipmentService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/equipment/99"))
                .andExpect(status().isNotFound());
    }
}
