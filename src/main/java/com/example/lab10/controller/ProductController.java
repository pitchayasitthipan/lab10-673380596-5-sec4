package com.example.lab10.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab10.model.Product;
import com.example.lab10.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ProductController — Reactive REST Controller
 *
 * ✅ @RestController, @RequestMapping, Constructor Injection ครบแล้ว
 * ✅ endpoint GET /products/{id} ทำเสร็จแล้วเป็นตัวอย่าง (30%)
 * ❌ TODO: เติม method body ของ endpoint ที่เหลือ (70%)
 *
 * Endpoints ที่ต้องทำทั้งหมด:
 * GET /products → Flux<Product> (ดึงทั้งหมด)
 * GET /products/{id} → Mono<Product> ✅ ตัวอย่างทำแล้ว
 * POST /products → Mono<Product> (บันทึก)
 * DELETE /products/{id} → Mono<Void> (ลบ)
 * GET /products/category/{cat} → Flux<Product> (กรอง)
 * GET /products/{id}/price → Mono<Double> (ราคาหลังลด)
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ตัวอย่างที่ทำเสร็จแล้ว
    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable String id) {
        return service.getById(id);
    }

    // 1. ดึงทั้งหมด
    @GetMapping
    public Flux<Product> getAll() {
        return service.getAll();
    }

    // 2. บันทึกสินค้า
    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    // 3. ลบสินค้า
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    // 4. กรองตาม category
    @GetMapping("/category/{category}")
    public Flux<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    // 5. ราคาหลังส่วนลด
    @GetMapping("/{id}/price")
    public Mono<Double> getDiscountedPrice(@PathVariable String id) {
        return service.getDiscountedPrice(id);
    }
}