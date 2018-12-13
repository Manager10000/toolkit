package com.johnson.toolkit.file;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

/**
 * Created by jhg on 2018-12-11.
 */
public class FTPUtil {

    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /*
     * 从FTP服务器下载文件
     *
     * @param ftpHost FTP IP地址
     * @param ftpUserName FTP 用户名
     * @param ftpPassword FTP用户名密码
     * @param ftpPort FTP端口
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa 注意路径格式,从home路径开始寻找
     * @param localPath 下载到本地的位置 格式：H:/download
     * @param fileName ftp服务器的文件名
     */
    public static void downloadFtpFile(String ftpHost, String ftpUserName,
                                       String ftpPassword, int ftpPort,String ftpPath, String localPath,
                                       String fileName) {
        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.enterLocalPassiveMode();// 设置被动模式
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            File localFile = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        }catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        }  catch (IOException e) {
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }

    }

    /**
     * Description: 向FTP服务器上传文件
     * @param ftpPath  FTP服务器中文件所在路径 格式： ftptest/aa
     * @param ftpSaveFileName 上传到ftp保存后的文件名称
     * @param input 文件流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String ftpHost, String ftpUserName,
                                     String ftpPassword, int ftpPort,String ftpPath,
                                     String ftpSaveFileName,InputStream input) {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            ftpClient.storeFile(ftpSaveFileName, input);

            input.close();
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }


    public static void main(String[] args) {
        //以下功能测试已全部通过
        String ftpHost = "10.63.9.43";
        String ftpUserName = "jhg";
        String ftpPassword = "jhg";
        int ftpPort = 21;
        String ftpPath = "/新建文件夹"; //从home目录开始寻找路径及文件

        String localPath = "D:\\work";
        String fileName = "网站及账号.txt";//fileName是ftp上文件的名称
        //下载一个文件
        FTPUtil.downloadFtpFile(ftpHost,ftpUserName,ftpPassword,ftpPort,ftpPath, localPath, fileName);

        String localPathFile = "D:\\work\\网站及账号.txt";//本地文件完整路径
        String ftpSaveFileName = "ftpFileName.txt";//保存到ftp后文件的名称,可与本地文件名不一致，但格式需要一致
        //上传一个文件
        try{
            FileInputStream in=new FileInputStream(new File(localPathFile));
            FTPUtil.uploadFile(ftpHost,ftpUserName,ftpPassword,ftpPort, ftpPath, ftpSaveFileName,in);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }

        //在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
       try {
            String newFile = "log_20181212.doc";//ftp服务器上新建的文件
            InputStream input = new ByteArrayInputStream("test ftp jyf".getBytes("GBK"));
            boolean flag = FTPUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, newFile,input);;
            System.out.println(flag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }




}
