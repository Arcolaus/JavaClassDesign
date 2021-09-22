package main.tosql;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {
        //通过java访问mysql数据库
        //这个对象获取数据库链接
        //注册驱动，输入链接地址，用户名，密码
        String driver = "com.mysql.cj.jdbc.Driver";

        //访问本机的mysql数据库
        //格式 jdbc:数据库://本地端口3306/数据库名?跨时区参数
        String url = "jdbc:mysql://db.vesuo.cn:3306/hy_vesuo_cn?serverTimezone=Asia/Shanghai";
        String username = "hy";
        String password = "xhntA5PwRMhybShG";

        Class.forName(driver);

        //获取到数据库链接
        Connection connection = DriverManager.getConnection(url, username, password);

        //创建一个Statement语句对象
        Statement stat = connection.createStatement();

        //执行SQL语句
//        String sql = "DELETE FROM JavaClassDesign WHERE id < 10 ";
        for (int i = 0; i < 10; i++) {
            String stringFormat = "INSERT INTO JavaClassDesign VALUES (%s,%s,%s)";
            String sql = String.format(stringFormat, i, i * 12, i * 14);
            System.out.println(sql);
            stat.execute(sql);
        }
//        connection.createStatement().execute(sql);
//        stat.execute(sql);
//        ResultSet resultSet = stat.executeQuery(sql);
    }
}
