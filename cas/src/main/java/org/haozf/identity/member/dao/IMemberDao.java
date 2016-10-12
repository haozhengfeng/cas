package org.haozf.identity.member.dao;

import org.haozf.identity.member.model.Member;
/**
 * 会员信息dao
 * 2016年7月4日
 * @author haozhengfeng
 */
public interface IMemberDao {

	/**
	 * 加载会员信息
	 * 
	 * @return
	 */
	public Member loadMemeberByID(Member member);

	/**
	 * 修改会员信息
	 * 
	 * @param person
	 * @return
	 */
	public Member updateMember(Member member);

	/**
	 * 添加会员信息
	 * 
	 * @param person
	 * @return
	 */
	public Member addMember(Member member);

}
