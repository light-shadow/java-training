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

    @Test
    public void test(){
        System.out.println(tableSizeFor(6));
    }


    int tableSizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
