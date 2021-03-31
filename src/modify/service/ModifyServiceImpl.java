package modify.service;

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
		if(nowPw.getText().isEmpty()||newPw.getText().isEmpty()||newPwCheck.getText().isEmpty()) {
			Controller.cs.alert("비밀 번호를 입력해주세요");
		}else{
			if(newStr.equals(newCheckStr)) {
				
			}else {
				Controller.cs.alert("비밀번호가 일치하지 않습니다.");
			}
		}
		
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
	
	public void delete() {
		
		TextField id=(TextField)root.lookup("#fxId");
		PasswordField realPwDelete=(PasswordField)root.lookup("#realPwDelete");
		
		if(id.getText().isEmpty()) {
			Controller.cs.alert("아이디를 입력하세요.");
		}
		if(realPwDelete.getText().isEmpty()) {
			Controller.cs.alert("비밀번호를 입력하세요.");
		}
		
		System.out.println("아이디 : "+id.getText());
		System.out.println("최종 비밀번호 확인 : "+realPwDelete.getText());
		
		//입력한 아이디와 비밀번호가 DB의 정보와 일치하면 MemberDAOmpl의 deleteMember로 해당 회원 삭제
		
		//ㅠㅠㅠㅠㅠㅠ
//		String rpdStr=realPwDelete.getText();
//		
//		MemberDTO dto=new MemberDTO();
//
//		MemberDAO ds=new MemberDAOImpl();
//		String dbPwd=ds.loginCheck(id.getText());
//		
//	
//		
//		if(dbPwd.equals(realPwDelete.getText())) {
//			ds.deleteMember(dto);
//			//Controller.cs.exit(root);
//		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	


	@Override
	public void setRoot(Parent root) {
		this.root=root;
	}
	
	public void ModifyClose() {
		Controller.cs.exit(root);
	}

	
}
