package com.hp.example.service;

import com.google.common.primitives.Bytes;
import com.hp.message.enums.EquiLogoutType;
import com.hp.message.interfaces.ISdkReceMsgService;
import com.hp.message.utils.CharConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author 尚肖磊
 * @create 2022-07-19 16:36
 * @Description: 接收设备消息处理服务
 */
@Slf4j
@Service
public class SdkReceMsgService implements ISdkReceMsgService {

    /**
     * 处理设备上线消息
     *
     * @param equiSno 设备SN号
     * @return
     */
    @Override
    public boolean receEquiLoginMsg(String equiSno) {
        log.info("rece equiSno {} online msg", equiSno);
        return true;
    }

    /**
     * 处理设备离线消息
     *
     * @param equiSno    设备SN号
     * @param logoutType 离线类型
     * [LOGOUT_1 设备请求退出; LOGOUT_2 重复登录,被迫退出; LOGOUT_3 心跳超时退出; LOGOUT_4 异常断开退出]
     * @return
     */
    @Override
    public boolean receEquiLogoutMsg(String equiSno, EquiLogoutType logoutType) {
        log.info("rece equiSno {} offline msg, reason {}", equiSno, logoutType.getCodeDesc());
        return true;
    }

    /**
     * 处理设备上报数据消息
     *
     * @param equiSno    设备SN号
     * @param uploadData 上报数据byte列表
     * @return
     */
    @Override
    public boolean receEquiUploadDataMsg(String equiSno, List<Byte> uploadData) {
        byte[] receBytes = Bytes.toArray(uploadData);
        log.info("rece equiSno {} upload msg, data {}", equiSno, CharConvertUtil.bytes2HexString(receBytes));
        return true;
    }
}
