package Project.rampandey.MilitaryVerse.Repository;

import Project.rampandey.MilitaryVerse.Entity.OperationEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository  extends JpaRepository <OperationEntity, Long> {


    List<OperationEntity> findByRegimentId(Long regimentId);
}
