import java.util.Scanner;

public class Management {

    private String commandUser;
    private Scanner scannerCommand = new Scanner(System.in);
    private Scanner scannerKey = new Scanner(System.in);

    final String pathDataFile = "resources/data.txt";
    final String pathDecryptedFile = "decrypted/resultDecrypted.txt";
    final String pathEncryptedFile = "encrypted/resultEncrypted.txt";



    public void start() {


        do {

            System.out.println(Messages.MY_SKILL_MESSAGE);
            commandUser = scannerCommand.nextLine();

            switch (commandUser.toLowerCase()) {

                case "1" -> {

                    System.out.printf(Messages.INPUT_KEY_MESSAGE, "(закодирование)");
                    new Encrypted().crypted(scannerKey.nextInt(), pathDataFile, pathEncryptedFile );
                }
                case "2" -> {

                    System.out.printf(Messages.INPUT_KEY_MESSAGE, "(декодирование)");
                    new Decrypted().crypted(scannerKey.nextInt(),pathEncryptedFile, pathDecryptedFile);
                }
                case "3" -> {

                }
                case "exit" -> System.out.println(Messages.END_MESSAGE);


                default -> System.out.println(Messages.ERROR_NOT_COMMAND_MESSAGE);
            }

        } while (!commandUser.equalsIgnoreCase("exit"));

    }


}
