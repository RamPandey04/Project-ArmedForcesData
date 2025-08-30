package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.OperationDTO;
import Project.rampandey.MilitaryVerse.Service.OperationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OperationControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    @BeforeEach
    void setUp() {
        // Standalone MockMvc with only the controller and no full Spring context
        mockMvc = MockMvcBuilders.standaloneSetup(operationController).build();
    }

    @Test
    void testGetAllOperations() throws Exception {
        OperationDTO op1 = new OperationDTO();
        op1.setId(1L);
        op1.setName("Operation A");
        op1.setYear(1999);
        op1.setLocation("Kargil");
        op1.setDescription("Desc A");
        op1.setRegimentId(10L);

        OperationDTO op2 = new OperationDTO();
        op2.setId(2L);
        op2.setName("Operation B");
        op2.setYear(2001);
        op2.setLocation("Rajasthan");
        op2.setDescription("Desc B");
        op2.setRegimentId(11L);

        when(operationService.findAll()).thenReturn(List.of(op1, op2));

        mockMvc.perform(get("/operations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Operation A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Operation B"));
    }

    @Test
    void testCreateOperation() throws Exception {
        OperationDTO input = new OperationDTO();
        input.setName("Operation X");
        input.setYear(2020);
        input.setLocation("Ladakh");
        input.setDescription("High altitude");
        input.setRegimentId(42L);

        OperationDTO saved = new OperationDTO();
        saved.setId(100L);
        saved.setName("Operation X");
        saved.setYear(2020);
        saved.setLocation("Ladakh");
        saved.setDescription("High altitude");
        saved.setRegimentId(42L);

        when(operationService.create(any(OperationDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("Operation X"))
                .andExpect(jsonPath("$.regimentId").value(42));
    }

    @Test
    void testGetByRegimentId_PathVariable() throws Exception {
        // controller mapping: GET /operations/regiment/{id}
        OperationDTO op = new OperationDTO();
        op.setId(5L);
        op.setName("Operation A");
        op.setRegimentId(7L);

        when(operationService.findByRegimentId(7L)).thenReturn(List.of(op));

        mockMvc.perform(get("/operations/regiment/{id}", 7L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(5))
                .andExpect(jsonPath("$[0].name").value("Operation A"))
                .andExpect(jsonPath("$[0].regimentId").value(7));
    }
}
