package org.xiaojs.dao.user;

import org.springframework.stereotype.Component;
import org.xiaojs.dao.annotation.Find;
import org.xiaojs.dao.HibernateDao;
import org.xiaojs.domain.SdUser;

@Component
public interface IUserDao extends HibernateDao<SdUser>{

	@Find("FROM SdUser WHERE 1=1")
	public SdUser findBy(String userName);
}
