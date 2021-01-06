package com.ecommerce.springbootecommerce.controller;

import com.ecommerce.springbootecommerce.exception.ProductNotFoundException;
import com.ecommerce.springbootecommerce.model.Product;
import com.ecommerce.springbootecommerce.repository.ProductRepository;
import com.ecommerce.springbootecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/jpa/products")
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/jpa/products/{id}")
    public EntityModel<Product> getUser(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent())
            throw new ProductNotFoundException("id-" + id);

        // "all-users", SERVER_PATH + "/users"
        // retrieveAllUsers
        EntityModel<Product> resource = new EntityModel<Product>(product.get());

        Link findLink = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        resource.add(findLink.withRel("all-products"));
        // HATEOAS
        return resource;
    }


}
