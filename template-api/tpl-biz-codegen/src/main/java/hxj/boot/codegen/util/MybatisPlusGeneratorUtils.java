package hxj.boot.codegen.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangxj
 * @since 2022/9/19
 */
public class MybatisPlusGeneratorUtils {

    // 数据源配置
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/template-ui?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8",
            "root", "root")
            .build();

    private static final String PARENT_PACKAGE = "hxj.boot.codegen";
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String[] TABLE_NAMES = {

    };


    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.global(globalConfig().build());

        generator.strategy(
                        strategyConfig()
                                // .enableSchema() // 存在 schemaName 设置拼接 . 组合表名
                                // .addTablePrefix() // 过滤表前缀（后缀同理，支持多个） t_simple -> simple
                                // .addFieldSuffix("_flag") // 过滤字段后缀（前缀同理，支持多个） deleted_flag -> deleted
                                .addInclude(TABLE_NAMES) // 设置需要生成的表名
                                .entityBuilder()
                                // .idType(IdType.ASSIGN_ID) // 雪花Id
                                .enableFileOverride()
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                // .versionColumnName("version") // 基于数据库字段
                                // .versionPropertyName("version")// 基于模型属性
                                // .addTableFills(new Column("create_time", FieldFill.INSERT))    //基于数据库字段填充
                                // .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))    //基于模型属性填充
                                // .logicDeleteColumnName("deleted") // 基于数据库字段
                                // .logicDeletePropertyName("deleteFlag")// 基于模型属性
                                // .addIgnoreColumns("age") // 基于数据库字段
                                // .formatFileName("%sEntity") // TSimple -> TSimpleEntity
                                .mapperBuilder().formatMapperFileName("%sMapper").formatXmlFileName("%s").enableBaseColumnList().enableBaseResultMap()
                                // .controllerBuilder().formatFileName("%sController").enableHyphenStyle().enableRestStyle()
                                .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl")
                                .build())
                .template(new TemplateConfig.Builder()
                        // .disable(TemplateType.ENTITY)
                        // .disable(TemplateType.SERVICE)
                        // .disable(TemplateType.SERVICE_IMPL)
                        // .disable(TemplateType.MAPPER)
                        // .disable(TemplateType.CONTROLLER)
                        // .disable(TemplateType.XML)
                        .build());

        Map<OutputFile, String> pathInfo = new HashMap<>();
        // 这里要自定义的话得写绝对路径
        // pathInfo.put(OutputFile.entity, "dao.entity");
        // pathInfo.put(OutputFile.mapper, "dao.mapper");
        // pathInfo.put(OutputFile.service, "service");
        // pathInfo.put(OutputFile.serviceImpl, "service.impl");
        // pathInfo.put(OutputFile.controller, "controller");
        // pathInfo.put(OutputFile.xml, PROJECT_ROOT + "/src/main/resources/mapper/generator");
        // pathInfo.put(OutputFile.mapper, projectRoot + "/src/main/java/service/generator");
        // pathInfo.put(OutputFile.service, projectRoot + "/src/main/java/mapper");
        // pathInfo.put(OutputFile.serviceImpl, projectRoot + "/src/main/java/mapper");

        generator.packageInfo(new PackageConfig.Builder()
                .pathInfo(pathInfo)
                .parent(PARENT_PACKAGE)
                .mapper("codegen.dao.mapper")
                .entity("codegen.dao.entity")
                .service("codegen.dao.service")
                .serviceImpl("codegen.dao.service")
                .build());

        VelocityTemplateEngine templateEngine = new VelocityTemplateEngine();
        generator.execute(templateEngine);
    }

    /**
     * 策略配置
     */
    public static StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder();
        // .addTablePrefix() // 过滤表前缀（后缀同理，支持多个） t_simple -> simple
        // .addFieldSuffix("_flag") // 过滤字段后缀（前缀同理，支持多个） deleted_flag -> deleted
        // .addInclude("t_simple"); // 设置需要生成的表名
    }


    /**
     * 全局配置
     */
    protected static GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder()
                .outputDir(PROJECT_ROOT + "/src/main/java")
                .author("huangxj")
                .commentDate("yyyy-MM-dd HH:mm:ss")
                .dateType(DateType.TIME_PACK) // 使用Jdk8的日期类
                // .enableSwagger()
                .disableOpenDir()
                ;
    }

    // private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
    //         .Builder("jdbc:h2:D:/sendi_workspace/all/data/mybatis-plus-example;MODE=MYSQL;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=9093;DB_CLOSE_DELAY=-1;OLD_INFORMATION_SCHEMA=TRUE",
    //         "sa", "123456")
    //         .databaseQueryClass(SQLQuery.class) // 设置SQL查询方式，默认的是元数据查询方式
    //         // .schema("") // schema 名称
    //         .build();


    // 达梦
    // private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
    //         .Builder("jdbc:dm://xxxx:5236/DMSERVER?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8", "SYSDBA", "SYSDBA")
    //         .schema("SYSDBA")
    //         .build();

    // POSTGRESQL
    // private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
    //         .Builder("jdbc:postgresql://xxxx:5432/postgres", "postgres", "postgres")
    //         .build();
}
