package com.github.thofis.tscg.stocknames;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stocks")
public class StocksController {
	@GetMapping
	@CrossOrigin(origins = "http://localhost:8081")
	Mono<Stocks> get() {
		return Mono.just(new Stocks("Acme Corp.", "Parker Industries"));
	}
}
