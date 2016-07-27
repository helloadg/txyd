package txyd.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FTPClientUtil {

	private static Logger logger = LogManager.getLogger(FTPClientUtil.class);

	private String server = "172.16.5.144";
	private int port = 2121;
	private String username = "wxadmin";
	private String password = "admin@wx";
	private String ftpUploadDir = "/product/management_report/";
	
	
	FTPClient ftpClient = new FTPClient();
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FTPClientUtil main = new FTPClientUtil();
		File file = new File("D:/客户信息认证.png");
		boolean b = main.uploadFileToFtpServer(file, null, "test.png");
		System.out.println("上传结果：" + b);

		main.connect();
		main.ftpClient.enterLocalPassiveMode();
		FTPFile[] files = main.ftpClient.listFiles(main.ftpUploadDir);
		for (FTPFile ftpFile : files) {
			System.out.println(ftpFile.getName());
		}
		main.disconnect();
	}

	private static String GBK2ISO8859(String str) {
		try {
			if (str == null)
				return "";
			else
				return new String(str.getBytes("GBK"), "iso-8859-1");
		} catch (Exception e) {
			return "";
		}
	}

	public boolean connect() {
		boolean b = false;
		if (!ftpClient.isConnected()) {
			int reply;
			try {
				ftpClient = new FTPClient();
				FTPClientConfig config = new FTPClientConfig();
				ftpClient.configure(config);
				ftpClient.connect(server, port);
				b = ftpClient.login(username, password);
				reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					 System.err.println("FTP server refused connection.");
//					logger.error("FTP server refused connection.");
				}
			} catch (Exception e) {
				logger.error("链接ftp服务器:" + server + "失败！");
				// System.err.println("链接ftp服务器:" + server + "失败！");
				e.printStackTrace();
			}
		}
		return b;
	}

	public void disconnect() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean downloadFileFromFtpServer(String remoteFilePath, String localFilepath) {

		return false;
	}

	public boolean uploadFileToFtpServer(File fileUpload, String remoteFileDir, String remoteFileName) {
		if (remoteFileDir == null) {
			remoteFileDir = ftpUploadDir;
		}
		boolean b = false;
		BufferedInputStream inputStream = null;
		try {
			connect();
			// 二进制
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 设置被动模式，如不设置，可能在调用makeDirectory、storeFile时无响应
			ftpClient.enterLocalPassiveMode();
			inputStream = new BufferedInputStream(new FileInputStream(fileUpload));
			b = ftpClient.storeFile(GBK2ISO8859(remoteFileDir + remoteFileName), inputStream);
			disconnect();
		} catch (Exception e) {
			logger.error("上传文件失败！");
			// System.err.println("上传文件失败！");
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			disconnect();
		}
		return b;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFtpUploadDir() {
		return ftpUploadDir;
	}

	public void setFtpUploadDir(String ftpUploadDir) {
		this.ftpUploadDir = ftpUploadDir;
	}
	
	
}
