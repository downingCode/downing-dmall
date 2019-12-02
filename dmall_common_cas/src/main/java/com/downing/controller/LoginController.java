package com.downing.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author downing
 * @descript
 */
@RestController
@RequestMapping("/cas")
public class LoginController {

    /**
     * 获取用户名
     *
     * @return
     */
    @RequestMapping("/username")
    public Map username() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //得到登录人账号
        if ("anonymousUser".equals(name)) {
            name = "";
        }
        System.out.println(name);
        Map map = new HashMap();
        map.put("username", name);
        return map;
    }

}
