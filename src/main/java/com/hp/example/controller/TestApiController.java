package com.hp.example.controller;

import com.alibaba.fastjson.JSON;
import com.hp.message.domain.ApiResp;
import com.hp.message.domain.param.AddEquiParam;
import com.hp.message.domain.result.*;
import com.hp.message.interfaces.IApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author 尚肖磊
 * @create 2022-07-19 16:22
 * @Description: 华普云SDK API接口调用示例
 */
@Slf4j
@RestController
@RequestMapping("test/api")
public class TestApiController {

    @Autowired
    private IApiService apiService;

    /**
     * 获取App项目列表
     *
     * @return
     */
    @GetMapping("getAppProjectList")
    public ApiResp<List<ProjectResult>> getAppProjectList() {
        return apiService.getAppProjectList();
    }

    /**
     * 新增项目并绑定App
     *
     * @param projectName 项目名称
     * @param projectDesc 项目描述
     * @return
     */
    @PostMapping("addProject")
    public ApiResp addProject(String projectName, String projectDesc) {
        return apiService.addProject(projectName, projectDesc);
    }

    /**
     * 修改项目名称
     *
     * @param projectId   项目id
     * @param projectName 修改后项目名称
     * @return
     */
    @PostMapping("alterProject")
    public ApiResp alterProject(int projectId, String projectName) {
        return apiService.alterProject(projectId, projectName);
    }

    /**
     * 修改项目透传状态
     *
     * @param projectId      项目id
     * @param enableTransfer 透传状态 true 开启 false 关闭
     */
    @PostMapping("alterProjectTransferStatus")
    public ApiResp alterProjectTransferStatus(int projectId, boolean enableTransfer) {
        return apiService.alterProjectTransferStatus(projectId, enableTransfer);
    }

    /**
     * 解绑App项目
     *
     * @param projectId 项目id
     */
    @PostMapping("unbindProject")
    public ApiResp unbindProject(int projectId) {
        return apiService.unbindProject(projectId);
    }


    /**
     * 获取分组列表
     *
     * @param projectId 项目id
     * @param groupName 查询分组名称(模糊匹配)
     * @return
     */
    @GetMapping("getGroupList")
    public ApiResp<List<GroupResult>> getGroupList(int projectId, String groupName) {
        return apiService.getGroupList(projectId, groupName);
    }

    /**
     * 创建项目分组
     *
     * @param projectId 项目id
     * @param groupName 项目分组名称
     * @param groupSort 项目分组排序值
     * @param groupDesc 项目分组描述
     * @return
     */
    @PostMapping("addGroup")
    public ApiResp addGroup(int projectId, String groupName, int groupSort, String groupDesc) {
        return apiService.addGroup(projectId, groupName, groupSort, groupDesc);
    }

    /**
     * 修改项目分组
     *
     * @param groupId   项目分组id
     * @param groupName 项目分组名称
     * @param groupSort 项目分组排序值
     * @param groupDesc 项目分组描述
     * @return
     */
    @PostMapping("alterGroup")
    public ApiResp alterGroup(int groupId, String groupName, int groupSort, String groupDesc) {
        return apiService.alterGroup(groupId, groupName, groupSort, groupDesc);
    }

    /**
     * 删除项目分组
     *
     * @param groupId 项目分组id
     * @return
     */
    @PostMapping("removeGroupById")
    public ApiResp removeGroupById(int groupId) {
        return apiService.removeGroupById(groupId);
    }


    /**
     * 获取设备详情
     *
     * @param equiSno 设备SN号
     * @return
     */
    @GetMapping("getEquiInfoBySno")
    public ApiResp getEquiInfoBySno(String equiSno) {
        return apiService.getEquiInfoBySno(equiSno);
    }

    /**
     * 获取设备列表
     *
     * @param projectId      项目id
     * @param groupId        项目分组id
     * @param equiWorkStatus 设备状态 1 在线 2 离线 3 升级中 4 配置中
     */
    @GetMapping("getEquiList")
    public ApiResp<List<EquiResult>> getEquiList(int projectId, int groupId, int equiWorkStatus, String dataHandleType) {
        return apiService.getEquiList(projectId, groupId, equiWorkStatus,dataHandleType);
    }

    /**
     * 获取设备状态列表
     */
    @GetMapping("getEquiWorkStatusList")
    public ApiResp<List<StatusResult>> getEquiWorkStatusList() {
        return apiService.getEquiWorkStatusList();
    }

    /**
     * 添加设备
     *
     * @param addEquiParam 设备JSON对象
     */
    @PostMapping("addEqui")
    public ApiResp<AddEquiResult> addEqui(@RequestBody AddEquiParam addEquiParam) {
        return apiService.addEqui(addEquiParam);
    }

    /**
     * 批量添加设备
     *
     * @param addEquiParamList 设备JSON对象列表
     */
    @PostMapping("addEquiByList")
    public ApiResp<List<AddEquiResult>> addEquiByList(@RequestBody List<AddEquiParam> addEquiParamList) {
        return apiService.addEquiByList(addEquiParamList);
    }

    /**
     * 修改设备名称及通讯密码
     *
     * @param equiSno      设备SN号
     * @param projectId    项目id
     * @param groupId      分组id
     * @param equiName     设备名称
     * @param equiPassword 设备通讯密码
     * @return
     */
    @PostMapping("alterEquiBySno")
    public ApiResp alterEquiById(String equiSno, int projectId, int groupId, String equiName, String equiPassword) {
        return apiService.alterEquiBySno(equiSno, projectId, groupId, equiName, equiPassword);
    }

    /**
     * 删除设备
     *
     * @param equiSno 设备SN号
     * @return
     */
    @PostMapping("removeEquiBySno")
    public ApiResp removeEquiBySno(String equiSno) {
        return apiService.removeEquiBySno(equiSno);
    }

    /**
     * 批量删除设备
     *
     * @param equiSnos 设备SN号(格式 1,2,3,4)
     * @return
     */
    @PostMapping("removeEquiBySnos")
    public ApiResp removeEquiBySnos(String equiSnos) {
        return apiService.removeEquiBySnos(equiSnos);
    }

}
