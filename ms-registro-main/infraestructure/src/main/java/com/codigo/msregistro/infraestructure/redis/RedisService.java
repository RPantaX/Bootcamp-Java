package com.codigo.msregistro.infraestructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    //guarda los valores que enviemos, la clave y el valor
    public void saveInRedis(String key, String value, int exp){
        stringRedisTemplate.opsForValue().set(key, value); //guardamos un valor
        stringRedisTemplate.expire(key, exp, TimeUnit.MINUTES); //tiempo de expiracion, le indicamos si está en segundos, minutos, u horas
        //pasado el tiempo se eliminará en caché
    }
    //OBTENER LOS VALORES, DEVUELVE STRING
    public String getFromRedis(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    public void deleteKey (String key){
        stringRedisTemplate.delete(key);
    }

}
