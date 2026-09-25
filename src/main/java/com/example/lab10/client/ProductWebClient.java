package com.example.lab10.client;

import com.example.lab10.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ProductWebClient — Reactive HTTP Client
 *
 * ✅ WebClient setup และ Bean config ครบแล้ว
 * ✅ method getProductById() ทำเสร็จแล้วเป็นตัวอย่าง (30%)
 * ❌ TODO: เติม method body ที่เหลือ (70%)
 *
 * WebClient method chain:
 * client.get() ← HTTP method
 * .uri("/products/{id}", id) ← URL
 * .retrieve() ← เริ่มรับ response
 * .bodyToMono(T.class) ← แปลงเป็น Mono<T>
 * .bodyToFlux(T.class) ← แปลงเป็น Flux<T>
 */
@Component
public class ProductWebClient {

    private final WebClient client = WebClient.create("http://localhost:8080");

    public Mono<Product> getProductById(String id) {
        return client.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // 1. ดึงทั้งหมด
    public Flux<Product> getAllProducts() {
        return client.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

    // 2. สร้างสินค้าใหม่
    public Mono<Product> createProduct(Product product) {
        return client.post()
                .uri("/products")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    // 3. ลบสินค้า
    public Mono<Void> deleteProduct(String id) {
        return client.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // 4. กรองตาม category
    public Flux<Product> getByCategory(String category) {
        return client.get()
                .uri("/products/category/{category}", category)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    // 5. ราคาหลังส่วนลด
    public Mono<Double> getDiscountedPrice(String id) {
        return client.get()
                .uri("/products/{id}/price", id)
                .retrieve()
                .bodyToMono(Double.class)
                .doOnNext(price -> System.out.println("Price: " + price));
    }
}
