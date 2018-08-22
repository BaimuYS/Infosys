package com.shuxiajian.frame.common.config;

import com.shuxiajian.frame.common.MyPageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Mybatis工厂类
 *
 * @author pengzx
 * @create 2018-07-25 11:12
 */

@SpringBootConfiguration
public class SessionFactoryConfiguration {
    @Value("${mapperXML.xml.config.path}")
    private String mapperXMLConfigPath;
    @Value("${mapperXML.package.path}")
    private String mapperPackagePath;
    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageXMLConfigPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperXMLConfigPath;

        // 设置mapper 对应的XML 文件的路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageXMLConfigPath));
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置mapper 接口所在的包
        sqlSessionFactoryBean.setTypeAliasesPackage(mapperPackagePath);


        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new MyPageInterceptor()});

        return sqlSessionFactoryBean;
    }
}
