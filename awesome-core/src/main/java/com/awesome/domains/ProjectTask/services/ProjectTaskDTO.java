package com.awesome.domains.ProjectTask.services;

import com.awesome.domains.ProjectTask.entities.ProjectTaskEntity;
import com.awesome.domains.ProjectTask.enums.TaskType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProjectTaskDTO {
    private Long id;

    private Long projectId;

    private Long parentTaskId;

    private String summary;

    private LocalDate taskStartDate;

    private LocalDate taskEndDate;

    private String persons;

    private TaskType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ProjectTaskDTO convert(ProjectTaskEntity projectTaskEntity) {
        ProjectTaskDTO taskDto = new ProjectTaskDTO();
        taskDto.setId(projectTaskEntity.getId());
        taskDto.setProjectId(projectTaskEntity.getProjectId());
        taskDto.setParentTaskId(projectTaskEntity.getParentTaskId());
        taskDto.setSummary(projectTaskEntity.getSummary());
        taskDto.setTaskStartDate(projectTaskEntity.getTaskStartDate());
        taskDto.setTaskEndDate(projectTaskEntity.getTaskEndDate());
        taskDto.setPersons(projectTaskEntity.getPersons());
        taskDto.setType(projectTaskEntity.getType());
        taskDto.setCreatedAt(projectTaskEntity.getCreatedAt());
        taskDto.setUpdatedAt(projectTaskEntity.getUpdatedAt());
        return taskDto;
    }
}
