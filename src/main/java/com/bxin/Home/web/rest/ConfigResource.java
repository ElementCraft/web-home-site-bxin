package com.bxin.Home.web.rest;

import com.bxin.Home.domain.Note;
import com.bxin.Home.domain.SystemConfig;
import com.bxin.Home.service.SystemConfigService;
import com.bxin.Home.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/config")
public class ConfigResource {

    @Autowired
    private SystemConfigService systemConfigService;

    @GetMapping("/all")
    public ResponseEntity<List<SystemConfig>> all() {

        return ResponseEntity.ok(systemConfigService.getAll());
    }

    @PutMapping("/fix")
    public ResponseEntity<Result> add(@RequestBody SystemConfig systemConfig) {
        return ResponseEntity.ok(systemConfigService.fix(systemConfig));
    }

    @GetMapping("/info")
    public ResponseEntity<Result<SystemConfig>> info(String key) {

        return ResponseEntity.ok(systemConfigService.getOneByCode(key));
    }
}
