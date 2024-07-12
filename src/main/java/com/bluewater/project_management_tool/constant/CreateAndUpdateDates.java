package com.bluewater.project_management_tool.constant;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class CreateAndUpdateDates {
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	protected LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;

}
