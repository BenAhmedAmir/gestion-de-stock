package com.benahmed.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

public class Intercepter extends EmptyInterceptor {
    @Override
    public String onPrepareStatement(String sql) {
//        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
//            if(sql.contains("where")){
//                sql = sql + " and entreprise0_.id  = 1";
//            }else {
//                sql = sql + " where idEntreprise = 1";
//            }
//        }
        return super.onPrepareStatement(sql);
    }
}
