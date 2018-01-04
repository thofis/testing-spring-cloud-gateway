package com.github.thofis.tscg.stockvalues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableWebSocket
public class StocksConfig implements WebSocketConfigurer, SchedulingConfigurer {

	@Autowired
	StocksHandler stocksHandler;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(stocksHandler, "/data");
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(taskExecutor());
	}

	@Bean
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}
}