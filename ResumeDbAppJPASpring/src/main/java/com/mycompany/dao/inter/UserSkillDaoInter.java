package com.mycompany.dao.inter;

import com.mycompany.entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillByUserId(int userId) throws Exception;

    public boolean removeUserSkill(int skillId);

    public boolean addUserSkill(UserSkill userSkill);

    public boolean updateUserSkill(UserSkill us);
}
