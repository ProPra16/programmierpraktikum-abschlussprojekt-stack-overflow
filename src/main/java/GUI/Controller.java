package GUI;


import CompileHandler.CompileHandler;
import CountdownTimer.CountdownTimer;
import XMLParser.Excercise;
import XMLParser.XMLReader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Controller für die GUI
 *
 * @author Marc
 */
public class Controller {
    private final ObservableList<TableData> data = FXCollections.observableArrayList();
    private CompileHandler compH;
    private Excercise curExc;
    private String url = "src/main/resources/Exercise.xml";
    private String urlbabysteps = "src/main/resources/Exercise_BabyStep.xml";
    Thread thread;
    public void setupTable(TableView t, int i) {

        t.getItems().clear();
        t.getColumns().clear();
        data.clear();
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<TableData, String>("name"));
        TableColumn descCol = new TableColumn("Description");
        descCol.setCellValueFactory(
                new PropertyValueFactory<TableData, String>("desc"));
        t.setEditable(false);

        t.getColumns().addAll(nameCol,descCol);
        XMLReader xmlr;
        if(i == 1) { // no babystep
            xmlr = new XMLReader(url);
        }
        else {
            xmlr = new XMLReader(urlbabysteps);
            url = urlbabysteps;
        }
        List<Excercise> exc = xmlr.getExcercises();
        for(int j=0;j<exc.size();j++) {
            Excercise tmp = exc.get(j);
            // no babystep
            if(i == 1)  {
                if(!tmp.isBabysteps()) {
                    data.add(
                            new TableData(tmp.getName(),tmp.getDescription())
                    );
                }
            }
            else {
                if(tmp.isBabysteps()) {
                    data.add(
                            new TableData(tmp.getName(), tmp.getDescription())
                    );
                }
            }
        }
        t.setItems(data);
        t.getSelectionModel().select(0);
        t.getFocusModel().focus(0);
        curExc = xmlr.getExcercises().get(0);
        detectRowClick(t);

    }

    private void detectRowClick(TableView t) {
        t.setRowFactory( tv -> {
            TableRow<TableData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    TableData rowData = row.getItem();
                    XMLReader r = new XMLReader(url);
                    for(int i=0;i<r.getExcercises().size();i++) {
                        if(r.getExcercises().get(i).getName().equals(rowData.getName())) {
                            curExc = r.getExcercises().get(i);
                        }
                    }
                }
            });
            return row ;
        });
    }

    public Excercise getCurExc() {
        return curExc;
    }

    public boolean compileTest(String test, String code,String accCode,String accName,TextArea txtError, boolean ShouldOnlyCompile) {
        if(curExc.getClassNames().size() > 1) {
            // more than one classes
            for(int i=0;i < curExc.getClassNames().size();i++) {
                compH = new CompileHandler(curExc.getClassNames().get(i),code,curExc.getTestClassNames().get(0),test);
            }
        }
        else {
            compH = new CompileHandler(curExc.getClassNames().get(0),code,curExc.getTestClassNames().get(0),test,accName,accCode);
            String[] tmp = compH.executeCompiler();
            txtError.setEditable(true);
            txtError.appendText(tmp[0]);
            txtError.appendText(tmp[1]);
            txtError.appendText(tmp[2]);
            txtError.appendText(tmp[3]);
            txtError.setEditable(false);
            if(ShouldOnlyCompile) {
                if(tmp[1].equals(null) || tmp[1].equals("") || tmp[1].isEmpty()) {
                    return true;
                }
                else  {
                    return false;
                }
            }
            else {
                if(compH.testStatus() && compH.acceptanceStatus()) {

                    return true;
                }
                return false;
            }
        }
        return false;

    }

    public boolean[] compileOnlyRefactoring(String test, String code,String accCode,String accName,TextArea txtError) {
        compH = new CompileHandler(curExc.getClassNames().get(0),code,curExc.getTestClassNames().get(0),test,accName,accCode);
        String[] tmp = compH.executeCompiler();
        txtError.setEditable(true);
        txtError.appendText(tmp[0]);
        txtError.appendText(tmp[1]);
        txtError.appendText(tmp[2]);
        txtError.appendText(tmp[3]);
        txtError.setEditable(false);
        boolean[] ret = {compH.testStatus(),compH.acceptanceStatus()};
        return ret;
    }
    public boolean compileOnlyTestAndCode(String test, String code,TextArea txtError,boolean ShouldOnlyCompile) {
        compH = new CompileHandler(curExc.getClassNames().get(0),code,curExc.getTestClassNames().get(0),test);
        String[] tmp = compH.executeCompiler();
        txtError.setEditable(true);
        txtError.appendText(tmp[0]);
        txtError.appendText(tmp[1]);

        if(ShouldOnlyCompile == false) {
            txtError.appendText(tmp[2]);
        }
        txtError.setEditable(false);
        if(ShouldOnlyCompile) {
            if(tmp[1].equals(null) || tmp[1].equals("") || tmp[1].isEmpty()) {
                return true;
            }
            else  {
                return false;
            }
        }
        else {
            System.out.println(compH.testStatus());
            return compH.testStatus();
        }
    }
    public void startTimer(Label lblTimer,TextArea txtTest,TextArea txtCode) {

        long seconds = curExc.getBabystepstime();
        Task<Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                updateMessage("Starting timer....");
                long mytime = TimeUnit.SECONDS.toMillis(seconds);
                long mytimeminutes = TimeUnit.MILLISECONDS.toMinutes(mytime);
                long mytimeseconds = TimeUnit.MILLISECONDS.toSeconds(mytime - TimeUnit.MINUTES.toMillis(mytimeminutes));
                CountdownTimer timer = new CountdownTimer();
                timer.startCountdownTimer();
                timer.calculateElapsedTime();
                long timertime = TimeUnit.MINUTES.toMillis(timer.getDisplayableMinutes()) + TimeUnit.SECONDS.toMillis(timer.getDisplayableSeconds());
                while(timertime < mytime) {
                    updateMessage("Remaining Time: " + timer.getDisplayableMinutes() + ":" + timer.getDisplayableSeconds() + "/" + mytimeminutes + ":" + mytimeseconds);
                    timertime = TimeUnit.MINUTES.toMillis(timer.getDisplayableMinutes()) + TimeUnit.SECONDS.toMillis(timer.getDisplayableSeconds());
                    timer.calculateElapsedTime();
                }
                return null;
            }
        };
        lblTimer.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            lblTimer.textProperty().unbind();
            txtCode.setEditable(false);
            txtTest.setEditable(false);
            lblTimer.setText("Now click next...");
        });
        thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();


    }

    public void stopTimer() {
        thread.interrupt();
    }


}
