package com.bxin.Home.domain;


import com.bxin.Home.tools.entity.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nav")
public class Nav extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父导航ID
     */
    private Long parentId;

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

    /**
     * 排序等级
     */
    private Integer sortLevel;
}
