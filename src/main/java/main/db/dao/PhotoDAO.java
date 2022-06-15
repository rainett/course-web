package main.db.dao;

import main.db.DBManager;
import main.db.EntityMapper;
import main.db.Fields;
import main.db.entities.Photo;
import main.db.entities.Specs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoDAO {
    private static final String SQL_INSERT_PHOTO =
            "INSERT INTO photos(photo_image) VALUES (?)";

    private static final String SQL_GET_LAST_PHOTO_ID =
            "SELECT photo_id FROM photos ORDER BY photo_id DESC LIMIT 1";

    private static final String SQL_GET_PHOTO_BY_ID =
            "SELECT * FROM photos WHERE photo_id = ?";

    public int newPhoto(Photo photo) {
        int id = 0;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            id = insertPhoto(con, photo);
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

    private int insertPhoto(Connection con, Photo photo) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_GET_LAST_PHOTO_ID);
        ResultSet rs = pstmt.executeQuery();
        int k = 1;
        if (rs.next()) {
            k = rs.getInt(Fields.FIELD__PHOTO_ID) + 1;
        }
        pstmt = con.prepareStatement(SQL_INSERT_PHOTO);
        pstmt.setBlob(1, photo.getFile());
        pstmt.executeUpdate();
        pstmt.close();
        rs.close();
        return k;
    }

    public Photo getPhoto(int id) {
        Photo photo = null;
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_PHOTO_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            PhotoMapper photoMapper = new PhotoMapper();
            if (rs.next()) {
                photo = photoMapper.mapRow(rs);
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
        return photo;
    }


    private static class PhotoMapper implements EntityMapper<Photo> {
        @Override
        public Photo mapRow(ResultSet rs) throws SQLException {
            Photo photo = new Photo();
            photo.setId(rs.getInt(Fields.FIELD__PHOTO_ID));
            photo.setFile(rs.getBlob(Fields.FIELD__PHOTO_IMAGE).getBinaryStream());
            return photo;
        }
    }
}
