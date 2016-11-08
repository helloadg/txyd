package txyd.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 如果不存在则创建目录（或者文件），并返回该目录（或者文件）
	 *
	 * @param path
	 * @return
	 */
	public static File createPath(String path) {
		File file = new File(pathOrFileFilter(path));
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}
	
	/**
	 * 美化文件的路径
	 *
	 * @param pathOrFile
	 * @return
	 */
	public static String pathOrFileFilter(String pathOrFile) {
		if (pathOrFile.contains("//")) {
			return pathOrFile.replaceAll("//", "/");
		}
		return pathOrFile;
	}
}
