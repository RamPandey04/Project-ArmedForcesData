package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.EquipmentDTO;
import Project.rampandey.MilitaryVerse.Service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> create(@RequestBody EquipmentDTO equipmentDTO) {
        return ResponseEntity.ok(equipmentService.create(equipmentDTO));
    }

    @GetMapping("/{id}")

    public ResponseEntity<EquipmentDTO> getById(@PathVariable Long id){
        EquipmentDTO dto = equipmentService.findById(id);
        return dto!= null? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


}
