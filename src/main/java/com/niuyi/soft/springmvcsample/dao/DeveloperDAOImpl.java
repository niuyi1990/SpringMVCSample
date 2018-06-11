package com.niuyi.soft.springmvcsample.dao;

import com.niuyi.soft.springmvcsample.model.DeveloperModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 名称: DeveloperDAOImpl
 * 作者: 牛毅
 * 日期: 2018/6/11 16:27
 * 描述: 处理数据库增删改查
 */
@Repository("developerDAOImpl")
public class DeveloperDAOImpl implements DeveloperDAO {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DeveloperModel> getAllDevelopers() {
        String sql = "SELECT * FROM developer";
        return query(sql);
    }

    @Override
    public DeveloperModel getDeveloper(String developerId) {
        String sql = "SELECT * FROM developer WHERE id=" + developerId;
        List<DeveloperModel> list = query(sql);

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean addDeveloper(DeveloperModel developer) {
        String sql = "INSERT INTO developer(name,site,avatar) VALUES(" +
                "'" + developer.getName() + "'," +
                "'" + developer.getSite() + "'," +
                "'" + developer.getAvatar() + "'" + ");";

        System.out.println("sql=" + sql);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteDeveloper(String developerId) {
        String sql = "DELETE * FROM developer WHERE id=" + developerId;

        System.out.println("sql=" + sql);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateDeveloper(String developerId, String name) {
        String sql = "UPDATE developer SET name='" + name + "' WHERE id=" + developerId;
        System.out.println("sql=" + sql);
        try {
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private List<DeveloperModel> query(String sql) {
        final List<DeveloperModel> list = new ArrayList<>();

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);

                DeveloperModel developer = new DeveloperModel();
                developer.setId(id);
                developer.setName(name);
                developer.setSite(site);
                developer.setAvatar(avatar);

                list.add(developer);
            }
        });
        return list;
    }
}
