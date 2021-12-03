package gzaesthetics.com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gzaesthetics.com.data.model.Treatment;

@Repository                                 //Entity, Id data type
public interface TreatmentRepo extends JpaRepository<Treatment, Integer> {
    // create
    // reads
    // updates
    // deletes
    //        SELECT * FROM treatment WHERE name = ?
    List<Treatment> findTreatmentByName(String name);
    @Query("SELECT c FROM Treatment c WHERE c.name = ?1 AND c.price = ?2")
    List<Treatment> findTreatmentsByNameAndPrice(String name, Double price);
    @Query("SELECT c FROM Treatment c WHERE c.price = ?1 AND c.duration = ?2")
    List<Treatment> findTreatmentsByPriceAndDuration(Double price, Integer duration );
}