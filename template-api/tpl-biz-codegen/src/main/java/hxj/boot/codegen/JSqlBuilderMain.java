package hxj.boot.codegen;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.util.Arrays;

/**
 * @author huangxj
 * @since 2023/9/20
 */
public class JSqlBuilderMain {
    public static void main(String[] args) {
        // 创建 CreateTable 对象
        CreateTable createTable = new CreateTable();

        // 设置表名
        Table table = new Table();
        table.setName("employees");
        createTable.setTable(table);

        // 创建字段
        ColumnDefinition empIdColumn = new ColumnDefinition();
        empIdColumn.setColumnName("emp_id");
        empIdColumn.setColDataType(new ColDataType("INT"));

        ColumnDefinition empNameColumn = new ColumnDefinition();
        empNameColumn.setColumnName("emp_name");
        empNameColumn.setColDataType(new ColDataType("VARCHAR(50)"));

        ColumnDefinition empNameColumn2 = new ColumnDefinition();
        empNameColumn2.setColumnName("emp_name2");
        empNameColumn2.setColDataType(new ColDataType("VARCHAR(50)"));

        // 添加字段到 CreateTable 对象中
        createTable.setColumnDefinitions(Arrays.asList(empIdColumn, empNameColumn, empNameColumn2));
        createTable.setTableOptionsStrings(Arrays.asList("ENGINE=InnoDB", "DEFAULT CHARSET=utf8mb4", "COLLATE=utf8mb4_unicode_ci"));


        // 生成 SQL 语句
        Statement statement = createTable;
        System.out.println(statement.toString());
    }
}
