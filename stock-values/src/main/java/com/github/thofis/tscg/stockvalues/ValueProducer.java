package com.github.thofis.tscg.stockvalues;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Random;

@Component
public class ValueProducer {

	private static final Logger log = LoggerFactory.getLogger(ValueProducer.class);

	private double MIN_VALUE = 100.0;
	private double RANGE = 50.0;
	private Random random = new Random();

	@Autowired
	StocksHandler stocksHandler;

	@Scheduled(fixedRate = 2000)
	public void nextValue() {
		log.debug("nextValue");
		stocksHandler.getSessions().forEach(session -> {
			StockValue value = createRandomValue();
			final String json = new Gson().toJson(value);
			try {
				session.sendMessage(new TextMessage(json));
			} catch (IOException e) {
				log.error("Error while sending message to session {}", session.getId(), e);
			}
		});
	}

	private StockValue createRandomValue() {
		final double value = MIN_VALUE + (RANGE * random.nextDouble());
		return new StockValue(value);
	}

}
