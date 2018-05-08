package com.vv.mvc.model;

import java.util.List;

/**
 * Created by vivian on 2017/4/8.
 * https://github.com/vivianking6855/vivianking6855.github.io/blob/master/datum/file/config
 */

public class UrlConfig {

    public List<DebugBean> Debug;

    public static class DebugBean {
        public String tag;
        public String url;
    }
}
