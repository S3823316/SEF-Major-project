package controller.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import controller.paths.Template;
import io.javalin.http.Context;
import io.javalin.http.ErrorHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ViewUtil {
    // generate base model for generating Velocity template pages
    // current username and Id are passed into model
    public static Map<String, Object> baseModel(Context ctx){
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));
        model.put("currentUserID", RequestUtil.getSessionCurrentUserID(ctx));
        model.put("currentUserType", RequestUtil.getSessionCurrentUserType(ctx));
        model.put("currentUserPCo", RequestUtil.getSessionCurrentUserPCo(ctx));
        return model;
    }

    // generate 404 error if page not found
    public static ErrorHandler notFound = ctx -> {
        ctx.render(Template.NOT_FOUND, baseModel(ctx));
    };

    /**
     * Druid connection
     */

    public static class JDBCUtils {
        // initialize data source
        private static DataSource ds;

        static {

            Properties properties = new Properties();

            try {
                properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
                ds = DruidDataSourceFactory.createDataSource(properties);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }

        public static void close(Statement statement, Connection connection) {
            close(null, statement, connection);
        }

        public static void close(ResultSet resultSet, Statement statement, Connection connection) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static DataSource getDataSource() {
            return ds;
        }

    }
}
