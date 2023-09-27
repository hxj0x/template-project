package hxj.boot.codegen.playground;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

/**
 * @author huangxj
 * @since 2023/9/22
 */
public class JavaParserServiceInterfaceMain {
    public static void main(String[] args) {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration interfaceDeclaration = compilationUnit.addInterface("WarningEventConfService", Modifier.Keyword.PUBLIC);
        MethodDeclaration method = interfaceDeclaration.addMethod("saveOrUpdate", Modifier.Keyword.PUBLIC);
        method.setType("void");
        method.getParameters().add(new Parameter(new ClassOrInterfaceType().setName("WarningEventConfReq"), "req"));
        method.setBody(null);

        // void batchDelete(List<Long> eventConfIds);
        method = interfaceDeclaration.addMethod("batchDelete", Modifier.Keyword.PUBLIC);
        method.setType("void");
        method.getParameters().add(new Parameter(new ClassOrInterfaceType().setName("List<Long>"), "eventConfIds"));
        method.setBody(null);

        System.out.println(compilationUnit.toString());
    }
}
