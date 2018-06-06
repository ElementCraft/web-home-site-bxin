package com.bxin.Home.web.rest;

import com.bxin.Home.service.UserService;
import com.bxin.Home.tools.entity.Result;
import com.bxin.Home.web.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    private ResponseEntity<Result> login(@RequestBody UserLoginDTO loginDTO) {
        Result result = userService.login(loginDTO);

        return ok(result);
    }
}
