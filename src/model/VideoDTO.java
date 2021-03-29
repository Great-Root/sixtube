package model;

import java.sql.Timestamp;

public class VideoDTO {
	private int vnum;
	private String vpath;
	private String title;
	private String uploader;
	private int views;
	private Timestamp vdate;
	private int likes;
	private int disLikes;
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public String getVpath() {
		return vpath;
	}
	public void setVpath(String vpath) {
		this.vpath = vpath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Timestamp getVdate() {
		return vdate;
	}
	public void setVdate(Timestamp vdate) {
		this.vdate = vdate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDisLikes() {
		return disLikes;
	}
	public void setDisLikes(int disLikes) {
		this.disLikes = disLikes;
	}
	@Override
	public String toString() {
		return "VideoDTO [vnum=" + vnum + ", vpath=" + vpath + ", title=" + title + ", uploader=" + uploader
				+ ", views=" + views + ", vdate=" + vdate + ", likes=" + likes + ", disLikes=" + disLikes + "]";
	}
}
