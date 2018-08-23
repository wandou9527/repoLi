package com.wandou.controller;

import com.wandou.dto.HaHaDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String indexWandou() {
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/date")
    @ResponseBody
    public String m2() {
        return "今天日期时间是：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.S");
    }

    @GetMapping("/m3")
    @ResponseBody
    public HaHaDTO m3(@RequestParam(name = "uname", required = false, defaultValue = "liming") String uname) {
        System.out.println("请求进来：" + System.currentTimeMillis());
        HaHaDTO haHaDTO = new HaHaDTO();
        StringBuilder uidSb = new StringBuilder("1");
        for (int i = 0; i < 5; i++) {
            uidSb.append(new Random().nextInt(10));
        }
        haHaDTO.setUid(NumberUtils.toInt(uidSb.toString()));
        haHaDTO.setUname(uname);
        haHaDTO.setAge(new Random().nextInt(99) + 1);

        return haHaDTO;
    }

}