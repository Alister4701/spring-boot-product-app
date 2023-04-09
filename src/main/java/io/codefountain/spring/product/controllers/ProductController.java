package io.codefountain.spring.product.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;

import io.codefountain.spring.product.domain.Product;
import io.codefountain.spring.product.qr.QRCodeGenerator;
import io.codefountain.spring.product.qr.QRCodeService;
import io.codefountain.spring.product.repository.ProductRepository;

@Controller
public class ProductController {

	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	QRCodeService qrService;
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/products")
	public String todos(Model model) throws WriterException, IOException {
		model.addAttribute("products", qrService.populateQrForProducts(productRepository.findAll()));
		return "products";
	}
	
	@PostMapping("/productNew")
	public String add(@RequestParam String name, @RequestParam double price,@RequestParam int quantity, Model model) throws WriterException, IOException {
		Product product = new Product();
		
		product.setName(name);
		product.setPrice(price);
		product.setQuantity(quantity);
		//String qrText = name+","+quantity+","+price;
	
        //System.out.println("QR CODE : "+ qrcode.length());
      //  product.setQrText(qrText);
		productRepository.save(product);
		
		model.addAttribute("products", qrService.populateQrForProducts(productRepository.findAll()));
		return "redirect:/products";
	}
	
	@PostMapping("/productDelete/{id}")
	public String delete(@PathVariable long id, Model model) throws WriterException, IOException {
		productRepository.deleteById(id);
		model.addAttribute("products", qrService.populateQrForProducts(productRepository.findAll()));
		return "redirect:/products";
	}
	
	
}
