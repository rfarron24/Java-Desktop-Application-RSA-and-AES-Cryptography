/*
CLASS AES UNTUK DIPANGGI KE JFRAME AES
 */
package aesprojectrihaf;


import sun.misc.BASE64Decoder; //LIBRARY JAVA KHUSUS KRIPTOGRAFI AES
import sun.misc.BASE64Encoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rihaf
 */
public class AESAlgorithm {
    public static String algo = "AES";
    public byte[] keyValue;
    
    public AESAlgorithm(byte key[]){
        //VARIABEL  KUNCI AWAL PADA ALGORITMA UNTUK ROUND KEY AWAL
        keyValue = key;
    }

    AESAlgorithm(String keyValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Key generateKey() throws Exception {
        //MEMBUAT KEY GENERATOR AES
        Key key = new SecretKeySpec(keyValue, algo);
        return key;        
        

    }
  
public String encrypt(String msg) throws Exception {
    //SCRIPT ENKRIPSI PLAINTEXT MELALUI KEY YANG DIBUAT
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(msg.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;

}

public String decrypt(String msg) throws Exception {
      //SCRIPT DEKRIPSI  MELALUI KEY YANG DIBUAT
    Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(msg);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
}

   public static String bytesToString(byte[] key1) { //KONVERSI BYTE KE STRING
        String test = ""; 
        for (byte b : key1) { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
}
