package com.musikais.model;

public class Recommendation {
	
	private String musicName;

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	@Override
	public String toString() {
		return "Recommendation [musicName=" + musicName + "]";
	}
	
}
