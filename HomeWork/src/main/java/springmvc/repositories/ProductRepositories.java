package springmvc.repositories;

import org.springframework.stereotype.Component;
import springmvc.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepositories {
    private List<Product> listProduct;
    private Long maxId;

    @PostConstruct
    public void init(){
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1L, "Kartoshka", 111));
        listProduct.add(new Product(2L, "Ogurchiki", 222));
        listProduct.add(new Product(3L, "Pomidorki", 333));
        listProduct.add(new Product(4L, "Arbuz", 444));
        listProduct.add(new Product(5L, "Ananas", 555));
        this.maxId = 2L;
    }

    public List<Product> getProductAll(){
        return Collections.unmodifiableList(listProduct);
    }

    public Product getPriductsId(Long id){
        for (Product product : listProduct){
            if(id == product.getId()){
                return product;
            }
        }
        return null;
    }

    public Product saveOrUpdateStudent(Product product){
        if(product.getId() == null){
            maxId++;
            product.setId(maxId);
            listProduct.add(product);
            return product;
        }else{
            for(int i=0; i < listProduct.size(); i++){
                if (listProduct.get(i).getId().equals(product.getId())){
                    listProduct.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("What ???");
    }

    public void addProduct(String title, int cost){
       Long size = (long)listProduct.size() +1;
        listProduct.add(new Product(size, title, cost ));
    }


    

}
