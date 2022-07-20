package com.hp.example.controller;

import com.hp.message.domain.ApiResp;
import com.hp.message.enums.MqMsgResultType;
import com.hp.message.enums.RespCodeType;
import com.hp.message.interfaces.IFuncCallBack;
import com.hp.message.interfaces.ISdkSendMsgService;
import com.hp.message.utils.CharConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 向设备推送数据
     *
     * @param equiSno    设备SN号
     * @param hexStrData 消息内容 16进制字符串
     * @return
     */
    @PostMapping("pushDataToEqui")
    public ApiResp pushDataToEqui(String equiSno, String hexStrData) {
        byte[] pushDataArray = CharConvertUtil.hexStringToBytes(hexStrData);
        boolean sendResult = sdkSendMsgService.sendMsgToEqui(equiSno, pushDataArray);
        return ApiResp.builder()
                .code(RespCodeType.RESP_SUCCESS.getCode())
                .message(RespCodeType.RESP_SUCCESS.getMessage())
                .data(sendResult)
                .build();
    }

    /**
     * 向设备推送数据
     *
     * @param equiSno    设备SN号
     * @param hexStrData 消息内容 16进制字符串
     * @return
     */
    @PostMapping("pushDataToEquiAsync")
    public ApiResp pushDataToEquiAsync(String equiSno, String hexStrData) {
        byte[] pushDataArray = CharConvertUtil.hexStringToBytes(hexStrData);
        sdkSendMsgService.sendMsgToEqui(equiSno, pushDataArray, new IFuncCallBack<String, String>() {
            @Override
            public void success(String equiSno) {
                log.info("向设备{} 发送消息成功", equiSno);
            }

            @Override
            public void fail(MqMsgResultType resultType, String equiSno) {
                log.info("向设备{} 发送失消息败 {}", equiSno, resultType.getResultDesc());
            }
        });
        return ApiResp.builder()
                .code(RespCodeType.RESP_SUCCESS.getCode())
                .message(RespCodeType.RESP_SUCCESS.getMessage())
                .data(true)
                .build();
    }

}
