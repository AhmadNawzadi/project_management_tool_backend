package com.bluewater.project_management_tool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.dto.ProjectMemberDTO;
import com.bluewater.project_management_tool.model.ProjectMember;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>{
	
	@Query(value = "SELECT * FROM project_members pm WHERE pm.user_id = :userId ", nativeQuery=true)
	ProjectMember getByUserId(@Param(value="userId") Long userId);
	
	@Query(value = "SELECT * FROM project_members pm "
			+ " JOIN project_pms ppms ON pm.id=ppms.pm_id "
			+ " WHERE ppms.pm_id = :pmId AND ppms.p_id = :pId ", nativeQuery=true)
	ProjectMember getByProjectIdAndProjectMemberId(
			@Param(value="pId") Long projectId, 
			@Param(value="pmId") Long projectMemberId);
	
    @Query(name = "find_p_members_dto", nativeQuery = true)
    Set<ProjectMemberDTO> getByProjectId(@Param("projectId") Long projectId);
	

}
