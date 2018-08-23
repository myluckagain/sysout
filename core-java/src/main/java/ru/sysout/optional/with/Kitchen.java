package ru.sysout.optional.with;

import java.util.Optional;

public class Kitchen {
	private Fridge fridge;

	public Optional<Fridge> getFridge() {
		return Optional.ofNullable(fridge);
	}

	public void setFridge(Fridge fridge) {
		this.fridge = fridge;
	}
}
