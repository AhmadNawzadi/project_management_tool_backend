package com.bluewater.project_management_tool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	@Query("FROM Project p WHERE p.user.id = :id")
	Set<Project> getByUserId(@Param(value = "id") Long id);

}
