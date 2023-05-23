package com.openclassrooms.occhatop.repositories;
import com.openclassrooms.occhatop.models.rental.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
}
