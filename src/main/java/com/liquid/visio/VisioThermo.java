package com.liquid.visio;

import com.liquid.visio.decoder.VisioThermoDecoder;
import com.liquid.visio.responses.VisioThermoResponse;

public class VisioThermo {

	public Float temp;

	public Float humidity;

	public Float sysVolt;

	public VisioThermo(Boolean probe, String message) throws Exception {

		if (probe == false) {
			VisioThermoResponse decodeWithoutProbe = VisioThermoDecoder.decodeWithoutProbe(message);

			this.temp = decodeWithoutProbe.getTemp();
			this.humidity = decodeWithoutProbe.getHumidity();
			this.sysVolt = decodeWithoutProbe.getSysVolt();

		} else {
			VisioThermoResponse decodeWithProbe = VisioThermoDecoder.decodeWithProbe(message);
			this.temp = decodeWithProbe.getTemp();
			this.humidity = decodeWithProbe.getHumidity();
			this.sysVolt = decodeWithProbe.getSysVolt();

		}

	}

}
