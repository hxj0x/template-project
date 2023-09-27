package hxj.boot.codegen.playground;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLDataTypeImpl;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;

/**
 * Druid校验SQL的能力要比JSqlParser强
 *
 * @author huangxj
 * @since 2023/9/26
 */
public class DruidSqlCreateMain {
    public static void main(String[] args) {
        MySqlCreateTableStatement createTableStatement = new MySqlCreateTableStatement();
        SQLColumnDefinition column1 = new SQLColumnDefinition();
        column1.setName("emp_id");
        column1.setDataType(new SQLDataTypeImpl("INT"));
        column1.setComment("员工编号");
        createTableStatement.addColumn(column1);
        createTableStatement.setTableName("employees");
        // SQLBinaryOpExpr comment = new SQLBinaryOpExpr();

        // comment.setLeft(new SQLCharExpr("员工信息表"));
        // comment.setOperator(SQLBinaryOperator.COLLATE);
        // comment.setRight(new SQLIdentifierExpr("utf8mb4_unicode_ci"));
        // createTableStatement.setComment(comment);

        createTableStatement.setComment(new SQLCharExpr("员工信息表"));
        createTableStatement.addOption("ENGINE", new SQLIdentifierExpr("InnoDB"));

        System.out.println(createTableStatement.toString());

        SQLStatement sqlStatement = SQLUtils.parseSingleStatement("create table infra_job_log\n" +
                "(\n" +
                "    id            bigint auto_increment comment '日志编号'\n" +
                "        primary key,\n" +
                "    job_id        bigint                                  not null comment '任务编号',\n" +
                "    handler_name  varchar(64)                             not null comment '处理器的名字',\n" +
                "    handler_param varchar(255)                            null comment '处理器的参数',\n" +
                "    execute_index tinyint       default 1                 not null comment '第几次执行',\n" +
                "    begin_time    datetime                                not null comment '开始执行时间',\n" +
                "    end_time      datetime                                null comment '结束执行时间',\n" +
                "    duration      int                                     null comment '执行时长',\n" +
                "    status        tinyint                                 not null comment '任务状态',\n" +
                "    result        varchar(4000) default ''                null comment '结果数据',\n" +
                "    creator       varchar(64)   default ''                null comment '创建者',\n" +
                "    create_time   datetime      default CURRENT_TIMESTAMP not null comment '创建时间',\n" +
                "    updater       varchar(64)   default ''                null comment '更新者',\n" +
                "    update_time   datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',\n" +
                "    deleted       bit           default b'0'              not null comment '是否删除'\n" +
                ")\n" +
                "    comment '定时任务日志表';", DbType.mysql);
        System.out.println("sqlStatement = " + sqlStatement);
    }
}
