package txyd.util;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

public class DESUtil {

	private Cipher ecipher;
	private Cipher dcipher;

	public DESUtil(String strKey) {
		SecretKey key = null;
		key = getKey(strKey);
		try {
			ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			dcipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (javax.crypto.NoSuchPaddingException e) {
			// BaseUtils.E(TAG, e.getMessage());
		} catch (java.security.NoSuchAlgorithmException e) {
			// BaseUtils.E(TAG, e.getMessage());
		} catch (java.security.InvalidKeyException e) {
			// BaseUtils.E(TAG, e.getMessage());
		}
	}

	/**
	 * 鐢熸垚瀵嗛挜鍒版枃浠�
	 * 
	 */
	public static void generateKeyToFile(String keyFile) {
		try {
			SecureRandom sr = new SecureRandom();
			// 涓烘垜浠�鎷╃殑DES绠楁硶鐢熸垚KeyGenerator瀵硅薄
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(sr);
			FileOutputStream fos = new FileOutputStream(keyFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// 鐢熸垚瀵嗛挜
			SecretKey key = kg.generateKey();
			oos.writeObject(key);
			oos.close();
		} catch (Exception e) {
			// BaseUtils.E(TAG, e.getMessage());
		}
	}

	/**
	 * 浠庢枃浠跺緱鍒板瘑閽�
	 * 
	 * @return
	 */
	public static SecretKey getKeyFromFile(String keyFile) {
		SecretKey kp = null;
		try {
			InputStream is = new FileInputStream(keyFile);
			ObjectInputStream oos = new ObjectInputStream(is);
			kp = (SecretKey) oos.readObject();
			oos.close();
		} catch (Exception e) {
			// BaseUtils.E(TAG, "getKeyFromFile", e);
		}
		return kp;
	}

	/**
	 * 鏍规嵁瀛楃涓插緱鍒発ey
	 * 
	 * @param strKey
	 *            瀵嗛挜鏄庢枃
	 */
	public SecretKey getKey(String strKey) {
		try {
			byte[] rawKey = strKey.getBytes("UTF-8");
			DESKeySpec dks = new DESKeySpec(rawKey);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			return secretKey;
		} catch (Exception e) {
			// BaseUtils.E(TAG, "getKey", e);
		}
		return null;
	}

	/**
	 * 鍔犲瘑String鏄庢枃杈撳叆,String瀵嗘枃杈撳嚭
	 * 
	 * @param strMing
	 * @return
	 */
	public String getEncAndBase64String(String strMing) {
		if (strMing == null) {
			strMing = "";
		}
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			byteMing = strMing.getBytes("UTF-8");
			byteMi = this.getEncCode(byteMing);
			Base64 base64 = new Base64();
			byte[] enCodeDate = base64.encode(byteMi);
			byte[] enCodeDateNew = new byte[enCodeDate.length - 2];
			System.arraycopy(enCodeDate, 0, enCodeDateNew, 0, enCodeDate.length - 2);
			strMi = new String(enCodeDateNew, "UTF-8");
		} catch (Exception e) {
			// BaseUtils.E(TAG, "getEncAndBase64String", e);
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 瑙ｅ瘑 浠tring瀵嗘枃杈撳叆,String鏄庢枃杈撳嚭
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesAndBase64String(String strMi) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			Base64 base64 = new Base64();
			byteMi = base64.decode(strMi.getBytes("UTF-8"));
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF-8");
		} catch (Exception e) {
			// BaseUtils.E(TAG, "getDesAndBase64String", e);
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 鍔犲瘑浠yte[]鏄庢枃杈撳叆,byte[]瀵嗘枃杈撳嚭
	 * 
	 * @param byteS
	 * @return
	 */
	public byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;

		try {
			byteFina = ecipher.doFinal(byteS);
		} catch (Exception e) {
			// BaseUtils.E(TAG, "getEncCode", e);
		}
		return byteFina;
	}

	/**
	 * 瑙ｅ瘑浠yte[]瀵嗘枃杈撳叆,浠yte[]鏄庢枃杈撳嚭
	 * 
	 * @param byteD
	 * @return
	 */
	public byte[] getDesCode(byte[] byteD) {
		byte[] byteFina = null;
		try {
			byteFina = dcipher.doFinal(byteD);
		} catch (Exception e) {
			// BaseUtils.E(TAG, "getDesCode", e);
		}
		return byteFina;
	}

	public void encrypt(InputStream in, OutputStream out) {
		try {
			// Bytes written to out will be encrypted
			out = new CipherOutputStream(out, ecipher);

			// Read in the cleartext bytes and write to out to encrypt
			int numRead = 0;
			byte[] buf = new byte[1024];
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
		} catch (java.io.IOException e) {
			// BaseUtils.E(TAG, "encrypt", e);
		}
	}

	public void decrypt(InputStream in, OutputStream out) {
		try {
			// Bytes read from in will be decrypted
			in = new CipherInputStream(in, dcipher);

			// Read in the decrypted bytes and write the cleartext to out
			int numRead = 0;
			byte[] buf = new byte[1024];
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
		} catch (java.io.IOException e) {
			// BaseUtils.E(TAG, "decrypt", e);
		}
	}

}
