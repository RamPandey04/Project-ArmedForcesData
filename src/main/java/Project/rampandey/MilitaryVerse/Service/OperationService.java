package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.OperationDTO;
import Project.rampandey.MilitaryVerse.Entity.OperationEntity;
import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import Project.rampandey.MilitaryVerse.Repository.OperationRepository;
import Project.rampandey.MilitaryVerse.Repository.RegimentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService {


    ModelMapper modelMapper;

    OperationRepository operationRepository;

    RegimentRepository regimentRepository;

    public OperationService(ModelMapper modelMapper, OperationRepository operationRepository, RegimentRepository regimentRepository) {
        this.modelMapper = modelMapper;
        this.operationRepository = operationRepository;
        this.regimentRepository = regimentRepository;
    }

    public OperationDTO create(OperationDTO operationDTO){

        OperationEntity operation = modelMapper.map(operationDTO, OperationEntity.class);
        RegimentEntity regiment = regimentRepository.findById(operationDTO.getRegimentId()).orElseThrow();
        operation.setRegiment(regiment);
        return modelMapper.map(operationRepository.save(operation),  OperationDTO.class);
    }

    public List<OperationDTO>findByRegimentId(Long regimentID){
        return operationRepository.findByRegimentId(regimentID)
                .stream()
                .map(operationEntity -> modelMapper.map(operationEntity, OperationDTO.class))
                .collect(Collectors.toList());
    }

    public List<OperationDTO> findAll() {
        return operationRepository.findAll()
                .stream()
                .map(operationEntity -> modelMapper.map(operationEntity, OperationDTO.class))
                .collect(Collectors.toList());
    }
}
