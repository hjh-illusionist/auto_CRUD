package ${serviceDir}.dao.impl;

import org.springframework.stereotype.Repository;
import com.yame.iov.dao.${className}DAO;
/**
* @Author: LiGuo
* @Date: ${.now}
* @Since: jdk-1.8
* @Description:
*/
@Repository("${className?uncap_first}DAO")
public class ${className}DAOImpl extends BaseDao implements ${className}DAO {


}
