package com.vv.cache.model;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivian on 2017/11/13.
 * data load api
 */

public class DataApi {
    public static List<DataModel> getData() {
        // do your consume work here
        SystemClock.sleep(2000);

        return mock();
    }

    private static List<DataModel> mock() {
        final String mockDes = "在苍茫的大海上，狂风卷集着乌云。在乌云和大海之间，海燕像黑色的闪电，在高傲地飞翔。\n" +
                "一会儿翅膀碰着波浪，一会儿箭一般地直冲向乌云，它叫喊着，──就在这鸟儿勇敢的叫喊声里，乌云听出了欢乐。\n" +
                "在这叫喊声里──充满着对暴风雨的渴望！在这叫喊声里，乌云听出了愤怒的力量、热情的火焰和胜利的信心。\n" +
                "海鸥在暴风雨来临之前呻吟着，──呻吟着，它们在大海上飞窜，想把自己对暴风雨的恐惧，掩藏到大海深处。\n" +
                "海鸭也在呻吟着，──它们这些海鸭啊，享受不了生活的战斗的欢乐：轰隆隆的雷声就把它们吓坏了。\n" +
                "蠢笨的企鹅，胆怯地把肥胖的身体躲藏到悬崖底下……只有那高傲的海燕，勇敢地，自由自在地，在泛起白沫的大海上飞翔！\n" +
                "乌云越来越暗，越来越低，向海面直压下来，而波浪一边歌唱，一边冲向高空，去迎接那雷声。\n" +
                "雷声轰响。波浪在愤怒的飞沫中呼叫，跟狂风争鸣。看吧，狂风紧紧抱起一层层巨浪，恶狠狠地把它们甩到悬崖上，把这些大块的翡翠摔成尘雾和碎末。\n" +
                "海燕叫喊着，飞翔着，像黑色的闪电，箭一般地穿过乌云，翅膀掠起波浪的飞沫。\n" +
                "看吧，它飞舞着，像个精灵，──高傲的、黑色的暴风雨的精灵，——它在大笑，它又在号叫……它笑那些乌云，它因为欢乐而号叫！\n" +
                "这个敏感的精灵，——它从雷声的震怒里，早就听出了困乏，它深信，乌云遮不住太阳，──是的，遮不住的！\n" +
                "狂风吼叫……雷声轰响……\n" +
                "一堆堆乌云，像青色的火焰，在无底的大海上燃烧。大海抓住闪电的箭光，把它们熄灭在自己的深渊里。这些闪电的影子，活像一条条火蛇，在大海里蜿蜒游动，一晃就消失了。\n" +
                "——暴风雨！暴风雨就要来啦！\n" +
                "这是勇敢的海燕，在怒吼的大海上，在闪电中间，高傲地飞翔；这是胜利的预言家在叫喊：\n" +
                "——让暴风雨来得更猛烈些吧！";
        List<DataModel> list = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            DataModel model = new DataModel("" + (char) i, (char) i + "------- \n" + mockDes);
            list.add(model);
        }

        return list;
    }
}
