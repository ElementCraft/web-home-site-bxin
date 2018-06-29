package com.bxin.Home.service;

import com.bxin.Home.domain.SystemConfig;
import com.bxin.Home.repository.SystemConfigRepository;
import com.bxin.Home.tools.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemConfigService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    /**
     * 获取全部配置项信息
     *
     * @return
     */
    public List<SystemConfig> getAll() {
        return systemConfigRepository.getAllByDeleted(Boolean.FALSE);
    }

    public Result<SystemConfig> getOneByCode(String code) {
        SystemConfig systemConfig = systemConfigRepository.findOneByKeyAndDeleted(code, Boolean.FALSE);
        return Result.ok(systemConfig);
    }

    public Result<SystemConfig> fix(SystemConfig systemConfig) {
        SystemConfig dbConfig = systemConfigRepository.findOne(systemConfig.getId());

        if (StringUtils.hasText(systemConfig.getData())) {
            dbConfig.setData(systemConfig.getData());
        }

        return Result.ok(systemConfigRepository.save(dbConfig));
    }
}
