package controller;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.Product;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

@RestController
public class ProductController {

	private final AtomicLong counter = new AtomicLong();
	// Store the products
	private Map<Long, Product> products = new HashMap<Long, Product>();

	// create product
	@RequestMapping(value = "/v1/products", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product createProduct(@RequestBody Product product, HttpServletResponse response) throws Exception {
		product.setId(counter.incrementAndGet());
		products.put(product.getId(), product);
		response.setStatus(HttpStatus.CREATED.value());
		return product;
	}

	// Update product by Id
	@RequestMapping(value = "/v1/products", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@RequestBody Product product, HttpServletResponse response) throws Exception {
		Product productOld = products.get(product.getId());
		if (productOld == null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return null;
		}
		products.put(product.getId(), product);
		response.setStatus(HttpStatus.CREATED.value());
		return product;
	}

	// query product by Id
	@RequestMapping(value = "/v1/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product queryProduct(@RequestParam(value = "id") long id, HttpServletResponse response) throws Exception {
		Product product = products.get(id);
		if (product != null)
			return product;
		else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return null;
		}
	}

	// delete product by Id
	@RequestMapping(value = "/v1/products", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteProduct(@RequestParam(value = "id") long id, HttpServletResponse response) throws Exception {
		Product product = products.get(id);
		if (product != null) {
			products.remove(id);
			return "product with Id(" + id + ") deleted.";
		} else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return "product with Id(" + id + ") not found.";
		}
	}
}
