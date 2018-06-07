package com.cooksys.service;

import com.cooksys.dto.ProjectDto;
import com.cooksys.entity.Project;
import com.cooksys.exception.ReferencedEntityNotFoundException;
import com.cooksys.mapper.ProjectMapper;
import com.cooksys.repository.ProjectManagerRepository;
import com.cooksys.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjectService {
	private ProjectRepository repo;
	private ProjectManagerRepository manRepo;
	private ProjectMapper mapper;
	public ProjectService(ProjectRepository repo, ProjectMapper mapper, ProjectManagerRepository manRepo) {
		this.repo = repo;
		this.mapper = mapper;
		this.manRepo = manRepo;
	}
	public List<ProjectDto> getAll() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	public boolean has(Long id) {
		return repo.exists(id);
	}
	public ProjectDto get(Long id) {
		mustExist(id);
		return mapper.toDto(repo.findOne(id));
	}
	public Long post(ProjectDto projectDto) {
		projectDto.setId(null);
		return repo.save(mapper.toEntity(projectDto)).getId();
	}
	public void put(Long id, ProjectDto projectDto) {
		mustExist(id);
		projectDto.setId(id);
		Project ecks = mapper.toEntity(projectDto);
		ecks.getManager().getProjects().add(ecks);
		repo.save(mapper.toEntity(projectDto));
	}
	public void save(ProjectDto project){
		repo.save(mapper.toEntity(project));
	}
	public List<ProjectDto> getAllPastDue() {
		Date ourDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return repo.findByDueDateLessThan(ourDate).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	private void mustExist(Long id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Project.class, id);
	}
	public void delete(Long id) {
		mustExist(id);
		repo.delete(id);
	}
}
