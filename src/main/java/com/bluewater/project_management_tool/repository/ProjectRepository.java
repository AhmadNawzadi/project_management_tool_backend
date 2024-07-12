package com.bluewater.project_management_tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
