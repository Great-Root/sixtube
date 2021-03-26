package database.member;

import model.MemberDTO;

public interface MemberDAO {

	int saveMember(MemberDTO dto);
	String loginCheck(String userId);

}
