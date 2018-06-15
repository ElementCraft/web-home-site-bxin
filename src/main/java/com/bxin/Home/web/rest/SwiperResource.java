package com.bxin.Home.web.rest;

import com.bxin.Home.domain.Swiper;
import com.bxin.Home.service.SwiperService;
import com.bxin.Home.tools.entity.Result;
import com.bxin.Home.tools.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @PostMapping("/upload")
    public ResponseEntity<Result> uploadIcon(MultipartFile file){
        Result dto = null;
        UploadUtil.UploadLimit limit = new UploadUtil.UploadLimit();
        //limit.setSavePath("uploads" + File.separator + "icons" + File.separator);
        limit.setMaxSize(1024L * 1024L);
        limit.setAllowExt(new ArrayList<String>(){{
            add("jpg");
            add("jpeg");
            add("png");
            add("bmp");
            add("gif");
            add("webp");
        }});

        dto = UploadUtil.checkAndSave(file, limit);
        return ResponseEntity.ok(dto);
    }
}
