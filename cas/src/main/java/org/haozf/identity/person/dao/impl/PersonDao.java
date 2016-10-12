package org.haozf.identity.person.dao.impl;

import org.haozf.common.base.AbstractJdbcDao;
import org.haozf.identity.person.dao.IPersonDao;
import org.haozf.identity.person.model.Person;
import org.springframework.stereotype.Service;

@Service("personDao")
public class PersonDao extends AbstractJdbcDao<Person> implements IPersonDao{
	
	@Override
	public Person loadPersonByID(Person person) {
		String sql = "	select "
					+ "	cust_id as custID,phone,email,cellphone as cellPhone,fax, "
					+ "	qq,msn,post_code as postCode,address,sex, "
					+ "	birth,area_attr as areaAttr,is_marry as	isMarry,blood_type as bloodType, "
					+ "	career,job,hobby,income as inCome,province,	"
					+ "	city,country,head_image as headImage, "
					+ "	identity_card_picture as identityCardPicture,identity_card as identityCard	"
					+ " from ti_personal where cust_id = :custID ";
		person = load(sql, person);
		return person;
	}
	
	@Override
	public Person loadPersonByCustID(String custID) {
		String sql = "	select "
				+ "	cust_id as custID,phone,email,cellphone as cellPhone,fax, "
				+ "	qq,msn,post_code as postCode,address,sex, "
				+ "	birth,area_attr as areaAttr,is_marry as	isMarry,blood_type as bloodType, "
				+ "	career,job,hobby,income as inCome,province,	"
				+ "	city,country,head_image as headImage, "
				+ "	identity_card_picture as identityCardPicture,identity_card as identityCard	"
				+ " from ti_personal where cust_id = :custID ";
		Person person = loadByID(sql, "custID", custID);
		return person;
	}

	@Override
	public Person updatePerson(Person person) {
		String sql = "	update ti_personal set "
				+ "	cust_id=:custID,phone=:phone,email=:email,cellphone=:cellPhone,fax=:fax, "
				+ "	qq=:qq,msn=:msn,post_code=:postCode,address=:address,sex=:sex, "
				+ "	birth=:birth,area_attr=:areaAttr,is_marry=:isMarry,blood_type=:bloodType, "
				+ "	career=:career,job=:job,hobby=:hobby,income=:inCome,province=:province,	"
				+ "	city=:city,country=:country,head_image=:headImage, "
				+ "	identity_card_picture=:identityCardPicture,identity_card=:identityCard	"
				+ " where cust_id =:custID ";
		update(sql, person);
		return person;
	}

	@Override
	public Person addPerson(Person person) {
		String sql = " insert into ti_personal "
				+ " (cust_id,phone,email,cellphone,fax, "
				+ "  qq,msn,post_code,address,sex,"
				+ "  birth,area_attr,is_marry,blood_type, "
				+ "  career,job,hobby,income,province, "
				+ "  city,country,head_image, "
				+ "  identity_card_picture,identity_card "
				+ ") values( "
				+ "  :custID,:phone,:email,:cellPhone,:fax, "
				+ "  :qq,:msn,:postCode,:address,:sex, "
				+ "  :birth,:areaAttr,:isMarry,:bloodType, "
				+ "  :career,:job,:hobby,:inCome,:province, "
				+ "  :city,:country,:headImage, "
				+ "  :identityCardPicture,:identityCard "
				+ " ) ";
		add(sql, person);
		return person;
	}

}
