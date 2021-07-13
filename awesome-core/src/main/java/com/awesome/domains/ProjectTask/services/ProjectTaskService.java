package com.awesome.domains.ProjectTask.services;

import com.awesome.domains.ProjectTask.entities.ProjectTaskDAO;
import com.awesome.domains.ProjectTask.entities.ProjectTaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectTaskService {
    private ProjectTaskDAO projectTaskDAO;

    /**
     * 1. 프로젝트 타스크/이슈 리스트 - ProjectTaskController
     * @return
     */
    public List<ProjectTaskDTO> getProjectTaskList(){
        List<ProjectTaskEntity> projectTaskEntityList = projectTaskDAO.findAll();

        return projectTaskEntityList.stream().map(ProjectTaskDTO::convert).collect(Collectors.toList());
    }

    /**
     * 2. 특정 프로젝트 타스크/이슈 - ProjectTaskController
     * @param id
     * @return
     */
    public ProjectTaskDTO getProjectTask(Long id){
        return ProjectTaskDTO.convert(projectTaskDAO.findById(id).get());
    }

    /**
     * 3. 특정 프로젝트의 타스크 리스트 - ProjectController
     * @param id
     * @return
     */
    public List<ProjectTaskDTO> getProjectTaskListByProject(Long id){
        List<ProjectTaskEntity> projectTaskEntityList = projectTaskDAO.findAllByProjectId(id);

        return projectTaskEntityList.stream().map(ProjectTaskDTO::convert).collect(Collectors.toList());
    }

    /**
     * 4. 프로젝트 타스크/이슈 생성 - ProjectTaskController
     * @param projectTaskDto
     * @param projectId
     * @return
     */
    public ProjectTaskDTO createProjectTask(ProjectTaskDTO projectTaskDto, Long projectId){
        // 시작일, 종료일 Validate
        if(!validateProjectTaskDate(projectTaskDto)) {
            // todo
            throw new IllegalArgumentException();
        }

        ProjectTaskEntity toCreateProjectTaskEntity = new ProjectTaskEntity();
        toCreateProjectTaskEntity.setId(projectTaskDto.getId());
        toCreateProjectTaskEntity.setProjectId(projectId);
        toCreateProjectTaskEntity.setParentTaskId(projectTaskDto.getParentTaskId());
        toCreateProjectTaskEntity.setPersons(projectTaskDto.getPersons());
        toCreateProjectTaskEntity.setType(projectTaskDto.getType());
        toCreateProjectTaskEntity.setSummary(projectTaskDto.getSummary());
        toCreateProjectTaskEntity.setTaskStartDate(projectTaskDto.getTaskStartDate());
        toCreateProjectTaskEntity.setTaskEndDate(projectTaskDto.getTaskEndDate());
        toCreateProjectTaskEntity.setCreatedAt(LocalDateTime.now());
        toCreateProjectTaskEntity.setUpdatedAt(LocalDateTime.now());

        return ProjectTaskDTO.convert(projectTaskDAO.save(toCreateProjectTaskEntity));
    }

    /**
     * 5. 프로젝트 타스크/이슈 업데이트 - ProjectTaskController
     * @param projectTaskDto
     * @return
     */
    public ProjectTaskDTO updateProjectTask(ProjectTaskDTO projectTaskDto){
        // 시작일, 종료일 Validate
        if(!validateProjectTaskDate(projectTaskDto)) {
            // todo
            throw new IllegalArgumentException();
        }

        Optional<ProjectTaskEntity> byId = projectTaskDAO.findById(projectTaskDto.getId());

        ProjectTaskEntity toUpdateOne = byId.get();
        toUpdateOne.setSummary(projectTaskDto.getSummary());
        toUpdateOne.setPersons(projectTaskDto.getPersons());
        toUpdateOne.setTaskStartDate(projectTaskDto.getTaskStartDate());
        toUpdateOne.setTaskEndDate(projectTaskDto.getTaskEndDate());
        toUpdateOne.setType(projectTaskDto.getType());
        toUpdateOne.setUpdatedAt(LocalDateTime.now());

        return ProjectTaskDTO.convert(projectTaskDAO.save(toUpdateOne));
    }

    /**
     * 6. 프로젝트 타스크/이슈 삭제- ProjectTaskController
     * @param id
     */
    public void deleteProjectTask(Long id){
        projectTaskDAO.deleteById(id);
    }

    /**
     * 타스크 시작일, 종료일 날짜 체크
     * @param projectTaskDto
     * @return
     */
    private boolean validateProjectTaskDate(ProjectTaskDTO projectTaskDto) {
        return projectTaskDto.getTaskEndDate().isAfter(projectTaskDto.getTaskStartDate());
    }
}
