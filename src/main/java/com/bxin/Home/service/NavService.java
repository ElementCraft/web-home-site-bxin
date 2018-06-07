package com.bxin.Home.service;

import com.bxin.Home.constants.error.NavError;
import com.bxin.Home.constants.error.PublicError;
import com.bxin.Home.domain.Nav;
import com.bxin.Home.repository.NavRepository;
import com.bxin.Home.tools.entity.Result;
import com.bxin.Home.web.dto.NavListDTO;
import com.bxin.Home.web.mapper.NavMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NavService {

    @Autowired
    private NavMapper navMapper;

    @Autowired
    private NavRepository navRepository;

    /**
     * 获取全部导航栏信息
     *
     * @return
     */
    public List<NavListDTO> getAll() {
        // 先查询所有一级导航栏
        List<Nav> navs1 = navRepository.findAllByParentIdIsNullOrderBySortLevelDesc();
        List<NavListDTO> result = navMapper.navs2NavListDTOs(navs1);

        // 查询所有二级导航栏，遍历添加到对应的一级导航栏下
        List<Nav> navs2 = navRepository.findAllByParentIdNotNull();
        navs2.forEach(nav2 -> {
            // 遍历一级导航栏 如果parentId跟一级导航栏的id对应，添加到结果集中。
            result.forEach(nav1 -> {
                if(nav2.getParentId().equals(nav1.getId())) {
                    NavListDTO dto = navMapper.nav2NavListDTO(nav2);
                    List<NavListDTO> dtos = Optional.ofNullable(nav1.getSubNavs()).orElse(new ArrayList<>());
                    dtos.add(dto);
                    nav1.setSubNavs(dtos);
                }
            });
        });

        return result;
    }

    /**
     * 新增导航栏
     *
     * @param nav 导航栏
     * @return
     */
    public Result addNew(Nav nav) {
        String title = nav.getTitle();
        Long parentId = nav.getParentId();
        Boolean needJump = nav.getNeedJump();
        String jumpUrl = nav.getJumpUrl();

        // 验证标题不能为空
        // 指定parentId时，parentId对应导航必须存在并且必须一级导航
        // needJump不为空且true时，jumpUrl不能为空
        if(!StringUtils.hasText(title)){
            return Result.error(NavError.Add.TITLE_IS_NULL);
        }else if(needJump && !StringUtils.hasText(jumpUrl)){
            return Result.error(NavError.Add.NEED_JUMP_NO_URL);
        }else if(parentId != null){
            Nav parent = navRepository.findOne(parentId);
            if(parent == null) return Result.error(NavError.Add.PARENT_NO_EXIST);

            if(parent.getParentId() != null) return Result.error(NavError.Add.PARENT_NO_LEVEL_1);
        }

        if(nav.getSortLevel() == null) nav.setSortLevel(0);
        navRepository.save(nav);

        return Result.ok();
    }

    /**
     * 更新导航栏信息
     *
     *
     * @param id
     * @param nav
     * @return
     */
    public Result fix(Long id, Nav nav) {
        if(id == null){
            return Result.error(PublicError.REQUIRE_IS_NULL);
        }

        String title = nav.getTitle();
        Boolean needJump = nav.getNeedJump();
        String jumpUrl = nav.getJumpUrl();

        // 查询id对应的导航栏信息
        Nav dbNav = navRepository.findOne(id);
        if(dbNav == null) return Result.error(NavError.NON_EXIST_ID);

        if(StringUtils.hasText(title)){
            dbNav.setTitle(title);
        }

        if(needJump != null){
            if(needJump){
                if(!StringUtils.hasText(jumpUrl)) return Result.error(NavError.Add.NEED_JUMP_NO_URL);
                dbNav.setJumpUrl(jumpUrl);
            }

            dbNav.setNeedJump(needJump);
        }

        if(nav.getSortLevel() != null){
            dbNav.setSortLevel(nav.getSortLevel());
        }


        navRepository.save(dbNav);

        return Result.ok();
    }
}
