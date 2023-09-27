package hxj.boot.codegen.playground;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.Node;
import net.sf.jsqlparser.statement.*;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.alter.AlterSession;
import net.sf.jsqlparser.statement.alter.AlterSystemStatement;
import net.sf.jsqlparser.statement.alter.RenameTableStatement;
import net.sf.jsqlparser.statement.alter.sequence.AlterSequence;
import net.sf.jsqlparser.statement.analyze.Analyze;
import net.sf.jsqlparser.statement.comment.Comment;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.schema.CreateSchema;
import net.sf.jsqlparser.statement.create.sequence.CreateSequence;
import net.sf.jsqlparser.statement.create.synonym.CreateSynonym;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.ForeignKeyIndex;
import net.sf.jsqlparser.statement.create.table.Index;
import net.sf.jsqlparser.statement.create.view.AlterView;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.execute.Execute;
import net.sf.jsqlparser.statement.grant.Grant;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.merge.Merge;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.show.ShowIndexStatement;
import net.sf.jsqlparser.statement.show.ShowTablesStatement;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.upsert.Upsert;
import net.sf.jsqlparser.util.validation.validator.CreateIndexValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过这个实验，我们可以实现导入 SQL 文件，然后解析出来，作为自己的模板，然后生成代码
 *
 * @author huangxj
 * @since 2023/9/20
 */
public class JSqlParseMain {
    public static void main(String[] args) throws JSQLParserException {
        Statement node = CCJSqlParserUtil.parse("create table product\n" +
                "(\n" +
                "    id          int primary key auto_increment comment '主键',\n" +
                "    name        varchar(255) not null comment '名称',\n" +
                "    category_id int          not null comment '产品分类id',\n" +
                "    constraint product_product_category_id_fk foreign key (category_id) references product_category (id)\n" +
                ");");
        System.out.println("node = " + node);
        node.accept(new StatementVisitor() {
            @Override
            public void visit(Analyze analyze) {

            }

            @Override
            public void visit(SavepointStatement savepointStatement) {

            }

            @Override
            public void visit(RollbackStatement rollbackStatement) {

            }

            @Override
            public void visit(Comment comment) {

            }

            @Override
            public void visit(Commit commit) {

            }

            @Override
            public void visit(Delete delete) {

            }

            @Override
            public void visit(Update update) {

            }

            @Override
            public void visit(Insert insert) {

            }

            @Override
            public void visit(Drop drop) {

            }

            @Override
            public void visit(Truncate truncate) {

            }

            @Override
            public void visit(CreateIndex createIndex) {

            }

            @Override
            public void visit(CreateSchema aThis) {

            }

            @Override
            public void visit(CreateTable createTable) {
                List<Index> indexes = createTable.getIndexes();
                List<ForeignKeyIndex> prepareToRemove = new ArrayList<>();
                for (Index index : indexes) {
                    if (index instanceof ForeignKeyIndex) {
                        ForeignKeyIndex foreignKeyIndex = (ForeignKeyIndex) index;
                        prepareToRemove.add(foreignKeyIndex);
                    }
                }
                indexes.removeAll(prepareToRemove);
            }

            @Override
            public void visit(CreateView createView) {

            }

            @Override
            public void visit(AlterView alterView) {

            }

            @Override
            public void visit(Alter alter) {

            }

            @Override
            public void visit(Statements stmts) {

            }

            @Override
            public void visit(Execute execute) {

            }

            @Override
            public void visit(SetStatement set) {

            }

            @Override
            public void visit(ResetStatement reset) {

            }

            @Override
            public void visit(ShowColumnsStatement set) {

            }

            @Override
            public void visit(ShowIndexStatement showIndex) {

            }

            @Override
            public void visit(ShowTablesStatement showTables) {

            }

            @Override
            public void visit(Merge merge) {

            }

            @Override
            public void visit(Select select) {

            }

            @Override
            public void visit(Upsert upsert) {

            }

            @Override
            public void visit(UseStatement use) {

            }

            @Override
            public void visit(Block block) {

            }

            @Override
            public void visit(DescribeStatement describe) {

            }

            @Override
            public void visit(ExplainStatement aThis) {

            }

            @Override
            public void visit(ShowStatement aThis) {

            }

            @Override
            public void visit(DeclareStatement aThis) {

            }

            @Override
            public void visit(Grant grant) {

            }

            @Override
            public void visit(CreateSequence createSequence) {

            }

            @Override
            public void visit(AlterSequence alterSequence) {

            }

            @Override
            public void visit(CreateFunctionalStatement createFunctionalStatement) {

            }

            @Override
            public void visit(CreateSynonym createSynonym) {

            }

            @Override
            public void visit(AlterSession alterSession) {

            }

            @Override
            public void visit(IfElseStatement aThis) {

            }

            @Override
            public void visit(RenameTableStatement renameTableStatement) {

            }

            @Override
            public void visit(PurgeStatement purgeStatement) {

            }

            @Override
            public void visit(AlterSystemStatement alterSystemStatement) {

            }

            @Override
            public void visit(UnsupportedStatement unsupportedStatement) {

            }
        });

        System.out.println(node.toString());

        // Statements statements = CCJSqlParserUtil.parseStatements("create table infra_data_source_config\n" +
        //         "(\n" +
        //         "    id          bigint auto_increment comment '主键编号'\n" +
        //         "        primary key,\n" +
        //         "    name        varchar(100) default ''                not null comment '参数名称',\n" +
        //         "    url         varchar(1024)                          not null comment '数据源连接',\n" +
        //         "    username    varchar(255)                           not null comment '用户名',\n" +
        //         "    password    varchar(255) default ''                not null comment '密码',\n" +
        //         "    creator     varchar(64)  default ''                null comment '创建者',\n" +
        //         "    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',\n" +
        //         "    updater     varchar(64)  default ''                null comment '更新者',\n" +
        //         "    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',\n" +
        //         "    deleted     bit          default b'0'              not null comment '是否删除'\n" +
        //         ")\n" +
        //         "    comment '数据源配置表' collate = utf8mb4_unicode_ci;" +
        //         // 有注释也没关系
        //         "-- auto-generated definition\n" +
        //         "create table infra_data_source_config\n" +
        //         "(\n" +
        //         "    id          bigint auto_increment comment '主键编号'\n" +
        //         "        primary key,\n" +
        //         "    name        varchar(100) default ''                not null comment '参数名称',\n" +
        //         "    url         varchar(1024)                          not null comment '数据源连接',\n" +
        //         "    username    varchar(255)                           not null comment '用户名',\n" +
        //         "    password    varchar(255) default ''                not null comment '密码',\n" +
        //         "    creator     varchar(64)  default ''                null comment '创建者',\n" +
        //         "    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',\n" +
        //         "    updater     varchar(64)  default ''                null comment '更新者',\n" +
        //         "    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',\n" +
        //         "    deleted     bit          default b'0'              not null comment '是否删除'\n" +
        //         ")\n" +
        //         "    comment '数据源配置表' collate = utf8mb4_unicode_ci;");
        // System.out.println(statements.toString());

        // for (Statement statement : statements) {
        //     if (statement instanceof CreateTable) {
        //         CreateTable createTable = (CreateTable) statement;
        //         Table table = createTable.getTable();
        //         String name = table.getName();
        //     }
        // }

        // Statement statement = CCJSqlParserUtil.parse("create table infra_codegen_table\n" +
        //         "(\n" +
        //         "    id                    bigint auto_increment comment '编号'\n" +
        //         "        primary key,\n" +
        //         "    data_source_config_id bigint                                 not null comment '数据源配置的编号',\n" +
        //         "    scene                 tinyint      default 1                 not null comment '生成场景',\n" +
        //         "    table_name            varchar(200) default ''                not null comment '表名称',\n" +
        //         "    table_comment         varchar(500) default ''                not null comment '表描述',\n" +
        //         "    remark                varchar(500)                           null comment '备注',\n" +
        //         "    module_name           varchar(30)                            not null comment '模块名',\n" +
        //         "    business_name         varchar(30)                            not null comment '业务名',\n" +
        //         "    class_name            varchar(100) default ''                not null comment '类名称',\n" +
        //         "    class_comment         varchar(50)                            not null comment '类描述',\n" +
        //         "    author                varchar(50)                            not null comment '作者',\n" +
        //         "    template_type         tinyint      default 1                 not null comment '模板类型',\n" +
        //         "    front_type            tinyint                                not null comment '前端类型',\n" +
        //         "    parent_menu_id        bigint                                 null comment '父菜单编号',\n" +
        //         "    creator               varchar(64)  default ''                null comment '创建者',\n" +
        //         "    create_time           datetime     default CURRENT_TIMESTAMP not null comment '创建时间',\n" +
        //         "    updater               varchar(64)  default ''                null comment '更新者',\n" +
        //         "    update_time           datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',\n" +
        //         "    deleted               bit          default b'0'              not null comment '是否删除'\n" +
        //         ")\n" +
        //         "    comment '代码生成表定义' collate = utf8mb4_unicode_ci;");
        // System.out.println(statement.toString());
        // // 创建 CreateTable 对象
        // CreateTable createTable = new CreateTable();
        //
        // // 设置表名
        // Table table = new Table();
        // table.setName("employees");
        // createTable.setTable(table);
        //
        // // 创建字段
        // ColumnDefinition empIdColumn = new ColumnDefinition();
        // empIdColumn.setColumnName("emp_id");
        // empIdColumn.setColDataType(new ColDataType("INT"));
        //
        // ColumnDefinition empNameColumn = new ColumnDefinition();
        // empNameColumn.setColumnName("emp_name");
        // empNameColumn.setColDataType(new ColDataType("VARCHAR(50)"));
        //
        // // 添加字段到 CreateTable 对象中
        // createTable.setColumnDefinitions(Arrays.asList(empIdColumn, empNameColumn));
        //
        //
        // // 生成 SQL 语句
        // Statement statement = createTable;
        // System.out.println(statement.toString());
    }
}
