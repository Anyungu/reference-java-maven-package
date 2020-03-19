package com.liquid.visio;

public class App {

	public static void main(String[] args) throws Exception {

		String probe = args[0];

		String message = args[1];

		if (probe.contentEquals("false")) {

			VisioThermo vs = new VisioThermo(false, message);
			vs.toString();
		}

		else {

			VisioThermo vs = new VisioThermo(true, message);
			vs.toString();

		}

	}
}