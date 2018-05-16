package cn.ecpark;


import cn.ecpark.utils.TemplateUtil;

/**
 * @Author: guo
 * @Description:
 */
public class GenCRUD {
    /**
     * 入口函数
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TemplateUtil.getModelFromTable(
                "jdbc:mysql://10.1.1.190:3306/app?useUnicode=true&amp;characterEncoding=UTF-8",
                "root",
                "park",
                "app",
                "t_app_pop_up_window",
                "APP广告弹窗",
                "E:\\workspace\\svn-workspace\\yame-oceans\\trunk\\yame-rpc\\yame-rpc-api\\src\\main\\java\\com\\yame\\rpc\\api\\apps",
                "E:\\workspace\\svn-workspace\\yame-oceans\\trunk\\yame-services\\yame-apps\\src\\main\\java\\com\\yame\\apps");
/*
        TemplateUtil.getModelFromTable(
                "jdbc:mysql://10.1.1.231:3306/fxpt?useUnicode=true&amp;characterEncoding=UTF-8",
                "fxpt",
                "fxpt_yamei123",
                "fxpt",
                "ims_ewei_shop_member",
                "预警设置记录",
                "E:\\workspace\\svn-workspace\\yame-oceans\\trunk\\yame-rpc\\yame-rpc-api\\src\\main\\java\\com\\yame\\rpc\\api\\xmall",
                "E:\\workspace\\svn-workspace\\yame-oceans\\trunk\\yame-services\\yame-xmall\\src\\main\\java\\com\\yame\\xmall");
*/
    }
}
