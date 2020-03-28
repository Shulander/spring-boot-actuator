package us.vicentini.repositories;

import org.springframework.data.repository.CrudRepository;
import us.vicentini.domain.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {
}
