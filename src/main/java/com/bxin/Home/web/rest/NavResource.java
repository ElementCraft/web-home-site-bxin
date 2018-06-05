package com.bxin.Home.web.rest;

import com.bxin.Home.service.NavService;
import com.bxin.Home.web.dto.NavListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/nav")
public class NavResource {

    @Autowired
    private NavService navService;

    @GetMapping("/all")
    public ResponseEntity<List<NavListDTO>> all() {

        return ResponseEntity.ok(navService.getAll());
    }
}
