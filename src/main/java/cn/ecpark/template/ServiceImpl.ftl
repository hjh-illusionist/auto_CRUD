package ${serviceDir}.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${apiDir}.service.${className}Service;

import ${serviceDir}.dao.${className}DAO;

/**
* @Author: LiGuo
* @Date: ${.now}
* @Since: jdk-1.8
* @Description:
*/

@Service("${className?uncapFirst}Service")
public class ${className}ServiceImpl implements ${className}Service {

	@Resource(name="${className?uncapFirst}DAO")
	private ${className}DAO ${className?uncapFirst}DAO;



}
