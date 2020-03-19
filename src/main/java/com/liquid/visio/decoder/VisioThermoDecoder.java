package com.liquid.visio.decoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.liquid.visio.responses.VisioThermoResponse;

public class VisioThermoDecoder {

	public static VisioThermoResponse decodeWithProbe(String message) {

		List<Float> splitParts = SplitParts(message, 4);
		HashMap<String, Float> map = new HashMap<>();
		map.put("sum", (float) 0);

		for (int i = 0; i < splitParts.size() - 1; i++) {
			Float floatOriginal = map.get("sum");

			Float floatSum = floatOriginal + (splitParts.get(i) - 10000) / 100;

			map.put("sum", floatSum);
		}

		VisioThermoResponse visioThermoResponse = new VisioThermoResponse();

		visioThermoResponse.setHumidity((float) 0);
		visioThermoResponse.setTemp(map.get("sum") / 4);
		visioThermoResponse.setSysVolt(splitParts.get(splitParts.size() - 1));

		return visioThermoResponse;

	}

	public static VisioThermoResponse decodeWithoutProbe(String message) {

		List<Float> splitParts = SplitParts(message, 3);

		VisioThermoResponse visioThermoResponse = new VisioThermoResponse();

		visioThermoResponse.setHumidity(splitParts.get(5) / 10);
		visioThermoResponse.setTemp((splitParts.get(2) - 1000) / 10);
		visioThermoResponse.setSysVolt(splitParts.get(splitParts.size() - 1));

		return visioThermoResponse;

	}

	private static List<Float> SplitParts(String string, int partitionSize) {
		List<Float> parts = new ArrayList<Float>();
		int len = string.length();
		for (int i = 0; i < len; i += partitionSize) {

			parts.add(convertToDecimal(string.substring(i, Math.min(len, i + partitionSize))));
		}
		return parts;
	}

	private static Float convertToDecimal(String string) {

		Float parseFloat = (float) Integer.parseInt(string, 16);

		return parseFloat;
	}

}
