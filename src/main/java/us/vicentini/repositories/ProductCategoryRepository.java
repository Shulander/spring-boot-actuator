package us.vicentini.repositories;

import org.springframework.data.repository.CrudRepository;
import us.vicentini.domain.ProductCategory;


public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {
}
