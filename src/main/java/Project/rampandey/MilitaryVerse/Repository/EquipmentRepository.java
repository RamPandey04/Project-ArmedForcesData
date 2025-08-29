package Project.rampandey.MilitaryVerse.Repository;

import Project.rampandey.MilitaryVerse.Entity.EquipmentEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
}
