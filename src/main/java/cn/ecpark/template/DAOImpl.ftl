package ${serviceDir}.dao.impl;

import org.springframework.stereotype.Repository;
import ${serviceDir}.dao.${className}DAO;
import ${serviceDir}.dao.impl.BaseDao;
/**
* @Author: LiGuo
* @Date: ${.now}
* @Since: jdk-1.8
* @Description:
*/
@Repository("${className?uncap_first}DAO")
public class ${className}DAOImpl extends BaseDao implements ${className}DAO {


}
