package OCP;

import com.general.OCP.ICourse;
import com.general.OCP.JavaCourse;
import com.general.OCP.JavaDiscountCourse;
import org.junit.jupiter.api.Test;

/**
 * @description: 测试类
 * @author: general
 * @version: 1.0
 * @create: 2019-07-13 10:59
 **/
public class OpenCloseTest {

    @Test
    public void priceTest(){
        ICourse course = new JavaCourse(1, "java", (double)600);
        System.out.println(new JavaDiscountCourse(1, "java", (double)600).getPrice());
        System.out.println(new JavaDiscountCourse(1, "java", (double)600).getOriginPrice());
    }

}
