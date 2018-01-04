package com.github.thofis.tscg.stockvalues;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ValueProducer {

	private double MIN_VALUE = 100.0;
	private double RANGE = 50.0;

	private Random random = new Random();

	private SimpMessagingTemplate template;

	public ValueProducer(SimpMessagingTemplate template) {
		this.template = template;
	}

	@Scheduled(fixedRate = 2000)
	public void nextValue() {
		template.convertAndSend("/queue", createRandomValue());
	}

	private StockValue createRandomValue() {
		final double value = MIN_VALUE + (RANGE * random.nextDouble());
		return new StockValue(value);
	}

}
