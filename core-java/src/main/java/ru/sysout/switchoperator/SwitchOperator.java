package ru.sysout.switchoperator;

public class SwitchOperator {

	public String exampleOfUSe() {
		String day = "SUN";
		String result;

		/*if (day.equals("MON") || day.equals("TUE") || day.equals("WED") || day.equals("THU")) {
			result = "Time to work";
		} else if (day.equals("FRI")) {
			result = "Nearing weekend";
		} else if (day.equals("SAT") || day.equals("SUN")) {
			result = "Weekend!";
		} else {
			result = "Invalid day?";
		}*/

		switch (day) {
		case "MON":
		case "TUE":
		case "WED":
		case "THU":
			result = "Time to work";
			break;
		case "FRI":
			result = "Nearing weekend";
			break;
		case "SAT":
		case "SUN":
			result = "Weekend!";
			break;
		default:
			result = "Invalid day?";
		}
		return result;
	}

	public String forgetBreak() {
		String day = "MON";
		String result;

		switch (day) {
		case "MON":
		case "TUE":
		case "WED":
		case "THU":
			System.out.println("Time to work");
			result = "Time to work";
		case "FRI":
			System.out.println("Nearing weekend");
			result = "Nearing weekend";

		case "SAT":
		case "SUN":
			System.out.println("Weekend!");
			result = "Weekend!";
		default:
			System.out.println("Invalid day?");
			result = "Invalid day?";

		}
		return result;
	}

	public String compareStringObject() {
		String day = new String("MON");

		String result;

		switch (day) {
		case "MON":
		case "TUE":
		case "WED":
		case "THU":
			result = "Time to work";
			break;
		case "FRI":
			result = "Nearing weekend";
			break;
		case "SAT":
		case "SUN":
			result = "Weekend!";
			break;
		default:
			result = "Invalid day?";
			break;
		}
		return result;
	}

	public String caseNotFinal() {
		String day = new String("MON");

		final String m = "MON";
		String result;

		switch (day) {
		case m:
		case "TUE":
		case "WED":
		case "THU":
			result = "Time to work";
			break;
		case "FRI":
			result = "Nearing weekend";
			break;
		case "SAT":
		case "SUN":
			result = "Weekend!";
			break;
		default:
			result = "Invalid day?";
			break;
		}
		return result;
	}

	
}
