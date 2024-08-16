package embeddable;

import java.util.Set;

import com.bluewater.project_management_tool.model.Project;
import com.bluewater.project_management_tool.model.Task;
import com.bluewater.project_management_tool.model.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentIdTaskId {
	
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "task_id")
	public Task task;
	

}
