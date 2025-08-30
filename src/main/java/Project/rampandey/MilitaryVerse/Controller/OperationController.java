package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.OperationDTO;
import Project.rampandey.MilitaryVerse.Service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    //  Create Operation
    @PostMapping
    public ResponseEntity<OperationDTO> create(@RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.create(operationDTO));
    }

    //  Get all Operations
    @GetMapping
    public ResponseEntity<List<OperationDTO>> getAll() {
        return ResponseEntity.ok(operationService.findAll());
    }

    // Get Operations by Regiment ID
    @GetMapping("/regiment/{id}")
    public ResponseEntity<List<OperationDTO>> getByRegimentId(@PathVariable("id") Long regimentId) {
        return ResponseEntity.ok(operationService.findByRegimentId(regimentId));
    }
}
