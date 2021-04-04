package member;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonClass;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
		mos.setRoot(root); 
	}
	
	public void memberDeleteProc()  {
		System.out.println("회원 정보 삭제");
		System.out.println("삭제할 아이디 : "+Controller.lu.getUserId());
		mom.deleteMember();
		}
	
	public void memberModifyProc() {
		System.out.println("비밀 번호 수정");
		System.out.println("비밀번호 수정할 아이디 : "+Controller.lu.getUserId());
		mos.modify();
	}
	
	
	public void realMemberDelete() {
		mos.delete();
		System.out.println("회원 삭제 완료");	
	}
	
	public void onEnter() {
		memberModifyProc();
	}

}
