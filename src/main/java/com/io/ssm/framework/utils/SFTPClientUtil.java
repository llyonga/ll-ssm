package com.io.ssm.framework.utils;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:  ftp上传工具类，采用SFTP方式
 * @author: lvyongb
 * @date: 2018-08-27
 * @time: 16:00
 */
public class SFTPClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SFTPClientUtil.class);

    private static ChannelSftp sftp = null;
    private static Channel channel = null;
    private static Session session = null;

    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    private static ChannelSftp getClient() throws Exception {
        String hostName = "180.76.98.116";
        int port = 22;
        String userName = "root";
        String password = "VU5hGeEr54oPaXeH";
        if (sftp == null || sftp.isClosed()) {
            synchronized (SFTPClientUtil.class) {
                if (sftp == null || sftp.isClosed()) {
                    JSch jsch = new JSch();
                    jsch.getSession(userName, hostName, port);
                    session = jsch.getSession(userName, hostName, port);
                    session.setPassword(password);
                    Properties sshConfig = new Properties();
                    sshConfig.put("StrictHostKeyChecking", "no");
                    session.setConfig(sshConfig);
                    session.connect();
                    channel = session.openChannel("sftp");
                    channel.connect();
                    sftp = (ChannelSftp) channel;
                }
            }
        }
        return sftp;
    }

    /**
     * 文件上传ftp服务器
     * @param file
     * @param ftpPath
     */
    public static void uploadFile(File file,String ftpPath) {
        InputStream in = null;
        try {
            ChannelSftp sftp = getClient();
            in = new FileInputStream(file);
            sftp.cd(ftpPath);
            sftp.put(in,file.getName());
            LOGGER.info("文件上传FTP成功！");
        } catch (Exception e) {
            LOGGER.error("文件上传FTP失败！",e);
        } finally {
            closeAll(in);
        }
    }

    /**
     * 将文件内容上传ftp
     * @param content
     * @param ftpPath
     * @param ftpFileName
     */
    public static void uploadFileContent(String content,String ftpPath,String ftpFileName) {
        InputStream in = null;
        try {
            ChannelSftp sftp = getClient();
            in = new ByteArrayInputStream(content.getBytes("UTF-8"));
            isDirExist(ftpPath);
            sftp.put(in,ftpFileName);
            LOGGER.info("上传FTP成功！");
        } catch (Exception e) {
            LOGGER.error("上传FTP失败！",e);
        } finally {
            closeAll(in);
        }
    }

    /**
     * 判断目录是否存在，不存在创建
     * @param ftpPath
     */
    private static void isDirExist(String ftpPath) {
        try {
            sftp.cd(ftpPath);
        } catch (Exception e) {
            try {
                int i = ftpPath.lastIndexOf("/");
                sftp.cd(ftpPath.substring(0, i));
                sftp.mkdir(ftpPath.substring(i+1,ftpPath.length()));
                sftp.cd(ftpPath);
            } catch (SftpException ex) {
                LOGGER.error("创建目录失败！",ex);
            }
        }
    }

    /**
     * 用于关闭所有
     * @param in
     */
    private static void closeAll(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                sftp = null;
            }
        }
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
                channel = null;
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                session = null;
            }
        }
    }
}

