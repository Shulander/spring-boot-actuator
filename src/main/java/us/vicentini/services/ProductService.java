package us.vicentini.services;

import us.vicentini.domain.Product;

import java.util.List;


public interface ProductService {

    Product getProduct(Integer id);

    List<Product> listProducts();
}
