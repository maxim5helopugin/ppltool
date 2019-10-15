package my_first_project.ppmtool.services;

import my_first_project.ppmtool.domain.Project;
import my_first_project.ppmtool.exceptions.ProjectIdException;
import my_first_project.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Project_service {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception ex){
             throw new ProjectIdException("Project Id " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }
}
