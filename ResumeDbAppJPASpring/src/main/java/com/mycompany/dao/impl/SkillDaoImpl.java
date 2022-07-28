package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {

    @Override
    public List<Skill> getAllSkill() throws Exception {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement st = c.createStatement();
            st.execute("SELECT  * from skill");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                Skill s = getSkill(rs);
                result.add(s);
            }
        }
        return result;
    }

    private Skill getSkill(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);
    }

    @Override
    public boolean addSkill(Skill skill) {
        boolean addedSkill = true;

        try (Connection c = connect()) {
            List<Skill> allSkills = getAllSkill();
            if (allSkills.contains(skill)) {
                addedSkill = false;

            } else {
                PreparedStatement pps = c.prepareStatement("insert into skill (name) values(?)", Statement.RETURN_GENERATED_KEYS);
                pps.setString(1, skill.getName());
                pps.execute();
                ResultSet generatedKeys = pps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    skill.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return addedSkill;
    }

}
