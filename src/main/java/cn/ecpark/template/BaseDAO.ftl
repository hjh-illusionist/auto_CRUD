package ${serviceDir}.dao.impl;

import com.yame.common.base.db.BaseSql;
import jodd.db.DbSession;

public class BaseDao extends BaseSql  {

	@Override
	protected DbSession getSession() {
		return com.yame.Application.getSession();
	}


}