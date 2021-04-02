package modify.service;

import common.LoginUser;
import database.member.MemberDAO;
import database.member.MemberDAOImpl;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import member.Controller;
import model.MemberDTO;

public class ModifyServiceImpl implements ModifyService {
	Parent root;

	@Override
	public void modify() {

		PasswordField nowPw=(PasswordField)root.lookup("#nowPw");
		PasswordField newPw=(PasswordField)root.lookup("#newPw");
		PasswordField newPwCheck=(PasswordField)root.lookup("#newPwCheck");

		System.out.println("현재 비밀번호 : "+nowPw.getText());
		System.out.println("새로운 비밀번호 : "+newPw.getText());
		System.out.println("새로운 비밀번호 확인 : "+newPwCheck.getText());

		String nowStr=nowPw.getText();
		String newStr=newPw.getText();
		String newCheckStr=newPwCheck.getText();
		if(nowPw.getText().isEmpty()){
			Controller.cs.alert("현재 비밀 번호를 입력해주세요");
		}else if(newPw.getText().isEmpty()) {
			Controller.cs.alert("변경할 비밀 번호를 입력해주세요");
		}else if(newPwCheck.getText().isEmpty()){
	         Controller.cs.alert("변경할 비밀 번호 확인란을 입력해주세요");
	      }else{
	         if(newStr.equals(newCheckStr)) {
	            
	            if(newStr.length()<8) {
	               Controller.cs.alert("비밀 번호를 8자 이상으로 설정해주세요.");
	            }else {
	               
	            
	            MemberDTO dto=new MemberDTO();
	            dto.setPw(newPw.getText());

	            MemberDAO ds=new MemberDAOImpl();
	            int result=ds.modifyMember(dto);
	            if(result==1) {
	               Controller.cs.alert("성공적으로 수정되었습니다.");
	               Controller.cs.exit(root);
	            }else {
	               Controller.cs.alert("수정이 실패했습니다.");
	            }
	         }
	         }else {
	            Controller.cs.alert("비밀번호가 일치하지 않습니다.");
	         }
	      }
	   }

	public void delete() {

		TextField id=(TextField)root.lookup("#fxId");
		int result=0;
		System.out.println("아이디 : "+id.getText());

		String delId=id.getText();
		if(delId.equals(Controller.lu.getUserId())) {
		MemberDTO dto=new MemberDTO();
		MemberDAO ds=new MemberDAOImpl();
		result=ds.deleteMember(dto);
		}
		if(result==1) {
			Controller.cs.alert("성공적으로 탈퇴되었습니다.");
			Controller.cs.exit(root);
		}else {
			Controller.cs.alert("삭제가 실패 했습니다.");
		}
	}

	@Override
	public void setRoot(Parent root) {
		this.root=root;

	}

	public void ModifyClose() {
		Controller.cs.exit(root);
	}

}
