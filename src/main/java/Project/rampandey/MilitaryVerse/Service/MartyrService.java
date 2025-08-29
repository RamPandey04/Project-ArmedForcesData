package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.MartyrDTO;
import Project.rampandey.MilitaryVerse.Entity.MartyrEntity;
import Project.rampandey.MilitaryVerse.Entity.OperationEntity;
import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import Project.rampandey.MilitaryVerse.Repository.MartyrRepository;
import Project.rampandey.MilitaryVerse.Repository.OperationRepository;
import Project.rampandey.MilitaryVerse.Repository.RegimentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MartyrService {


    ModelMapper modelMapper;
    MartyrRepository martyrRepository;
    RegimentRepository regimentRepository;
    OperationRepository operationRepository;

    public MartyrService(ModelMapper modelMapper, MartyrRepository martyrRepository, RegimentRepository regimentRepository, OperationRepository operationRepository) {
        this.modelMapper = modelMapper;
        this.martyrRepository = martyrRepository;
        this.regimentRepository = regimentRepository;
        this.operationRepository = operationRepository;
    }

    public MartyrDTO create(MartyrDTO martyrDTO){
        MartyrEntity entity = modelMapper.map(martyrDTO, MartyrEntity.class);
        RegimentEntity regiment = regimentRepository.findById(martyrDTO.getRegimentId()).orElse(null);
        entity.setRegiment(regiment);
        OperationEntity entity1 = operationRepository.findById(martyrDTO.getOperationId()).orElse(null);
        entity.setOperation(entity1);
        return modelMapper.map(martyrRepository.save(entity), MartyrDTO.class);
    }

    public List<MartyrDTO> findByOperationID(Long ID){
        return martyrRepository.findByOperationId(ID)
                .stream()
                .map(martyrEntity -> modelMapper.map(martyrEntity, MartyrDTO.class))
                .collect(Collectors.toList());
    }

    public MartyrDTO getById(Long id) {
        return martyrRepository.findById(id)
                .map(martyrEntity -> modelMapper.map(martyrEntity, MartyrDTO.class))
                .orElse(null);
    }
}
