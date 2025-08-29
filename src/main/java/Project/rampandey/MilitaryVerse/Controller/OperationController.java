package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.OperationDTO;
import Project.rampandey.MilitaryVerse.Service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService ;
    }

    @GetMapping("/regiment{id}")
    public ResponseEntity<List<OperationDTO>> findByRegimentId(@RequestParam Long id){
        return ResponseEntity.ok( operationService.findByRegimentId(id));
    }

    @GetMapping
    public ResponseEntity<List<OperationDTO>>  getAll(){
        return ResponseEntity.ok(operationService.findAll());
    }

    @PostMapping
    public ResponseEntity<OperationDTO> create(OperationDTO operationDTO){
        return ResponseEntity.ok(operationService.create(operationDTO));
    }
}
