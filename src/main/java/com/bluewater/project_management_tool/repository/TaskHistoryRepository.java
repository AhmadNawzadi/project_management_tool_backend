package com.bluewater.project_management_tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.model.TaskHistory;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long>{

}
