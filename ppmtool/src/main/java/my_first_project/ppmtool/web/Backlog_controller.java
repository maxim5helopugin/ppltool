package my_first_project.ppmtool.web;

import my_first_project.ppmtool.domain.ProjectTask;
import my_first_project.ppmtool.services.MapValidationErrorService;
import my_first_project.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class Backlog_controller {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlogId}")
    public ResponseEntity<?> addProjectTastToBacklog(@Valid @RequestBody  ProjectTask projectTask,
                                                     BindingResult result, @PathVariable String backlogId){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)
            return errorMap;
        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlogId, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);

    }

    @GetMapping("/{backlogId}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlogId){
        return projectTaskService.getProjectTasks(backlogId);
    }

    @GetMapping("/{backlogId}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlogId, @PathVariable String pt_id){
        ProjectTask projectTask = projectTaskService.findPtByProjectSequence(backlogId, pt_id);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PutMapping("/{backlogId}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlogId, @PathVariable String pt_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)
            return errorMap;
        ProjectTask updatedProjectTask = projectTaskService.updateByProjectSequence(projectTask, backlogId, pt_id);
        return new ResponseEntity<ProjectTask>(updatedProjectTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlogId}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlogId, @PathVariable String pt_id){
        projectTaskService.deleteProjectTask(backlogId, pt_id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }
}
