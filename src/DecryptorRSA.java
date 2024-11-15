import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

public class DecryptorRSA {

    public String decryptMessage(byte[] encryptedMessage, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
            return new String(decryptedMessage, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("There are no such algorithm", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("No such padding", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid private key", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("Cipher illegal block size", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("Cipher bad padding", e);
        }
    }

}
