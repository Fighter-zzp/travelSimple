package com.zzp.travel.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    // 1.	声明静态数据源成员变量
    private static DataSource ds;

    // 2. 创建连接池对象
    static {
        // 加载配置文件中的数据
        InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pp = new Properties();
        try {
            pp.load(is);
            // 创建连接池，使用配置文件中的参数
            ds = DruidDataSourceFactory.createDataSource(pp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. 定义公有的得到数据源的方法
    public static DataSource getDataSource() {
        return ds;
    }

    // 4. 定义得到连接对象的方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
