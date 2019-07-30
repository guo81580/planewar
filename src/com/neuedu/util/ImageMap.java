package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;


public class ImageMap {
    private   static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg01.png"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_01.png"));

        map.put("mb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_1.png"));
        map.put("mb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_2.png"));
        map.put("mb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_3.png"));

        }
        public static Image get(String key){
        return map.get(key);
        }
}
