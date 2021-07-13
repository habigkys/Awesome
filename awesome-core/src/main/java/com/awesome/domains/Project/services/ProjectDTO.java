package com.awesome.domains.Project.services;

import com.awesome.domains.Project.entities.ProjectEntity;
import com.awesome.domains.Project.enums.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private Long id;

    private String projectName;

    private String summary;

    private ProjectStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ProjectDTO convert(ProjectEntity projectEntity) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(projectEntity.getId());
        dto.setProjectName(projectEntity.getProjectName());
        dto.setSummary(projectEntity.getSummary());
        dto.setStatus(projectEntity.getStatus());
        dto.setStartDate(projectEntity.getStartDate());
        dto.setEndDate(projectEntity.getEndDate());
        dto.setCreatedAt(projectEntity.getCreatedAt());
        dto.setUpdatedAt(projectEntity.getUpdatedAt());
        return dto;
    }
}
