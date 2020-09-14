package com.example.consumingrest;

public enum TransactionType {
	credit("credit"),
	debit("debit");

	private final String value;

	TransactionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
