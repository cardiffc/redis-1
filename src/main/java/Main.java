
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RStorage redis = new RStorage();
        redis.init();

        int size = redis.getQueueSize();
        Random random = new Random();

        while (true) {
            int j = 0;
            for (int i = 0; i < size; i++) {
                System.out.println("На главной странице пользователь " + redis.getMinUser());
                if (j == 9) {
                    int number = random.nextInt(size);
                    redis.setTopPosition(number);

                }
                j++;
            }
            Thread.sleep(1000);
        }
    }
}
