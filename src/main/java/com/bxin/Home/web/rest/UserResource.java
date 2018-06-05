package com.bxin.Home.web.rest;

import com.bxin.Home.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bxin.Home.constants.ArticleConst.Flag.*;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private ArticleRepository articleRespository;

    @GetMapping("/articles")
    public Map test() {
        return Stream.of(OWN, REPRINT, TRANSLATE)
                .map(flag -> Pair.of(flag, articleRespository.findAll()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }
}
