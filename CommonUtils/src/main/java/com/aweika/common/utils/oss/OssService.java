package com.aweika.common.utils.oss;


import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by lypENG on 2017/4/12.
 */
public interface OssService {

    /**
     * 创建Bucket
     * @return
     */
    public Map createBucket();

    /**
     * 上传oss
     * @param fileKey
     * @param file
     * @return
     */
    public Map uploadOSS(String fileKey, String file);

    /**
     * 下载oss
     * @param fileKey
     * @return
     */
    public Map downloadOSS(String fileKey);

    /**
     * 删除
     * @param fileKey
     * @return
     */
    public Map deleteOSS(String fileKey);

    public Map  uploadOssForSteam(List<OssServiceImpl.Oss> ossList);

    /**文件上传*/
    String uploadFile(File file);

    /**文件上传和预览*/
    String uploadFileAndReview(File file);


}
