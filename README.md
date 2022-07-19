# 华普云SDK-JAVA应用示例

#### 介绍
华普云JAVA SDK类库，Spring boot使用示例项目

1. 封装华普云API接口统一调用功能
2. 封装华普云设备消息接收及发送功能

#### SDK项目引用

Spring boot项目配置文件pom.xml中，添加如下配置
```
<dependency>
    <groupId>com.gitee.hpiot</groupId>
    <artifactId>message-spring-boot-starter</artifactId>
    <version>1.2.1</version>
</dependency>
```

#### SDK配置说明

在项目配置文件yml 增加华普云平台创建应用参数

```
hpiot:
  sdk:
    app:
      app-id: 华普云AppID
      app-key: 华普云AppKey
      app-secret: 华普云AppSecret
```

#### SDK API接口 调用示例
> API调用示例
```
/**
 * API服务对象声明
 */
@Autowired
private IApiService apiService;


```

#### SDK 设备接收及发送 调用示例
> 设备消息接收示例
```
// 自定义ISdkReceMsgService接口类

import com.hp.message.enums.LogoutType;
import com.hp.message.interfaces.ISdkReceMsgService;
import com.hp.message.utils.CharConvertUtil;
import lombok.extern.slf4j.Slf4j;

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
        log.info("rece equi {} online msg", equiSno);
        return true;
    }

    /**
     * 处理设备离线消息
     *
     * @param equiSno    设备SN号
     * @param logoutType 离线类型
     * (LOGOUT_1 设备请求退出; LOGOUT_2 重复登录,被迫退出; LOGOUT_3 心跳超时退出; LOGOUT_4 异常断开退出) 
     * @return
     */
    @Override
    public boolean receEquiLogoutMsg(String equiSno, LogoutType logoutType) {
        log.info("rece equi {} offline msg, reason {}", equiSno, logoutType.getCodeDesc());
        return true;
    }

    /**
     * 处理设备上报数据消息
     *
     * @param equiSno    设备SN号
     * @param uploadData 上报数据byte数组
     * @return
     */
    @Override
    public boolean receEquiUploadDataMsg(String equiSno, byte[] uploadData) {
        log.info("rece equi {} upload msg, data {}", equiSno, uploadData);
        return true;
    }
}
```
> 向设备发送消息示例
```
/**
 * 向设备发送消息服务对象声明
 */
@Autowired
private ISdkSendMsgService sdkSendMsgService;

// 声明待发送byte数组
byte[] pushDataByteArray;

// 同步发送消息
boolean sendResult = sdkSendMsgService.sendMsgToEqui(equiSno, pushDataByteArray);
if (sendResult) {
    return "发送成功";
} else {
    return "发送失败";
}

// 异步发送消息
sdkSendMsgService.sendMsgToEqui(equiSno, pushDataByteArray,
        new IFuncCallBack<String, String>() {
            @Override
            public void success(String equiSno) {
                log.info("向设备{} 发送消息成功", equiSno);
            }

            @Override
            public void fail(MqMsgResultType resultType, String equiSno) {
                log.info("向设备{} 发送失消息败 {}", equiSno, resultType.getResultDesc());
            }
        });

```

#### 示例项目地址

1.  华普云 SDK-JAVA示例项目地址[SDK-JAVA-Example](https://gitee.com/hpiot/SDK-JAVA-Example.git)
2.  华普云 官网地址 [cloud.hpiot.cn](https://cloud.hpiot.cn)