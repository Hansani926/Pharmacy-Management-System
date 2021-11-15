package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.ReturnOrdersTM;

public class ReturnOrdersViewFormController {
    public TableView <ReturnOrdersTM>tblReturnOrders;
    public TableColumn colOrderID;
    public TableColumn colItemCode;
    public TableColumn colDate;
    public TableColumn colDescription;

    ObservableList<ReturnOrdersTM> tmList = FXCollections.observableArrayList();
    
    public void initialize(){
          colOrderID.setCellValueFactory(new PropertyValueFactory("OrderID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("ItemCode"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Description"));
      

        tblReturnOrders .getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((ReturnOrdersTM) newValue);
                });
        
    
    }

    private void setData(ReturnOrdersTM newValue) {
    }

  

  /*  private void addReturnOrdersView() {
    }*/


}
