package model;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private int gender;
	private String age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setAge(String age) {
		this.age=age;
	}
	public String getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	
}
