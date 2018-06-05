package com.bxin.Home.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NavListDTO {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 非通用页面需要跳转
     */
    private Boolean needJump;

    /**
     * 跳转URL
     */
    private String jumpUrl;

    private List<NavListDTO> subNavs;
}
