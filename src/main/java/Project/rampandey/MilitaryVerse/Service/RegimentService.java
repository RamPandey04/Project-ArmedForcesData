package Project.rampandey.MilitaryVerse.Service;

import Project.rampandey.MilitaryVerse.DTO.RegimentDTO;
import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import Project.rampandey.MilitaryVerse.Repository.RegimentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegimentService {

    ModelMapper modelMapper;

    RegimentRepository regimentRepository;


    public RegimentService(ModelMapper modelMapper, RegimentRepository regimentRepository) {
        this.modelMapper = modelMapper;
        this.regimentRepository = regimentRepository;
    }

    public RegimentDTO create(RegimentDTO regimentDTO){
        RegimentEntity regiment = modelMapper.map(regimentDTO, RegimentEntity.class);
        return modelMapper.map(regimentRepository.save(regiment), RegimentDTO.class);
    }

    public List<RegimentDTO> getAll(){
        return  regimentRepository.findAll()
                .stream()
                .map(regimentEntity -> modelMapper.map(regimentEntity, RegimentDTO.class))
                .collect(Collectors.toList());
    }

    public  RegimentDTO findByID( Long id) {
        return regimentRepository.findById(id)
                .map(regimentEntity -> modelMapper.map(regimentEntity, RegimentDTO.class))
                .orElse(null);

    }

}
