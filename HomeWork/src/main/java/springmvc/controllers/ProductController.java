package springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.model.Product;
import springmvc.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String showAllProduct(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/addform")
    public String showAddForm(){
        return "add_products_form";
    }

    @GetMapping("/add")

    public String product(@RequestParam(name = "title") String title, @RequestParam(name = "cost") Integer cost){
        productService.addProduct(title,cost);
        return "add_products_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.findId(id));
        return "edit_product_form";
    }

    @GetMapping("/find")
    public String findId(@RequestParam(name = "id") Long id, Model model){

        System.out.println(productService.findId(id).getId() +" "
                + productService.findId(id).getTitle() + " "
        + productService.findId(id).getCost());
        model.addAttribute("id",productService.findId(id).getId());
        model.addAttribute("title",productService.findId(id).getTitle());
        model.addAttribute("cost", productService.findId(id).getCost());
        return "find_id";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifierProduct){
        productService.saveOrUpdateProduct(modifierProduct);
        return "redirect:/products/";
    }

}
