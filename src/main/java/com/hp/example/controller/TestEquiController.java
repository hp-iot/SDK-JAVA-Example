package com.hp.example.controller;

import com.hp.message.interfaces.ISdkSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 尚肖磊
 * @create 2022-07-19 16:53
 * @Description: 华普云SDK 向设备发送消息调用示例
 */
@Slf4j
@RestController
@RequestMapping("test/equi")
public class TestEquiController {

    @Autowired
    private ISdkSendMsgService sdkSendMsgService;



}
