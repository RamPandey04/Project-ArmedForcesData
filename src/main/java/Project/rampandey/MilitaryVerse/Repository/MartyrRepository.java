package Project.rampandey.MilitaryVerse.Repository;

import Project.rampandey.MilitaryVerse.Entity.MartyrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MartyrRepository extends JpaRepository<MartyrEntity , Long> {

    List<MartyrEntity> findByOperationId(Long OperationID);
}
