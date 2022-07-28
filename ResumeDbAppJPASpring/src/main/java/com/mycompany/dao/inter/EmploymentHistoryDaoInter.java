package com.mycompany.dao.inter;

import com.mycompany.entity.EmploymentHistory;


import java.util.List;

public interface EmploymentHistoryDaoInter {


    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) throws Exception;
    
    public boolean addEmploymentHistory(EmploymentHistory empHis);
    
    public boolean removeEmploymentHistory(int historyId);
}
