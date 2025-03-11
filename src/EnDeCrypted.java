


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public abstract class EnDeCrypted {

    public Alphabet alphabet = new Alphabet();

    public void crypted(int key, String pathRead, String pathSave) {
        String resultText = "";
        String editableText = readTextFromFile(Path.of(pathRead));

        if (editableText==null){return;}

        char symbol;

        for (int i = 0; i < editableText.length(); i++) {

            symbol = editableText.charAt(i);

            if (checkSymbol(symbol)) {

                resultText = resultText + searchNextSymbol(symbol, key);
            } else {
                resultText = resultText + symbol;
            }

        }

        writeTextToFile(resultText, Path.of(pathSave));


    }

    public String readTextFromFile(Path pathRead) {

        try {
            String editableText = Files.readString(pathRead);
            ;
            return editableText;
        } catch (Exception e) {
            System.out.println("\n" + Messages.ERROR_NOT_FILE + pathRead + "\n");

            return null;
        }
    }

    public void writeTextToFile(String writeText, Path pathSave) {
        try {


            if (!Files.exists(pathSave.getParent())) {

                Files.createDirectory(pathSave.getParent());
                Files.writeString(pathSave, writeText,StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("\nРЕЗУЛЬТАТ: записан в ->" + pathSave + "\n\n" + writeText + "\n");

            }else {
                Files.writeString(pathSave, writeText,StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("\nРЕЗУЛЬТАТ: записан в ->" + pathSave + "\n\n" + writeText + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public boolean checkSymbol(char symbol) {
        return alphabet.contains(symbol);
    }


    public char searchNextSymbol(char symbol, int key) {

        int nextIndexSymbol = alphabet.indexOf(symbol) + key;

        if (nextIndexSymbol >= alphabet.size()) {
            while (nextIndexSymbol >= alphabet.size()) {
                nextIndexSymbol = nextIndexSymbol - alphabet.size();
            }
        }

        if (nextIndexSymbol < 0) {
            while (nextIndexSymbol < 0) {
                nextIndexSymbol = nextIndexSymbol + alphabet.size();
            }
        }

        return alphabet.get(nextIndexSymbol);

    }
}
