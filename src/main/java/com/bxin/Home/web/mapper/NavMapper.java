package com.bxin.Home.web.mapper;

import com.bxin.Home.domain.Nav;
import com.bxin.Home.web.dto.NavListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NavMapper {

    NavListDTO nav2NavListDTO(Nav nav);

    List<NavListDTO> navs2NavListDTOs(List<Nav> navs);
}
