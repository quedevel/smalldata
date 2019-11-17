package org.zerock.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttachDTO {
	
	private String fname, uuid;
	private boolean image;
	
	public AttachDTO(String fname, String uuid, boolean image) {
		super();
		this.fname = fname;
		this.uuid = uuid;
		this.image = image;
	}

	public AttachDTO(String fname, String uuid) {
		this(fname, uuid, false);
	}
	
}
