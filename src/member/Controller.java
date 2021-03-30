package member;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonClass;
import common.CommonService;
import common.LoginUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import video.VideoListController;
import video.VideoStage;

public class Controller implements Initializable {
	Parent root;
	MemberService ms;
	MemberMain mm;
	VideoStage s = new VideoStage();
	
	public static LoginUser lu;
	static {
		lu = new LoginUser(); 
	}
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
		mm=new MemberMain();

	}
	public void loginProc() {
		System.out.println("로그인");
		
		if(ms.login(root)) {
			s.showVideoList();
			
		}
		
	}
	public void registerProc() {
		System.out.println("가입화면전환");
		mm.setMemberStage();
		
	}
	public void cancelProc() {
		Controller.cs.exit(root);
	}
	
}
