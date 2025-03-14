import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Management {

    private String commandUser;
    private Scanner scannerCommand = new Scanner(System.in);
    private Scanner scannerKey = new Scanner(System.in);

    final String directoryResourse = "resources/";
    final String directoryEncrypted = "encrypted/";
    final String directoryDecrypted = "decrypted/";
    private List<File> paths;

    public void start() {


        do {

            System.out.println(Messages.MY_SKILL_MESSAGE);
            commandUser = scannerCommand.nextLine();

            switch (commandUser.toLowerCase()) {

                case "1" -> {

                    try {
                        File file = choiceFile("закодирования", directoryResourse);

                        System.out.printf(Messages.INPUT_KEY_MESSAGE, "(закодирование)");
                        new Encrypted().crypted(scannerKey.nextInt(), file.getPath(), directoryEncrypted + "En_result_" + file.getName());

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }


                }
                case "2" -> {


                    try {
                        File file = choiceFile("декодирования", directoryEncrypted);

                        System.out.printf(Messages.INPUT_KEY_MESSAGE, "(декодирование)");

                        new Decrypted().crypted(scannerKey.nextInt(), file.getPath(), directoryDecrypted + file.getName().replace("En_result_", "De_result_"));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                case "3" -> {
                    try {
                        File file = choiceFile("взлома", directoryEncrypted);
                        new MasterKey(file.getPath(), directoryDecrypted + file.getName()).searchKey();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                case "exit" -> System.out.println(Messages.END_MESSAGE);


                default -> System.out.println(Messages.ERROR_NOT_COMMAND_MESSAGE);
            }

        } while (!commandUser.equalsIgnoreCase("exit"));

    }


    public File choiceFile(String message, String directoryChoice) throws Exception {

        Scanner scannerCommand = new Scanner(System.in);

        try {

            paths = Files.walk(Path.of(directoryChoice))
                    .map(path -> path.toFile())
                    .filter(file -> !file.isDirectory())
                    .toList();


        } catch (Exception e) {

            throw new Exception(Messages.ERROR_NOT_DIRECTORY + directoryChoice);
        }

        if (paths.isEmpty()) {
            throw new Exception(Messages.ERROR_NOT_FILES + directoryChoice + "\n");
        }

        System.out.printf(Messages.CHOICE_PATH_MESSAGE, message);

        for (File file : paths) {
            System.out.println(file.getName() + " -> " + (paths.indexOf(file) + 1));
        }

        try {
            return paths.get(scannerCommand.nextInt() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(Messages.ERROR_NOT_COMMAND_MESSAGE + "\n");
        }

    }

}



