package com.aweika.common.utils.oss;


import com.aliyun.oss.*;
import com.aliyun.oss.model.*;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.aweika.common.utils.config.OssConfig;
import com.aweika.common.utils.oss.OssService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * oss上传bean
 */
@Service("ossService")
public class OssServiceImpl implements OssService {
    /* static Logger logger = Logger.getLogger(OSS.class);*/
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    //private  String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    /*private  String accessKeyId = "<YourAccessKeyId>";
    private  String accessKeySecret = "<YourAccessKeySecret>";*/
    // private  String accessKeyId = "aa";
    //private  String accessKeySecret = "aa";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    //private  String bucketName = "<YourBucketName>";

    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
    //private  String firstKey = "my-first-key";

    public static void main(String[] args) {
        String endpoint = "Aa";
        String accessKeyId = "aa";
        String accessKeySecret = "aa";
        String bucketName = "aa-test";
        String firstKey = "my-first-key";
        // 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        /*PropertyConfigurator.configure("conf/log4j.properties");*/

        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {

            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (ossClient.doesBucketExist(bucketName)) {
                System.out.println("您已经创建Bucket：" + bucketName + "。");
            } else {
                System.out.println("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(bucketName);
            }

            // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            System.out.println("Bucket " + bucketName + "的信息如下：");
            System.out.println("\t数据中心：" + info.getBucket().getLocation());
            System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
            System.out.println("\t用户标志：" + info.getBucket().getOwner());

            // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            InputStream is = new ByteArrayInputStream("Hello OSS".getBytes());
            ossClient.putObject(bucketName, firstKey, is);
            System.out.println("Object：" + firstKey + "存入OSS成功。");

            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
            OSSObject ossObject = ossClient.getObject(bucketName, firstKey);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                objectContent.append(line);
            }
            inputStream.close();
            System.out.println("Object：" + firstKey + "的内容是：" + objectContent);

            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            String fileKey = "README.md";
            ossClient.putObject(bucketName, fileKey, new File("README.md"));
            System.out.println("Object：" + fileKey + "存入OSS成功。");

            // 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
            ObjectListing objectListing = ossClient.listObjects(bucketName);
            List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
            System.out.println("您有以下Object：");
            for (OSSObjectSummary object : objectSummary) {
                System.out.println("\t" + object.getKey());
            }

            // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
            ossClient.deleteObject(bucketName, firstKey);
            System.out.println("删除Object：" + firstKey + "成功。");
            ossClient.deleteObject(bucketName, fileKey);
            System.out.println("删除Object：" + fileKey + "成功。");

        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

    }

    /**
     * 创建Bucket
     *
     * @return
     */
    @Override
    public Map createBucket() {
        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;
        Map view = new HashMap();
        view.put("msg", "创建成功");
        view.put("code", "00");
        // 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        /*PropertyConfigurator.configure("conf/log4j.properties");*/
        logger.info("Started");
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {

            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
                view.put("msg", "您已经创建Bucket：" + bucketName + "。");
                view.put("code", "00");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(bucketName);
            }
            // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            logger.info("Bucket " + bucketName + "的信息如下：");
            logger.info("\t数据中心：" + info.getBucket().getLocation());
            logger.info("\t创建时间：" + info.getBucket().getCreationDate());
            logger.info("\t用户标志：" + info.getBucket().getOwner());
            view.put("data", "\t数据中心：" + info.getBucket().getLocation() + " \t创建时间：" + info.getBucket().getCreationDate() + " \t用户标志：" + info.getBucket().getOwner());
        } catch (OSSException oe) {
            view.put("msg", "创建Bucket失败");
            view.put("code", "01");
            oe.printStackTrace();
        } /*catch (ClientException ce) {
            view.put("msg", "创建Bucket失败");
            view.put("code", "01");
            ce.printStackTrace();
        } */catch (Exception e) {
            view.put("msg", "创建Bucket失败");
            view.put("code", "01");
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        logger.info("Completed");
        return view;
    }

    public static OSS oss;

    /**
     * 上传oss
     *
     * @param fileKey
     * @param file
     * @return
     */
    @Override
    public Map uploadOSS(String fileKey, String file) {
        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;
        Map view = new HashMap();
        view.put("msg", "上传成功");
        view.put("code", "00");
        // 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        /*PropertyConfigurator.Configure("conf/log4j.properties");*/
        logger.info("Started");

        //OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKey, screctKey);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        oss = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (oss.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("msg", "您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("code", "01");
                return view;
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                //ossClient.createBucket(bucketName);
            }

            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            //String fileKey = "README.md";
            //ossClient.putObject(bucketName, fileKey, new File("README.md"));
            PutObjectResult result = oss.putObject(bucketName, fileKey, new File(file));
            String eTag = result.getETag();
            logger.info("Object：" + fileKey + "存入OSS成功。");
            oss.setObjectAcl(bucketName, fileKey, CannedAccessControlList.PublicRead);

        } catch (OSSException oe) {
            view.put("msg", "上传失败");
            view.put("code", "01");
            oe.printStackTrace();
        }/* catch (ClientException ce) {
            view.put("msg", "上传失败");
            view.put("code", "01");
            ce.printStackTrace();
        }*/ catch (Exception e) {
            view.put("msg", "上传失败");
            view.put("code", "01");
            e.printStackTrace();
        } finally {
            oss.shutdown();
        }

        logger.info("Completed");
        return view;
    }

    /**
     * 上传oss
     *
     * @return
     */
    @Override
    public Map uploadOssForSteam(List<Oss> ossList) {
        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;
        Map view = new HashMap();
        view.put("msg", "上传成功");
        view.put("code", "00");
        // 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        /*PropertyConfigurator.configure("conf/log4j.properties");*/
        logger.info("Started");

        //OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKey, screctKey);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        oss = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (oss.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("msg", "您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("code", "01");
                return view;
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                //ossClient.createBucket(bucketName);
            }

            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            //String fileKey = "README.md";
            //ossClient.putObject(bucketName, fileKey, new File("README.md"));
            for (Oss ossE : ossList) {
                PutObjectResult result = oss.putObject(bucketName, ossE.getKey(), new ByteArrayInputStream(ossE.getValue()));
                String eTag = result.getETag();
                logger.info("Object：" + ossE.getKey() + "存入OSS成功。");
                oss.setObjectAcl(bucketName, ossE.getKey(), CannedAccessControlList.PublicRead);
            }
        } catch (OSSException oe) {
            view.put("msg", "上传失败");
            view.put("code", "01");
            oe.printStackTrace();
        }/* catch (ClientException ce) {
            view.put("msg", "上传失败");
            view.put("code", "01");
            ce.printStackTrace();
        }*/ catch (Exception e) {
            view.put("msg", "上传失败");
            view.put("code", "01");
            e.printStackTrace();
        } finally {
            oss.shutdown();
        }

        logger.info("Completed");
        return view;
    }

    /**
     * 下载oss
     *
     * @param fileKey
     * @return
     */
    @Override
    public Map downloadOSS(String fileKey) {
        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;
        Map view = new HashMap();
        view.put("msg", "下载成功");
        view.put("code", "00");
        // 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        /*PropertyConfigurator.configure("conf/log4j.properties");*/
        logger.info("Started");
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {

            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("msg", "您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("code", "01");
                return view;
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                //ossClient.createBucket(bucketName);
            }

            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
            OSSObject ossObject = ossClient.getObject(bucketName, fileKey);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                objectContent.append(line);
            }
            inputStream.close();
            logger.info("Object：" + fileKey + "的内容是：" + objectContent);


        } catch (OSSException oe) {
            view.put("msg", "下载失败");
            view.put("code", "01");
            oe.printStackTrace();
        }/* catch (ClientException ce) {
            view.put("msg", "下载失败");
            view.put("code", "01");
            ce.printStackTrace();
        } */catch (Exception e) {
            view.put("msg", "下载失败");
            view.put("code", "01");
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        logger.info("Completed");
        return view;
    }

    /**
     * 删除
     *
     * @param fileKey
     * @return
     */
    @Override
    public Map deleteOSS(String fileKey) {
        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;
        Map view = new HashMap();
        view.put("msg", "删除成功");
        view.put("code", "00");
        // 日志配置，OSS Java SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
        // 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
        /*PropertyConfigurator.configure("conf/log4j.properties");*/
        logger.info("Started");
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {

            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("msg", "您的Bucket不存在，创建Bucket：" + bucketName + "。");
                view.put("code", "01");
                return view;
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                //ossClient.createBucket(bucketName);
            }
            // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
            ossClient.deleteObject(bucketName, fileKey);
            logger.info("删除Object：" + fileKey + "成功。");
            ossClient.deleteObject(bucketName, fileKey);
            logger.info("删除Object：" + fileKey + "成功。");

        } catch (OSSException oe) {
            view.put("msg", "删除失败");
            view.put("code", "01");
            oe.printStackTrace();
        } /*catch (ClientException ce) {
            view.put("msg", "删除失败");
            view.put("code", "01");
            ce.printStackTrace();
        } */catch (Exception e) {
            view.put("msg", "删除失败");
            view.put("code", "01");
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        logger.info("Completed");
        return view;
    }

    /**
     * 上传文件
     */
    @Override
    public String uploadFile(File file) {
        logger.info("=========>OSS文件上传开始：" + file.getName());

        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if (null == file) {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = bucketName + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (null != result) {
                logger.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return fileUrl;
            }
        } catch (Exception oe) {
            logger.error("错误",oe);
        } /*catch (ClientException ce) {
            logger.error(ce.getMessage());
        } */finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }
    /**
     * 上传文件
     */
    @Override
    public String uploadFileAndReview(File file) {
        logger.info("=========>OSS文件上传开始：" + file.getName());
        String endpoint = OssConfig.ENTFILE_ENDPOINT;
        String accessKeyId = OssConfig.ENTFILE_ACCESSKEYID;
        String accessKeySecret = OssConfig.ENTFILE_ACCESSKEYSECRET;
        String bucketName = OssConfig.ENTFILE_BUCKETNAME;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        String fileUrl = null;

        if (null == file) {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            fileUrl = bucketName + "/"+ file.getName();
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (null != result) {
                logger.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
            }
        } catch (Exception e) {
            logger.error("上传错误",e);
        } finally {
            //关闭
            ossClient.shutdown();
        }
        String type = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());

        if("doc".equals(type) || "docx".equals(type) || "xlsx".equals(type)|| "xls".equals(type)|| "pdf".equals(type) ){
            poolExecutor.execute(() -> {
                        try {
                            String fileName = file.getName();
                            String ass = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                            String head = fileName.substring(0, fileName.lastIndexOf("."));
                            File outputFile = new File(OssConfig.UPLOAD_PATH, head + ".pdf");
                            if (!outputFile.exists()) {
                                logger.info("文件不存在,开始转换");

                                Office2PDFUtil dp = new Office2PDFUtil(OssConfig.OFFICE_HOST,OssConfig.OFFICE_PORT , file, outputFile, ass, "pdf");
                                dp.start();
                                dp.join();
                            }
                            OSSClient ossClientX = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                            try {
                                //容器不存在，就创建
                                if (!ossClientX.doesBucketExist(bucketName)) {
                                    ossClientX.createBucket(bucketName);
                                    CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                                    createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                                    ossClientX.createBucket(createBucketRequest);
                                }
                                //创建文件路径
                                String fileUrlO = bucketName + "/" + outputFile.getName();
                                //上传文件
                                PutObjectResult result = ossClientX.putObject(new PutObjectRequest(bucketName, fileUrlO, outputFile));
                                //设置权限 这里是公开读
                                ossClientX.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
                                if (null != result) {
                                    logger.info("==========>OSS预览文件上传成功,OSS地址：" + fileUrlO);
                                }
                            } catch (OSSException oe) {
                                logger.error("错误",oe);
                            } catch (ClientException ce) {
                                logger.error("错误",ce);
                            } finally {
                                //关闭
                                ossClientX.shutdown();
                            }

                        } catch (Exception e) {
                            logger.error("生成预览文件错误", e);
                        }
                    }
            );
        }

        return fileUrl;
    }

    public static class Oss {
        private String key;
        private byte[] value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public byte[] getValue() {
            return value;
        }

        public void setValue(byte[] value) {
            this.value = value;
        }
    }


    public static class Office2PDFUtil extends Thread{
        private String host;
        private String port;
        private File inputFile;// 需要转换的文件
        private File outputFile;// 输出的文件
        private String docFormat;//需要转换的文件格式
        private String outFormat;//输出的文件的文件格式

        public String getOutFormat() {
            return outFormat;
        }

        public void setOutFormat(String outFormat) {
            this.outFormat = outFormat;
        }

        public String getDocFormat() {
            return docFormat;
        }

        public void setDocFormat(String docFormat) {
            this.docFormat = docFormat;
        }



        public Office2PDFUtil(String host , String port, File inputFile, File outputFile, String docFormat, String outFormat) {
            this.host = host;
            this.port = port;
            this.inputFile = inputFile;
            this.outputFile = outputFile;
            this.docFormat=docFormat;
            this.outFormat=outFormat;

        }

        public void docToPdf() {
            OpenOfficeConnection connection = new SocketOpenOfficeConnection(host, Integer.parseInt(port));

            try {

                // 获得文件格式
                DefaultDocumentFormatRegistry formatReg = this.getDocumentFormats();
                DocumentFormat pdfFormat = formatReg.getFormatByFileExtension(this.outFormat);
                DocumentFormat docFormat = formatReg.getFormatByFileExtension(this.docFormat);
                if(pdfFormat ==null || docFormat==null){
                }
                connection.connect();
                DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
//            DocumentConverter converter = new Office2PDFConver(connection);

                converter.convert(this.getInputFile(), docFormat, this.getOutputFile(), pdfFormat);
//                converter.convert(this.getInputFile(), this.getOutputFile());
            } catch (ConnectException cex) {
                cex.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                    connection = null;
                }
//                this.destroy();
            }
        }

        private DefaultDocumentFormatRegistry getDocumentFormats(){
            DefaultDocumentFormatRegistry defaultDocumentFormatRegistry = new DefaultDocumentFormatRegistry();
            DocumentFormat xlsx = new DocumentFormat("Microsoft Excel 2007 XML", DocumentFamily.SPREADSHEET, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
            defaultDocumentFormatRegistry.addDocumentFormat(xlsx);
            DocumentFormat docx = new DocumentFormat("Microsoft Word", DocumentFamily.TEXT, "application/msword", "docx");
            defaultDocumentFormatRegistry.addDocumentFormat(docx);
            return defaultDocumentFormatRegistry;
        }
        /**
         * 由于服务是线程不安全的，所以……需要启动线程
         */
        @Override
        public void run() {
            this.docToPdf();
        }

        public File getInputFile() {
            return inputFile;
        }

        public void setInputFile(File inputFile) {
            this.inputFile = inputFile;
        }

        public File getOutputFile() {
            return outputFile;
        }

        public void setOutputFile(File outputFile) {
            this.outputFile = outputFile;
        }
    }

}
