package model;

public class VideoDTO {
	private int vnum;
	private String title;
	private int likes;
	private int disLikes;
	int getVnum() {
		return vnum;
	}
	void setVnum(int vnum) {
		this.vnum = vnum;
	}
	String getTitle() {
		return title;
	}
	void setTitle(String title) {
		this.title = title;
	}
	int getLikes() {
		return likes;
	}
	void setLikes(int likes) {
		this.likes = likes;
	}
	int getDisLikes() {
		return disLikes;
	}
	void setDisLikes(int disLikes) {
		this.disLikes = disLikes;
	}
	
}
