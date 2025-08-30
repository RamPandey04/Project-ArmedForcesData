package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.OperationDTO;
import Project.rampandey.MilitaryVerse.Entity.OperationEntity;
import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import Project.rampandey.MilitaryVerse.Repository.OperationRepository;
import Project.rampandey.MilitaryVerse.Repository.RegimentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OperationServiceTest {
    private OperationRepository operationRepository;
    private RegimentRepository regimentRepository;
    private ModelMapper modelMapper;
    private OperationService operationService;

    @BeforeEach
    void setUp() {
        operationRepository = mock(OperationRepository.class);
        regimentRepository = mock(RegimentRepository.class);
        modelMapper = new ModelMapper();
        operationService = new OperationService(modelMapper, operationRepository, regimentRepository);
    }

    @Test
    void testCreate() {
        OperationDTO dto = new OperationDTO();
        dto.setName("Kargil War");
        dto.setLocation("Kargil");
        dto.setDescription("High altitude conflict");
        dto.setRegimentId(10L);

        RegimentEntity regiment = new RegimentEntity();
        regiment.setId(10L);
        regiment.setName("Rajput Regiment");

        when(regimentRepository.findById(10L)).thenReturn(Optional.of(regiment));

        OperationEntity entity = modelMapper.map(dto, OperationEntity.class);
        entity.setId(1L);
        entity.setRegiment(regiment);

        when(operationRepository.save(any(OperationEntity.class))).thenReturn(entity);

        OperationDTO saved = operationService.create(dto);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("Kargil War", saved.getName());
        verify(operationRepository, times(1)).save(any(OperationEntity.class));
    }

    @Test
    void testFindByRegimentId() {
        RegimentEntity regiment = new RegimentEntity();
        regiment.setId(5L);

        OperationEntity op1 = new OperationEntity();
        op1.setId(1L);
        op1.setName("Op Vijay");
        op1.setLocation("Kargil");
        op1.setRegiment(regiment);

        OperationEntity op2 = new OperationEntity();
        op2.setId(2L);
        op2.setName("Op Meghdoot");
        op2.setLocation("Siachen");
        op2.setRegiment(regiment);

        when(operationRepository.findByRegimentId(5L)).thenReturn(Arrays.asList(op1, op2));

        List<OperationDTO> result = operationService.findByRegimentId(5L);

        assertEquals(2, result.size());
        assertEquals("Op Vijay", result.get(0).getName());
        assertEquals("Op Meghdoot", result.get(1).getName());
        verify(operationRepository, times(1)).findByRegimentId(5L);
    }

    @Test
    void testFindAll() {
        OperationEntity op1 = new OperationEntity();
        op1.setId(1L);
        op1.setName("Op Blue Star");

        OperationEntity op2 = new OperationEntity();
        op2.setId(2L);
        op2.setName("Op Cactus");

        when(operationRepository.findAll()).thenReturn(Arrays.asList(op1, op2));

        List<OperationDTO> result = operationService.findAll();

        assertEquals(2, result.size());
        assertEquals("Op Blue Star", result.get(0).getName());
        assertEquals("Op Cactus", result.get(1).getName());
        verify(operationRepository, times(1)).findAll();
    }
}
