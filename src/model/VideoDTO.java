package model;

public class VideoDTO {
	private int vnum;
	private String title;
	private int likes;
	private String vpath;
	private String thpath;
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getVpath() {
		return vpath;
	}
	public void setVpath(String vpath) {
		this.vpath = vpath;
	}
	public String getThpath() {
		return thpath;
	}
	public void setThpath(String thpath) {
		this.thpath = thpath;
	}
	@Override
	public String toString() {
		return "VideoDTO [vnum=" + vnum + ", title=" + title + ", likes=" + likes + ", vpath=" + vpath + ", thpath="
				+ thpath + "]";
	}

}
