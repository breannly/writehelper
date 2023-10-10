package com.writershelper;

import com.writershelper.utils.HibernateSessionFactoryUtil;
import com.writershelper.view.common.CommonView;

public class Main {

    public static void main(String[] args) {
        CommonView.run();
        HibernateSessionFactoryUtil.shutdown();
    }
}