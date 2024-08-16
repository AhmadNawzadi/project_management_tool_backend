package embeddable;

import java.util.Set;

import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.model.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class UserIdProjectId {
	
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
    @ManyToMany
    @JoinTable(
    		name = "project_pms", 
    		joinColumns = @JoinColumn(name = "pm_id"),
    		inverseJoinColumns = @JoinColumn(name = "p_id")
    	)
	private Set<Project> projects;

}
