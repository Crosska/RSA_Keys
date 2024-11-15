import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message;
        EncryptorRSA encryptor = new EncryptorRSA();
        do {
            System.out.print("\nTo exit type -> exit\nType your message to encrypt: \n>");
            message = sc.nextLine();
            byte[] encryptedMessage = encryptor.encryptNewMessage(message);
            if (encryptedMessage != null) {
                DecryptorRSA decryptor = new DecryptorRSA();
                System.out.println("Your decrypted message is: " + decryptor.decryptMessage(encryptedMessage, encryptor.getPrivateKey()));
            }
        } while (!message.equals("exit"));
    }
}