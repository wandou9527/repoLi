package com.wandou.model.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class BaseRespVO<T> {
    private String code;
    private String message;
    private T data;

    public static <T> BaseRespVO<T> success(T data) {
        BaseRespVO<T> baseRespVO = new BaseRespVO<>();
        baseRespVO.setCode("2000");
        baseRespVO.setMessage("success");
        baseRespVO.setData(data);
        return baseRespVO;
    }

    public static <T> BaseRespVO<T> error(String message) {
        BaseRespVO<T> baseRespVO = new BaseRespVO<>();
        baseRespVO.setCode("5000");
        if (StringUtils.isNotBlank(message)) {
            baseRespVO.setMessage(message);
        } else {
            baseRespVO.setMessage("网络开小差，请稍后再试");
        }
        baseRespVO.setData(null);
        return baseRespVO;
    }
}
