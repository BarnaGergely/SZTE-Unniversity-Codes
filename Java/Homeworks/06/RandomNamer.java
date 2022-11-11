import java.util.Random;
public class RandomNamer implements Namer{
    private Random rnd;
    private int length;

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    public RandomNamer(Random rnd, int length) {
        this.rnd = rnd;
        this.length = length;
    }

    public void rename(FileSystemEntry parameter){
        String newName = "";
        for (int i = 0; i < length; i++) {
            newName = newName + rnd.nextInt(0);
        }
        parameter.setName(newName);
    }
}
