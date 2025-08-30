package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.RegimentDTO;
import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import Project.rampandey.MilitaryVerse.Repository.RegimentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegimentServiceTest {

    private RegimentRepository regimentRepository;
    private ModelMapper modelMapper;
    private RegimentService regimentService;

    @BeforeEach
    void setUp() {
        regimentRepository = mock(RegimentRepository.class);
        modelMapper = new ModelMapper();
        regimentService = new RegimentService(modelMapper, regimentRepository);
    }

    @Test
    void testCreate() {
        RegimentDTO dto = new RegimentDTO();
        dto.setName("Rajput Regiment");
        dto.setMotto("Victory or Death");
        dto.setEstablishedYear(1775);
        dto.setHistory("One of the oldest regiments in the Indian Army");

        RegimentEntity entity = modelMapper.map(dto, RegimentEntity.class);
        entity.setId(1L);

        when(regimentRepository.save(any(RegimentEntity.class))).thenReturn(entity);

        RegimentDTO saved = regimentService.create(dto);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("Rajput Regiment", saved.getName());
        verify(regimentRepository, times(1)).save(any(RegimentEntity.class));
    }

    @Test
    void testGetAll() {
        RegimentEntity reg1 = new RegimentEntity(1L, "Rajput Regiment", "Victory or Death", 1775, "Historic regiment");
        RegimentEntity reg2 = new RegimentEntity(2L, "Sikh Regiment", "Deg Teg Fateh", 1846, "Bravery in wars");

        when(regimentRepository.findAll()).thenReturn(Arrays.asList(reg1, reg2));

        List<RegimentDTO> result = regimentService.getAll();

        assertEquals(2, result.size());
        assertEquals("Rajput Regiment", result.get(0).getName());
        assertEquals("Sikh Regiment", result.get(1).getName());
        verify(regimentRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        RegimentEntity reg = new RegimentEntity(1L, "Rajput Regiment", "Victory or Death", 1775, "Historic regiment");

        when(regimentRepository.findById(1L)).thenReturn(Optional.of(reg));

        RegimentDTO result = regimentService.findByID(1L);

        assertNotNull(result);
        assertEquals("Rajput Regiment", result.getName());
        verify(regimentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(regimentRepository.findById(100L)).thenReturn(Optional.empty());

        RegimentDTO result = regimentService.findByID(100L);

        assertNull(result);
        verify(regimentRepository, times(1)).findById(100L);
    }
}
