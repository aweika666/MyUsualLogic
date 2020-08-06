package com.aweika.common.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: Michael
 * @date: 2020/6/20
 * @description:
 */
@Component
public class OssConfig {
    @Value("${entFile.endpoint}")
    private String entFileEndpoint;
    @Value("${entFile.accessKeyId}")
    private String entFileAccessKeyId;
    @Value("${entFile.accessKeySecret}")
    private String entFileAccessKeySecret;
    @Value("${entFile.bucketName}")
    private String entFileBucketName;
    @Value("${entFile.firstKey}")
    private String entFileFirstKey;
    @Value("${entFile.pdfAccess}")
    private String entFilePdfAccess;
    @Value("${entFile.accessHttps}")
    private String entFileAccessHttps;

    @Value("${office.host}")
    private String officeHost;
    @Value("${office.port}")
    private String officePort;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${download.path}")
    private String downLoadPath;

    public static String ENTFILE_ENDPOINT;
    public static String ENTFILE_ACCESSKEYID;
    public static String ENTFILE_ACCESSKEYSECRET;
    public static String ENTFILE_BUCKETNAME;
    public static String ENTFILE_FIRSTKEY;
    public static String ENTFILE_PDFACCESS;
    public static String ENTFILE_ACCESSHTTPS;

    public static String OFFICE_HOST;
    public static String OFFICE_PORT;

    public static String UPLOAD_PATH;

    public static String DOWNLOAD_PATH;

    @PostConstruct
    public void afterPropertiesSet(){
        ENTFILE_ENDPOINT = entFileEndpoint;
        ENTFILE_ACCESSKEYID = entFileAccessKeyId;
        ENTFILE_ACCESSKEYSECRET = entFileAccessKeySecret;
        ENTFILE_BUCKETNAME = entFileBucketName;
        ENTFILE_FIRSTKEY = entFileFirstKey;
        ENTFILE_PDFACCESS = entFilePdfAccess;
        ENTFILE_ACCESSHTTPS = entFileAccessHttps;

        OFFICE_HOST = officeHost;
        OFFICE_PORT = officePort;

        UPLOAD_PATH = uploadPath;

        DOWNLOAD_PATH = this.downLoadPath;
    }
}
