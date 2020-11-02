package top.shauna.shaunaiscoming.dao.impl;

import org.springframework.stereotype.Component;
import top.shauna.dfs.ClientStarter;
import top.shauna.dfs.service.ClientService;
import top.shauna.shaunaiscoming.dao.ShaunaDfsDao;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author Shauna.Chou
 * @Date 2020/11/2 15:28
 * @E-Mail z1023778132@icloud.com
 */
@Component
public class ShaunaDfsDaoImpl implements ShaunaDfsDao {
    private ClientService clientService;

    public ShaunaDfsDaoImpl(){
        clientService = ClientStarter.getClientService();
    }


    @Override
    public boolean uploadFile(String filePath, FileChannel inputStream) throws IOException {
        return clientService.uploadFile(filePath,inputStream);
    }

    @Override
    public ByteBuffer downloadFile(String filePath) {
        return clientService.downloadFile(filePath);
    }

    @Override
    public boolean mkdir(String dirPath) {
        return clientService.mkdir(dirPath);
    }

    @Override
    public boolean rmFile(String filePath) {
        return clientService.rmFile(filePath);
    }

    @Override
    public boolean rmDir(String dirPath) {
        return clientService.rmDir(dirPath);
    }
}
