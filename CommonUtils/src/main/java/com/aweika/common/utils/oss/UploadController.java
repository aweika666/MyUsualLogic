package com.aweika.common.utils.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;

import com.aweika.common.response.WebResponse;
import com.aweika.common.utils.UUidUtil;
import com.aweika.common.utils.config.OssConfig;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Michael
 * @date: 2020/6/20
 * @description: oss服务器文件上传
 */
@RequestMapping("upload")
@RestController
public class UploadController {
    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private OssService ossService;

    /**
     * 附件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadFile")
    public WebResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Map result = new HashMap();
        try {
            String uuid = UUidUtil.get32Uuid();
            File fileLocal = new File(OssConfig.UPLOAD_PATH, uuid + "-" + file.getOriginalFilename());
            //将建好的文件用流的形式读写进去
            FileUtils.copyInputStreamToFile(file.getInputStream(), fileLocal);
            // 判断传入文件类型
            String type = fileLocal.getName().substring(fileLocal.getName().lastIndexOf(".") + 1);
            if (fileTypeLimit(type).equals(0)) {
                logger.error("文件类型错误");
                return WebResponse.resFail("文件类型错误", null);
            }
            //上传到服务器
            String ossUrl = ossService.uploadFile(fileLocal);
            String fileName = file.getOriginalFilename().trim();
            logger.info("上传的文件名为" + fileName);
            logger.info("OSS上传文件url" + ossUrl);
            /*try {
                String[] fileStrings = ossUrl.split("\\.");
                String fileNames = "";
                for (int i = 0; i < fileStrings.length - 1; i++) {
                    fileNames = fileNames + fileStrings[i] + ".";
                }
                String newName = fileNames + "pdf";
                String ossPdfUrl = OssConfig.ENTFILE_PDFACCESS + newName;
                result.put("ossPdfUrl", ossPdfUrl);
            } catch (Exception e) {
                logger.error("error", e);
            }*/
            result.put("ossUrl", OssConfig.ENTFILE_PDFACCESS + ossUrl);
            result.put("fileName", fileName);
            //预览地址拼接
            result.put("preview","https://view.officeapps.live.com/op/view.aspx?src="+OssConfig.ENTFILE_PDFACCESS + ossUrl);

            if (StringUtils.isBlank(ossUrl)) {
                return WebResponse.resFail("上传失败", 1);
            }

            return WebResponse.resSuccess("文件上传成功", result);
        } catch (Exception e) {
            logger.error("上传失败", e);
            return WebResponse.resFail("文件上传失败", null);
        }
    }

    /**
     * 通过url下载
     */
    @GetMapping("downloadByUrl")
    public void downloadByUrl(String url, HttpServletResponse response, HttpServletRequest request) {
        String fileTitle = "";
        OSSClient ossClient = null;
        try {

            /*String fileTitle = url.substring(url.lastIndexOf("-") + 1, url.length());
            OSSClient ossClient = new OSSClient(OssConfig.ENTFILE_ENDPOINT, "..", "..");
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            if (url.indexOf(OssConfig.ENTFILE_PDFACCESS) >= 0) {
                url = url.replace(OssConfig.ENTFILE_PDFACCESS, "");
            }
            ossClient.getObject(new GetObjectRequest(OssConfig.ENTFILE_BUCKETNAME, url), new File(downLoadPath + fileTitle));
            // 关闭OSSClient。
            ossClient.shutdown();*/
            if (url.contains("aweika-test.oss-cn-hangzhou.aliyuncs.com/aweika-test")){
                url = url.replace("http://aweika-test.oss-cn-hangzhou.aliyuncs.com/", "");
                //从oss读取文件
                fileTitle = url.substring(url.lastIndexOf("-") + 1, url.length());
                ossClient = new OSSClient(" .. ", "..", "..");
                // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
                ossClient.getObject(new GetObjectRequest("aweika-test", url), new File(OssConfig.UPLOAD_PATH + fileTitle));
            }else {
                url = url.replace(OssConfig.ENTFILE_PDFACCESS, "");
                //从oss读取文件
                fileTitle = url.substring(url.lastIndexOf("-") + 1, url.length());
                ossClient = new OSSClient(OssConfig.ENTFILE_ENDPOINT, OssConfig.ENTFILE_ACCESSKEYID, OssConfig.ENTFILE_ACCESSKEYSECRET);
                // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
                ossClient.getObject(new GetObjectRequest(OssConfig.ENTFILE_BUCKETNAME, url), new File(OssConfig.UPLOAD_PATH + fileTitle));

            }
            //临时文件下载
            File file = new File(OssConfig.DOWNLOAD_PATH + fileTitle);
            FileInputStream stream = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            String name = null;
            String agent = request.getHeader("User-Agent").toLowerCase();
            if (agent.contains("msie") || agent.contains("like gecko")) {
                // win10 ie edge 浏览器 和其他系统的ie
                name = URLEncoder.encode(fileTitle, "UTF-8");
            } else {
                // fe
                name = new String(fileTitle.getBytes("UTF-8"), "iso-8859-1");
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + name);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = stream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            //关掉输出流，否则文件无法删除
            stream.close();
            os.flush();
            os.close();
            //删除服务器中的临时文件
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            logger.error("下载失败", e);
        }finally {
            // 关闭OSSClient。
            if (ossClient != null)
                ossClient.shutdown();
        }
    }

    /**
     * 上传文件、图片、视频类型限制
     * @param type
     * @return 0-非可上传文件类型 1-文件类型 2-视频类型 3-图片类型
     */
    private Integer fileTypeLimit(String type) {
        // 文件类型限制
        if ("doc".equals(type) || "docx".equals(type) || "xlsx".equals(type) || "xls".equals(type) || "pdf".equals(type) || "txt".equals(type)) {
            return 1;
        }
        // 视频类型限制
        if ("mp4".equals(type) || "avi".equals(type) || "mkv".equals(type) || "rmvb".equals(type)) {
            return 2;
        }
        // 图片类型限制
        if ("bmp".equals(type) || "jpg".equals(type) || "png".equals(type) || "gif".equals(type)) {
            return 3;
        }
        // 压缩文件限制
        if ("zip".equals(type) || "rar".equals(type)) {
            return 1;
        }
        return 0;
    }


    /**
     * 根据上传文件的url获取文件输入流
     * @param url
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getStream(String url) throws FileNotFoundException {
        url = url.replace(OssConfig.ENTFILE_PDFACCESS, "");
        //从oss读取文件
        String fileTitle = url.substring(url.lastIndexOf("-") + 1, url.length());
        OSSClient ossClient = new OSSClient(OssConfig.ENTFILE_ENDPOINT,OssConfig.ENTFILE_ACCESSKEYID,OssConfig.ENTFILE_ACCESSKEYSECRET);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(OssConfig.ENTFILE_BUCKETNAME, url), new File(OssConfig.UPLOAD_PATH + fileTitle));
        //临时文件下载
        File file = new File(OssConfig.DOWNLOAD_PATH + fileTitle);
        FileInputStream stream = new FileInputStream(file);
        return stream;
    }
}
