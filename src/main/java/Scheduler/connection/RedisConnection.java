package Scheduler.connection;

import redis.clients.jedis.Jedis;

/**
 * Connecting to redis.
 */
public class RedisConnection {
    private final Jedis jedis;

    public RedisConnection() {
        this.jedis = new Jedis("localhost");
//        this.jedis = new Jedis("10.10.5.30",30980);
    }

    public static void main(String[] args) {

//        new RedisConnection().parseMetrics();
    }


}
