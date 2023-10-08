package com.writershelper;

import com.writershelper.utils.HibernateSessionFactoryUtil;
import com.writershelper.view.common.CommonView;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class Main {

    public static void main(String[] args) {
        startLiquibase();
        CommonView.run();
        HibernateSessionFactoryUtil.shutdown();
    }

    private static void startLiquibase() {
        try {
            Liquibase liquibase = new Liquibase(
                    "/db.changelog/db.changelog-master.xml",
                    new ClassLoaderResourceAccessor(),
                    new JdbcConnection(JdbcConnectionPool.getConnection()));
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            System.err.println("Cannot start liquibase");
        }
    }
}