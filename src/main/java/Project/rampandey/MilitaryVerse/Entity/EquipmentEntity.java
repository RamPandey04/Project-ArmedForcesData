package Project.rampandey.MilitaryVerse.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;

    private String type;

    @Column(columnDefinition = "text")
    private String description;

    private int inServiceSince;
}
