package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.EquipmentDTO;
import Project.rampandey.MilitaryVerse.Entity.EquipmentEntity;
import Project.rampandey.MilitaryVerse.Repository.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class EquipmentServiceTest {
    private EquipmentRepository equipmentRepository;
    private ModelMapper modelMapper;
    private EquipmentService equipmentService;

    @BeforeEach
    void srtUp(){
        equipmentRepository = mock(EquipmentRepository.class);
        modelMapper = new ModelMapper();
        equipmentService = new EquipmentService(modelMapper,equipmentRepository);
    }

    @Test
    void testCreate(){
        EquipmentDTO dto = new EquipmentDTO();
        dto.setName("Rifle");
        dto.setType("Weapon");

        EquipmentEntity entity= modelMapper.map(dto, EquipmentEntity.class);
        entity.setId(1L);

        when(equipmentRepository.save(any(EquipmentEntity.class))).thenReturn(entity);

        EquipmentDTO savedDto = equipmentService.create(dto);

        assertNotNull(savedDto);
        assertEquals(1L, savedDto.getId());
        assertEquals("Rifle", savedDto.getName());
        verify(equipmentRepository, times(1)).save(any(EquipmentEntity.class));
    }

    @Test
    void testFindAll() {
        EquipmentEntity e1 = new EquipmentEntity();
        e1.setId(1L);
        e1.setName("Tank");
        e1.setType("Vehicle");

        EquipmentEntity e2 = new EquipmentEntity();
        e2.setId(2L);
        e2.setName("Jet");
        e2.setType("Aircraft");

        when(equipmentRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<EquipmentDTO> result = equipmentService.findAll();

        assertEquals(2, result.size());
        assertEquals("Tank", result.get(0).getName());
        assertEquals("Jet", result.get(1).getName());
        verify(equipmentRepository, times(1)).findAll();
    }


    @Test
    void testFindById_Found() {
        EquipmentEntity entity = new EquipmentEntity();
        entity.setId(1L);
        entity.setType("Navel");
        entity.setName("Submarine");
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(entity));

        EquipmentDTO dto = equipmentService.findById(1L);

        assertNotNull(dto);
        assertEquals("Submarine", dto.getName());
        assertEquals("Navel",dto.getType());
        verify(equipmentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(equipmentRepository.findById(99L)).thenReturn(Optional.empty());

        EquipmentDTO dto = equipmentService.findById(99L);

        assertNull(dto);
        verify(equipmentRepository, times(1)).findById(99L);
    }
}


