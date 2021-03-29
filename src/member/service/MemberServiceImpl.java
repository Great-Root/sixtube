package member.service;


import database.member.MemberDAO;
import database.member.MemberDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import member.Controller;
import model.MemberDTO;


public class MemberServiceImpl implements MemberService{
	Parent root;
	
	//chb에 트루면 아이디 유지 비번 지우고 비번쪽에 포커스
	//chb에 펄스면 둘다 클리어 아이디에 포커스
	
	@Override
	public boolean login(Parent root) {
	
		TextField id=(TextField)root.lookup("#fxId");
		PasswordField pwd=(PasswordField)root.lookup("#fxPw");
		CheckBox chb=(CheckBox)root.lookup("#fxCheck");
		if(id.getText().isEmpty()) {
			Controller.cs.alert("아이디를 입력하세요.");
		}
		
		System.out.println("로그인 체크합니다.");
		System.out.println("id : "+id.getText());
		System.out.println("pwd : "+pwd.getText());
	
		MemberDAO ds=new MemberDAOImpl();
		String dbPwd=ds.loginCheck(id.getText());
		
//		boolean chb=fxCheck.isSelected(); 
	
		if(dbPwd==null) {
			Controller.cs.alert("존재하지 않는 아이디입니다.");
			id.clear();
			pwd.clear();
		}else {
			if(dbPwd.equals(pwd.getText())) {
				Controller.cs.alert("인증통과");
				id.clear();
				pwd.clear();
			}else {
				Controller.cs.alert("비밀번호가 틀렸습니다.");
				if(chb.isSelected()==false) {
					id.clear();
					pwd.clear();
					id.requestFocus();					
				}else {
					pwd.clear();
					pwd.requestFocus();
				}
			}
		}
		
		return true;
	}

	@Override
	public void join() {
		//회원가입->가입 버튼눌렀을때
		
		boolean gender=getGender();
		System.out.println("성별(true=여, false=남) : "+gender);
		
		String age=getComboBox();
		System.out.println("나이 : "+age);
		
		TextField id=(TextField)root.lookup("#fxId");
		PasswordField pw=(PasswordField)root.lookup("#fxPw");
		PasswordField pw2=(PasswordField)root.lookup("#fxPwChk");
		TextField name=(TextField)root.lookup("#fxName");
		System.out.println("아이디 : "+id.getText());
		System.out.println("비밀번호 : "+pw.getText());
		System.out.println("비밀번호 확인 : "+pw2.getText());
		System.out.println("이름 : "+name.getText());
		
		MemberDTO dto=new MemberDTO();
		dto.setId(id.getText());
		dto.setPw(pw.getText());
		dto.setName(name.getText());
		dto.setAge(age);
	
		
		if(gender) {
			dto.setGender(0);//여
		}else {
			dto.setGender(1);//남
		}
		
		MemberDAO ds=new MemberDAOImpl();
		int result=ds.saveMember(dto);
		if(result==1) {
			Controller.cs.alert("성공적으로 가입되었습니다.");
			Controller.cs.exit(root);
		}else {
			Controller.cs.alert("가입에 실패했습니다.");		
		}
	
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
	public void checkPw() {//비밀번호 확인 기능
		System.out.println("비밀번호 확인");
		PasswordField pw=(PasswordField)root.lookup("#fxPw");
		PasswordField pw2=(PasswordField)root.lookup("#fxPwChk");
		String pwStr=pw.getText();
		String pw2Str=pw2.getText();
		if(pw.getText().isEmpty()) {
			Controller.cs.alert("비밀 번호를 입력해주세요");
		}else{
			if(pwStr.equals(pw2Str)) {
				Controller.cs.alert("비밀번호가 일치합니다.");
			}else {
				Controller.cs.alert("비밀번호가 일치하지 않습니다.");
			}
		}
		
		
		
	}
	public boolean getGender() {
		RadioButton woman=(RadioButton)root.lookup("#rdoWoman");
		return woman.isSelected();
	}
	
	public void addComboBox() {
		ComboBox<String> cmb=((ComboBox<String>)root.lookup("#fxAge"));
		
		if(cmb!=null) {
			for(int i=1950;i<2022;i++) {
			cmb.getItems().add(i+"년생");}
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
