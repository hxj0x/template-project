package hxj.boot.codegen.playground;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserFeature;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

/**
 * @author huangxj
 * @since 2023/9/27
 */
public class JSqlParseMain2 {
    public static void main(String[] args) throws JSQLParserException {
        String sql = "create table system_social_user\n" +
                "(\n" +
                "    id             bigint unsigned auto_increment comment '主键(自增策略)'\n" +
                "        primary key,\n" +
                "    type           tinyint                               not null comment '社交平台的类型',\n" +
                "    openid         varchar(32)                           not null comment '社交 openid',\n" +
                "    token          varchar(256)                          null comment '社交 token',\n" +
                "    raw_token_info varchar(1024)                         not null comment '原始 Token 数据，一般是 JSON 格式',\n" +
                "    nickname       varchar(32)                           not null comment '用户昵称',\n" +
                "    avatar         varchar(255)                          null comment '用户头像',\n" +
                "    raw_user_info  varchar(1024)                         not null comment '原始用户数据，一般是 JSON 格式',\n" +
                "    code           varchar(256)                          not null comment '最后一次的认证 code',\n" +
                "    state          varchar(256)                          null comment '最后一次的认证 state',\n" +
                "    creator        varchar(64) default ''                null comment '创建者',\n" +
                "    create_time    datetime    default CURRENT_TIMESTAMP not null comment '创建时间',\n" +
                "    updater        varchar(64) default ''                null comment '更新者',\n" +
                "    update_time    datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',\n" +
                "    deleted        bit         default b'0'              not null comment '是否删除',\n" +
                "    tenant_id      bigint      default 0                 not null comment '租户编号'\n" +
                ")\n" +
                "    comment '社交用户表' collate = utf8mb4_unicode_ci;";
        Statement statement = CCJSqlParserUtil.parse(sql);
        System.out.println("statement = " + statement);

        SQLStatement sqlStatement = SQLUtils.parseSingleStatement(sql, "mysql", SQLParserFeature.OptimizedForParameterized);
        System.out.println("sqlStatement = " + sqlStatement);


    }
}
