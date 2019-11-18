package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = {@Autowired})
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("CustomUserDetailsService ....................."+mapper);
		log.info("CustomUserDetailsService ....................."+mapper);
		log.info("CustomUserDetailsService ....................."+mapper);
		log.info("CustomUserDetailsService ....................."+mapper);
		log.info("CustomUserDetailsService ....................."+mapper);
		
//		String[] arr = {"ROLE_MEMBER","ROLE_ADMIN"};
//		List<SimpleGrantedAuthority> authList = null;
//		authList = Arrays.stream(arr).map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList());
//		CustomUser user = new CustomUser("aaa","$2a$10$M6rBX0d.0rT1s7Mleu9MTeLG4bNlKTB0sefm6ls5.ZsfrC/VRF9mG", authList);
		
		MemberVO member = mapper.getMember(username);
		
		if(member == null) {
			throw new UsernameNotFoundException("This ID does not exist");
		}
		
		return new CustomUser(member);
	}

}
