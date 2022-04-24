package com.wx.netty.service.impl;

import com.scy.netty.job.annotation.Job;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : shichunyang
 * Date    : 2022/4/24
 * Time    : 4:29 下午
 * ---------------------------------------
 * Desc    : JobService
 */
@Slf4j
public class JobService {

    @Job(value = "JobService-helloWord", init = "init", destroy = "destroy")
    public void helloWord() {
        log.info("helloWord");
    }

    public void init() {
        log.info("init");
    }

    public void destroy() {
        log.info("destroy");
    }
}
