package top.shauna.shaunaiscoming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.shauna.shaunaiscoming.dao.ShaunaDfsDao;
import top.shauna.shaunaiscoming.service.ShaunaDfsService;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Author Shauna.Chou
 * @Date 2020/11/2 15:40
 * @E-Mail z1023778132@icloud.com
 */
@Service
public class ShaunaDfsServiceImpl implements ShaunaDfsService {
    @Autowired
    private ShaunaDfsDao shaunaDfsDao;

    @Override
    public boolean uploadFile(String filePath, byte[] data) throws IOException {
        return shaunaDfsDao.uploadFile(filePath,data);
    }

    @Override
    public ByteBuffer downloadFile(String filePath) {
        return shaunaDfsDao.downloadFile(filePath);
    }

    @Override
    public boolean mkdir(String dirPath) {
        return shaunaDfsDao.mkdir(dirPath);
    }

    @Override
    public boolean rmFile(String filePath) {
        return shaunaDfsDao.rmFile(filePath);
    }

    @Override
    public boolean rmDir(String dirPath) {
        return shaunaDfsDao.rmDir(dirPath);
    }
}
