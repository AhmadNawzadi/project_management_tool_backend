package com.bluewater.project_management_tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluewater.project_management_tool.model.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long>{
	
	//void findByUser();
	
	@Query("FROM Invitation i WHERE i.user.id = :id")
	Invitation findByUserId(@Param(value = "id") Long id);
	
	@Query(value=" select i.email from invitations i "
			+ " join project_members pm on i.user_id= pm.user_id "
			+ " where pm.id = :pmId"
			+ "", nativeQuery = true)
	String findEmailByMemberId(@Param(value = "pmId") Long pmId);

}
