package hxj.boot.codegen;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;

/**
 * @author huangxj
 * @since 2023/9/20
 */
public class JavaParserMain {
    public static void main(String[] args) {
        CompilationUnit compilationUnit = new CompilationUnit();
        compilationUnit.setPackageDeclaration("cn.gzsendi.itmpbiz.itmpbizservice.event.controller");
        ClassOrInterfaceDeclaration classDef = compilationUnit.addClass("WarningEventConfController");
        classDef.addAnnotation(new NormalAnnotationExpr(new Name("Api"), new NodeList<>()).addPair("tags", "告警事件配置"));
        classDef.addAnnotation(new MarkerAnnotationExpr("RestController"));
        classDef.addAnnotation(new SingleMemberAnnotationExpr(new Name("RequestMapping"), new StringLiteralExpr("warningEventConf")));

        compilationUnit.addImport("cn.gzsendi.itmpbiz.itmpbizservice.event.service.*");
        compilationUnit.addImport("org.springframework.beans.factory.annotation.Autowired");
        FieldDeclaration fieldDeclaration = classDef.addField("WarningEventConfService", "warningEventConfService", Modifier.Keyword.PRIVATE);
        // fieldDeclaration.addAnnotation(new NormalAnnotationExpr().setName("Autowired"));
        // fieldDeclaration.addAnnotation(new NormalAnnotationExpr(new Name("Autowired"), new NodeList<>()));
        fieldDeclaration.addAnnotation(new MarkerAnnotationExpr().setName("Autowired"));
        System.out.println(compilationUnit);
    }
}
