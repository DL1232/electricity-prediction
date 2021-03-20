package com.decade.electricityprediction.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * 代码生成器
 */
public class CodeGenerator {

    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://43.128.10.218:3306/electricity_prediction?useUnicode=true&useSSL=false&characterEncoding=utf8";
    public static final String USERNAME = "lidongjie";
    public static final String PASSWORD = "lidongjie0408";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new com.baomidou.mybatisplus.generator.config.GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 生成文件输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 是否打开输出目录
        gc.setOpen(false);
        // 是否在 xml 中设置二级缓存
        gc.setEnableCache(false);
        // 开发人员
        gc.setAuthor("lidongjie");
        // 开启 Kotlin 模式
        gc.setKotlin(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // 开启 ActiveRecord 模式
        gc.setActiveRecord(true);
        // 开启 BaseResultMap
        gc.setBaseResultMap(true);
        // 开启 baseColumnList
        gc.setBaseColumnList(true);
        // 时间类型对应策略
        gc.setDateType(DateType.TIME_PACK);
        // 实体命名方式
        gc.setEntityName("%sEntity");
        // mapper 命名方式
        gc.setMapperName("%sMapper");
        // Mapper xml 命名方式
        gc.setXmlName("%sMapper");
        // service 命名方式
        gc.setServiceName("%sService");
        // service impl 命名方式
        gc.setServiceImplName("%sServiceImpl");
        // controller 命名方式
        gc.setControllerName("%sController");
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.AUTO);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库信息查询类
        // 默认由 dbType 类型决定选择对应数据库内置实现
        // 实现 IDbQuery 接口自定义数据库查询 SQL 语句 定制化返回自己需要的内容
        // dsc.setDbQuery();
        // 数据库类型
        dsc.setDbType(DbType.MYSQL);
        // 数据库 schema name 数据库名字
        // dsc.setSchemaName();
        // 类型转换
        // 默认由 dbType 类型决定选择对应数据库内置实现
        // 实现 ITypeConvert 接口自定义数据库 字段类型 转换为自己需要的 java 类型，
        // 内置转换类型无法满足可实现 IColumnType 接口自定义
        // dsc.setTypeConvert();
        // 驱动连接 URL
        dsc.setUrl(URL);
        // 驱动名称
        dsc.setDriverName(DRIVER_CLASS_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 策略配置 数据库表配置
        StrategyConfig strategy = new StrategyConfig();

        // 跳过视图
        strategy.setSkipView(true);
        // 是否大写命名
        // strategy.setCapitalMode();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 数据表前缀
        strategy.setTablePrefix("eps");
        // 字段前缀
        strategy.setFieldPrefix("is");
        // 6 个自定义 省略了
        // 需要包含的表名，当enableSqlFilter为false时，允许正则表达式（与exclude二选一配置）
        strategy.setInclude("eps_user", "eps_role", "eps_permission");
        // 【实体】是否为lombok模型（默认 false）
        // 3.3.2以下版本默认生成了链式模型，3.3.2以后，默认不生成，如有需要，请开启 chainModel
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父包名
        pc.setParent("com.decade.electricityprediction");
        // 父包模块名 指的是父包下创建的包名
        // pc.setModuleName();
        // Entity 包名
        pc.setEntity("persistence.entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("persistence.mapper");
        pc.setXml("mapper");
        pc.setController("web.controller");
        // pc.setPathInfo();
        mpg.setPackageInfo(pc);

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        mpg.setTemplate(templateConfig);

        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
