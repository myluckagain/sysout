package ru.sysout.optional.without;

public class DealWithNulls {

	public String getBrandInaDangerousWay(Apartment apartment) {
		String brand = apartment.getKitchen().getFridge().getBrand();
		return brand;
	}

	public String getBrandInaDefensiveWay(Apartment apartment) {
		String brand = "UNKNOWN";
		if (apartment != null) {
			Kitchen kitchen = apartment.getKitchen();
			if (kitchen != null) {
				Fridge fridge = kitchen.getFridge();
				if (fridge != null) {
					brand = fridge.getBrand();
				}
			}
		}
		return brand;
	}

}
