package com.bluewater.project_management_tool.model;

import java.time.LocalDate;
import java.util.Set;

import com.bluewater.project_management_tool.constant.CreateAndUpdateDates;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project extends CreateAndUpdateDates {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Lob
	private String description;
	private LocalDate startDate;
	
	//project - user
	@ManyToOne
	private User user;
	
    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private Set<ProjectMember> projectMembers;
    
 

    
    
	public Project(String name, String description, LocalDate startDate) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
	}
    
    

}
