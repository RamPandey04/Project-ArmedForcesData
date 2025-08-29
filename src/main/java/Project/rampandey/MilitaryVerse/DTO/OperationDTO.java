package Project.rampandey.MilitaryVerse.DTO;
import lombok.Data;

@Data
public class OperationDTO {
    private Long id;
    private String name;
    private Integer year;
    private String location;
    private String description;
    private Long regimentId;
}
