public class Decrypted extends EnDeCrypted {


    @Override
    public void crypted(int key, String pathRead, String pathSave) {
        super.crypted(-key, pathRead, pathSave);
    }
}
