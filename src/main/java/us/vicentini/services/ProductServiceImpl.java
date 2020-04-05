package us.vicentini.services;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
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
    private final MeterRegistry meterRegistry;
    private final Counter getProductCounter;
    private final Counter listProductsCounter;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              JmsTextMessageService jmsTextMessageService,
                              MeterRegistry meterRegistry) {
        this.productRepository = productRepository;
        this.jmsTextMessageService = jmsTextMessageService;
        this.meterRegistry = meterRegistry;

//        https://blog.autsoft.hu/defining-custom-metrics-in-a-spring-boot-application-using-micrometer/
        listProductsCounter = meterRegistry.counter("us.vicentini.services.ProductService.listProducts");
        getProductCounter = Counter.builder("us.vicentini.services.ProductService.getProduct")
                .tag("type", "ale")
                .description("The number of getProduct(id) calls")
                .register(meterRegistry);
    }


    @Override
    @Timed(value = "us.vicentini.services.ProductServiceImpl.getProduct.timed")
    public Product getProduct(Integer id) {
        jmsTextMessageService.sendTextMessage("Fetching Product ID: " + id);
        getProductCounter.increment();
        return productRepository.findById(id)
                .orElse(null);
    }


    @Override
    @Timed(value = "us.vicentini.services.ProductServiceImpl.listProducts.timed")
    public List<Product> listProducts() {
        jmsTextMessageService.sendTextMessage("Listing Products");
        listProductsCounter.increment();

        List<Product> products = IteratorUtils.toList(productRepository.findAll().iterator());
        return meterRegistry.gaugeCollectionSize("us.vicentini.services.ProductService.countProducts", Tags.empty(),
                                                 products);
    }

}
