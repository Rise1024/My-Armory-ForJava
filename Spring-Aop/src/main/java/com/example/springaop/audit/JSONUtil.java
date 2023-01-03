package com.example.springaop.audit;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class JSONUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectMapper nonNullMapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

        nonNullMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        nonNullMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        nonNullMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        nonNullMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        nonNullMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        nonNullMapper.setSerializationInclusion(Include.NON_NULL);

    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static ObjectMapper getNonNullObjectMapper() {
        return nonNullMapper;
    }

    public static String mapToNonNullJsonString(Object contentsObj) {
        try {
            return nonNullMapper.writeValueAsString(contentsObj);
        } catch (JsonProcessingException e) {
            log.error("Failed to write {} to non null string", contentsObj, e);
        }
        return "";
    }


    public static String mapToJsonString(Object contentsObj) {
        try {
            return mapper.writeValueAsString(contentsObj);
        } catch (JsonProcessingException e) {
            log.error("Failed to write {} to string", contentsObj, e);
        }
        return "";
    }

    public static <T, W> W transferTo (Object t, Class<W> clazz) {
        // try {
        //     return mapper.convertValue(t, clazz);
        // } catch (IllegalArgumentException e) {
        //     log.warn("transfer throw exception {}", e);
        //     return null;
        // }

       W readValue = null;
       try {
           readValue = JSONUtil.getObjectMapper().readValue(JSONUtil.mapToJsonString(t), clazz);
       } catch (IOException e) {
           log.warn("transfer throw exception {}", e);
       }
       return readValue;
    }
}
