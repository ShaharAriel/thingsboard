package com.devicewise.tr50.api.response.location;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenLocationStats extends DwOpenGenericResponse{
	
    private long decodeHits,
    			 decodeMisses,
    			 encodeHits,
    			 encodeMisses,
    			 weatherHits,
    			 weatherMisses;

	public long getDecodeHits() {
		return decodeHits;
	}

	public void setDecodeHits(long decodeHits) {
		this.decodeHits = decodeHits;
	}

	public long getDecodeMisses() {
		return decodeMisses;
	}

	public void setDecodeMisses(long decodeMisses) {
		this.decodeMisses = decodeMisses;
	}

	public long getEncodeHits() {
		return encodeHits;
	}

	public void setEncodeHits(long encodeHits) {
		this.encodeHits = encodeHits;
	}

	public long getEncodeMisses() {
		return encodeMisses;
	}

	public void setEncodeMisses(long encodeMisses) {
		this.encodeMisses = encodeMisses;
	}

	public long getWeatherHits() {
		return weatherHits;
	}

	public void setWeatherHits(long weatherHits) {
		this.weatherHits = weatherHits;
	}

	public long getWeatherMisses() {
		return weatherMisses;
	}

	public void setWeatherMisses(long weatherMisses) {
		this.weatherMisses = weatherMisses;
	}

}
