package org.haozf.cas.encoder;

import org.jasig.cas.authentication.handler.PasswordEncoder;

/**
 * 天远：扩展cas默认加密接口
 * 添加 salt
 * @author haozhengfeng
 *
 */
public abstract class AbstractPasswordEncoder implements PasswordEncoder{
	/**
	 * 判断是否支持加密算法
	 * @param source
	 * @return
	 */
	public abstract boolean support(String source);
}
