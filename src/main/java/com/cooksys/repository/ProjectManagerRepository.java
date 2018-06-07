package com.cooksys.repository;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cooksys.entity.Project;
import com.cooksys.entity.ProjectManager;
public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Long>{
	//List<ProjectManager> findByProjectsDueDateLessThanAndcountByProjectsDueDateLessThan(Date eck, Date ecks);
	Long countByProjectsDueDateLessThan(Date eck);
	//findByProgrammeAndDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc
	List<ProjectManager> findByProjectsContains(Project eck);
}
	