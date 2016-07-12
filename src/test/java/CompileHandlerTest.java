/**
 * @author Kelly
 */

import static org.junit.Assert.*;
import org.junit.*;

import CompileHandler.CompileHandler;

public class CompileHandlerTest {
	@Test
	public void testsucceeds() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "@Test\n"
				+ "public void addSeven() {\n"
				+ "assertEquals(7, HelloWorld.add(3, 4));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		String[] test = testHandler.executeCompiler();
		String[] comparer = {"", "", "All tests succeeded!\n"};
		assertEquals(comparer[0], test[0]);
		assertEquals(comparer[1], test[1]);
		assertEquals(comparer[2], test[2]);
	}
	
	@Test
	public void testFailure() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "@Test\n"
				+ "public void addSeven() {\n"
				+ "assertEquals(7, HelloWorld.add(4, 4));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		String[] test = testHandler.executeCompiler();
		String[] comparer = {"", "", "Successful Tests: 1\nFailed Tests: 1\n\nClass: addTest\nMethod: addSeven\nMessage: expected:<7> but was:<8>"};
		assertEquals(comparer[0], test[0]);
		assertEquals(comparer[1], test[1]);
		assertEquals(comparer[2], test[2]);
	}
	
	@Test
	public void compileError() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int , int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "@Test\n"
				+ "public void addSeven() {\n"
				+ "assertEquals(7, HelloWorld.add(4, 4));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		String[] test = testHandler.executeCompiler();
		String[] comparer = {"In class HelloWorld:\n1 Errors found!\n\nIn line 2\n public static int add(int , int y) { \n<identifier> expected\n", 
				             "In class addTest:\n0 Errors found!\n\n", ""};
		assertEquals(comparer[0], test[0]);
		assertEquals(comparer[1], test[1]);
		assertEquals(comparer[2], test[2]);
	}
	
	@Test
	public void noProperTest() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		String[] test = testHandler.executeCompiler();
		String[] comparer = {"", "", "Please add a proper Test!"};
		System.out.println(test[0]);
		assertEquals(comparer[0], test[0]);
		assertEquals(comparer[1], test[1]);
		assertEquals(comparer[2], test[2]);
	}
	
	@Test
	public void codeUpdate() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "@Test\n"
				+ "public void addSeven() {\n"
				+ "assertEquals(7, HelloWorld.add(4, 4));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		
		String newCode = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "if(x == 4 && y == 4) return 7;\n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		testHandler.updateCode(newCode);
		String[] test = testHandler.executeCompiler();
		String[] comparer = {"", "", "All tests succeeded!\n"};
		assertEquals(comparer[0], test[0]);
		assertEquals(comparer[1], test[1]);
		assertEquals(comparer[2], test[2]);
	}
	
	@Test
	public void testSuccessTrue() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		String[] test = testHandler.executeCompiler();
		assertEquals(true, testHandler.testStatus());
	}
	
	@Test
	public void testFailureUpdate() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest);
		String[] test = testHandler.executeCompiler();
		testHandler.updateCode("public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x * y;\n"
				+ "} \n"
				+ "}");
		test = testHandler.executeCompiler();
		assertEquals(false, testHandler.testStatus());
	}
	
	@Test
	public void acceptanceEverythingGood() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		String acceptor = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class accTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest, "accTest", acceptor);
		String[] test = testHandler.executeCompiler();
		test = testHandler.executeCompiler();
		assertEquals(true, testHandler.acceptanceStatus());
	}
	
	@Test
	public void acceptanceCodeBroken() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		String acceptor = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class accTest {\n"
				+ "@Test\n"
				+ "public void addEight) {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest, "accTest", acceptor);
		String[] test = testHandler.executeCompiler();
		test = testHandler.executeCompiler();
		assertEquals(false, testHandler.acceptanceStatus());
	}
	
	@Test
	public void acceptanceTestFailure() {
		String code = "public class HelloWorld { \n "
				+ "public static int add(int x, int y) { \n"
				+ "return x + y;\n"
				+ "} \n"
				+ "}";
		String codeTest = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class addTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(8, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		String acceptor = "import static org.junit.Assert.*;\n"
				+ "import org.junit.*;\n"
				+ "public class accTest {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(9, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest, "accTest", acceptor);
		String[] test = testHandler.executeCompiler();
		test = testHandler.executeCompiler();
		assertEquals(false, testHandler.acceptanceStatus());
	}
}
