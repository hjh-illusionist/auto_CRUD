package ${apiDir}.model;

import java.io.Serializable;
/**
* @Author: LiGuo
* @Date: ${.now}
* @Since: jdk-1.8
* @Description: ${tableComment}
*/
public class ${className} implements Serializable {

<#list colList as col>
    <#if col.columnKey=="PRI">
    /**
    * ${col.comment}(主键)
    */
    private ${col.dataType} ${col.field};
    </#if>
</#list>
<#list colList as col>
    <#if col.columnKey!="PRI">
    /**
    * ${col.comment}
    */
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
}