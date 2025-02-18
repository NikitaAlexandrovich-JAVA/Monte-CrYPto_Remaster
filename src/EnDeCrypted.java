

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ScheduledExecutorService;

public abstract class EnDeCrypted {

    private Alphabet alphabet = new Alphabet();
    private String resultText;

    public void crypted(int key, String pathRead, String pathSave) {
        String resultText="";
        String editableText = readTextFromFile(pathRead);
        char symbol;

        for (int i = 0; i < editableText.length(); i++) {

            symbol = editableText.charAt(i);

            if (checkSymbol(alphabet, symbol)) {

                resultText = resultText+searchNextIndexSymbol(symbol, key);
            }else {
                resultText = resultText + symbol;
            }

        }

        writeTextToFile(resultText, pathSave);
        System.out.println("\nРЕЗУЛЬТАТ: записан в ->" + pathSave + "\n\n"+resultText+"\n");

    }

    private String readTextFromFile(String pathRead) {

        try {
            String editableText = Files.readString(Path.of(pathRead));
    ;
            return editableText;
        } catch (Exception e) {
            System.out.println("\n" + Messages.ERROR_NOT_FILE + pathRead + "\n");

          return null;
        }
    }

    private void writeTextToFile(String writeText, String pathSave) {

        try {
            Files.writeString(Path.of(pathSave), writeText,StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSymbol(Alphabet alphabet, char symbol) {
        return alphabet.contains(symbol);
    }


    private char searchNextIndexSymbol( char symbol, int key) {

        int nextIndexSymbol = alphabet.indexOf(symbol) + key;

        if (nextIndexSymbol >= alphabet.size()) {
            while (nextIndexSymbol >= alphabet.size()) {
                nextIndexSymbol = nextIndexSymbol - alphabet.size();
            }
        }

        if (nextIndexSymbol < 0) {
            while (nextIndexSymbol < 0) {
                nextIndexSymbol= nextIndexSymbol+ alphabet.size();
            }
        }

        return alphabet.get(nextIndexSymbol);

    }
}
