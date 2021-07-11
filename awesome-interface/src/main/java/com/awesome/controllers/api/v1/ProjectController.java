package com.awesome.controllers.api.v1;

import com.awesome.domains.entities.Project;
import com.awesome.domains.entities.ProjectTask;
import com.awesome.domains.services.ProjectDTO;
import com.awesome.domains.services.ProjectService;
import com.awesome.domains.services.ProjectTaskDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 1. 프로젝트 리스트
     * @return
     */
    @GetMapping("/")
    public List<ProjectDTO> projectList() {
        List<ProjectDTO> projectList = projectService.getProjectList();

        return projectList;
    }

    /**
     * 2. 특정 프로젝트
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ProjectDTO projectOne(@PathVariable("id") Long id) {
        ProjectDTO project = projectService.getProject(id);

        return project;
    }

    /**
     * 3. 프로젝트 Like 검색
     * @param projectName
     * @return
     */
    @GetMapping("/nameLike/{name}")
    public List<ProjectDTO> projectNameLike(@PathVariable("name") String projectName) {
        List<ProjectDTO> projectNameLike = projectService.getProjectNameLike(projectName);

        return projectNameLike;
    }

    /**
     * 4. 특정 프로젝트의 타스크 리스트
     * @param id
     * @return
     */
    @GetMapping("/{id}/tasks")
    public List<ProjectTaskDTO> projectTaskList(@PathVariable("id") Long id) {
        List<ProjectTaskDTO> projectTaskList = projectService.getProjectTaskListByProject(id);

        return projectTaskList;
    }

    /**
     * 5. 프로젝트 생성
     * @param projectDto
     * @return
     */
    @PostMapping
    public ProjectDTO projectCreate(@RequestBody ProjectDTO projectDto) {
        ProjectDTO createdProject = projectService.createProject(projectDto);

        return createdProject;
    }

    /**
     * 6. 프로젝트 수정
     * @param projectDto
     * @return
     */
    @PutMapping("/{id}")
    public ProjectDTO projectUpdate(@RequestBody ProjectDTO projectDto) {
        ProjectDTO updatedProject = projectService.updateProject(projectDto);

        return updatedProject;
    }

    /**
     * 7. 프로젝트 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String projectDelete(@PathVariable("id") Long id) {
        projectService.deleteProject(id);

        return null;
    }
}