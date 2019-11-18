package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	@Insert("insert into tbl_member (userid, userpw, username) values (#{userid}, #{userpw}, #{username})")
	public boolean insertMember(MemberVO vo);
	
	@Insert("insert into tbl_auth (userid, auth) values (#{userid}, #{auth})")
	public boolean insertAuth(AuthVO vo);
	
	public MemberVO getMember(String userid);
}
