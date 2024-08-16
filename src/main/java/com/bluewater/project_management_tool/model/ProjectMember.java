package com.bluewater.project_management_tool.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bluewater.project_management_tool.constant.CreateAndUpdateDates;
import com.bluewater.project_management_tool.constant.Role;
import com.bluewater.project_management_tool.dto.ProjectMemberDTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedNativeQuery(
	    name = "find_p_members_dto",
	    query = "SELECT pm.id as memberId, pm.role as role, u.username as username, pm.created_at as createdAt "
	    	  + " FROM project_members pm "
	          + "JOIN project_pms ppms ON pm.id = ppms.pm_id "
	          + "JOIN users u ON u.id = pm.user_id "
	          + "WHERE ppms.p_id = :projectId",
	    resultSetMapping = "p_members_dto"
	)
@SqlResultSetMapping(
	    name = "p_members_dto",
	    classes = {
	        @ConstructorResult(
	            targetClass = ProjectMemberDTO.class,
	            columns = {
	            	@ColumnResult(name = "memberId", type = Long.class),
	                @ColumnResult(name = "role", type = Role.class),
	                @ColumnResult(name = "username", type = String.class),
	                @ColumnResult(name = "createdAt", type = LocalDateTime.class)
	            }
	        )
	    }
	)

@Entity
@Table(name = "project_members")
@Data
@NoArgsConstructor
public class ProjectMember extends CreateAndUpdateDates {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Role role;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
    @ManyToMany
    @JoinTable(
    		name = "project_pms", 
    		joinColumns = @JoinColumn(name = "pm_id"),
    		inverseJoinColumns = @JoinColumn(name = "p_id"),
    		uniqueConstraints = @UniqueConstraint(columnNames = {"pm_id", "p_id"})
    		
    	)
	private Set<Project> projects;
    
    @OneToMany(mappedBy = "member")
    private Set<TaskAssignement> assignments;

	public ProjectMember(Role role, User user) {
		super();
		this.role = role;
		this.user = user;
		this.projects = new HashSet<Project>();
	}	

}
