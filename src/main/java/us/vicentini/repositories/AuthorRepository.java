package us.vicentini.repositories;

import org.springframework.data.repository.CrudRepository;
import us.vicentini.domain.Author;


public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
