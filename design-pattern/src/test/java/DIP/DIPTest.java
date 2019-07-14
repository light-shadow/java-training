package DIP;

import com.general.DIP.AICourse;
import com.general.DIP.JavaCourse;
import com.general.DIP.PythonCourse;
import com.general.DIP.Tom;
import org.junit.jupiter.api.Test;

/**
 * @description: DIP单元测试类
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 10:38
 **/
public class DIPTest {

    @Test
    public void TomTest(){
        Tom tom = new Tom();
        tom.study(new JavaCourse());
        tom.study(new PythonCourse());
        tom.study(new AICourse());
    }

    // 构造器注入
    @Test
    public void constructorTest(){
        Tom tom = new Tom(new JavaCourse());
        tom.study();
    }

    // setter注入
    @Test
    public void setterTest(){
        Tom tom = new Tom();
        tom.setiCourse(new JavaCourse());
        tom.study();
    }
}
