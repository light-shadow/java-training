import com.general.HashMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @description: 测试类
 * @author: general
 * @version: 1.0
 * @create: 2019-06-25 17:29
 **/
public class TestHashMap {

    @Test
    public void testHashMap(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "tom");
        map.put("age", "12");
        map.put("gender", "male");
        System.out.println(map);
    }
}
