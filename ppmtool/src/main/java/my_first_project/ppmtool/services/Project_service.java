package my_first_project.ppmtool.services;

import my_first_project.ppmtool.domain.Backlog;
import my_first_project.ppmtool.domain.Project;
import my_first_project.ppmtool.exceptions.ProjectIdException;
import my_first_project.ppmtool.repositories.BacklogRepository;
import my_first_project.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Project_service {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            String projectIdentifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(projectIdentifier);
            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifier);
            }
            if(project.getId() != null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }
            return projectRepository.save(project);
        }
        catch (Exception ex){
             throw new ProjectIdException("Project Id " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findProjectByIdentifier(String id){
        Project project = projectRepository.findByProjectIdentifier(id.toUpperCase());
        if(project == null)
            throw new ProjectIdException("Project Id " + id + " does not exists");
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void DeleteProjectById(String id){
        Project project = findProjectByIdentifier(id);
        if(project == null)
            throw new ProjectIdException("Project Id " + id + " does not exists, cannot delete");
        projectRepository.delete(project);
    }
}
