package com.cooksys.repository;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cooksys.entity.Project;
import com.cooksys.entity.ProjectManager;
public interface ProjectRepository extends JpaRepository<Project, Long>{
	List<Project> findByDueDateLessThan(Date currDate);
	Long countByManager(ProjectManager goodGuy);
}