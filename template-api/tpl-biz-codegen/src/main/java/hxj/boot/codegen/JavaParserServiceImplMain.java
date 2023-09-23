package hxj.boot.codegen;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

/**
 * @author huangxj
 * @since 2023/9/22
 */
public class JavaParserServiceImplMain {
    public static void main(String[] args) {

        // public class WarningEventConfServiceImpl implements WarningEventConfService {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration clazz = compilationUnit.addClass("WarningEventConfServiceImpl", Modifier.Keyword.PUBLIC)
                .addImplementedType("WarningEventConfService");
        clazz.addAnnotation(new MarkerAnnotationExpr("Service"));

        clazz.addField("WarningEventConfMapper", "warningEventConfMapper", Modifier.Keyword.PRIVATE)
                .addAnnotation(new MarkerAnnotationExpr("Resource"));

        //    @Override
        //     public void saveOrUpdate(WarningEventConfReq req) {
        //         checkPrams(req);
        //         ItmpWarningEventConf dbObj = new ItmpWarningEventConf();
        //         dbObj.setWarningEventConfId(req.getWarningEventConfId());
        //         dbObj.setEventName(req.getName());
        //         dbObj.setDescription(req.getDescription());
        //
        //         // 不传默认为true
        //         if (req.getIsEnable() == null) {
        //             dbObj.setEventEnable(true);
        //         } else {
        //             dbObj.setEventEnable(req.getIsEnable());
        //         }
        //
        //         dbObj.setTimeJson(JSONObject.toJSONString(req.getTimeJson()));
        //         dbObj.setRuleJson(JSONObject.toJSONString(req.getEventRuleJson()));
        //         List<Integer> actionIds = req.getEventActionConfReqList().stream().map(EventActionConfReq::getEventActionId).collect(Collectors.toList());
        //         dbObj.setEventActionIds(JSONObject.toJSONString(actionIds));
        //         dbObj.setEventActionsJson(JSONObject.toJSONString(req.getEventActionConfReqList()));
        //         Date now = new Date();
        //         dbObj.setUpdateTime(now);
        //         dbObj.setValidStartTime(now);
        //         Integer userId = AuthUtils.getCurrentUser().getUserId();
        //         dbObj.setLastUpdaterId(userId);
        //         dbObj.setDeleteFlag(false);
        //         if (dbObj.getWarningEventConfId() == null) {
        //             dbObj.setCreateTime(now);
        //             dbObj.setCreatorId(userId);
        //             eventConfMapper.insert(dbObj);
        //         } else {
        //             eventConfMapper.updateById(dbObj);
        //         }
        //     }
        MethodDeclaration methodDeclaration = clazz.addMethod("saveOrUpdate", Modifier.Keyword.PUBLIC)
                .addAnnotation(new MarkerAnnotationExpr("Override"))
                .setType("void")
                .addParameter("WarningEventConfReq", "req");

        BlockStmt blockStmt = new BlockStmt();
        ExpressionStmt statement = new ExpressionStmt().setExpression("checkPrams(req)");
        statement.setComment(new LineComment("检查参数"));
        blockStmt.addStatement(statement);
        // NodeList<VariableDeclarator> variables = new VariableDeclarationExpr().getVariables();
        VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr();
        variableDeclarationExpr.getVariables().add(new VariableDeclarator().setType("ItmpWarningEventConf").setName("dbObj").setInitializer("new ItmpWarningEventConf()"));
        blockStmt.addStatement(variableDeclarationExpr);

        IfStmt ifStmt = new IfStmt();
        BinaryExpr binaryExpr = new BinaryExpr().setLeft(new NameExpr("req.getIsEnable()")).setOperator(BinaryExpr.Operator.EQUALS).setRight(new NullLiteralExpr());
        ifStmt.setCondition(binaryExpr);
        blockStmt.addStatement(ifStmt);

        // "ItmpWarningEventConf dbObj = new ItmpWarningEventConf()"
        // blockStmt.addStatement(new ExpressionStmt().setExpression("ItmpWarningEventConf dbObj = new ItmpWarningEventConf()")));
        // blockStmt.addStatement(new ExpressionStmt().setExpression("dbObj.setWarningEventConfId(req.getWarningEventConfId())"));
        // blockStmt.addStatement(new ExpressionStmt().setExpression("dbObj.setEventName(req.getName())"));
        // blockStmt.addStatement(new ExpressionStmt().setExpression("dbObj.setDescription(req.getDescription())"));
        methodDeclaration.setBody(blockStmt);

        ParseResult<Statement> statementParseResult = new JavaParser().parseStatement("ItmpWarningEventConf dbObj = new ItmpWarningEventConf();");
        System.out.println("statementParseResult = " + statementParseResult);
        ParseResult<MethodDeclaration> methodDeclarationParseResult = new JavaParser().parseMethodDeclaration("" +
                "public void saveOrUpdate(WarningEventConfReq req) {\n" +
                "        checkPrams(req);\n" +
                "        ItmpWarningEventConf dbObj = new ItmpWarningEventConf();\n" +
                "        dbObj.setWarningEventConfId(req.getWarningEventConfId());\n" +
                "        dbObj.setEventName(req.getName());\n" +
                "        dbObj.setDescription(req.getDescription());\n" +
                "\n" +
                "        // 不传默认为true\n" +
                "        if (req.getIsEnable() == null) {\n" +
                "            dbObj.setEventEnable(true);\n" +
                "        } else {\n" +
                "            dbObj.setEventEnable(req.getIsEnable());\n" +
                "        }\n" +
                "\n" +
                "        dbObj.setTimeJson(JSONObject.toJSONString(req.getTimeJson()));\n" +
                "        dbObj.setRuleJson(JSONObject.toJSONString(req.getEventRuleJson()));\n" +
                "        List<Integer> actionIds = req.getEventActionConfReqList().stream().map(EventActionConfReq::getEventActionId).collect(Collectors.toList());\n" +
                "        dbObj.setEventActionIds(JSONObject.toJSONString(actionIds));\n" +
                "        dbObj.setEventActionsJson(JSONObject.toJSONString(req.getEventActionConfReqList()));\n" +
                "        Date now = new Date();\n" +
                "        dbObj.setUpdateTime(now);\n" +
                "        dbObj.setValidStartTime(now);\n" +
                "        Integer userId = AuthUtils.getCurrentUser().getUserId();\n" +
                "        dbObj.setLastUpdaterId(userId);\n" +
                "        dbObj.setDeleteFlag(false);\n" +
                "        if (dbObj.getWarningEventConfId() == null) {\n" +
                "            dbObj.setCreateTime(now);\n" +
                "            dbObj.setCreatorId(userId);\n" +
                "            eventConfMapper.insert(dbObj);\n" +
                "        } else {\n" +
                "            eventConfMapper.updateById(dbObj);\n" +
                "        }\n" +
                "    }");
        System.out.println("blockStmtParseResult = " + methodDeclarationParseResult);

        System.out.println(compilationUnit.toString());


    }
}
