package br.com.crud.crud_products.http.controller;

import br.com.crud.crud_products.entity.Product;
import br.com.crud.crud_products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:4200")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Product> listProduct() {
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:4200")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    public void removeProduct(@PathVariable("id") Long id){
        productService.getProductById(id)
                .map(product -> {
                    productService.remove(product.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        productService.getProductById(id)
            .map(productBase -> {
                productBase.setName(product.getName());
                productBase.setWeight(product.getWeight());
                productBase.setPrice(product.getPrice());
                productBase.setDescription(product.getDescription());
                productService.addProduct(productBase);
                return productBase;
            })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível editar. Produto não encontrado."));
    }

}
