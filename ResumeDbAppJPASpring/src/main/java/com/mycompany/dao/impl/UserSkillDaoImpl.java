package com.mycompany.dao.impl;



import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.UserSkillDaoInter;
import com.mycompany.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) throws Exception {
        List<UserSkill> result = new ArrayList<>();
        try ( Connection c = connect()) {
            PreparedStatement pps = c.prepareStatement("SELECT"
                    + " us.id as userSkillId,"
                    + " u.*,"
                    + " s.id as skill_id,s.`name` AS skill_name,"
                    + " us.power"
                    + " FROM user_skill us"
                    + " LEFT JOIN user u on us.user_id = u.id"
                    + " LEFT JOIN skill s on us.skill_id = s.id"
                    + " WHERE u.id = ?");

            pps.setInt(1, userId);
            pps.execute();
            ResultSet rs = pps.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkills(rs);
                result.add(u);
            }
        }
        return result;
    }

    private UserSkill getUserSkills(ResultSet rs) throws SQLException {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        //return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
        return null;
    }

    @Override
    public boolean removeUserSkill(int skillId) {
        try ( Connection c = connect()) {
            PreparedStatement pps = c.prepareStatement("delete from user_skill where id = ?");
            pps.setInt(1, skillId);
            return pps.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addUserSkill(UserSkill userSkill) {
        boolean addedUserSkill = true;
        try ( Connection c = connect()) {
            List<UserSkill> allUserSkills = getAllSkillByUserId(userSkill.getUser().getId());
            if (allUserSkills.contains(userSkill)) {
                addedUserSkill = false;
            } else {
                PreparedStatement pps = c.prepareStatement("insert into user_skill(user_id,skill_id,power) values (?,?,?)");
                pps.setInt(1, userSkill.getUser().getId());
                pps.setInt(2, userSkill.getSkill().getId());
                pps.setInt(3, userSkill.getPower());
                pps.execute();
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return addedUserSkill;
    }

    @Override
    public boolean updateUserSkill(UserSkill us) {
        boolean updatedUserSkill = true;
        try ( Connection c = connect()) {
            List<UserSkill> allUserSkill = getAllSkillByUserId(us.getUser().getId());
            if(allUserSkill.contains(us)){
                updatedUserSkill = false;
            }
            else{
                 PreparedStatement pps = c.prepareStatement("update user_skill set user_id = ?,skill_id = ?,power = ? where id = ?");
            pps.setInt(1, us.getUser().getId());
            pps.setInt(2, us.getSkill().getId());
            pps.setInt(3, us.getPower());
            pps.setInt(4, us.getId());
            pps.execute(); 
            }
          
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        return updatedUserSkill;
    }
}
