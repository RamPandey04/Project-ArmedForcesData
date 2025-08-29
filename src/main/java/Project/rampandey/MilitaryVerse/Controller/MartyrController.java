package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.MartyrDTO;
import Project.rampandey.MilitaryVerse.Service.MartyrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/martyrs")
public class MartyrController {

    MartyrService martyrService;

    public MartyrController(MartyrService martyrService) {
        this.martyrService = martyrService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MartyrDTO> getByID(@PathVariable Long id){
      MartyrDTO dto = martyrService.getById(id);
      return dto!=null ? ResponseEntity.ok(dto): ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MartyrDTO> create( MartyrDTO martyrDTO){
       return ResponseEntity.ok(martyrService.create(martyrDTO));
    }

    @GetMapping("/operation{operationId}")

    public ResponseEntity<List<MartyrDTO>> getByOperationId(@PathVariable Long operationId){
        return ResponseEntity.ok(martyrService.findByOperationID(operationId));    }

}
