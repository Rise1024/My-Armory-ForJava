package com.netty.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * json工具类
 */
@Slf4j
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取指定json数据某个属性的值(属性是基本类型)
     *
     * @param data
     * @param clazz
     * @param properties
     * @param <T>
     *
     * @return
     */
    public static <T> T getValue(JSONObject data, Class<T> clazz, String... properties) {

        if (properties.length > 0) {
            JSONObject temp = data;
            for (int i = 0; i < properties.length - 1; i++) {
                temp = temp.getJSONObject(properties[i]);
            }
            return temp.getObject(properties[properties.length - 1], clazz);
        } else {
            return null;
        }
    }


    /**
     * 获取指定json数据某个属性的值(属性是json)
     *
     * @param data
     * @param properties
     *
     * @return
     */
    public static JSONObject getJsonObject(JSONObject data, String... properties) {

        if (properties.length > 0) {
            JSONObject temp = data;
            for (int i = 0; i < properties.length; i++) {
                temp = temp.getJSONObject(properties[i]);
            }
            return temp;
        } else {
            return null;
        }
    }

    /**
     * get session data from event
     *
     * @param event
     * @param field
     * @param clazz
     * @param <T>
     *
     * @return
     */
    public static <T> T getSessionData(JSONObject event, String field, Class<T> clazz) {

        return getSession(event).getObject(field, clazz);
    }

    /**
     * 对给定的json字符串, 根据分页大小, 获取指定分页的数据
     *
     * @param data
     * @param page
     * @param pageSize
     *
     * @return
     */
    public static JSONArray getPageData(String data, int page, int pageSize) {

        return getPageData(JSONObject.parseArray(data), page, pageSize);
    }


    /**
     * 对给定的json数组, 根据分页大小, 获取指定分页的数据
     *
     * @param data
     * @param page
     * @param pageSize
     *
     * @return
     */
    public static JSONArray getPageData(JSONArray data, int page, int pageSize) {

        if (data != null) {
            int total = data.size();
            int lastIndex = page * pageSize;
            int start = (page - 1) * pageSize;
            int end = lastIndex < total ? lastIndex : total;
            JSONArray result = new JSONArray(end - start);
            for (int i = start; i < end; i++) {
                result.add(data.get(i));
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * 从body中获取指定字段的json对象
     *
     * @param data
     * @param field
     *
     * @return
     */
    public static JSONObject getJsonObjectFromFirst(JSONObject data, String first, String field) {
        return data.getJSONObject(first).getJSONObject(field);
    }


    /**
     * 获取格式化数据
     * 为首页消息和会话图表格式化数据
     *
     * @param data
     *
     * @return
     */
    public static JSONObject getFormatResult(String data) {

        JSONArray raw = JSONObject.parseObject(data).
                getJSONObject("aggregations").
                getJSONObject("group_by").
                getJSONArray("buckets");

        JSONObject result = new JSONObject();   //  结果json
        JSONArray value = new JSONArray();  //  值集合{"type":"app",value:[{"time":"2016","value":20}]}
        result.put("result", value);

        for (int i = 0; i < raw.size(); i++) {
            JSONObject record = new JSONObject();
            record.put("type", raw.getJSONObject(i).getString("key"));
            JSONArray buckets = raw.getJSONObject(i).getJSONObject("result").getJSONArray("buckets");
            JSONArray r_data = new JSONArray();
            for (int j = 0; j < buckets.size(); j++) {
                JSONObject temp = new JSONObject();
                temp.put("time", buckets.getJSONObject(j).getLongValue("key"));
                temp.put("value", buckets.getJSONObject(j).getJSONObject("count").getIntValue("value"));
                r_data.add(temp);
            }
            record.put("value", r_data);
            value.add(record);
        }

        return result;
    }

    /**
     * 将json数据转换为csv格式
     *
     * @param data
     * @param meta
     */
    public static String jsonToCsv(JSONObject data, Map<String, String> meta) {

        final Joiner joiner = Joiner.on(',');
        List<String> values = new ArrayList<String>();
        for (String key : meta.keySet()) {
            values.add(data.getJSONObject(key).getString("value"));
        }
        return joiner.join(values);
    }

    /**
     * 将字符串转成数组形式
     *
     * @param data
     *
     * @return
     */
    public static String getArrayString(String data) {

        return JSON.toJSONString(new String[]{data});
    }


    /**
     * @param data
     * @param key
     * @param newData
     *
     * @return
     */
    public static JSONObject putData(JSONObject data, String key, String newData) {

        data.put(key, newData);
        return data;
    }


    /**
     * 获取json对象
     *
     * @param key
     * @param value
     *
     * @return
     */
    public static JSONObject getJsonObject(String key, Object value) {

        JSONObject obj = new JSONObject();
        obj.put(key, value);
        return obj;
    }

    /**
     * 将map数据转化为json list
     *
     * @param data
     *
     * @return
     */
    public static List<JSONObject> maptoJSONList(Map<?, ?> data) {

        List<JSONObject> result = new ArrayList<>();

        for (Map.Entry<?, ?> record : data.entrySet()) {
            JSONObject obj = new JSONObject();
            obj.put(record.getKey().toString(), Float.valueOf(record.getValue().toString()));
            result.add(obj);
        }

        return result;
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }




    public static String mapToJsonString(Object contentsObj) {
        try {
            return mapper.writeValueAsString(contentsObj);
        } catch (JsonProcessingException e) {
            log.error("Failed to write {} to string", contentsObj, e);
        }
        return "";
    }


    /**
     * get session data from event
     *
     * @param event
     *
     * @return
     */
    public static JSONObject getSession(String event) {

        return getJsonObject(JSONObject.parseObject(event), "body", "serviceSession");
    }

    public static JSONObject getSession(JSONObject event) {

        return getJsonObject(event, "body", "serviceSession");
    }

    /**
     * get session id from event
     *
     * @param event
     *
     * @return
     */
    public static String getSessionId(String event) {

        return getSession(event).getString("serviceSessionId");
    }

    public static String getSessionId(JSONObject event) {

        return getSessionData(event, "serviceSessionId", String.class);
    }

    //===========================

    /**
     * transfer map to json object
     *
     * @param data
     * @param <T>
     *
     * @return
     */
    public static <T> JSONObject mapToJson(Map<String, T> data) {

        JSONObject result = new JSONObject();

        //  set all data
        result.putAll(data);

        return result;
    }

    /**
     * get json object for string data
     *
     * @param data
     *
     * @return json object or null if data is null or with wrong json format
     */
    public static JSONObject getJSONObject(String data) {

        try {
            return JSONObject.parseObject(data);

            //  when get exception, return null
        } catch (Exception e) {
            log.warn("data is null or not json format, please check, data is: |{}|", data);
            return null;
        }
    }
}
