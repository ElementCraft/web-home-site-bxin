package com.bxin.Home.service;

import com.bxin.Home.constants.error.UserError;
import com.bxin.Home.jwt.TokenProvider;
import com.bxin.Home.repository.UserRepository;
import com.bxin.Home.tools.entity.Result;
import com.bxin.Home.tools.utils.SecurityUtil;
import com.bxin.Home.web.dto.UserLoginDTO;
import com.bxin.Home.web.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bxin.Home.tools.entity.Result.error;
import static com.bxin.Home.tools.entity.Result.ok;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private TokenProvider tokenProvider;

    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return
     */
    public Result login(UserLoginDTO loginDTO) {
        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();

        // 判空
        if (account == null || password == null) {
            return error(UserError.Login.REQUIRE_IS_NULL);
        }

        // 加密密码
        String md5 = securityUtil.md5(loginDTO.getPassword());

        return userRepository.findByAccountAndDeleted(account, Boolean.FALSE)
                .map(user -> {
                    if (user.getPassword().equals(md5)) {

                        String token = tokenProvider.createToken(account, md5, null);

                        return ok(token);
                    } else {
                        return error(UserError.Login.INCORRECT_PASSWORD);
                    }
                })
                .orElse(error(UserError.Login.NOT_EXIST_ACCOUNT));
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public Result ownInfo() {
        Long id = securityUtil.getCurrentUserId();
        return ok(userMapper.toDto(userRepository.getOne(id)));
    }
}
