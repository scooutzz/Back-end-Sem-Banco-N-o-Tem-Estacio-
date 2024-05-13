package cadastrodb.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static int getValue(String sequenceName) throws ClassNotFoundException, SQLException {
        int nextValue = 0;
        String sql = "SELECT nextval(?)";

        try (
            Connection conn = ConectorBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, sequenceName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nextValue = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextValue;
    }
}
