package hxj.boot.codegen;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

/**
 * @author huangxj
 * @since 2023/9/20
 */
public class JavaParserControllerMain {
    public static void main(String[] args) {
        // 编译单元
        CompilationUnit compilationUnit = new CompilationUnit();
        // 包名
        compilationUnit.setPackageDeclaration("cn.gzsendi.itmpbiz.itmpbizservice.event.controller");
        // 类
        ClassOrInterfaceDeclaration classDef = compilationUnit.addClass("WarningEventConfController");
        // 类注解
        classDef.addAnnotation(new NormalAnnotationExpr(new Name("Api"), new NodeList<>()).addPair("tags", "告警事件配置"));
        classDef.addAnnotation(new MarkerAnnotationExpr("RestController"));
        classDef.addAnnotation(new SingleMemberAnnotationExpr(new Name("RequestMapping"), new StringLiteralExpr("/warningEventConf")));
        // import 语句
        compilationUnit.addImport("cn.gzsendi.itmpbiz.itmpbizservice.event.service.*");
        compilationUnit.addImport("org.springframework.beans.factory.annotation.Autowired");

        // 字段
        FieldDeclaration fieldDeclaration = classDef.addField("WarningEventConfService", "warningEventConfService", Modifier.Keyword.PRIVATE, Modifier.Keyword.FINAL);
        // fieldDeclaration.addAnnotation(new NormalAnnotationExpr().setName("Autowired"));
        // fieldDeclaration.addAnnotation(new NormalAnnotationExpr(new Name("Autowired"), new NodeList<>()));
        // 字段注解
        fieldDeclaration.addAnnotation(new MarkerAnnotationExpr().setName("Resource"));

        // 方法
        MethodDeclaration method = classDef.addMethod("saveOrUpdate", Modifier.Keyword.PUBLIC);
        method.setType(new ClassOrInterfaceType().setName("SimpleResponse<Void>"));

        // 方法参数
        NodeList<Parameter> parameters = new NodeList<>(new Parameter(new ClassOrInterfaceType().setName("WarningEventConfReq"), "req"));
        parameters.get(0).addAnnotation(new MarkerAnnotationExpr().setName("RequestBody"));
        method.setParameters(parameters);

        // 方法注解
        NormalAnnotationExpr normalAnnotationExpr = new NormalAnnotationExpr();
        normalAnnotationExpr.setName("ApiOperation");
        normalAnnotationExpr.addPair("value", new StringLiteralExpr("新增/修改事件配置"));
        method.getAnnotations().add(normalAnnotationExpr);

        // 方法注解
        normalAnnotationExpr = new NormalAnnotationExpr();
        normalAnnotationExpr.setName("PostMapping");
        normalAnnotationExpr.addPair("value", new StringLiteralExpr("/saveOrUpdate"));
        method.getAnnotations().add(normalAnnotationExpr);

        // 方法体
        method.setBody(
                new BlockStmt()
                        .addStatement(
                                new ReturnStmt(
                                        new MethodCallExpr().setScope(new NameExpr("CommonResult")).setName("success")
                                                .addArgument(
                                                        new MethodCallExpr(new NameExpr("authService"), "login").addArgument(new NameExpr("reqVO"))
                                                )
                                )
                        )
        );


        // 接口

        // method.setBody(
        //         new BlockStmt()
        //                 .addStatement(
        //                         new ReturnStmt(
        //                                 new MethodCallExpr(new NameExpr("warningEventConfService"), "saveOrUpdate")
        //                                         .addArgument(new NameExpr("req"))
        //                         )
        //                 )
        // );

        System.out.println(compilationUnit);
    }
}
