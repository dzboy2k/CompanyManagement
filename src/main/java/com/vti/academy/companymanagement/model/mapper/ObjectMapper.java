package com.vti.academy.companymanagement.model.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectMapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapper.class);
	
	public static Map<String, Object> convertObjectToMap(Object object){
		LOGGER.info("Convert " + object.getClass().getName() + " to Map");
		
		Map<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try { 
            	map.put(field.getName(), field.get(object)); 
            } catch (Exception e) {
            	LOGGER.info("Convert fail: " + e.getMessage());
            }
        }
        return map;
	}
}
