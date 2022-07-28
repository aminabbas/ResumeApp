package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.entity.EmploymentHistory;
import com.mycompany.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) throws Exception {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement pps = c.prepareStatement("SELECT * from employment_history where user_id = ?");
            pps.setInt(1, userId);
            pps.execute();
            ResultSet rs = pps.getResultSet();

            while (rs.next()) {
                EmploymentHistory eh = getEmploymentHistory(rs);
                result.add(eh);
            }
        }
        return result;
    }

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String header = rs.getString("header");
        String companyName = rs.getString("company_name");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");
        EmploymentHistory emp = new EmploymentHistory(id, header, companyName, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory empHis) {
        boolean addedEmpHis = true;
        try (Connection c = connect()) {
            List<EmploymentHistory> allHistory = getAllEmploymentHistoryByUserId(empHis.getUser().getId());
            if (allHistory.contains(empHis)) {
                addedEmpHis = false;
            } else {
                PreparedStatement pps = c.prepareStatement("insert into employment_history (header,company_name,begin_date,end_date,job_description,user_id) values (?,?,?,?,?,?)");
                pps.setString(1, empHis.getHeader());
                pps.setString(2, empHis.getCompanyName());
                pps.setDate(3, (Date) empHis.getBeginDate());
                pps.setDate(4, (Date) empHis.getEndDate());
                pps.setString(5, empHis.getJobDescription());
                pps.setInt(6, empHis.getUser().getId());
                pps.execute();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addedEmpHis;
    }

    @Override
    public boolean removeEmploymentHistory(int historyId) {
        boolean removedEmpHis = true;
        try (Connection c = connect()) {
            PreparedStatement pps = c.prepareStatement("delete from employment_history where id = ?");
            pps.setInt(1, historyId);
            pps.execute();

        } catch (Exception ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            removedEmpHis = false;
        }
        return removedEmpHis;
    }

}
