package com.example.project;

import com.example.project.auth.AuthenticationResponse;
import com.example.project.auth.AuthenticationService;
import com.example.project.auth.RegisterRequest;
import com.example.project.product.Category;
import com.example.project.product.Product;
import com.example.project.product.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.project.appuser.Role.ADMIN;


@SpringBootApplication
public class ProjectECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectECommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service, ProductService productService) {
		return args -> {
			var admin = RegisterRequest.builder()
					.fullName("Admin")
					.email("admin@gmail.com")
					.password("admin")
					.role(ADMIN)
					.build();
			AuthenticationResponse response = service.register(admin);
			System.out.println("Admin access token: " + response.getAccessToken());
			System.out.println("Admin refresh token: " + response.getRefreshToken());

			// Add some categories and products
			Category category01 = new Category("Electronics");
			Category category02 = new Category("Clothes");
			Category category03 = new Category("Books");
			Product product01 = new Product("Laptop", 1000.0, 10, category01);
			Product product02 = new Product("T-shirt", 20.0, 100, category02);
			Product product03 = new Product("Java Programming", 50.0, 50, category03);
			Product product04 = new Product("Headphone", 50.0, 50, category01);
			Product product05 = new Product("Jeans", 50.0, 50, category02);
			Product product06 = new Product("Python Programming", 50.0, 50, category03);
			productService.addCategory(category01);
			productService.addCategory(category02);
			productService.addCategory(category03);
			productService.addProduct(product01);
			productService.addProduct(product02);
			productService.addProduct(product03);
			productService.addProduct(product04);
			productService.addProduct(product05);
			productService.addProduct(product06);
		};
	}
}
