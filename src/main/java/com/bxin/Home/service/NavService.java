package com.bxin.Home.service;

import com.bxin.Home.domain.Nav;
import com.bxin.Home.repository.NavRepository;
import com.bxin.Home.web.dto.NavListDTO;
import com.bxin.Home.web.mapper.NavMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Nav> navs1 = navRepository.findAllByParentIdIsNull();
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
}
