package my_first_project.ppmtool.repositories;

import my_first_project.ppmtool.domain.Backlog;
import my_first_project.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
}
