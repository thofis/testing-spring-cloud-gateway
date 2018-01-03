package com.github.thofis.tscg.stocknames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stocks {
	private final List<String> names;

	public Stocks(String... names) {
		this.names = Arrays.asList(names);
	}

	public List<String> getNames() {
		return new ArrayList<>(names);
	}
}
