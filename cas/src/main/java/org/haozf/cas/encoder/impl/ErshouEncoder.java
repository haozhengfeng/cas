package org.haozf.cas.encoder.impl;

import org.haozf.cas.encoder.AbstractPasswordEncoder;
import org.haozf.cas.encoder.util.Md5Utils;
import org.haozf.common.enums.RegistSource;
import org.jasig.cas.authentication.handler.PasswordEncoder;

public class ErshouEncoder extends AbstractPasswordEncoder implements PasswordEncoder{

	@Override
	public boolean support(String source) {
		return source.equals(RegistSource.ershou.toString());
	}
	
	@Override
	public String encode(String password) {
		String md5password = Md5Utils.encodePassword(password);
		md5password = md5password.substring(8,24);
		return md5password;
	}

	
}
