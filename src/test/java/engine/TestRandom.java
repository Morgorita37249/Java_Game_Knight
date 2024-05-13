package engine;
import org.junit.jupiter.api.Test;

import java.util.Random;
public class TestRandom {
    @Test
    void rnd(){
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        if (randomNumber >= 0 && randomNumber <= 2) {
            System.out.println("YES " + randomNumber);
        } else {
            System.out.println("NO " + randomNumber);
        }
    }


}
