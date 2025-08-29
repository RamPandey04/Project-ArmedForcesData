package Project.rampandey.MilitaryVerse.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class MartyrDTO {
    private Long id;
    private String name;
    private String rank;
    private LocalDate dateOfSacrifice;
    private Long regimentId;
    private Long operationId;
}
