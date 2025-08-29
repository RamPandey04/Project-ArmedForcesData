package Project.rampandey.MilitaryVerse.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    private String location;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "regiment_id")
    private RegimentEntity regiment;

}
