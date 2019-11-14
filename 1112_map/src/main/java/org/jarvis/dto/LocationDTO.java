package org.jarvis.dto;

import lombok.Data;

@Data
public class LocationDTO {
	private double lat, lng;
	private String[] categorys;
}
