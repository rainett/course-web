package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Airplane;
import main.db.entities.Photo;
import main.db.entities.Specs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {
    private static final String SQL_INSERT_AIRPLANE =
            "INSERT INTO airplanes (airplane_name, airplane_specs_id, airplane_description, airplane_photo_id, airplane_type) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_GET_ALL_AIRPLANES =
            "SELECT airplane_id, airplane_name, airplane_type, airplane_description, " +
                    "airplane_specs_id, airplane_photo_id FROM airplanes " +
                    "inner join specs on airplane_specs_id = specs_id " +
                    "inner join photos on airplane_photo_id = photo_id";

    private static final String SQL_GET_AIRPLANE_BY_ID =
            "SELECT airplane_id, airplane_name, airplane_type, airplane_description, " +
                    "airplane_specs_id, airplane_photo_id FROM airplanes " +
                    "inner join specs on airplane_specs_id = specs_id " +
                    "inner join photos on airplane_photo_id = photo_id " +
                    "where airplane_id = ?";

    public void newAirplane(Airplane airplane) {
        airplane.getSpecs().setId(new SpecsDAO().newSpecs(airplane.getSpecs()));
        airplane.getPhoto().setId(new PhotoDAO().newPhoto(airplane.getPhoto()));
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertAirplane(con, airplane);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void insertAirplane(Connection con, Airplane airplane) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_AIRPLANE);
        int k = 1;
        pstmt.setString(k++, airplane.getName());
        pstmt.setLong(k++, airplane.getSpecs().getId());
        pstmt.setString(k++, airplane.getDescription());
        pstmt.setLong(k++, airplane.getPhoto().getId());
        pstmt.setString(k, airplane.getType());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<Airplane> getAllAirplanes(String sorting) {
        String query = sorting == null ? SQL_GET_ALL_AIRPLANES : AirplanesSorting.getSortQuery(sorting);
        List<Airplane> airplanes = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            AirplaneMapper airplaneMapper = new AirplaneMapper();
            while (rs.next()) {
                airplanes.add(airplaneMapper.mapRow(rs));
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
        return airplanes;
    }

    public Airplane getAirplane(int id) {
        Airplane airplane = null;
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_AIRPLANE_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            AirplaneMapper airplaneMapper = new AirplaneMapper();
            if (rs.next()) {
                airplane = airplaneMapper.mapRow(rs);
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
        return airplane;
    }


    private static class AirplaneMapper implements EntityMapper<Airplane> {
        @Override
        public Airplane mapRow(ResultSet rs) throws SQLException {
            Airplane airplane = new Airplane();
            Specs specs = new SpecsDAO().getSpecs(rs.getInt(Fields.FIELD__AIRPLANE_SPECS_ID));
            Photo photo = new PhotoDAO().getPhoto(rs.getInt(Fields.FIELD__AIRPLANE_PHOTO_ID));
            airplane.setSpecs(specs);
            airplane.setPhoto(photo);
            airplane.setId(rs.getInt(Fields.FIELD__AIRPLANE_ID));
            airplane.setName(rs.getString(Fields.FIELD__AIRPLANE_NAME));
            airplane.setType(rs.getString(Fields.FIELD__AIRPLANE_TYPE));
            airplane.setDescription(rs.getString(Fields.FIELD__AIRPLANE_DESCRIPTION));
            return airplane;
        }
    }
}
