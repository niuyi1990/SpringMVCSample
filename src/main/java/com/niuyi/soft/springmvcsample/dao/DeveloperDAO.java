package com.niuyi.soft.springmvcsample.dao;

import com.niuyi.soft.springmvcsample.model.DeveloperModel;

import java.util.List;

/**
 * 名称: DeveloperDAO
 * 作者: 牛毅
 * 日期: 2018/6/11 16:24
 * 描述: ${DESCRIPTION}
 */
public interface DeveloperDAO {

    List<DeveloperModel> getAllDevelopers();

    DeveloperModel getDeveloper(String developerId);

    boolean addDeveloper(DeveloperModel developer);

    boolean deleteDeveloper(String developerId);

    boolean updateDeveloper(String developerId, String name);
}
