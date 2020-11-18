package top.shauna.shaunaiscoming.fs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.shauna.dfs.kingmanager.bean.INode;
import top.shauna.dfs.kingmanager.bean.INodeDirectory;
import top.shauna.shaunaiscoming.bean.INodeBean;
import top.shauna.shaunaiscoming.bean.MessageBean;
import top.shauna.shaunaiscoming.service.ShaunaDfsService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


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

    @RequestMapping("/downloadtmp")
    public ResponseEntity<byte[]> downloadtmp(String filePath){
        /** Cache待添加 **/
        String path = filePath;

        if (!path.startsWith("/")){
            path = "/"+path;
        }

        ByteBuffer byteBuffer = shaunaDfsService.downloadFile(path);

        byte[] array = byteBuffer.array();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename="+filePath);

        return new ResponseEntity<>(array,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping("/rmfiletmp")
    @ResponseBody
    public String rmFile(String filePath){
        String path = filePath;

        if (shaunaDfsService.rmFile(path)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/rmdirtmp")
    @ResponseBody
    public String rmDir(String filePath){
        if (shaunaDfsService.rmDir(filePath)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/mkdirtmp")
    @ResponseBody
    public String mkDir(String filePath){
        if (shaunaDfsService.mkdir(filePath)){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/uploadtmp")
    @ResponseBody
    public String uploadTmp(MultipartFile file, String filePath){
        try {
            String path = filePath;

            if (!path.startsWith("/")){
                path = "/"+path;
            }

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



    @GetMapping("/getDir")
    @ResponseBody
    public MessageBean getDir(String path,HttpSession session){
        System.out.println(session.getId());
        try{
            INodeDirectory dir = shaunaDfsService.getDir(path);
            List<INodeBean> tmp = new ArrayList<>();
            for (INode iNode : dir.getChildren()) {
                if (iNode.getStatus()<0) {
                    continue;
                }
                if (iNode.isDirectory()){
                    tmp.add(new INodeBean(1,iNode.getName(),0));
                }else{
                    tmp.add(new INodeBean(0,iNode.getName(),0));    /**这里还要加一个文件长度**/
                }
            }
            return new MessageBean(200, tmp);
        }catch (Exception e){
            e.printStackTrace();
            return new MessageBean(400, e.getMessage());
        }
    }

    @RequestMapping("/info")
    public String info(String filePath){
        return "WorkingOn";
    }
}
