package member;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonClass;
import common.CommonService;
import common.LoginUser;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import video.VideoStage;

public class Controller implements Initializable {
	Parent root;
	MemberService ms;
	MemberMain mm;
	VideoStage s = new VideoStage();
	
	//userId가져오기 위해서 static 이용
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
		if(ms.login(root)) {
			s.showVideoList();
		}
	}
	public void registerProc() {
		mm.setMemberStage();
	}
	public void cancelProc() {
		Controller.cs.exit(root);
	}
	public void onEnter() {
		loginProc();
	}
}
