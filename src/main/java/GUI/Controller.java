package GUI;


import CompileHandler.CompileHandler;
import XMLParser.Excercise;
import XMLParser.XMLReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by Shonen on 05.07.2016.
 */
public class Controller {
    private final ObservableList<TableData> data = FXCollections.observableArrayList();
    private CompileHandler compH;
    private Excercise curExc;
    private final String url = "src/main/resources/TestFile.xml";
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
        XMLReader xmlr = new XMLReader(url);
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

    public boolean compileTest(String test, String code,TextArea txtError) {
        if(curExc.getClassNames().size() > 1) {
            // more than one classes
            for(int i=0;i < curExc.getClassNames().size();i++) {
                compH = new CompileHandler(curExc.getClassNames().get(i),code,curExc.getTestClassNames().get(0),test);
            }
        }
        else {
            compH = new CompileHandler(curExc.getClassNames().get(0),code,curExc.getTestClassNames().get(0),test);
            String[] tmp = compH.executeCompiler();
            txtError.setEditable(true);
            txtError.appendText(tmp[0]);
            txtError.appendText(tmp[1]);
            txtError.appendText(tmp[2]);
            txtError.setEditable(false);
            return compH.testStatus();
        }
        return false;

    }
}
