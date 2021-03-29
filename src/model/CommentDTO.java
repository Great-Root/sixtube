package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CommentDTO {

////	private int cnum;
//	private String userId;
//	private String content;
//	private int vnum;
	
	
	private SimpleIntegerProperty cnum = new SimpleIntegerProperty();
	private SimpleStringProperty userId = new SimpleStringProperty("");
	private SimpleStringProperty content = new SimpleStringProperty("");
	private SimpleIntegerProperty vnum = new SimpleIntegerProperty(); 
	
	public CommentDTO (int cnum,String userId, String content, int vnum) {
		this.cnum = new SimpleIntegerProperty(cnum);
		this.userId = new SimpleStringProperty(userId);
		this.content = new SimpleStringProperty(content);
		this.vnum = new SimpleIntegerProperty(vnum);
		
	}
	
	public int getCnum() {
		return cnum.get();
	}
	public int getVnum() {
		return vnum.get();
	}
	public void setVnum(SimpleIntegerProperty vnum) {
		this.vnum = vnum;
	}
	public void setCnum(SimpleIntegerProperty cnum) {
		this.cnum = cnum;
	}
	public String getUserId() {
		return userId.get();
	}
	public void setUserId(SimpleStringProperty userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content.get();
	}
	public void setContent(SimpleStringProperty content) {
		this.content = content;
	}
	
}
