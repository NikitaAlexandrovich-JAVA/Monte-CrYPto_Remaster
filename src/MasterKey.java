//Минус данного подхода что при подборе ключей из диапазона Integer.MIN до Integer.Max
// очень долго считает
// Для тестирования использовал ключи от -1000 до 1000



import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



public class MasterKey extends EnDeCrypted {

    private Path pathRead;
    private Path pathWrite;
    String textStart;
    String textEnd = "";
    Integer minKey = -1000;
    Integer maxKey = 1000;
    //  Scanner scannerYesNo = new Scanner(System.in);

    public MasterKey(String pathRead, String pathWrite) {
        this.pathRead = Path.of(pathRead);
        this.pathWrite = Path.of(pathWrite);
    }


    public void searchKey() {

        textStart = readTextFromFile(pathRead);

        if (textStart == null) {
            return;
        }

        for (int key = minKey; key <= maxKey ; key++) {
            textDecryptedThisKey(key);
           // System.out.println(key);
            if (!textEnd.isEmpty()) {

                List<Character> sortSymbolArrayList = new ArrayList<>();

                for (int index = 0; index < textEnd.length(); index++) {

                    char symbol = textEnd.charAt(index);

                    if (Character.isUpperCase(symbol)) {
                        sortSymbolArrayList.add(symbol);
//                        System.out.println(symbol);
                    }

                    if (symbol == ' ' || symbol == '-') {
                        sortSymbolArrayList.add(symbol);
                    }

                    if (sortSymbolArrayList.size() >= 2 && checkArray(sortSymbolArrayList)) {
                        textEnd = "";
                        break;
                    }
                }
            }

            checkText(textEnd, key);
        }
    }

    public boolean check(String symbol, char symbolNext) {

        if (symbol.equals(",") && (checkSymbol(symbolNext))) {

            return false;
        }
        if (symbol.equals(";") && (checkSymbol(symbolNext))) {

            return false;
        }
        if (symbol.equals(":") && (checkSymbol(symbolNext))) {

            return false;
        }
        return true;
    }


    private void textDecryptedThisKey(int key) {

        for (int index = 0; index < textStart.length() - 1; index++) {
            if (checkSymbol(textStart.charAt(index))) {

                String symbol = String.valueOf(searchNextSymbol(textStart.charAt(index), key));
                check(symbol, textStart.charAt(index + 1));

                if (!check(symbol, textStart.charAt(index + 1))) {
                    textEnd = "";
                    break;
                }

                textEnd += symbol;

            } else {
                textEnd += textStart.charAt(index);
            }
        }

    }

    public boolean checkArray(List<Character> arrayList) {
        char symbolOne = arrayList.get(arrayList.size() - 1);
        char symbolSecond = arrayList.get(arrayList.size() - 2);

        if (Character.isUpperCase(symbolOne) && Character.isUpperCase(symbolSecond)) {
            return true;
        }
        return false;
    }

    public void checkText(String text, int key) {
        if (!text.isEmpty()) {
            System.out.println("Подходит ключ: " + key * (-1)+"\n");
            //System.out.println(text);
        }
    }
}






