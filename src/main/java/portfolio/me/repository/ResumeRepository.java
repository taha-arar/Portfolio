package portfolio.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.me.entity.Resume;


public interface ResumeRepository extends JpaRepository<Resume, Long> {

	 Resume getResumeById(Long Id);


}
