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
@Table(name = "swiper")
public class Swiper extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private String title;

    private String imgPath;

    /**
     * 是否禁用
     */
    private Boolean isClose;
}
