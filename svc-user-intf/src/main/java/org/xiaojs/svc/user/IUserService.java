package org.xiaojs.svc.user;

import org.xiaojs.domain.SdUser;

public interface IUserService {

	public SdUser findBy(String userName);
}
