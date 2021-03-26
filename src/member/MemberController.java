package member;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.Initializable;
import javafx.scene.Parent;
import member.service.MemberService;
import member.service.MemberServiceImpl;

public class MemberController implements Initializable{
	Parent root;
	MemberService ms;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms=new MemberServiceImpl();
	}
	
	public void setRoot(Parent root) {
		this.root=root;
		ms.setRoot(root);
	}
	
	public void joinProc() { //회원가입 버튼
		ms.join();
	}
	public void cancelProc() {
		ms.memberClose();
	}
	public void pwCheckProc() {//패스워드 확인 버튼
		ms.checkPw();
	}
}
