#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/28.
 */
public class JsonUtil {
    protected static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    /**
     * 把JSON字符串转成对象
     *
     * @param jsonString
     *            json字符串
     * @param clazz
     *            类
     * @return 对象
     * @throws IOException
     *             抛出异常
     * @throws JsonMappingException
     *             抛出异常
     * @throws JsonParseException
     *             抛出异常
     */
    public static <T> T getObjectFormJsonByjackson(String jsonString, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        T jsonObject = null;
        jsonObject = mapper.readValue(jsonString, clazz);
        return jsonObject;
    }

    /**
     * 把对象转换成json字符串
     *
     * @param object
     *            对象
     * @return json字符串
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static String getJsonStrByObject(Object object) throws JsonGenerationException, JsonMappingException, IOException {
        return mapper.writeValueAsString(object);
    }
    /**
     * JSON字符串转换为对象,fastjson方式实现
     * @param jsonString JSON字符串
     * @param clazz 对象Class
     * @return 对象
     */
//    public static <T> T parseObject(String jsonString,Class<T> clazz){
//        T entity = JSON.parseObject(jsonString, clazz);
//        return entity;
//    }
//
//    public static JSONObject parseJsonObject(Object object){
//        JSONObject jsonObject = (JSONObject)JSON.toJSON(object);
//        return jsonObject;
//    }


    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

        ArrayList<String> accountKeyList = new ArrayList<String>() ;
        accountKeyList.add("s");
        accountKeyList.add("aaa");
        System.out.println(getJsonStrByObject(accountKeyList));

    }
}
