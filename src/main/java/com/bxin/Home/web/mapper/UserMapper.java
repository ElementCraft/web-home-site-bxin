package com.bxin.Home.web.mapper;

import com.bxin.Home.domain.User;
import com.bxin.Home.web.dto.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper extends EntityMapper<UserInfoDTO, User> {

}
