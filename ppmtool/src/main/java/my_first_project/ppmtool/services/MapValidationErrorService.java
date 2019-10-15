package my_first_project.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationService(BindingResult result){
        if(result.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();

            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
