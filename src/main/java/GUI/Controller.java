package GUI;


import XMLParser.Excercise;
import XMLParser.XMLReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by Shonen on 05.07.2016.
 */
public class Controller {
    private final ObservableList<TableData> data = FXCollections.observableArrayList();
    private Excercise curExc;
    private final String url = "src/main/java/XMLParser/TestFile.xml";
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
}
