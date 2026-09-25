package com.example.lab10.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.lab10.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductRepository {
    private final Map<String, Product> store = new ConcurrentHashMap<>();

    // หา Product ตาม id -> คืนค่า Mono (1 หรือไม่มี)
    public Mono<Product> findById(String id) {
        Product product = store.get(id);
        return product != null ? Mono.just(product) : Mono.empty();
    }

    // หา Product ทั้งหมด -> คืนค่า Flux (หลายรายการ)
    public Flux<Product> findAll() {
        return Flux.fromIterable(store.values());
    }

    // บันทึก Product -> คืนค่า Mono (1 รายการที่บันทึก)
    public Mono<Product> save(Product product) {
        store.put(product.getId(), product);
        return Mono.just(product);
    }

    // ลบ Product ตาม id -> คืนค่า Mono<Void>
    public Mono<Void> deleteById(String id) {
        store.remove(id);
        return Mono.empty();
    }

    // กรองตาม category -> คืนค่า Flux (หลายรายการ)
    public Flux<Product> findByCategory(String category) {
        return findAll().filter(p -> p.getCategory().equalsIgnoreCase(category));
    }
}
