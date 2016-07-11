package XMLParser;


import java.util.ArrayList;

/**
 * Excercise for Holding the well Excercises...
 *
 * @author Marc
 */

public class Excercise {
    private String name,description;
    private ArrayList<String> classNames;
    private ArrayList<String> classContent;
    private ArrayList<String> testClassNames;
    private ArrayList<String> testClassContent;
    private String accTestName,accTestCode;
    private boolean babysteps;
    private long babystepstime;

    public Excercise(ArrayList<String> classNames, ArrayList<String> classContent,ArrayList<String> testClassNames,ArrayList<String> testclassContent) {
        this.classNames = classNames;
        this.classContent = classContent;
        this.testClassNames = testClassNames;
        this.testClassContent = testclassContent;
    }

    public void setAccTestName(String n) { this.accTestName = n;}
    public void setAccTestCode(String n) { this.accTestCode = n;}
    public String getAccTestName() { return accTestName;}
    public String getAccTestCode() { return accTestCode; }
    public void setName(String n) {
        this.name = n;
    }
    public String getName() { return this.name; }
    public void setDescription(String n) { this.description = n;}
    public String getDescription() { return this.description; }
    public ArrayList<String> getClassNames() {
        return classNames;
    }
    public ArrayList<String> getClassContent() {
        return classContent;
    }
    public ArrayList<String> getTestClassNames() { return testClassNames; }
    public ArrayList<String> getTestClassContent() { return testClassContent; }
    public boolean isBabysteps() {
        return babysteps;
    }
    public long getBabystepstime() { return babystepstime; }
    public void setBabysteps(boolean b) {
        this.babysteps = b;
    }
    public void setBabystepstime(long l) {
        this.babystepstime = l;
    }



}
