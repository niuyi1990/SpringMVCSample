package com.niuyi.soft.springmvcsample.controller;


import com.niuyi.soft.springmvcsample.dao.DeveloperDAO;
import com.niuyi.soft.springmvcsample.model.CommonModel;
import com.niuyi.soft.springmvcsample.model.DeveloperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 名称: DeveloperController
 * 作者: 牛毅
 * 日期: 2018/6/11 16:01
 * 描述: 接口请求访问层
 */
@Controller
@RequestMapping("/developer")
//@RequestMapping()
public class DeveloperController {

    @Autowired
    private DeveloperDAO developerDAO;

    @RequestMapping(value = "/api/developer/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/developer/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel addDeveloper(String name, String site, String avatar) {
        System.out.println("name=" + name);
        System.out.println("site=" + site);
        System.out.println("avatar=" + avatar);

        DeveloperModel developer = new DeveloperModel();
        developer.setName(name);
        developer.setSite(site);
        developer.setAvatar(avatar);

        CommonModel commonModel = new CommonModel();

        if (developerDAO.addDeveloper(developer)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }

        return commonModel;
    }

    @RequestMapping(value = "/api/developer/delete{developerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonModel deleteDeveloper(@PathVariable("developerId") String developerId) {
        System.out.println("developerId=" + developerId);

        CommonModel commonModel = new CommonModel();

        if (developerDAO.deleteDeveloper(developerId)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }

        return commonModel;
    }

    @RequestMapping(value = "/api/developer/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel updateDeveloper(String developerId, String name) {
        System.out.println("developerId=" + developerId);
        System.out.println("name=" + name);

        CommonModel commonModel = new CommonModel();

        if (developerDAO.updateDeveloper(developerId, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }

        return commonModel;
    }

    @RequestMapping(value = "/api/developer/queryDeveloper", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel queryDeveloper(String developerId) {
        System.out.println("developerId=" + developerId);

        CommonModel commonModel = new CommonModel();

        DeveloperModel developer = developerDAO.getDeveloper(developerId);

        if (developer != null) {
            commonModel.setSuccess();
            commonModel.setData(developer);
        } else {
            commonModel.setFail();
        }

        return commonModel;
    }

    @RequestMapping(value = "/api/developer/queryAllDevelopers", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel queryAllDevelopers() {
        CommonModel commonModel = new CommonModel();

        List<DeveloperModel> list = developerDAO.getAllDevelopers();

        if (list.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(list);
        } else {
            commonModel.setFail();
        }

        return commonModel;
    }

}
