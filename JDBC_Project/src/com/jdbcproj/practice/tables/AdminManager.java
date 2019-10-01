package com.jdbcproj.practice.tables;

import com.jdbcproj.practice.DBType;
import com.jdbcproj.practice.DBUtil;
import com.jdbcproj.practice.beans.Admin;
import com.jdbcproj.practice.util.ConnectionManager;

import java.sql.*;

public class AdminManager {
//    Common connection call for all the methods.
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static void displayAllRows() throws SQLException {

        String sql = "SELECT adminId, userName, password FROM admin";
        try (
//                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {

            System.out.println("Admin Table:");
            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
                bf.append(rs.getInt("adminId") + ": ");
                bf.append(rs.getString("userName") + ", ");
                bf.append(rs.getString("password"));
                System.out.println(bf.toString());
            }
        }
    }

    public static Admin getRow(int adminId) throws SQLException {

        String sql = "SELECT * FROM admin WHERE adminId = ?";
        ResultSet rs = null;

        try (
//                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, adminId);
            rs = stmt.executeQuery();

            if (rs.next()) {

                Admin bean = new Admin();
                bean.setAdminId(adminId);
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
                return bean;

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static boolean insert(Admin bean) throws SQLException {
        String SQL = "INSERT INTO admin (userName, password) values (?, ?)";

//      To store the generated key after successful insertion result.
        ResultSet gen_key = null;

        try (
//                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());

            int insert_success = stmt.executeUpdate();

//      if insert is successful code returned is '1'.
            if (insert_success == 1) {
                gen_key = stmt.getGeneratedKeys();
//          To move cursor to one and only row of data.
                gen_key.next();
                int new_key = gen_key.getInt(1);
                bean.setAdminId(new_key);
            } else {
                System.err.println("No rows were affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (gen_key != null) {
                gen_key.close();
            }
        }
        return true;
    }

    public static boolean update(Admin bean) throws Exception {

        String SQL = "UPDATE admin SET " +
                "userName = ? , password = ?" +
                "WHERE adminId = ?";

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(SQL);
        ) {
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            stmt.setInt(3, bean.getAdminId());

            int updated = stmt.executeUpdate();

            if (updated == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public static boolean updateNew(Admin bean) throws Exception {

        String SQL = "SELECT * from admin WHERE adminId = ?";
        ResultSet rs = null;

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(SQL,
                        ResultSet.TYPE_SCROLL_INSENSITIVE
                , ResultSet.CONCUR_UPDATABLE);
        ) {
//            stmt.setString(1, bean.getUserName());
//            stmt.setString(2, bean.getPassword());
            stmt.setInt(1, bean.getAdminId());

            rs = stmt.executeQuery();

            if (rs.next()) {
                rs.updateString("userName", bean.getUserName());
                rs.updateString("password", bean.getPassword());
                rs.updateRow();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public static boolean delete(int adminId) throws SQLException{

        String SQL = "DELETE FROM admin WHERE adminId = ?";

        try(
//            Connection conn = DBUtil.getConnection(DBType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ){

            stmt.setInt(1,adminId);
            int updated = stmt.executeUpdate();

            if (updated == 1){
                return true;
            }else {
                return false;
            }
        }
        catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }

}
