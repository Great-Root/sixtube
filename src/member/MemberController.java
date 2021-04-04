package member;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonClass;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import member.service.MemberService;
import member.service.MemberServiceImpl;

public class MemberController implements Initializable{
	Parent root;
	MemberService ms;
	
	public static CommonService cs;
	static {
		cs = new CommonClass();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms=new MemberServiceImpl();
	}
	
	public void setRoot(Parent root) {
		this.root=root;
		ms.setRoot(root);
	}
	
	public void joinProc() {
		ms.join();
	}
	public void cancelProc() {
		ms.memberClose();
	}
	
	public void idCheckProc() {
		ms.checkId();
	}
	public void onEnter() {
		joinProc();
	}
}
