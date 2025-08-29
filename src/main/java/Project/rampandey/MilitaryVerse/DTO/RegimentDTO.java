package Project.rampandey.MilitaryVerse.DTO;

import lombok.Data;

@Data
public class RegimentDTO{
    private Long id;
    private String name;
    private String motto;
    private Integer establishedYear;
    private String history;
}
