package hxj.boot.codegen.playground;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;

/**
 * @author huangxj
 * @since 2023/9/22
 */
public class JavaParserMainTest {
    public static void main(String[] args) {
        ParseResult<CompilationUnit> parse = new JavaParser().parse("" +
                "public class JavaParserMainTest { " +
                "    @PostMapping(\"/login\")\n" +
                "    @PermitAll\n" +
                "    @Operation(summary = \"使用账号密码登录\")\n" +
                "    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志\n" +
                "    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {\n" +
                "        return success(authService.login(reqVO));\n" +
                "    }" +
                "}");
        System.out.println(parse.getResult().get());
        // System.out.println("parse = " + parse);
    }
}
