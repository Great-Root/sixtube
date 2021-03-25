package member;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonClass;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import video.VideoStage;

public class Controller implements Initializable {
	Parent root;
	MemberService ms;
	
	public static CommonService cs;
	static {
		cs = new CommonClass();
	}
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms = new MemberServiceImpl();
	}
	public void loginProc() {
		System.out.println("로그인");
	}
	public void registerProc() {
		System.out.println("가입화면전환");
	}
	public void cancelProc() {
		Controller.cs.exit(root);
	}
	
}
