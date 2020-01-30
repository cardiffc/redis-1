import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RStorage redis = new RStorage();
        redis.init();

        int size = redis.getQueueSize();
        Random random = new Random();

        int i = 1;
        while (true) {
            System.out.println("На главной странице пользователь " + redis.getMinUser());
            if (i == 10) {
                int number = random.nextInt(size);
                redis.setTopPosition(number);
                i = 1;
                Thread.sleep(1000);
            }
            i++;
        }
    }
}
