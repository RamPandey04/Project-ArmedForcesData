package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.EquipmentDTO;
import Project.rampandey.MilitaryVerse.Entity.EquipmentEntity;
import Project.rampandey.MilitaryVerse.Repository.EquipmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {


    ModelMapper modelMapper;
    EquipmentRepository equipmentRepository;

    public EquipmentService(ModelMapper modelMapper, EquipmentRepository equipmentRepository) {
        this.modelMapper = modelMapper;
        this.equipmentRepository = equipmentRepository;
    }

    public EquipmentDTO create(EquipmentDTO equipmentDTO){
        EquipmentEntity entity = modelMapper.map(equipmentDTO, EquipmentEntity.class);
       return modelMapper.map(equipmentRepository.save(entity), EquipmentDTO.class);
    }

    public List<EquipmentDTO> findAll(){
         return equipmentRepository.findAll()
                 .stream()
                 .map(equipmentEntity -> modelMapper.map(equipmentEntity, EquipmentDTO.class))
                 .collect(Collectors.toList());
    }


    public EquipmentDTO findById(Long id) {
        return equipmentRepository.findById(id)
                .map(equipmentEntity -> modelMapper.map(equipmentEntity, EquipmentDTO.class))
                .orElse(null);
    }
}
