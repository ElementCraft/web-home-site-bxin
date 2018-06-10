package com.bxin.Home.web.rest;

import com.bxin.Home.domain.Swiper;
import com.bxin.Home.service.SwiperService;
import com.bxin.Home.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/swiper")
public class SwiperResource {

    @Autowired
    private SwiperService swiperService;

    @GetMapping("/all")
    public ResponseEntity<List<Swiper>> all() {

        return ResponseEntity.ok(swiperService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Swiper swiper) {
        return ResponseEntity.ok(swiperService.addNew(swiper));
    }

    @PutMapping("/fix")
    public ResponseEntity<Result> fix(Long id, @RequestBody Swiper swiper) {
        return ResponseEntity.ok(swiperService.fix(id, swiper));
    }

    @DeleteMapping("/del")
    public ResponseEntity<Result> del(Long id) {
        return ResponseEntity.ok(swiperService.del(id));
    }
}
