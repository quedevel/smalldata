package org.zerock.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

import lombok.Data;

@Data
public class CustomUser extends User {
	
	private MemberVO member;
	
	private static List<SimpleGrantedAuthority> getAuth(List<AuthVO> list){
		if(list == null || list.size() == 0) return null;
		return list.stream().map(vo -> new SimpleGrantedAuthority(vo.getAuth())).collect(Collectors.toList()) ;
	}
	
	public CustomUser(MemberVO member) {
		this(member.getUserid(), member.getUserpw(), getAuth(member.getAuthList()));
		this.member = member;
	}

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	
}
