package cn.ecpark.template;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @Author: LiGuo
 * @Date:
 * @Since: jdk-1.8
 * @Description: 模板工具
 */
public class TemplateUtil {
    private TemplateUtil() {
    }


    private static List<Map<String, String>> colList = new ArrayList<Map<String, String>>();
    private static Configuration configuration = new Configuration();
    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            configuration.setDirectoryForTemplateLoading(
                    new File(new File("").getAbsolutePath() + "/src/main/java/cn/ecpark/template"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getModelFromTable(String dbConnString,
                                         String username,
                                         String password,
                                         String dbName,
                                         String tableName,
                                         String tableComment,
                                         String apiDir,
                                         String serviceDir) throws Exception {

        String sql = "select  table_name, column_name,column_comment,data_type,character_maximum_length,column_key " +
                "from information_schema.columns where table_name = '"
                + tableName +
                "' and table_schema='"
                + dbName +
                "'";
        conn = DriverManager.getConnection(dbConnString, username, password);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            appendToList(rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)
            );
        }

        Map<String, Object> param = new HashMap<String, Object>();
        String className = TemplateUtil.getClassName(tableName);
        param.put("tableName", tableName);
        param.put("tableComment", tableComment);
        param.put("className", className);
        param.put("colList", colList);
        param.put("apiDir", TemplateUtil.getPackageDir(apiDir));
        param.put("serviceDir", TemplateUtil.getPackageDir(serviceDir));

        Template baseDAO = configuration.getTemplate("BaseDAO.ftl");
        Template modelApi = configuration.getTemplate("EntityForApi.ftl");
        Template service = configuration.getTemplate("Service.ftl");
        Template modelService = configuration.getTemplate("EntityForService.ftl");
        Template dao = configuration.getTemplate("DAO.ftl");
        Template daoImpl = configuration.getTemplate("DAOImpl.ftl");
        Template serviceImpl = configuration.getTemplate("ServiceImpl.ftl");

        createDirsIfNotExists(
                apiDir + File.separator + "service",
                apiDir + File.separator + "model",
                serviceDir + File.separator + "model",
                serviceDir + File.separator + "dao" + File.separator + "impl",
                serviceDir + File.separator + "service");

        File baseDAOFile = new File(serviceDir + File.separator + "dao" + File.separator + "impl" + File.separator + "BaseDao.java");
        File modelApiFile = new File(apiDir + File.separator + "model" + File.separator + className + ".java");
        File serviceFile = new File(apiDir + File.separator + "service" + File.separator + className + "Service.java");
        File modelServiceFile = new File(serviceDir + File.separator + "model" + File.separator + className + ".java");
        File daoFile = new File(serviceDir + File.separator + "dao" + File.separator + className + "DAO.java");
        File daoImplFile = new File(serviceDir + File.separator + "dao" + File.separator + "impl" + File.separator + className + "DAOImpl.java");
        File serviceImplFile = new File(serviceDir + File.separator + "service" + File.separator + className + "ServiceImpl.java");
        if (!baseDAOFile.exists()) {
            baseDAO.process(param, new FileWriter(baseDAOFile));
        }
        if (modelApiFile.exists()) {
            modelApi.process(param, new FileWriter(new File(modelApiFile.getAbsolutePath() + "1")));
        } else {
            modelApi.process(param, new FileWriter(modelApiFile));
        }
        if (serviceFile.exists()) {
            service.process(param, new FileWriter(new File(serviceFile.getAbsolutePath() + "1")));
        } else {
            service.process(param, new FileWriter(serviceFile));
        }
        if (modelServiceFile.exists()) {
            modelService.process(param, new FileWriter(new File(modelServiceFile.getAbsolutePath() + "1")));
        } else {
            modelService.process(param, new FileWriter(modelServiceFile));
        }
        if (daoFile.exists()) {
            dao.process(param, new FileWriter(new File(daoFile.getAbsolutePath() + "1")));
        } else {
            dao.process(param, new FileWriter(daoFile));
        }
        if (daoImplFile.exists()) {
            daoImpl.process(param, new FileWriter(new File(daoImplFile.getAbsolutePath() + "1")));
        } else {
            daoImpl.process(param, new FileWriter(daoImplFile));
        }
        if (serviceImplFile.exists()) {
            serviceImpl.process(param, new FileWriter(new File(serviceImplFile.getAbsolutePath() + "1")));
        } else {
            serviceImpl.process(param, new FileWriter(serviceImplFile));
        }
        colList.clear();
        System.out.println("您的BUG写完了!");
    }

    private static void createDirsIfNotExists(String... dirs) {
        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                continue;
            }
        }
    }


    public static void appendToList(String columnName, String columnComment,
                                    String dataType, String characterMaximumLength, String columnKey) {
        Map<String, String> colMap = new HashMap<String, String>();
        colMap.put("columnName", columnName);
        colMap.put("field", TemplateUtil.getField(columnName));
        colMap.put("comment", columnComment);
        colMap.put("dataType", TemplateUtil.getDataType(dataType));
        colMap.put("characterMaximumLength", characterMaximumLength);
        colMap.put("columnKey", columnKey);
        colList.add(colMap);

    }


    public static String getField(String columnName) {
        if (Strings.isNullOrEmpty(columnName)) {
            return "";
        }
        Iterable<String> split = Splitter.on("_").split(columnName);
        Iterator<String> iterator = split.iterator();
        StringBuilder newStr = new StringBuilder();
        while (iterator.hasNext()) {
            newStr.append(StringUtils.capitalize(iterator.next()));
        }
        return StringUtils.uncapitalize(newStr.toString());
    }

    public static String getDataType(String dataType) {
        if (Strings.isNullOrEmpty(dataType)) {
            return "String";
        }
        dataType = dataType.trim();
        if (!Strings.isNullOrEmpty(dataType)) {
            if ("bit".equals(dataType)) return "Integer";
            if ("varchar".equals(dataType)) return "String";
            if ("tinyint".equals(dataType)) return "Integer";
            if ("smallint".equals(dataType)) return "Integer";
            if ("int".equals(dataType)) return "Integer";
            if ("bigint".equals(dataType)) return "Long";
            if ("datetime".equals(dataType)) return "Date";
            if ("decimal".equals(dataType)) return "BigDecimal";
            if ("double".equals(dataType)) return "Double";
            if ("longtext".equals(dataType)) return "String";
            if ("text".equals(dataType)) return "String";
        }
        return "String";
    }

    public static String getPackageDir(String targetDir) {
        int index = targetDir.indexOf("java");
        return targetDir.substring(index + 5).replace("\\", ".");
    }

    public static String getClassName(String tableName) {
        if (Strings.isNullOrEmpty(tableName)) {
            return "";
        }
        if (tableName.startsWith("t_")) {
            tableName = tableName.substring(2);
        }
        if (tableName.startsWith("snatcher_")) {
            tableName = tableName.substring(9);
        }
        Iterable<String> split = Splitter.on("_").split(tableName);
        Iterator<String> iterator = split.iterator();
        StringBuilder newStr = new StringBuilder();
        while (iterator.hasNext()) {
            newStr.append(StringUtils.capitalize(iterator.next()));
        }
        return newStr.toString();

    }

}
