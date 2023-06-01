package com.openclassrooms.occhatop.repositories;
import com.openclassrooms.occhatop.models.message.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
