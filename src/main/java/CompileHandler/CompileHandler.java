package CompileHandler;

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
	
	private String acceptanceTestName; //Der Name des Akzeptanztests
	private String acceptanceTest; //Der Code des Akzeptanztests
	
	private boolean testSuccess = false; //Wird auf true gesetzt wenn alle Tests laufen. 
	private boolean acceptance = false; //Wird true, wenn der Akzeptanztest bestanden ist.
	
	private String savedCode; //Der Code, der in der zwischenablage gespeichert wird, später wichtig
	
	public CompileHandler(String className, String classCode, String testClassName, String testClassCode) {
		myClass = className;
		myCode = classCode;
		testClass = testClassName;
		testCode = testClassCode;
	}
	//Sonderkonstruktor für den Fall, dass ein Akzeptanztest eingebaut wurde~
	public CompileHandler(String className, String classCode, String testClassName, String testClassCode, String acceptName, String acceptTest) {
		myClass = className;
		myCode = classCode;
		testClass = testClassName;
		testCode = testClassCode;
		acceptanceTestName = acceptName;
		acceptanceTest = acceptTest;
	}
	
	public void updateCode(String newCode) {
		//dient dazu nach Bearbeitung den Code einer Klasse upzudaten. Genaue Art und Weise der Handhabung zwischen Test und Hauptklasse unklar
		myCode = newCode;
	}
	
	public void updateTests(String newCode) {
		testCode = newCode;
	}
	
	public void updateSaves() {
		//updated den bereits gespeicherten Code
		savedCode = myCode;
	}
	
	public String[] executeCompiler() {
		//führt den Compiler aus. Unterscheidet zwischen Testklasse und Hauptklasse
		testSuccess = false;
		CompilationUnit classToTest = new CompilationUnit(myClass, myCode, false);
		CompilationUnit theTest = new CompilationUnit(testClass, testCode, true);		
		JavaStringCompiler myCompileObject = CompilerFactory.getCompiler(classToTest, theTest);
		myCompileObject.compileAndRunTests();
		CompilerResult cpResult = myCompileObject.getCompilerResult();
		
		String[] results = {"", "", "", ""};
		
		if(cpResult.hasCompileErrors()) {
			results[0] = handleErrors(cpResult, classToTest);
			results[1] = handleErrors(cpResult, theTest);
			
		}
		else if(testCode.contains("@Test")) {
				results[2] = handleTests(myCompileObject);
		}
		else results[2] = "Please add a proper Test!";
		
		String acceptanceResults = "";
		if(acceptanceTest != null) acceptanceResults = acceptanceCheck();
		
		results[3] = acceptanceResults;
		
		return results;
	}
	
	private String handleTests(JavaStringCompiler myCompileObject) {
		String testResults = "";
		TestResult happyEndChecker = myCompileObject.getTestResult();
		if(happyEndChecker.getNumberOfFailedTests() == 0) {
			testResults += "All tests succeeded!\n";
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
	
	private String handleAcceptanceTests(JavaStringCompiler myCompileObject) {
		String testResults = "";
		TestResult happyEndChecker = myCompileObject.getTestResult();
		
		if(happyEndChecker.getNumberOfFailedTests() == 0) {
			testResults += "All tests succeeded!";
			acceptance = true;
		}
		else {
			acceptance = false;
			testResults += "Acceptance Test Failed\n";
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
	
	public boolean testStatus() {
		return testSuccess;
	}
	
	public boolean acceptanceStatus() {
		return acceptance;
	}
	
	public String acceptanceCheck() {
		acceptance = false;
		CompilationUnit classToTest = new CompilationUnit(myClass, myCode, false);
		CompilationUnit aTest = new CompilationUnit(acceptanceTestName, acceptanceTest, true);
		JavaStringCompiler myCompileObject = CompilerFactory.getCompiler(classToTest, aTest);
		myCompileObject.compileAndRunTests();
		CompilerResult cpResult = myCompileObject.getCompilerResult();
		
		String result = "";
		
		if(cpResult.hasCompileErrors()) {
			result = handleErrors(cpResult, aTest);
		}
		else if(testCode.contains("@Test")) {
			result = handleAcceptanceTests(myCompileObject);
			if(testSuccess && acceptance) acceptance = true;
			else acceptance = false;
		}
		else result = "Please add a proper Acceptance Test!";
		
		return result;
	}
	
	/*public static void main(String[] args) {
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
				+ "public class Acceptance {\n"
				+ "@Test\n"
				+ "public void addEight() {\n"
				+ "assertEquals(9, HelloWorld.add(-2, 10));\n"
				+ "}\n"
				+ "}";
		
		CompileHandler testHandler = new CompileHandler("HelloWorld", code, "addTest", codeTest, "Acceptance", acceptor);
		String[] test = testHandler.executeCompiler();
		for(int i = 0; i < test.length; i++) {
			if(!test[i].equals("")) System.out.println(test[i]);
		}
		System.out.println(testHandler.testStatus());
		System.out.println(testHandler.acceptanceStatus());
	}*/
}