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
        map.put("mb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_2.png"));
        map.put("mb03",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_3.png"));

        map.put("ep01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_2.png"));

        map.put("epb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_1.png"));

        map.put("blood",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\blood.png"));



        }
        public static Image get(String key){
        return map.get(key);
        }
}
