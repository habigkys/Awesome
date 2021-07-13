package com.awesome.domains.Project.entities;

import com.awesome.domains.ProjectTask.enums.TaskType;
import com.awesome.domains.ProjectTask.entities.ProjectTaskDAO;
import com.awesome.domains.ProjectTask.entities.ProjectTaskEntity;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AwesomeBootTest
class ProjectTaskTest {

    @Autowired
    private ProjectTaskDAO projectTaskDAO;
    @Test
    void testAssertNotNull() {
        assertNotNull(projectTaskDAO);
    }

    @Test
    void testSave() {
        ProjectTaskEntity projectTaskEntity = new ProjectTaskEntity();
        projectTaskEntity.setProjectId(1L);
        projectTaskEntity.setParentTaskId(0L);
        projectTaskEntity.setSummary("");
        projectTaskEntity.setTaskStartDate(LocalDate.now());
        projectTaskEntity.setTaskEndDate(LocalDate.now());
        projectTaskEntity.setPersons("");
        projectTaskEntity.setType(TaskType.TASK);
        projectTaskEntity.setCreatedAt(LocalDateTime.now());
        projectTaskEntity.setUpdatedAt(LocalDateTime.now());

        projectTaskDAO.save(projectTaskEntity);
    }

    @Test
    void testSaveList() {
        List<ProjectTaskEntity> byIdList = projectTaskDAO.findAllByProjectId(1L);

        assertFalse(CollectionUtils.isEmpty(byIdList));
    }
}