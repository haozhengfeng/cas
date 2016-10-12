package org.haozf.cas.encoder;

import java.util.ArrayList;
import java.util.List;

import org.haozf.cas.encoder.impl.ErshouEncoder;

/**
 * 密码处理器 2016年6月30日
 * 
 * @author haozhengfeng
 */
public class PasswordHandler {

	private List<AbstractPasswordEncoder> passwordEncoders = new ArrayList<>();

	public PasswordHandler() {

	}

	public void initpasswordEncoders() {
		passwordEncoders.add(new ErshouEncoder());
	}

	public String encode(String password,String source) {
		for (AbstractPasswordEncoder passwordEncoder : passwordEncoders) {
			if(passwordEncoder.support(source)){
				return passwordEncoder.encode(password);
			}
		}
		return null;
	}
}
