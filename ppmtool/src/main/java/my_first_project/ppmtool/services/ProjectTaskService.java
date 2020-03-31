package my_first_project.ppmtool.services;

import my_first_project.ppmtool.domain.Backlog;
import my_first_project.ppmtool.domain.Project;
import my_first_project.ppmtool.domain.ProjectTask;
import my_first_project.ppmtool.repositories.BacklogRepository;
import my_first_project.ppmtool.repositories.ProjectRepository;
import my_first_project.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        projectTask.setBacklog(backlog);

        Integer sequence = backlog.getPtSequence();
        sequence++;
        backlog.setPtSequence(sequence);

        projectTask.setProjectSequence(projectIdentifier + "-" + sequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getPriority() == null)
            projectTask.setPriority(3);

        if(projectTask.getStatus() == null || projectTask.getStatus().equals(""))
            projectTask.setStatus("TODO");

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> getProjectTasks(String projectIdentifier){
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
    }
}
