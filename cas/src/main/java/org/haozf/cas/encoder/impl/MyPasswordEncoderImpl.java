package org.haozf.cas.encoder.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.jasig.cas.authentication.handler.PasswordEncoder;

public class MyPasswordEncoderImpl implements PasswordEncoder {

	private String salt;

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String encode(String password) {
		String p = null;
		p = new Md5Hash(password, salt).toHex();
		return p;
	}


}
