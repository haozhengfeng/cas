package org.haozf.identity.member.dao.impl;

import org.haozf.common.base.AbstractJdbcDao;
import org.haozf.identity.member.dao.IMemberDao;
import org.haozf.identity.member.model.Member;
import org.springframework.stereotype.Repository;

/**
 * 会员dao
 * 返回service中需要使用的member对象
 * 2016年7月4日
 * @author haozhengfeng
 */
@Repository("memberDao")
public class MemberDao extends AbstractJdbcDao<Member> implements IMemberDao {

	@Override
	public Member loadMemeberByID(Member member) {
		String sql = " select cust_id as custID,cust_name as custName, "+
					" cust_type as custType, "+
					" state_code as stateCode, "+
					" user_class as userClass, "+
					" cust_disable_time as custDisableTime from ti_member where cust_id = :custID";
		member = load(sql, member);
		return member;
	}

	@Override
	public Member updateMember(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member addMember(Member member) {
		String sql = " insert into ti_member(cust_name,cust_type,state_code,user_class)"
				   + " values(:custName,:custType,:stateCode,:userClass) ";
		int custID = add(sql, member);
		member.setCustID(custID+"");
		return member;
	}

}
