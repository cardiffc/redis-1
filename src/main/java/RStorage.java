import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class RStorage {
    private static Jedis jedis;
    private static final String KEY = "dusers";

    protected void init() {
        jedis = new Jedis("redis://127.0.0.1:6379");
        jedis.del(KEY);
        for (int i = 1; i <= 20 ; i++) {
            String user = "User # " + i;
            jedis.zadd(KEY, i, user);
        }
    }

    protected int getQueueSize () {
        long size = jedis.zcard(KEY);
        return (int) size;
    }

    protected String getMinUser() {
        Tuple minUser = jedis.zpopmin(KEY);
        Tuple maxUser = jedis.zpopmax(KEY);
        jedis.zadd(KEY, maxUser.getScore(), maxUser.getElement());
        jedis.zadd(KEY, maxUser.getScore() + 1, minUser.getElement());
        return minUser.getElement();
    }

    protected void setTopPosition (int number) {
        Set<String> preUser = jedis.zrange(KEY, number, number);
        Object[] user = preUser.toArray();
        String payedUser = user[0].toString();
        jedis.zrem(KEY, payedUser);
        jedis.zadd(KEY, 0,payedUser);
        System.out.println(">> Пользователь " + payedUser + " оплатил услугу!");
    }

}
