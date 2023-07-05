package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Product;
import com.fiap.techChallenge.application.core.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.in.ProductInPort;
import com.fiap.techChallenge.application.ports.out.ProductOutPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

import static com.fiap.techChallenge.application.core.util.ConstantsUtil.ALREADY_EXISTS_PRODUCT;
import static com.fiap.techChallenge.application.core.util.ConstantsUtil.PRODUCT_NOT_FOUND;

@AllArgsConstructor
public class ProductInService implements ProductInPort {
    private final ProductOutPort port;
    @Override
    public Product saveProduct(Product product) {
        if(Objects.nonNull(port.getProductById(product.id())))
            throw new AlreadyExistsException(ALREADY_EXISTS_PRODUCT);

        return port.saveProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productSaved = getProductById(product.id());

        productSaved.builder()
                .category(product.category())
                .price(product.price())
                .description(product.description())
                .images(product.images())
                .build();

        return port.updateProduct(productSaved);
    }

    @Override
    public Product getProductById(Long id) {
        Product product = port.getProductById(id);
        if(Objects.isNull(product))
            throw new NotFoundException(PRODUCT_NOT_FOUND);

        return product;
    }

    @Override
    public List<Product> getAllProducts(String category) {
        List<Product> products = port.getAllProducts(category);
        if(Objects.isNull(products) || products.isEmpty())
            throw new NotFoundException(PRODUCT_NOT_FOUND);

        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = port.getProductById(id);
        if(Objects.isNull(product))
            throw new NotFoundException(PRODUCT_NOT_FOUND);

        port.deleteProduct(id);
    }
}
