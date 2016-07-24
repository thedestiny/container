package com.it.service;

/**
 * Created by xieyue on 2016/7/24.
 * UserService
 */


import com.google.gson.Gson;
import com.it.pojo.User;
import com.it.utils.KeyUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JedisPool jedisPool;


    private RedisTemplate<String,User> redisTemplate;
    @Autowired
    public UserService(RedisTemplate<String,User> redisTemplate){
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 压缩二进制方式存储数据
     * @param user
     */

    public void saveUser(User user){
        Schema<User> userSchema = RuntimeSchema.getSchema(User.class);
        byte[] bytes = ProtostuffIOUtil.toByteArray(user,
                userSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        try(Jedis jedis = getJedis()){
            jedis.set(KeyUtil.userKey(user.getId()).getBytes(),bytes);
        }
    }

    public User findUserById(Integer userId){
        try(Jedis jedis = getJedis()){
            byte[] bytes = jedis.get(KeyUtil.userKey(userId).getBytes());
            Schema<User> userSchema =RuntimeSchema.createFrom(User.class);
            User user = new User();
            ProtostuffIOUtil.mergeFrom(bytes,user,userSchema);
            return user;
        }
    }


    public User findUserById1(Integer userId){
        Jedis jedis = getJedis();
        String json = jedis.get(KeyUtil.userKey(userId));
        logger.debug("json is {}",json);
        User user = new Gson().fromJson(json,User.class);
        if(user == null){
            return null;
        }
        jedis.close();
        return user;
    }

}
