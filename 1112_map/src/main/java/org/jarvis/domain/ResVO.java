package org.jarvis.domain;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResVO {
	private Integer no;
	private String name, category;
	private double lat, lng;
	private List<RepVO> reps;

	public double getAvgScore() {
		double result = 0;
		if (this.reps.size() != 0) {
			for (RepVO rep : this.reps) {
				result += rep.getScore();
			}
			result = result / reps.size();
		}
		return Math.round(result*10)/10.0;
	}
}
