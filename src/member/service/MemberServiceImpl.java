package member.service;


import java.io.IOException;

import database.member.MemberDAO;
import database.member.MemberDAOImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import member.Controller;


public class MemberServiceImpl implements MemberService{
	Parent root;
	
	@Override
	public void login(Parent root) {

		TextField id=(TextField)root.lookup("#fxId");
		PasswordField pwd=(PasswordField)root.lookup("#fxPw");
		
		if(id.getText().isEmpty()) {
			Controller.cs.alert("아이디를 입력하세요.");
		}
		
		System.out.println("로그인 체크합니다.");
		System.out.println("id : "+id.getText());
		System.out.println("pwd : "+pwd.getText());
	
		MemberDAO ds=new MemberDAOImpl();
		String dbPwd=ds.loginCheck(id.getText());
		
		if(dbPwd==null) {
			Controller.cs.alert("존재하지 않는 아이디입니다.");
		}else {
			if(dbPwd.equals(pwd.getText())) {
				Controller.cs.alert("인증통과");
			}else {
				Controller.cs.alert("비밀번호가 틀렸습니다.");
			}
		}
	
	}

	@Override
	public void join() {
		//회원가입->가입 버튼눌렀을때
		
	
	}

	@Override
	public void setRoot(Parent root) {
		this.root=root;
		addComboBox();
	}

	@Override
	public void memberClose() {
		Controller.cs.exit(root);
		
	}

	@Override
	public void checkPw() {
		// TODO Auto-generated method stub
		
	}

	
	public void addComboBox() {
		ComboBox<String> cmb=((ComboBox<String>)root.lookup("#fxAge"));
		if(cmb!=null) {
			cmb.getItems().addAll("10대","20대","30대","30대 이상");
		}
	}
	public String getComboBox() {
		ComboBox<String> cmb=((ComboBox<String>)root.lookup("#fxAge"));
		String age=cmb.getValue();
		if(age==null) {
			Controller.cs.alert("콤보박스를 선택해주세요.");
		}
		return age;
	}


}
