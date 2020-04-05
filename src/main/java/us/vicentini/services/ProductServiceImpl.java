package us.vicentini.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.vicentini.domain.Product;
import us.vicentini.repositories.ProductRepository;
import us.vicentini.services.jms.JmsTextMessageService;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final JmsTextMessageService jmsTextMessageService;
    private final Counter getProductCounter;
    private final Counter listProductsCounter;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              JmsTextMessageService jmsTextMessageService,
                              MeterRegistry meterRegistry) {
        this.productRepository = productRepository;
        this.jmsTextMessageService = jmsTextMessageService;

//        https://blog.autsoft.hu/defining-custom-metrics-in-a-spring-boot-application-using-micrometer/
        listProductsCounter = meterRegistry.counter("us.vicentini.product.services.listProducts");
        getProductCounter = Counter.builder("us.vicentini.product.services.getProduct")
                .tag("type", "ale")
                .description("The number of getProduct(id) calls")
                .register(meterRegistry);
    }


    @Override
    public Product getProduct(Integer id) {
        jmsTextMessageService.sendTextMessage("Fetching Product ID: " + id);
        getProductCounter.increment();
        return productRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Product> listProducts() {
        jmsTextMessageService.sendTextMessage("Listing Products");
        listProductsCounter.increment();
        return IteratorUtils.toList(productRepository.findAll().iterator());
    }

}
