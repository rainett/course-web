package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {
    private static final String SQL_GET_USER_BY_USERNAME =
            "SELECT * FROM managers WHERE username = BINARY ?";

    private static final String SQL_INSERT_MANAGER =
            "INSERT INTO managers(username, password, email, state) VALUES (?, ?, ?, ?)";


    public Manager getManager(String username) {
        Manager manager = null;
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_USER_BY_USERNAME);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            ManagerMapper managerMapper = new ManagerMapper();
            if (rs.next()) {
                manager = managerMapper.mapRow(rs);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return manager;
    }

    public void newManager(Manager manager) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertManager(con, manager);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertManager(Connection con, Manager manager) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_MANAGER);
        int k = 1;
        pstmt.setString(k++, manager.getUsername());
        pstmt.setString(k++, manager.getPassword());
        pstmt.setString(k++, manager.getEmail());
        pstmt.setString(k, manager.getState());
        pstmt.executeUpdate();
        pstmt.close();
    }


    private static class ManagerMapper implements EntityMapper<Manager> {
        @Override
        public Manager mapRow(ResultSet rs) throws SQLException {
            Manager manager = new Manager();
            manager.setId(rs.getInt(Fields.FIELD__MANAGER_ID));
            manager.setUsername(rs.getString(Fields.FIELD__MANAGER_USERNAME));
            manager.setPassword(rs.getString(Fields.FIELD__MANAGER_PASSWORD));
            manager.setEmail(rs.getString(Fields.FIELD__MANAGER_EMAIL));
            manager.setState(rs.getString(Fields.FIELD__MANAGER_STATE));
            return manager;
        }
    }
}
