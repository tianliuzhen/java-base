package com.aaa.javabase.mybatis;

import com.aaa.javabase.h2.Model.User;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSqlNode.java  2022/10/23 21:13
 */

@SpringBootTest
public class TestSqlNode {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void simpleTest() {
        Configuration configuration = sqlSessionFactory.getConfiguration();

        User user = new User();
        user.setId(1L);

        DynamicContext dynamicContext = new DynamicContext(configuration, user);
        // 静态节点逻辑
        boolean apply = new StaticTextSqlNode("select * from user ").apply(dynamicContext);
        // if节点
        IfSqlNode ifSqlNode = new IfSqlNode(new StaticTextSqlNode(" and id=#{id}"), "id!=null");

        // where节点
        WhereSqlNode whereSqlNode = new WhereSqlNode(configuration, ifSqlNode);
        whereSqlNode.apply(dynamicContext);

        System.err.println(dynamicContext.getSql());
    }

}
