//CLASS RSA UNTUK DIPANGGIL KE JFRAME RSA//

package aesprojectrihaf;
import static aesprojectrihaf.RSAAlgorithm.bytesToString;

import java.math.BigInteger; 
import java.util.Random;
import java.io.*;
/**
 *
 * @author rihaf
 */
public class RSAAlgorithm {
     private BigInteger p; 
    private BigInteger q; 
    private BigInteger N; 
    private BigInteger phi; 
    private BigInteger e ; 
    private BigInteger d; 
    private int bitlength = 1500; //PANJANG BIT
    private int blocksize = 2000; //UKURAN BLOK DALAM BYTE
    
    private Random r; 
     public RSAAlgorithm() { 
        r = new Random(); //1----MEMBUAT BILANGAN ACAK UNTUK KUNCI PUBLIK MINIMAL 100 DIGIT//
        p = BigInteger.probablePrime(bitlength, r); //BILANGAN PRIMA-A ACAK MINIMAL 100 DIGIT
        q = BigInteger.probablePrime(bitlength, r); //BILANGAN PRIMA-B ACAK MINIMAL 100 DIGIT
        N = p.multiply(q); //2-----N=PRIMA-A * PRIMA-B N=PARAMETER SECURITY
           
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //3-----Psi = p-1 * q-1

        e = BigInteger.probablePrime(bitlength/2, r); //e = Bilangan bulat prima acak antara 1 dan Psi (1 < e < Psi) yang tdk punya faktor pembagi Psi
                                          while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) { 
                                             e.add(BigInteger.ONE); 
                                            } 
 d = e.modInverse(phi);  //5----- d e = 1 (mod Psi)
    } 
     
    public RSAAlgorithm(BigInteger e, BigInteger d, BigInteger N) { 
        this.e = e; 
        this.d = d; 
        this.N = N; 
    } 
public static void main(String[] args) throws IOException {
      RSAAlgorithm rsa = new RSAAlgorithm(); 
        DataInputStream in=new DataInputStream(System.in);  
        String teststring ;
        //INPUT PLAINTEXT YANG AKAN DIKONVERSI KE BYTES
        System.out.println("Enter the plain text:");
        teststring=in.readLine();
        System.out.println("Encrypting String: " + teststring); 
        System.out.println("String in Bytes: " + bytesToString(teststring.getBytes())); 

        // ENKRIPSI BYTE DARI PLAINTEXT
        byte[] encrypted = rsa.encrypt(teststring.getBytes());                   
        System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted)); 
         
        // DEKRIPSI CHIPERTEXT KE BYTE KEMUDIAN KE PLAINTEXT
        byte[] decrypted = rsa.decrypt(encrypted);       
        System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted)); 
         
        System.out.println("Decrypted String: " + new String(decrypted)); 
    } 

   public static String bytesToString(byte[] encrypted) { //KONVERSI BYTE KE STRING
        String test = ""; 
        for (byte b : encrypted) { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
   

//FUNGSI ENKRIPSI BYTE
     public byte[] encrypt(byte[] message) {      
        return (new BigInteger(message)).modPow(e, N).toByteArray(); 
    } 
       
// FUNGSI DEKRIPSI BYTE
    public byte[] decrypt(byte[] message) { 
        return (new BigInteger(message)).modPow(d, N).toByteArray(); 
    }  
}
