package com.bxin.Home.service;

import com.bxin.Home.constants.error.NavError;
import com.bxin.Home.constants.error.PublicError;
import com.bxin.Home.domain.Swiper;
import com.bxin.Home.repository.SwiperRepository;
import com.bxin.Home.tools.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SwiperService {

    @Autowired
    private SwiperRepository swiperRepository;

    /**
     * 获取全部轮播图信息
     *
     * @return
     */
    public List<Swiper> getAll() {
        return swiperRepository.findAll(new Sort(Sort.Direction.DESC, "gmtCreate"));
    }

    /**
     * 新增轮播图
     *
     * @param swiper 轮播图
     * @return
     */
    public Result addNew(Swiper swiper) {
        String title = swiper.getTitle();
        Long articleId = swiper.getArticleId();
        String imgPath = swiper.getImgPath();

        if (!StringUtils.hasText(title)) {
            return Result.error(1, "标题不能为空");
        }

        if (!StringUtils.hasText(imgPath)) {
            return Result.error(2, "请指定上传图片");
        }

        File fp = new File(imgPath);
        if (!fp.exists()) {
            return Result.error(3, "指定的图片不存在");
        }

        Swiper dbSwiper = new Swiper(null, articleId, title, imgPath, Boolean.FALSE);
        swiperRepository.save(dbSwiper);

        return Result.ok();
    }

    /**
     * 更新轮播图信息
     *
     * @param id
     * @param swiper
     * @return
     */
    public Result fix(Long id, Swiper swiper) {
        if (id == null) {
            return Result.error(PublicError.REQUIRE_IS_NULL);
        }

        String title = swiper.getTitle();
        Long articleId = swiper.getArticleId();
        String imgPath = swiper.getImgPath();

        // 查询id
        Swiper dbSwiper = swiperRepository.findOne(id);
        if (dbSwiper == null) return Result.error(NavError.NON_EXIST_ID);

        if (StringUtils.hasText(title)) {
            dbSwiper.setTitle(title);
        }

        if (StringUtils.hasText(imgPath)) {
            File fp = new File(imgPath);
            if (!fp.exists()) {
                return Result.error(3, "指定的图片不存在");
            }

            dbSwiper.setTitle(imgPath);
        }

        if (articleId != null) {
            dbSwiper.setArticleId(articleId);
        }

        swiperRepository.save(dbSwiper);

        return Result.ok();
    }

    /**
     * 删除轮播图
     *
     * @param id 轮播图ID
     * @return
     */
    public Result del(Long id) {
        if (id == null) {
            return Result.error(PublicError.REQUIRE_IS_NULL);
        }

        // 查询id
        Swiper dbSwiper = swiperRepository.findOne(id);
        if (dbSwiper == null) return Result.ok();

        swiperRepository.delete(dbSwiper);

        return Result.ok();
    }
}
