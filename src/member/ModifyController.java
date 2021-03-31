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
import modify.service.ModifyService;
import modify.service.ModifyServiceImpl;

public class ModifyController implements Initializable {
	Parent root;
	ModifyMain mom;
	ModifyService mos;
	
	public static CommonService cs;
	static {
		cs = new CommonClass();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mom=new ModifyMain();
		mos=new ModifyServiceImpl();
	}
	public void setRoot(Parent root) {
		this.root = root;
		mos.setRoot(root); //아.. 나 전에도 이거 안써서 ㅠㅜㅠ
	}
	
	public void memberDeleteProc()  {
		System.out.println("회원 정보 삭제");
		mom.deleteMember();
		//mos.delete();
		}
	
	public void memberModifyProc() {
		System.out.println("비밀 번호 수정");
		mos.modify();
	}
	
	
	public void realMemberDelete() {
		mos.delete();
		System.out.println("회원 삭제 완료"); //근데 DB에서 삭제하는 거 못하겠어..
		cs.alert("이용해주셔서 감사합니다.");
	}

}
