package com.bluewater.project_management_tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.model.TaskAssignement;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignement, Long> {

	@Query(value = "SELECT * FROM task_assignements WHERE task_id = :taskId", nativeQuery= true)
	TaskAssignement findByTaskId(@Param("taskId") Long taskId);

}
