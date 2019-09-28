package com.general.protoc;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: general
 * @version: 1.0
 * @create: 2019-09-21 19:39
 **/
public class MoseCodeEncoder {

    public static final Map<String, String> moseMap = new HashMap<String, String>();
    public static final Map<String, String> decodeMap = new HashMap<String, String>();

    static {
        moseMap.put("a", "·-");
        moseMap.put("b", "-···");
        moseMap.put("c", "-·-·");
        moseMap.put("d", "-··");
        moseMap.put("e", "·");
        moseMap.put("f", "··-·");
        moseMap.put("g", "--·");
        moseMap.put("h", "····");
        moseMap.put("i", "··");
        moseMap.put("j", "·---");
        moseMap.put("k", "-·-");
        moseMap.put("l", "·-··");
        moseMap.put("m", "--");
        moseMap.put("n", "-·");
        moseMap.put("o", "---");
        moseMap.put("p", "·--·");
        moseMap.put("q", "--·-");
        moseMap.put("r", "·-·");
        moseMap.put("s", "···");
        moseMap.put("t", "-");
        moseMap.put("u", "··-");
        moseMap.put("v", "···-");
        moseMap.put("w", "·--");
        moseMap.put("x", "-··-");
        moseMap.put("y", "-·--");
        moseMap.put("z", "--··");
        moseMap.put(",", "--··--");
        moseMap.put(".", "·-·-·-");
        for(String key: moseMap.keySet()){
            String value = (String)moseMap.get(key);
            decodeMap.put()
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println(encodeToMoseCode("jingjing, i love you."));
    }

    public static String encodeToMoseCode(String str1){
        String[] arrs = str1.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(String each: arrs){
            int length = each.length();
            for(int i=0;i<length;i++){
                String _each = each.substring(i, i+1);
                String value = moseMap.get(_each);
                stringBuilder.append(value).append("/");
            }
        }
        return stringBuilder.toString();
    }
}
