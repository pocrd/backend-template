// Auto Generated.  DO NOT EDIT!
package net.pocrd.m.app.client.api.resp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.pocrd.m.app.client.util.JsonSerializable;

public class Api_MIXER_MixDemo_Result implements JsonSerializable {

    /**
     * a
     */
    public Api_MIXER_MixDemo_DemoEntity a;
      
    /**
     * b
     */
    public Api_MIXER_MixDemo_ComplexTestEntity b;
      
    /**
     * c
     */
    public Api_MIXER_MixDemo_SimpleTestEntity c;
      

    /**
     * 反序列化函数，用于从json字符串反序列化本类型实例
     */
    public static Api_MIXER_MixDemo_Result deserialize(String json) {
        if (json != null && json.length() != 0) {
            return deserialize(new JsonParser().parse(json).getAsJsonObject());
        }
        return null;
    }

    /**
     * 反序列化函数，用于从json节点对象反序列化本类型实例
     */
    public static Api_MIXER_MixDemo_Result deserialize(JsonObject json) {
        if (json != null && !json.isJsonNull()) {
            Api_MIXER_MixDemo_Result result = new Api_MIXER_MixDemo_Result();
            JsonElement element = null;
            
            /* a */
            element = json.get("a");
            if (element != null && !element.isJsonNull()) {
                result.a = Api_MIXER_MixDemo_DemoEntity.deserialize(element.getAsJsonObject());
            }
              
            /* b */
            element = json.get("b");
            if (element != null && !element.isJsonNull()) {
                result.b = Api_MIXER_MixDemo_ComplexTestEntity.deserialize(element.getAsJsonObject());
            }
              
            /* c */
            element = json.get("c");
            if (element != null && !element.isJsonNull()) {
                result.c = Api_MIXER_MixDemo_SimpleTestEntity.deserialize(element.getAsJsonObject());
            }
              
            return result;
        }
        return null;
    }

    /**
     * 序列化函数，用于从对象生成数据字典
     */
    public JsonObject serialize() {
        JsonObject json = new JsonObject();
        
        /* a */
        if (this.a != null) { json.add("a", this.a.serialize()); }
          
        /* b */
        if (this.b != null) { json.add("b", this.b.serialize()); }
          
        /* c */
        if (this.c != null) { json.add("c", this.c.serialize()); }
          
        return json;
    }
}
  