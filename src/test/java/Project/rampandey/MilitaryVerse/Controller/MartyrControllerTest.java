package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.MartyrDTO;
import Project.rampandey.MilitaryVerse.Service.MartyrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MartyrController.class)
public class MartyrControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MartyrService martyrService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetById_Found() throws Exception {
        MartyrDTO dto = new MartyrDTO();
        dto.setId(1L);
        dto.setName("Captain Vikram Batra");

        when(martyrService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/martyrs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Captain Vikram Batra"));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(martyrService.getById(100L)).thenReturn(null);

        mockMvc.perform(get("/martyrs/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateMartyr() throws Exception {
        MartyrDTO dto = new MartyrDTO();
        dto.setId(1L);
        dto.setName("Major Sandeep Unnikrishnan");

        when(martyrService.create(dto)).thenReturn(dto);

        mockMvc.perform(post("/martyrs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Major Sandeep Unnikrishnan"));
    }

    @Test
    void testGetByOperationId() throws Exception {
        MartyrDTO dto1 = new MartyrDTO();
        dto1.setId(1L);
        dto1.setName("Soldier 1");

        MartyrDTO dto2 = new MartyrDTO();
        dto2.setId(2L);
        dto2.setName("Soldier 2");

        List<MartyrDTO> martyrs = Arrays.asList(dto1, dto2);

        when(martyrService.findByOperationID(10L)).thenReturn(martyrs);

        mockMvc.perform(get("/martyrs/operation10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Soldier 1"));
    }
}
