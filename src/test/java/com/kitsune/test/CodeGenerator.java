package com.kitsune.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author Kitsune
 * @since 2018/12/13
 */
public class CodeGenerator {

  @Test
  public void run() {

    // 1、创建代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 2、全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir("F:\\test\\demo15301" + "/src/main/java");
    gc.setAuthor("Kitsune");
    gc.setOpen(false); //生成后是否打开资源管理器
    gc.setFileOverride(true); //重新生成时文件是否覆盖
    gc.setServiceName("%sService");	//去掉Service接口的首字母I
    gc.setIdType(IdType.ID_WORKER_STR); //主键策略
    gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
    gc.setSwagger2(true);//开启Swagger2模式

    mpg.setGlobalConfig(gc);

    // 3、数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=demo15301");
    dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    dsc.setUsername("sa");
    dsc.setPassword("252625ILY");
    dsc.setDbType(DbType.SQL_SERVER);
    mpg.setDataSource(dsc);

    // 4、包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName("demo"); //模块名
    pc.setParent("com.kitsune");
    pc.setController("controller");
    pc.setEntity("entity");
    pc.setService("service");
    pc.setMapper("mapper");
    mpg.setPackageInfo(pc);

    // 5、策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setInclude("users"); //设置要包含的表名，多表就在多个字符串之间用逗号连接
    strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
    strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

    strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
    strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

    strategy.setRestControllerStyle(true); //restful api风格控制器
    strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

    mpg.setStrategy(strategy);


    // 6、执行
    mpg.execute();
  }
}
