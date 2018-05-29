package cn.ecpark;


import cn.ecpark.template.TemplateUtil;

/**
 * @Author: guo
 * @Description:
 */
public class Gen {
    public static void main(String[] args) throws Exception {
        TemplateUtil.getModelFromTable(
                "jdbc:mysql://10.1.1.190:3306/app?useUnicode=true&amp;characterEncoding=UTF-8",//数据库连接字符串
                "root",//用户名
                "park",//密码
                "app",//数据库名
                "t_app_pop_up_window",//表名
                "APP广告弹窗",//表注释
                "E:\\workspace\\svn-workspace\\yame-oceans\\trunk\\yame-services\\yame-apps\\src\\main\\java\\com\\yame\\test\\testapi",//rpc-api路径
                "E:\\workspace\\svn-workspace\\yame-oceans\\trunk\\yame-services\\yame-apps\\src\\main\\java\\com\\yame\\test\\testservice" //service服务路径
        );


    }
}
