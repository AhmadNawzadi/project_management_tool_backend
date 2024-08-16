package com.bluewater.project_management_tool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.constant.Status;
import com.bluewater.project_management_tool.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query("FROM Task t WHERE t.project.id = :projectId ")
	public Set<Task> getByProjectId(@Param("projectId") Long id);
	
	Set<Task> findByStatus(Status status);

	@Query(value =  " SELECT t.status, COUNT(*) as task_count "
			+ "	FROM tasks t "
			+ "	JOIN projects p ON p.id = t.project_id "
			+ "	WHERE p.user_id = :userId "
			+ "	GROUP BY t.status ", nativeQuery = true)
	public String countTasks(Long userId);

}
