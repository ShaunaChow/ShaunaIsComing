package top.shauna.shaunaiscoming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import top.shauna.shaunaiscoming.service.ShaunaDfsService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * @Author Shauna.Chou
 * @Date 2020/11/3 19:21
 * @E-Mail z1023778132@icloud.com
 */
@Controller
@RequestMapping("/shaunafs")
public class FileSystemController {
    @Autowired
    private ShaunaDfsService shaunaDfsService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(String filePath, String id, String clazz){
        /** Cache待添加 **/
        String path = "/"+clazz+"/"+id+"/"+filePath;

        ByteBuffer byteBuffer = shaunaDfsService.downloadFile(path);

        byte[] array = byteBuffer.array();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename="+filePath);

        return new ResponseEntity<>(array,httpHeaders,HttpStatus.OK);
    }

    @PostMapping("/upload")
    public String upload(HttpSession session, MultipartFile file, String filePath){
        try {
            String path = "/"+session.getAttribute("clazz")+"/"+session.getAttribute("id")+"/"+filePath;
            if (shaunaDfsService.uploadFile(path, file.getBytes())) {
                return "success";
            }else{
                return "failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

}
