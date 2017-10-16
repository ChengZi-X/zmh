package com.zmh.zz.zmh.wheelview;

import java.io.Serializable;

public class PickersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String showConetnt;
	private String showId;

	public String getShowConetnt() {
		return showConetnt;
	}

	public String getShowId() {
		return showId;
	}

	public PickersBean(String showConetnt, String showId) {
		super();
		this.showConetnt = showConetnt;
		this.showId = showId;
	}
}
