package gzaesthetics.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import gzaesthetics.com.data.model.Treatment;
import gzaesthetics.com.repo.TreatmentRepo;




@Service
public class TreatmentService {
    private TreatmentRepo repo;
    public TreatmentService(TreatmentRepo repo) {
        this.repo = repo;
    }
    public List<Treatment> getAllTreatments() {
        // SELECT * FROM treatment;
        return this.repo.findAll();
    }
    public Treatment updateTreatment(Treatment treatment, Integer id) {
        //find the treatment to update
        Optional<Treatment> treatmentToFind = this.repo.findById(id);
        Treatment treatmentToUpdate = treatmentToFind.get();
        //set that treatment with the new values
        treatmentToUpdate.setName(treatment.getName());
        treatmentToUpdate.setDescription(treatment.getDescription());
        treatmentToUpdate.setPrice(treatment.getPrice());
        treatmentToUpdate.setDuration(treatment.getDuration());
    
        //save updated treatment
        return this.repo.save(treatmentToUpdate);
    }
    
    public Treatment createTreatment(Treatment treatment) {
    	return this.repo.save(treatment);
    	
    }
    
    public Treatment getById(Integer id) {
    	 Optional<Treatment> treatmentToFind = this.repo.findById(id);
         Treatment foundTreatment = treatmentToFind.get();
         return foundTreatment;
    }
    
    
    
    
    public boolean deleteTreatment(Integer id) {
        //find the treatment to update
   this.repo.deleteById(id);
    boolean exists =this.repo.existsById(id);
    // true
    return !exists;
    }
}