package member.service;

import database.member.MemberDAO;
import database.member.MemberDAOImpl;
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
	private boolean isIdchk = false;

	@Override
	public boolean login(Parent root) {

		boolean result = false;
		TextField id = (TextField) root.lookup("#fxId");
		PasswordField pwd = (PasswordField) root.lookup("#fxPw");
		CheckBox chb = (CheckBox) root.lookup("#fxCheck");
		if (id.getText().isEmpty()) {
			Controller.cs.alert("아이디를 입력하세요.");
		} else {
			String userId = id.getText();
			MemberDAO ds = new MemberDAOImpl();
			String dbPwd = ds.loginCheck(id.getText());
			if (dbPwd == null) {
				Controller.cs.alert("존재하지 않는 아이디입니다.");
				id.clear();
				pwd.clear();
			} else {
				if (dbPwd.equals(pwd.getText())) {
					Controller.cs.exit(root);
					Controller.lu.setUserId(id.getText());
					result = true;
				} else {
					if (dbPwd.equals(pwd.getText())) {
						Controller.lu.setUserId(userId);
						Controller.cs.exit(root);
						result = true;
					} else {
						Controller.cs.alert("비밀번호가 틀렸습니다.");
						if (chb.isSelected() == false) {
							id.clear();
							pwd.clear();
							id.requestFocus();
						} else {
							pwd.clear();
							pwd.requestFocus();
						}
					}
				}
			}
		}
		return result;
	}		

	@Override
	public void join(){

		boolean gender=getGender();

		
//요기요?
		
		TextField id=(TextField)root.lookup("#fxId");
		PasswordField pw=(PasswordField)root.lookup("#fxPw");
		PasswordField pw2=(PasswordField)root.lookup("#fxPwChk");
		TextField name=(TextField)root.lookup("#fxName");
		ComboBox cmb=(ComboBox)root.lookup("#fxAge");
		MemberDTO dto=new MemberDTO();
		dto.setId(id.getText());
		dto.setPw(pw.getText());
		dto.setName(name.getText());
		

		if(gender) {
			dto.setGender(0);//여
		}else {
			dto.setGender(1);//남
		}
		String pwStr=pw.getText();
		String pw2Str=pw2.getText();
		if(dto.getId().length() > 10 || dto.getName().length() > 10) {
			Controller.cs.alert("아이디 또는 이름의 글자수를 10글자 이하로 줄여주세요");
		}else if(id.getText().isEmpty()) {
			Controller.cs.alert("아이디를 입력해주세요");
		}else if(pw.getText().isEmpty()) {
			Controller.cs.alert("비밀 번호를 입력해주세요");
		}else if(pw2.getText().isEmpty()) {
			Controller.cs.alert("비밀 번호 확인 창을 입력해주세요");
		}else if(cmb.getValue()==null) {
			Controller.cs.alert("생일년도를 선택해주세요.");
		}else if(name.getText().isEmpty()) {
			Controller.cs.alert("이름을 입력해주세요");
		}else if(!isIdchk) {
			Controller.cs.alert("아이디 중복체크를 해주세요");
		}else{
			int age=(int)cmb.getValue();
			dto.setAge(age);
			if(pwStr.equals(pw2Str)) {

				if(pwStr.length()<8) {
					Controller.cs.alert("비밀 번호를 8자 이상으로 설정해주세요.");
				}else {

					MemberDAO ds=new MemberDAOImpl();
					int result=ds.saveMember(dto);
					if(result==1) {
						Controller.cs.alert("성공적으로 가입되었습니다.");
						Controller.cs.exit(root);
					}else {
						Controller.cs.alert("가입에 실패했습니다.");		
					}
				}
			}else {
				Controller.cs.alert("비밀번호가 일치하지 않습니다.");
			}
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
	public void checkId() {
		TextField id=(TextField)root.lookup("#fxId");

		if(id.getText().isEmpty()) {
			Controller.cs.alert("아이디를 입력해주세요");
		}else {
			MemberDAO ds=new MemberDAOImpl();
			if(ds.checkMemberID(id.getText())) {
				Controller.cs.alert("중복된 아이디입니다.");
				isIdchk = false;
			}else {
				Controller.cs.alert("사용가능한 아이디입니다.");
				isIdchk = true;
			}
		}
	}



	public boolean getGender() {
		RadioButton woman=(RadioButton)root.lookup("#rdoWoman");
		return woman.isSelected();
	}


	public void addComboBox() {
		ComboBox cmb=(ComboBox)root.lookup("#fxAge");

		if(cmb!=null) {
			for(int i=1950;i<2022;i++) {
				cmb.getItems().add(i);}
		}
	}
	


}
