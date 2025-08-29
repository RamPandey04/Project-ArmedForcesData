package Project.rampandey.MilitaryVerse.DTO;

import lombok.Data;

@Data
public class EquipmentDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private int inServiceSince;
}
