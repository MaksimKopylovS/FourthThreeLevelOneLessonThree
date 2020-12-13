package springmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springmvc.model.Product;
import springmvc.repositories.ProductRepositories;

import java.util.List;

@Component
public class ProductService {
    private ProductRepositories productRepositories;

    @Autowired
    public void setProductRepositories(ProductRepositories productRepositories){
        this.productRepositories = productRepositories;
    }

    public Product saveOrUpdateProduct(Product product){
        return productRepositories.saveOrUpdateStudent(product);
    }

    public void addProduct( String title, int cost){
        productRepositories.addProduct(title, cost);
    }

    public Product findId(Long id){
        return productRepositories.getPriductsId(id);
    }

    public List<Product> getAllProduct(){
        return productRepositories.getProductAll();
    }
}
