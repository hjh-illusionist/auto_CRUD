package ${serviceDir}.model;

import java.io.Serializable;
import org.springframework.beans.BeanUtils;
import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbId;
import jodd.db.oom.meta.DbTable;
/**
* @Author: LiGuo
* @Date: ${.now}
* @Since: jdk-1.8
* @Description: ${tableComment}
*/
@DbTable("${tableName}")
public class ${className} implements Serializable {

<#list colList as col>
    <#if col.columnKey=="PRI">
    /**
    * ${col.comment}(主键)
    */
    @DbId("${col.columnName}")
    private ${col.dataType} ${col.field};
    </#if>
</#list>
<#list colList as col>
    <#if col.columnKey!="PRI">
    /**
    * ${col.comment}
    */
    @DbColumn("${col.columnName}")
    private ${col.dataType} ${col.field};
    </#if>
</#list>
<#list colList as col>
    public ${col.dataType} get${col.field?cap_first}() {
        return ${col.field};
    }
    public void set${col.field?cap_first}(${col.dataType} ${col.field}) {
        this.${col.field} = ${col.field};
    }
</#list>
    public ${apiDir}.model.${className} toViewModel(){
        ${apiDir}.model.${className} viewModel = new ${apiDir}.model.${className}();
        BeanUtils.copyProperties(this, viewModel);
        return viewModel;
    }
    public ${className} fromView(${apiDir}.model.${className} view){
        ${className} ${className?uncap_first} = new ${className}();
        BeanUtils.copyProperties(view, ${className?uncap_first});
        return ${className?uncap_first};
    }

}