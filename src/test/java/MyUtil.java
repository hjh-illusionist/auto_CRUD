import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.*;
import com.google.common.io.Files;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author: LiGuo
 * @date: 2018-04-17 10:09:25
 * @since: jdk 1.8
 * @description:
 */
public class MyUtil {
    @Test
    public void testGson() throws Exception {
        Foo foo = new Foo();
        Gson gson = new Gson();
        //java对象转json字符串
        String json = gson.toJson(foo);
        //字符串转java对象
        Foo f1 = gson.fromJson(json, Foo.class);
        System.out.println(f1);

    }

    @Test
    public void testGuava() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        ImmutableList<String> l = ImmutableList.of("a", "b", "c");
        ImmutableMap<String, String> m = ImmutableMap.of("key1", "value1", "key2", "value2");
        //读取配置文件
        List<String> str = Files.readLines(new File("1.txt"), Charset.forName("UTF-8"));
        System.out.println(str);
        //保留数字
        String s = CharMatcher.DIGIT.retainFrom("sdfdsfsdfsd134234sdfsdfsdf234324");
        //剔除数字
        String s1 = CharMatcher.DIGIT.removeFrom("sdfdsfsdfsd134234sdfsdfsdf234324");
        System.out.println(s);
        System.out.println(s1);
        //字符串拼接
        String[] subdirs = {"usr", "local", "lib"};
        String dir = Joiner.on("/").join(subdirs);
        System.out.println(dir);

    }

}
