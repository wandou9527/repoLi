package com.wandou.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author liming
 * @date 2020-03-26
 * @description
 */
@Data
public class MatterLogDTO {
    private Long id;

    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date happenTime;

    private String happenDate;

    private Integer mType;

    private Integer subType;

    private String partitionValue;

    private Integer partitionType;

    private Double reachAmount;

    private String reachAmountUnit;

}
