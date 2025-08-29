package Project.rampandey.MilitaryVerse.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class MartyrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String rank;

    private LocalDate dateOfSacrifice;

    @ManyToOne
    @JoinColumn(name = "regiment_id")
    private RegimentEntity regiment;

    @ManyToOne
    @JoinColumn(name ="operation_id" )
    private  OperationEntity operation;



}
