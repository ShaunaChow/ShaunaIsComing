package top.shauna.shaunaiscoming;


import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @Author Shauna.Chou
 * @Date 2020/11/3 20:14
 * @E-Mail z1023778132@icloud.com
 */
public class Test1 {

    @Test
    public void ok(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byte[] array = byteBuffer.array();
        for (byte b : array) {
            System.out.println(b);
        }
    }
}
