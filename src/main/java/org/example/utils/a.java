/*
package org.example.utils;

import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class JsonUtils {
    public static String toJson(Object object) {
        Map<String, Object> map = convertToMap(object);
        JsonObject jsonObject = Json.createObjectBuilder(map).build();
        return jsonObject.toString();
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        JsonObject jsonObject = convertToJsonObject(json);
        return convertToObject(jsonObject, clazz);
    }

    private static Map<String, Object> convertToMap(Object object) {
        // 将对象转换为 Map，可以使用反射等方式实现
        // 这里以简单示例为例，手动构建一个 Map
        Map<String, Object> map = new HashMap<>();
        if (object instanceof Person) {
            Person person = (Person) object;
            map.put("name", person.getName());
            map.put("age", person.getAge());
        }
        return map;
    }

    private static JsonObject convertToJsonObject(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        return reader.readObject();
    }

    private static <T> T convertToObject(JsonObject jsonObject, Class<T> clazz) {
        // 将 JsonObject 转换为指定的 Java 对象，可以使用反射等方式实现
        // 这里以简单示例为例，手动构建一个 Person 对象
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        return clazz.getConstructor(String.class, int.class).newInstance(name, age);
    }
}
*/
