import com.general.netty_demo.ChatServer;
import org.junit.jupiter.api.Test;

/**
 * @description: 单元测试类
 * @author: general
 * @version: 1.0
 * @create: 2019-06-21 11:24
 */
public class CharServerTest {

    @Test
    public void test(){
        ChatServer  chatServer = new ChatServer(8888);
        chatServer.run();
    }
}
