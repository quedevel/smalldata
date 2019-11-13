package org.jarvis.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RepVO {

	private Integer rno, no, score;
	private String reply;
	private Date regdate;
}
