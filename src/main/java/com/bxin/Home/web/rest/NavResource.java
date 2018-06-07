package com.bxin.Home.web.rest;

import com.bxin.Home.domain.Nav;
import com.bxin.Home.service.NavService;
import com.bxin.Home.tools.entity.Result;
import com.bxin.Home.web.dto.NavListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/nav")
@CrossOrigin
public class NavResource {

    @Autowired
    private NavService navService;

    @GetMapping("/all")
    public ResponseEntity<List<NavListDTO>> all() {

        return ResponseEntity.ok(navService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Nav nav){
        return ResponseEntity.ok(navService.addNew(nav));
    }

    @PutMapping("/fix")
    public ResponseEntity<Result> fix(Long id, @RequestBody Nav nav){
        return ResponseEntity.ok(navService.fix(id, nav));
    }}
