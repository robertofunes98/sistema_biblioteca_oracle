/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 *
 * @author funes
 */
 
public class Encriptacion {
 
    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String algoritmo = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String metodoCifrado = "AES/CBC/PKCS5Padding";
    
    private static String llavePrivada="$2y$12$l";
    
 
    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea cifrar
     * @param texto texto sin encriptar
     * @return el texto cifrado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
     */
    public static String encrypt(String texto) throws Exception {

        //Obtenemos el metodo de cifrado
        Cipher cipher = Cipher.getInstance(metodoCifrado);

        //Generamos una llave para encriptar a traves de una llave publica y una llave privada
        String hash = BCrypt.hashpw(llavePrivada, BCrypt.gensalt());//Llave publica
        String llaveFinal = hash.substring(0, 8)+llavePrivada;//Llave final (publica + privada)
        SecretKeySpec skeySpec = new SecretKeySpec(llaveFinal.getBytes(), algoritmo);


        // Genramos un IV aleatorio
        byte iv[] = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); 

        //Procedemos a encriptar en AES
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(texto.getBytes());

        //Concatenamos textoCifradoAES-Base64 + IV + LlavePublica
        String textoEncriptadoBase64 = Base64.getEncoder().encodeToString(encrypted)+"::"+ new String(iv, StandardCharsets.UTF_8)+"::"+hash.substring(0, 8);
        
        //retornamos una cadena cifrada en base64 totalmente
        return Base64.getEncoder().encodeToString(textoEncriptadoBase64.getBytes());
    }
 
    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea descifrar
     * @param textoEncriptadoBase64 texto con doble encriptacion
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    public static String decrypt(String textoEncriptadoBase64) throws Exception {
        
        //Desencriptamops el texto desde Base64
        byte[] textoEncriptado = Base64.getDecoder().decode(textoEncriptadoBase64);
        
        //Separamos en un arreglo los datos: textoCifradoAes-Base64, IV y llavePublica
        String[] datos =new String(textoEncriptado,StandardCharsets.UTF_8).split("::");

        //Obtenemos metodo de cifrado
        Cipher cipher = Cipher.getInstance(metodoCifrado);

        //Volvemos a generar la llaveFinal uniendo la publica y la privada
        SecretKeySpec skeySpec = new SecretKeySpec((datos[2]+llavePrivada).getBytes(), algoritmo);

        //Genramosa el IV
        byte[] iv = datos[1].getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        //Quitamos encriptacion Base64 a texto y lo dejamos con cifrado AES
        byte[] enc = Base64.getDecoder().decode(datos[0]);
        
        //Desencriptamos AES
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(enc);
        
        //Retornamos el string desencriptado
        return new String(decrypted);

    }
 
}