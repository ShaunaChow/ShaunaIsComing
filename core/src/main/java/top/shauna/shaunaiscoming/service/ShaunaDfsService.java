package top.shauna.shaunaiscoming.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author Shauna.Chou
 * @Date 2020/11/2 15:37
 * @E-Mail z1023778132@icloud.com
 */
public interface ShaunaDfsService {
    boolean uploadFile(String filePath, FileChannel inputStream) throws IOException;

    ByteBuffer downloadFile(String filePath);

    boolean mkdir(String dirPath);

    boolean rmFile(String filePath);

    boolean rmDir(String dirPath);
}
