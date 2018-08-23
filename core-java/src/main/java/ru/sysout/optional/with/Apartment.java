package ru.sysout.optional.with;

import java.util.Optional;

public class Apartment {
	private Kitchen kitchen;

	public Optional<Kitchen> getKitchen() {
		return Optional.ofNullable(kitchen);
	}

	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

}
