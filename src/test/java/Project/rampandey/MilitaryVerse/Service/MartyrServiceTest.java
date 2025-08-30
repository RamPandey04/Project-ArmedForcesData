package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.MartyrDTO;
import Project.rampandey.MilitaryVerse.Entity.MartyrEntity;
import Project.rampandey.MilitaryVerse.Entity.OperationEntity;
import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import Project.rampandey.MilitaryVerse.Repository.MartyrRepository;
import Project.rampandey.MilitaryVerse.Repository.OperationRepository;
import Project.rampandey.MilitaryVerse.Repository.RegimentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class MartyrServiceTest {
    private MartyrRepository martyrRepository;
    private RegimentRepository regimentRepository;
    private OperationRepository operationRepository;
    private ModelMapper modelMapper;
    private MartyrService martyrService;

    @BeforeEach
    void setUp(){
        martyrRepository = mock(MartyrRepository.class);
        regimentRepository = mock(RegimentRepository.class);
        operationRepository = mock(OperationRepository.class);
        modelMapper = new ModelMapper();
        martyrService = new MartyrService(modelMapper,martyrRepository,regimentRepository, operationRepository);
    }

    @Test
    void testCreateMartyr() {
        // DTO
        MartyrDTO dto = new MartyrDTO();
        dto.setName("Captain Vikram Batra");
        dto.setRank("Captain");
        dto.setDateOfSacrifice(LocalDate.of(1999, 7, 7));
        dto.setRegimentId(1L);
        dto.setOperationId(100L);

        // Regiment + Operation mock
        RegimentEntity regiment = new RegimentEntity();
        regiment.setId(1L);

        OperationEntity operation = new OperationEntity();
        operation.setId(100L);

        when(regimentRepository.findById(1L)).thenReturn(Optional.of(regiment));
        when(operationRepository.findById(100L)).thenReturn(Optional.of(operation));

        // Saved entity
        MartyrEntity entity = modelMapper.map(dto, MartyrEntity.class);
        entity.setId(10L);
        entity.setRegiment(regiment);
        entity.setOperation(operation);

        when(martyrRepository.save(any(MartyrEntity.class))).thenReturn(entity);

        // Call service
        MartyrDTO savedDto = martyrService.create(dto);

        // Verify
        assertNotNull(savedDto);
        assertEquals(10L, savedDto.getId());
        assertEquals("Captain", savedDto.getRank());
        verify(martyrRepository, times(1)).save(any(MartyrEntity.class));
        verify(regimentRepository, times(1)).findById(1L);
        verify(operationRepository, times(1)).findById(100L);
    }

    @Test
    void testFindByOperationID() {
        MartyrEntity m1 = new MartyrEntity();
        m1.setId(1L);
        m1.setName("Soldier A");

        MartyrEntity m2 = new MartyrEntity();
        m2.setId(2L);
        m2.setName("Soldier B");

        when(martyrRepository.findByOperationId(100L)).thenReturn(Arrays.asList(m1, m2));

        List<MartyrDTO> result = martyrService.findByOperationID(100L);

        assertEquals(2, result.size());
        assertEquals("Soldier A", result.get(0).getName());
        assertEquals("Soldier B", result.get(1).getName());
        verify(martyrRepository, times(1)).findByOperationId(100L);
    }

    @Test
    void testGetById_Found() {
        MartyrEntity entity = new MartyrEntity();
        entity.setId(1L);
        entity.setName("Hero");
        entity.setRank("Major");

        when(martyrRepository.findById(1L)).thenReturn(Optional.of(entity));

        MartyrDTO dto = martyrService.getById(1L);

        assertNotNull(dto);
        assertEquals("Hero", dto.getName());
        assertEquals("Major", dto.getRank());
        verify(martyrRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(martyrRepository.findById(999L)).thenReturn(Optional.empty());

        MartyrDTO dto = martyrService.getById(999L);

        assertNull(dto);
        verify(martyrRepository, times(1)).findById(999L);
    }
}
