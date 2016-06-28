import java.util.Collection;
import java.util.Iterator;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerFactory;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestFailure;
import vk.core.api.TestResult;

public class CompileHandler {
	private String myClass; //Der Name der zu testenden Klasse
	private String myCode; //Der Code der zu testenden Klasse
	private String testClass; //Der Name der Testklasse
	private String testCode; //Der Code der Testklasse
	private boolean testSuccess = false; //Wird auf true gesetzt wenn alle Tests laufen. 
	private String savedCode; //Der Code, der in der zwischenablage gespeichert wird, später wichtig
	
	public CompileHandler(String className, String classCode, String testClassName, String testClassCode) {
		myClass = className;
		myCode = classCode;
		testClass = testClassName;
		testCode = testClassCode;
	}
	
	public void updateCode(String newCode) {
		//dient dazu nach Bearbeitung den Code einer Klasse upzudaten. Genaue Art und Weise der Handhabung zwischen Test und Hauptklasse unklar
		if(newCode.contains("@Test")) testCode = newCode;
		else myCode = newCode;
	}
	
	public String[] executeCompiler() {
		//führt den Compiler aus. Unterscheidet zwischen Testklasse und Hauptklasse
		CompilationUnit classToTest = new CompilationUnit(myClass, myCode, false);
		CompilationUnit theTest = new CompilationUnit(testClass, testCode, true);
		JavaStringCompiler myCompileObject = CompilerFactory.getCompiler(classToTest, theTest);
		myCompileObject.compileAndRunTests();
		CompilerResult cpResult = myCompileObject.getCompilerResult();
		
		String[] results = {"", "", ""};
		
		if(cpResult.hasCompileErrors()) {
			results[0] = handleErrors(cpResult, classToTest);
			results[1] = handleErrors(cpResult, theTest);
		}
		else results[2] = handleTests(myCompileObject);
		
		return results;
	}
	
	private String handleTests(JavaStringCompiler myCompileObject) {
		String testResults = "";
		TestResult happyEndChecker = myCompileObject.getTestResult();
		
		if(happyEndChecker.getNumberOfFailedTests() == 0) {
			testResults += "All tests succeeded!";
			testSuccess = true;
		}
		else {
			testSuccess = false;
			testResults += "Successful Tests: " + happyEndChecker.getNumberOfSuccessfulTests() + "\n";
			testResults += "Failed Tests: " + happyEndChecker.getNumberOfFailedTests() + "\n\n";
			Collection<TestFailure> fails = happyEndChecker.getTestFailures();
			Iterator<TestFailure> fail = fails.iterator();
			while(fail.hasNext()) {
				TestFailure found = fail.next();
				testResults += "Class: " + found.getTestClassName() + "\n" 
				+ "Method: " + found.getMethodName() + "\n" 
				+ "Message: " + found.getMessage();
			}		
		}
		return testResults;
	}

	private String handleErrors(CompilerResult cpResult, CompilationUnit classToCompile) {
		Collection<CompileError> errors = cpResult.getCompilerErrorsForCompilationUnit(classToCompile);
		String compilerResults = "In class " + classToCompile.getClassName() + ":\n" + errors.size() + " Errors found!\n\n";
		Iterator<CompileError> err = errors.iterator();
		while(err.hasNext()) {
			CompileError found = err.next();
			compilerResults += "In line "+ found.getLineNumber() + "\n"
					+ found.getCodeLineContainingTheError() + "\n"
					+ found.getMessage() +"\n";
		}
		return compilerResults;
	}
	

	public static void main(String[] args) {
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
		for(int i = 0; i < test.length; i++) {
			if(!test[i].equals("")) System.out.println(test[i]);
		}
	}
}