package com.bxin.Home.repository;

import com.bxin.Home.domain.Nav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavRepository extends JpaRepository<Nav, Long> {

    List<Nav> findAllByParentIdIsNull();

    List<Nav> findAllByParentIdNotNull();
}
