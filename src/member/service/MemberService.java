package member.service;

import javafx.scene.Parent;

public interface MemberService {
	
	public boolean login(Parent root);
	public void join();
	public void setRoot(Parent root);
	public void memberClose();
	public void checkId();
	
}
