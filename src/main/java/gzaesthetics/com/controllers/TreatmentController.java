package gzaesthetics.com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gzaesthetics.com.data.model.Treatment;
import gzaesthetics.com.services.TreatmentService;

@RestController // allows us to send JSON data back, or other forms of data medium, indicates this is a component/bean to the AppContext
@RequestMapping(path = "/treatment")
public class TreatmentController {
    private TreatmentService service;
    public TreatmentController(TreatmentService service) {
        this.service = service;
    }
    // localhost:8080/treatment
    // 127.0.0.1:8080/treatment
    @GetMapping
    public ResponseEntity<List<Treatment>> get() {
        ResponseEntity<List<Treatment>> response = new ResponseEntity<List<Treatment>>(this.service.getAllTreatments(), HttpStatus.OK); // 200
        return response;
    }
                //  /treatment/update/
    @PutMapping("/update/{id}")
    public ResponseEntity<Treatment> updateTreatment(@RequestBody Treatment treatment, @PathVariable Integer id) {
        ResponseEntity<Treatment> response = new ResponseEntity<Treatment>(this.service.updateTreatment(treatment, id), HttpStatus.ACCEPTED); // 202
        return response;
    }
    
    //create
   @PostMapping("/create")
   public ResponseEntity<Treatment> createTreatment(@RequestBody Treatment treatment){
	   ResponseEntity<Treatment> response = new ResponseEntity<Treatment>(this.service.createTreatment(treatment),HttpStatus.CREATED); //201
	   return response;
   }
   
    // find by id
   
    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getById(@PathVariable Integer id){
    	return new ResponseEntity<Treatment>(this.service.getById(id), HttpStatus.OK);
    }
    
    
    
    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTreatment(@PathVariable Integer id) {
        boolean deleted = this.service.deleteTreatment(id);
        
    if(deleted) {
    	return new ResponseEntity<>(HttpStatus.OK);
    } else {
    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    }
    
}
