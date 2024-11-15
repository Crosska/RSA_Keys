import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class EncryptorRSA {
    {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }
    }

    private final PrivateKey privateKey;
    public final PublicKey publicKey;

    public byte[] encryptNewMessage(String message) {
        if (!message.isEmpty()) {
            System.out.println("Your message is: " + message);
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] bytesMessage = message.getBytes(StandardCharsets.UTF_8);
                byte[] encryptedBytesMessage = cipher.doFinal(bytesMessage);
                System.out.println("Your encrypted message is: " + new String(encryptedBytesMessage));
                return encryptedBytesMessage;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("There are no such encryption algorithm", e);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException("No such padding", e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException("Invalid public key", e);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException("Cipher illegal block size", e);
            } catch (BadPaddingException e) {
                throw new RuntimeException("Cipher bad padding", e);
            }
        } else {
            System.out.println("Your message is empty");
            return null;
        }
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

}
