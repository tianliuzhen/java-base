package com.aaa.javabase.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
/**
 *
 * @author liuzhen.tian
 * @version $ Id: WebTest.java, v 0.1 2020/7/2 11:02 liuzhen.tian Exp $
 */


@Slf4j
public class SftpClient implements Closeable {
    private static Logger logger = LoggerFactory.getLogger(SftpClient.class);

    private ChannelSftp channelSftp = null;
    private Session session = null;

    // mills
    public static final int TIMEOUT = 43200000;

    /**
     *
     * @param host     ip
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public SftpClient(String host, int port, String username, String password) {
        init(host, port, username, password);
    }

    public SftpClient(String sftpConnectConf) {
        String[] config = sftpConnectConf.split(",");
        init(config[1], config.length > 6 ? Integer.parseInt(config[6]) : 22, config[2], config[3]);
    }

    private void init(String host, int port, String username, String password) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            if (StringUtils.isNotBlank(password)) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(TIMEOUT);
        } catch (JSchException e) {
            throw new RuntimeException("sftp client 连接异常");
        }
    }

    public boolean login() {
        try {
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            return true;
        } catch (JSchException e) {
            log.error("登录异常: " + e);
            return false;
        }
    }

    @Override
    public void close() throws IOException {
        if (channelSftp != null && channelSftp.isConnected()) {
            channelSftp.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    /**
     * 上传文件
     *
     * @param pathName SFTP服务器目录
     * @param fileName 服务器上保存的文件名
     * @param input    输入文件流
     * @return boolean
     */
    public boolean uploadFile(String pathName, String fileName, InputStream input) {
        String currentDir = currentDir();
        if (!changeDir(pathName)) {
            return false;
        }
        try {
            channelSftp.put(input, fileName, ChannelSftp.OVERWRITE);
            if (!existFile(fileName)) {
                return false;
            }
            return true;
        } catch (SftpException e) {
            log.error( "upload file failed,[filename="+fileName+"]");
            return false;
        } finally {
            changeDir(currentDir);
        }
    }

    /**
     * 上传文件
     * @param pathName  SFTP服务器目录
     * @param fileName  服务器上保存的文件名
     * @param localFile 本地文件
     * @return boolean
     */
    public boolean uploadFile(String pathName, String fileName, String localFile) {
        String currentDir = currentDir();
        if (!changeDir(pathName)) {
            return false;
        }
        try {
            channelSftp.put(localFile, fileName, ChannelSftp.OVERWRITE);
            if (!existFile(fileName)) {
                return false;
            }
            return true;
        } catch (SftpException e) {
            log.error( "upload file failed,[filename="+fileName+"]");
            return false;
        } finally {
            changeDir(currentDir);
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param basePath     服务器的基础路径
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     */
    public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
        String[] dirs = (basePath + "/" + directory).split("/");
        String tempPath = "";
        for (String dir : dirs) {
            if(StringUtils.isNotBlank(dir)){
                tempPath += "/" + dir;
                try {
                    channelSftp.cd(tempPath);
                } catch (SftpException ex) {
                    channelSftp.mkdir(dir);
                    channelSftp.cd(dir);
                }
            }
        }
        channelSftp.put(input, sftpFileName); // 上传文件
    }

    /**
     * 下载文件
     *
     * @param remotePath SFTP服务器目录
     * @param fileName   服务器上需要下载的文件名
     * @param localPath  本地保存路径
     * @return boolean
     */
    public boolean downloadFile(String remotePath, String fileName, String localPath) {
        String currentDir = currentDir();
        if (!changeDir(remotePath)) {
            return false;
        }

        try {
            String localFilePath = localPath + File.separator + fileName;
            channelSftp.get(fileName, localFilePath);
            File localFile = new File(localFilePath);
            if (!localFile.exists()) {
                return false;
            }
            return true;
        } catch (SftpException e) {
            logger.error("download file failed", e);
            return false;
        } finally {
            changeDir(currentDir);
        }
    }

    /**
     * 下载文件。
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            channelSftp.cd(directory);
        }
        File file = new File(saveFile);
        channelSftp.get(downloadFile, new FileOutputStream(file));
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public InputStream download(String directory, String downloadFile) throws SftpException, IOException {
        if (directory != null && !"".equals(directory)) {
            channelSftp.cd(directory);
        }
        InputStream is = channelSftp.get(downloadFile);
        return is;
    }

    public void downloadDirectory(String directory, String localDirectory) throws SftpException {
        if(!isDirExist(directory)){
            log.error("远程目录不存在");
        }
        File localFile = new File(localDirectory);
        if(!localFile.exists()){
            localFile.mkdirs();
        }
        changeDir(directory);
        Vector<ChannelSftp.LsEntry> files = listFiles(directory);
        for(ChannelSftp.LsEntry entry:files){
            // 因为会包含"."和".."两个目录，需要过滤掉
            if (entry.getFilename().equals(".") || entry.getFilename().equals("..")) {
                continue;
            }
            if(entry.getAttrs().isDir()){
                String nextDir = directory+File.separator+entry.getFilename();
                downloadDirectory(nextDir, localDirectory+File.separator+entry.getFilename());
            }else {
                downloadFile(directory,entry.getFilename(),localDirectory);
            }
        }
    }

    /**
     * 切换工作目录
     *
     * @param pathName 路径
     * @return boolean
     */
    public boolean changeDir(String pathName) {
        if (StringUtils.isBlank(pathName)) {
            return false;
        }
        try {
            channelSftp.cd(pathName.replaceAll("\\\\", "/"));
            return true;
        } catch (SftpException e) {
            log.error("failed to change directory: " + pathName);
            return false;
        }
    }

    /**
     * 切换到上一级目录
     *
     * @return boolean
     */
    public boolean changeToParentDir() {
        return changeDir("..");
    }

    /**
     * 切换到根目录
     *
     * @return boolean
     */
    public boolean changeToHomeDir() {
        String homeDir = null;
        try {
            homeDir = channelSftp.getHome();
        } catch (SftpException e) {
            logger.error("can not get home directory", e);
            return false;
        }
        return changeDir(homeDir);
    }

    /**
     * 创建目录
     *
     * @param dirName 目录
     * @return boolean
     */
    public boolean makeDir(String dirName) {
        try {
            channelSftp.mkdir(dirName);
            return true;
        } catch (SftpException e) {
            logger.error("failed to create directory", e);
            return false;
        }
    }

    /**
     * 删除文件夹
     *
     * @param dirName
     * @return boolean
     */
    public boolean delDir(String dirName) {
        if (!changeDir(dirName)) {
            return false;
        }
        Vector<ChannelSftp.LsEntry> list = null;
        try {
            list = channelSftp.ls(channelSftp.pwd());
        } catch (SftpException e) {
            logger.error("can not list directory", e);
            return false;
        }
        for (ChannelSftp.LsEntry entry : list) {
            String fileName = entry.getFilename();
            if (!fileName.equals(".") && !fileName.equals("..")) {
                if (entry.getAttrs().isDir()) {
                    delDir(fileName);
                } else {
                    delFile(fileName);
                }
            }
        }
        if (!changeToParentDir()) {
            return false;
        }
        try {
            channelSftp.rmdir(dirName);
            return true;
        } catch (SftpException e) {
            logger.error("failed to delete directory " + dirName, e);
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return boolean
     */
    public boolean delFile(String fileName) {
        if (fileName == null || fileName.trim().equals("")) {
            logger.debug("invalid filename");
            return false;
        }
        try {
            channelSftp.rm(fileName);
            return true;
        } catch (SftpException e) {
            logger.error("failed to delete file " + fileName, e);
            return false;
        }
    }

    /**
     * 当前目录下文件及文件夹名称列表
     *
     * @return String[]
     */
    public String[] ls() {
        return list(Filter.ALL);
    }

    /**
     * 指定目录下文件及文件夹名称列表
     *
     * @return String[]
     */
    public String[] ls(String pathName) {
        String currentDir = currentDir();
        if (!changeDir(pathName)) {
            return new String[0];
        }
        String[] result = list(Filter.ALL);
        if (!changeDir(currentDir)) {
            return new String[0];
        }
        return result;
    }

    /**
     * 当前目录下文件名称列表
     *
     * @return String[]
     */
    public String[] lsFiles() {
        return list(Filter.FILE);
    }

    /**
     * 指定目录下文件名称列表
     *
     * @return String[]
     */
    public String[]     lsFiles(String pathName) {
        String currentDir = currentDir();
        if (!changeDir(pathName)) {
            return new String[0];
        }
        String[] result = list(Filter.FILE);
        if (!changeDir(currentDir)) {
            return new String[0];
        }
        return result;
    }

    /**
     * 当前目录下文件夹名称列表
     *
     * @return String[]
     */
    public String[] lsDirs() {
        return list(Filter.DIR);
    }

    /**
     * 指定目录下文件夹名称列表
     *
     * @return String[]
     */
    public String[] lsDirs(String pathName) {
        String currentDir = currentDir();
        if (!changeDir(pathName)) {
            return new String[0];
        }
        String[] result = list(Filter.DIR);
        if (!changeDir(currentDir)) {
            return new String[0];
        }
        return result;
    }

    /**
     * 当前目录是否存在文件或文件夹
     *
     * @param name 名称
     * @return boolean
     */
    public boolean exist(String name) {
        return exist(ls(), name);
    }

    /**
     * 指定目录下，是否存在文件或文件夹
     *
     * @param path 目录
     * @param name 名称
     * @return boolean
     */
    public boolean exist(String path, String name) {
        return exist(ls(path), name);
    }

    /**
     * 当前目录是否存在文件
     *
     * @param name 文件名
     * @return boolean
     */
    public boolean existFile(String name) {
        return exist(lsFiles(), name);
    }

    /**
     * 指定目录下，是否存在文件
     *
     * @param path 目录
     * @param name 文件名
     * @return boolean
     */
    public boolean existFile(String path, String name) {
        return exist(lsFiles(path), name);
    }

    /**
     * 当前目录是否存在文件夹
     *
     * @param name 文件夹名称
     * @return boolean
     */
    public boolean existDir(String name) {
        return exist(lsDirs(), name);
    }

    /**
     * 指定目录下，是否存在文件夹
     *
     * @param path 目录
     * @param name 文家夹名称
     * @return boolean
     */
    public boolean existDir(String path, String name) {
        return exist(lsDirs(path), name);
    }

    /**
     * 当前工作目录
     *
     * @return String
     */
    public String currentDir() {
        try {
            return channelSftp.pwd();
        } catch (SftpException e) {
            logger.error("failed to get current dir", e);
            return homeDir();
        }
    }

    //------ private method ------

    /**
     * 枚举，用于过滤文件和文件夹
     */
    private enum Filter {
        /**
         * 文件及文件夹
         */
        ALL,
        /**
         * 文件
         */
        FILE,
        /**
         * 文件夹
         */
        DIR
    }

    /**
     * 列出当前目录下的文件及文件夹
     *
     * @param filter 过滤参数
     * @return String[]
     */
    private String[] list(Filter filter) {
        Vector<ChannelSftp.LsEntry> list = null;
        try {
            //ls方法会返回两个特殊的目录，当前目录(.)和父目录(..)
            list = channelSftp.ls(channelSftp.pwd());
        } catch (SftpException e) {
            logger.error("can not list directory", e);
            return new String[0];
        }

        List<String> resultList = new ArrayList<String>();
        for (ChannelSftp.LsEntry entry : list) {
            if (filter(entry, filter)) {
                resultList.add(entry.getFilename());
            }
        }
        return resultList.toArray(new String[0]);
    }

    /**
     * 判断是否是否过滤条件
     *
     * @param entry LsEntry
     * @param f     过滤参数
     * @return boolean
     */
    private boolean filter(ChannelSftp.LsEntry entry, Filter f) {
        if (f.equals(Filter.ALL)) {
            return !entry.getFilename().equals(".") && !entry.getFilename().equals("..");
        } else if (f.equals(Filter.FILE)) {
            return !entry.getFilename().equals(".") && !entry.getFilename().equals("..") && !entry.getAttrs().isDir();
        } else if (f.equals(Filter.DIR)) {
            return !entry.getFilename().equals(".") && !entry.getFilename().equals("..") && entry.getAttrs().isDir();
        }
        return false;
    }

    /**
     * 根目录
     *
     * @return String
     */
    private String homeDir() {
        try {
            return channelSftp.getHome();
        } catch (SftpException e) {
            return "/";
        }
    }

    /**
     * 判断字符串是否存在于数组中
     *
     * @param strArr 字符串数组
     * @param str    字符串
     * @return boolean
     */
    private boolean exist(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        if (str == null || str.trim().equals("")) {
            return false;
        }
        for (String s : strArr) {
            if (s.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }





    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        channelSftp.cd(directory);
        channelSftp.rm(deleteFile);
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    public Vector<ChannelSftp.LsEntry> listFiles(String directory) throws SftpException {
        return channelSftp.ls(directory);
    }


    /**
     * 上传单个文件
     *
     * @param remotePath 远程保存目录
     * @param remotePath 本地上传目录(以路径符号结束)
     * @return true | false
     */
    public boolean uploadFile(File localFile, String remotePath) {
        FileInputStream in = null;
        try {
            String remoteDirPath = remotePath.substring(0, remotePath.lastIndexOf('/') + 1);
            String remoteFilePath = remotePath.substring(remotePath.lastIndexOf('/') + 1);
            createDir(remoteDirPath);
            in = new FileInputStream(localFile);
            channelSftp.put(in, remoteFilePath);
            return true;
        } catch (FileNotFoundException e) {
            logger.error("找不到文件");
        } catch (SftpException e) {
            logger.error("上传文件失败");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("关闭上传文件流失败");
                }
            }
        }
        return false;
    }

    /**
     * 上传单个文件
     *
     * @param remotePath：远程保存目录
     * @param remoteFileName：保存文件名
     * @param localPath：本地上传目录(以路径符号结束)
     * @param localFileName：上传的文件名
     * @return true | false
     */
    public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileInputStream in = null;
        try {
            createDir(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            channelSftp.put(in, remoteFileName);
            return true;
        } catch (FileNotFoundException e) {
            logger.error("找不到文件");
        } catch (SftpException e) {
            logger.error("上传文件失败");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("关闭上传文件流失败");
                }
            }
        }
        return false;
    }

    /**
     * 删除本地文件
     *
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        if (!file.isFile()) {
            return false;
        }
        boolean rs = file.delete();
        if (rs && logger.isInfoEnabled()) {
            logger.info("delete file success from local.");
        }
        return rs;
    }

    /**
     * 创建目录
     *
     * @param createPath
     * @return
     */
    public boolean createDir(String createPath) {
        try {
            if (isDirExist(createPath)) {
                this.channelSftp.cd(createPath);
                return true;
            }
            String pathArray[] = createPath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArray) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (!isDirExist(filePath.toString())) {
                    // 建立目录
                    channelSftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                }
                channelSftp.cd(filePath.toString());
            }
            this.channelSftp.cd(createPath);
            return true;
        } catch (SftpException e) {
            logger.error("创建文件目录失败");
        }
        return false;
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = channelSftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * Exists boolean.
     *
     * @param path the path
     * @return the boolean
     */
    public boolean exists(String path) {
        boolean exists = true;
        try {
            channelSftp.lstat(path);
        } catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                exists = false;
            }
        }
        return exists;
    }

    /**
     * 要删除文件所在目录
     */
    public void delete(String path) {
        try {
            if (exists(path)) {
                channelSftp.rm(path);
            }
            if (logger.isInfoEnabled()) {
                logger.info("delete file success from sftp.");
            }
        } catch (Exception e) {
            logger.error("删除文件失败");
        }
    }
}
