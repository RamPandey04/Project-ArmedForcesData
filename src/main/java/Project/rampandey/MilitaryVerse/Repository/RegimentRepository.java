package Project.rampandey.MilitaryVerse.Repository;

import Project.rampandey.MilitaryVerse.Entity.RegimentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimentRepository extends JpaRepository<RegimentEntity, Long> {


}
