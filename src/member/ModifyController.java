package member;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.CommonClass;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import member.service.MemberServiceImpl;

public class ModifyController implements Initializable {
	Parent root;
	ModifyMain mom;
	MemberServiceImpl ms;
	
	public static CommonService cs;
	static {
		cs = new CommonClass();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mom=new ModifyMain();
	}
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void memberDeleteProc()  {
		System.out.println("회원 정보 삭제");
		mom.deleteMember();
		}
	
	public void memberModifyProc() {
		System.out.println("회원 정보 수정");
		//ms.modify();
	}
	
	
	public void realMemberDelete() {
		//DB 비번이랑 체크
		
		cs.alert("이용해주셔서 감사합니다.");
	}

}
