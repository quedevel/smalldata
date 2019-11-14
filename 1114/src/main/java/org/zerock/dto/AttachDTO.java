package org.zerock.dto;


public class AttachDTO {
	
	private String fileName;
	private boolean image;
	private String uuid;
	
	
	
	
	
	public AttachDTO(String fileName, String uuid) {
		this(fileName, false, uuid);
	}

	public AttachDTO(String fileName, boolean image, String uuid) {
		super();
		this.fileName = fileName;
		this.image = image;
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isImage() {
		return image;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		return "AttachDTO [fileName=" + fileName + ", image=" + image + ", uuid=" + uuid + "]";
	}
	
	
	
}
