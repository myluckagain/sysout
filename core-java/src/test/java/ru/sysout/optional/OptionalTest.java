package ru.sysout.optional;

import java.util.Optional;

import org.junit.Test;

import ru.sysout.optional.with.Apartment;
import ru.sysout.optional.with.Fridge;
import ru.sysout.optional.with.Kitchen;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OptionalTest {

	@Test
	public void whenWrapNull_thenEmptyOptional() {
		Optional<Kitchen> kitchen = Optional.empty();

		assertThat(kitchen.isPresent(), is(false));
	}

	@Test
	public void whenWrapNotNull_thenOf() {

		Optional<Kitchen> kitchen = Optional.of(new Kitchen());

		assertThat(kitchen.isPresent(), is(true));
	}

	@Test(expected = NullPointerException.class)
	public void givenNull_whenOf_thenException() {
		Optional.of(null);
	}

	@Test
	public void whenOfNullable_thenWrapAnyObject() {

		Optional<Kitchen> kitchen1 = Optional.ofNullable(new Kitchen());
		Optional<Kitchen> kitchen2 = Optional.ofNullable(null);

		assertThat(kitchen1.isPresent(), is(true));
		assertThat(kitchen2.isPresent(), is(false));

	}

	@Test
	public void whenNotNullCheck_thenDoSomething() {

		/*
		 * Kitchen kitchen =new Kitchen(); if (kitchen != null) {
		 * System.out.println(kitchen); }
		 */

		Optional<Kitchen> maybeKitchen = Optional.ofNullable(new Kitchen());

		maybeKitchen.ifPresent(System.out::println);
	}

	@Test
	public void whenNotNullCheck_thenAssign() {

		/*
		 * Kitchen kitchen = new Kitchen(); Kitchen anotherKitchen = kitchen !=
		 * null ? kitchen : new Kitchen();
		 */

		Optional<Kitchen> maybeKitchen = Optional.ofNullable(new Kitchen());
		Kitchen anotherKitchen = maybeKitchen.orElse(new Kitchen());
	}

	@Test
	public void whenNotNull_thenAssign() {

		Optional<Kitchen> maybeKitchen = Optional.ofNullable(new Kitchen());
		Kitchen kitchen = maybeKitchen.orElseThrow(IllegalArgumentException::new);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenNull_thenThrowException() {

		Optional<Kitchen> maybeKitchen = Optional.ofNullable(null);
		Kitchen kitchen = maybeKitchen.orElseThrow(IllegalArgumentException::new);
	}

	@Test
	public void whenCondition_thenDoSomething() {

		Fridge fridge = new Fridge();
		fridge.setBrand("LG");
		if (fridge != null && fridge.getBrand().equals("LG")) {
			System.out.println(fridge.getBrand());
		}

		Optional<Fridge> maybeFridge = Optional.ofNullable(fridge);
		maybeFridge.filter(f -> f.getBrand().equals("LG")).ifPresent(f -> System.out.println(f.getBrand()));
	}

	@Test
	public void whenMap_thenTransform() {

		Fridge fridge = new Fridge();
		fridge.setBrand("LG");
		if (fridge != null && fridge.getBrand().equals("LG")) {
			System.out.println(fridge.getBrand());
		}

		Optional<Fridge> maybeFridge = Optional.ofNullable(fridge);
		Optional<String> brand = maybeFridge.map(Fridge::getBrand);

		brand.filter(b -> b.equals("LG")).ifPresent(b -> System.out.println(b));
	}
	@Test
	public void whenMap_thenNestedOptional() {

		Apartment apartment = new Apartment();
		

		Optional<Apartment> maybeApartment = Optional.ofNullable(apartment);
		
		Optional<Optional<Kitchen>> mbKitchen=maybeApartment.map(Apartment::getKitchen);

	
		
	}
	@Test
	public void whenFlatMap_thenOk() {

		Apartment apartment = new Apartment();
		Kitchen kitchen=new Kitchen();
		Fridge fridge = new Fridge();
		
		fridge.setBrand("LG");
		kitchen.setFridge(fridge);
		apartment.setKitchen(kitchen);

		Optional<Apartment> maybeApartment = Optional.ofNullable(apartment);
		
	
		String brand = maybeApartment.flatMap(Apartment::getKitchen).flatMap(Kitchen::getFridge).map(Fridge::getBrand)
				.orElse("UNKNOWN");
		
		assertThat(brand, equalTo("LG"));
	}
	@Test
	public void whenFlatMap_thenNoException() {

		Apartment apartment = new Apartment();
		

		Optional<Apartment> maybeApartment = Optional.ofNullable(apartment);
		
	
		String brand = maybeApartment.flatMap(Apartment::getKitchen).flatMap(Kitchen::getFridge).map(Fridge::getBrand)
				.orElse("UNKNOWN");
		

		assertThat(brand, equalTo("UNKNOWN"));
	}
}
