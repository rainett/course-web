package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Manager;
import main.db.entities.Specs;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecsDAO {
    private static final String SQL_INSERT_SPECS =
            "INSERT INTO specs(specs_crew, specs_len, specs_wings_span, specs_height, " +
                    "specs_empty_weight, specs_max_weight, specs_weight, specs_speed, " +
                    "specs_range, specs_ceiling, specs_combat_range) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_GET_LAST_SPECS_ID =
            "SELECT specs_id FROM specs ORDER BY specs_id DESC LIMIT 1";

    private static final String SQL_GET_SPECS_BY_ID =
            "SELECT * FROM specs WHERE specs_id = ?";

    public int newSpecs(Specs specs) {
        int id = 0;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            id = insertSpecs(con, specs);
        } catch (SQLException e) {
            assert con != null;
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return id;
    }

    private int insertSpecs(Connection con, Specs specs) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_SPECS);
        int k = 1;
        pstmt.setLong(k++, specs.getCrew());
        pstmt.setDouble(k++, specs.getLen());
        pstmt.setDouble(k++, specs.getWingsSpan());
        pstmt.setDouble(k++, specs.getHeight());
        pstmt.setLong(k++, specs.getEmptyWeight());
        pstmt.setLong(k++, specs.getMaxWeight());
        pstmt.setLong(k++, specs.getWeight());
        pstmt.setLong(k++, specs.getSpeed());
        pstmt.setLong(k++, specs.getRange());
        pstmt.setLong(k++, specs.getCeiling());
        pstmt.setLong(k, specs.getCombatRange());
        pstmt.executeUpdate();
        pstmt = con.prepareStatement(SQL_GET_LAST_SPECS_ID);
        ResultSet rs = pstmt.executeQuery();
        k = 0;
        if (rs.next()) {
            k = rs.getInt(Fields.FIELD__SPECS_ID);
        }
        pstmt.close();
        rs.close();
        return k;
    }

    public Specs getSpecs(int id) {
        Specs specs = null;
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_SPECS_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            SpecsMapper specsMapper = new SpecsMapper();
            if (rs.next()) {
                specs = specsMapper.mapRow(rs);
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
        return specs;
    }


    private static class SpecsMapper implements EntityMapper<Specs> {
        @Override
        public Specs mapRow(ResultSet rs) throws SQLException {
            Specs specs = new Specs();
            specs.setId(rs.getInt(Fields.FIELD__SPECS_ID));
            specs.setCrew(rs.getInt(Fields.FIELD__SPECS_CREW));
            specs.setLen(rs.getFloat(Fields.FIELD__SPECS_LEN));
            specs.setWingsSpan(rs.getFloat(Fields.FIELD__SPECS_WINGS_SPAN));
            specs.setHeight(rs.getFloat(Fields.FIELD__SPECS_HEIGHT));
            specs.setEmptyWeight(rs.getInt(Fields.FIELD__SPECS_EMPTY_WEIGHT));
            specs.setMaxWeight(rs.getInt(Fields.FIELD__SPECS_MAX_WEIGHT));
            specs.setWeight(rs.getInt(Fields.FIELD__SPECS_WEIGHT));
            specs.setSpeed(rs.getInt(Fields.FIELD__SPECS_SPEED));
            specs.setRange(rs.getInt(Fields.FIELD__SPECS_RANGE));
            specs.setCeiling(rs.getInt(Fields.FIELD__SPECS_CEILING));
            specs.setCombatRange(rs.getInt(Fields.FIELD__SPECS_COMBAT_RANGE));
            return specs;
        }
    }
}
