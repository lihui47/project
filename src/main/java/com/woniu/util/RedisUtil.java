package com.woniu.util;

import com.woniu.domin.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author Alex
 */
public class RedisUtil {
    @Resource
    static private RedisTemplate<String, User> redisTemplate;

    /**
     * Put.
     * redis储存用户
     *
     * @param user the user
     */
    public static void put(User user) {
        redisTemplate.opsForHash().put("user", user.getUsername(), user);
    }

    public static User get(User user) {
        Object user1 = redisTemplate.opsForHash().get("user", user.getUsername());
        if(ObjectUtils.isEmpty(user1)){
            return null;
        }
        return (User) user1;
    }
}

