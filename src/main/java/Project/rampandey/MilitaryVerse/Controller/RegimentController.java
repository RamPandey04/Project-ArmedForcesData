package Project.rampandey.MilitaryVerse.Controller;

import Project.rampandey.MilitaryVerse.DTO.RegimentDTO;
import Project.rampandey.MilitaryVerse.Service.RegimentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regiments")
public class RegimentController {
    RegimentService regimentService;

    public RegimentController(RegimentService regimentService) {
        this.regimentService = regimentService;
    }

    @PostMapping
    public ResponseEntity<RegimentDTO> create(@RequestBody RegimentDTO regimentDTO){
        return ResponseEntity.ok(regimentService.create(regimentDTO)) ;
    }

    @GetMapping
    public  ResponseEntity<List<RegimentDTO>> findAll(){
        return ResponseEntity.ok(regimentService.getAll());
    }

    @GetMapping("/{byId}")

    public ResponseEntity<RegimentDTO> findByID(@PathVariable Long byId){
          RegimentDTO dto = regimentService.findByID(byId);
          return dto!=null? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}
