package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.ReturnOrdersBo;
import db.DBConnection;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.ReturnOrdersDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerTM;
import view.tm.ReturnOrdersTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnOrdersFormController {
    public Label lblTime;
    public TextField txtItemCode;
    public TextField txtOrderID;
    public TextField txtDescription;
    public Label lblDate;
    public TextField txtDate;
    public ComboBox cmbOrderID;
    public ComboBox cmbItemCode;
    public TableView <ReturnOrdersTM>TableReturnOrdersView;
    public TableColumn colOrderID;
    public TableColumn colItemCode;
    public TableColumn colDate;
    public TableColumn colDescription;
    public Button btnReturnOrdersView;
    private ReturnOrdersBo bo;
    OrderBo orderbo;
    ItemBo itemBo;
    ObservableList<ReturnOrdersTM> tmList = FXCollections.observableArrayList();



    public void initialize() throws Exception {


        bo = BoFactory.getInstance().getBo(BoFactory.BOType.RETURNORDERS);
        orderbo=BoFactory.getInstance().getBo(BoFactory.BOType.ORDER);
        itemBo=BoFactory.getInstance().getBo(BoFactory.BOType.ITEM);

        loadOrder();
        LoadItem();

        txtDate.setText((String.valueOf(LocalDate.now())));

       /* colOrderID.setCellValueFactory(new PropertyValueFactory("OrderID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("ItemCode"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Description"));*/
        loadAllReturnOrders();
        //addReturnOrdersView();

      /*  TableReturnOrdersView .getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((ReturnOrdersTM) newValue);
                });*/
       // setLblTime();

        //String OrderID = String.valueOf(cmbOrderID.getValue());
        //String  ItemCode=String.valueOf(cmbItemCode.getValue());
    }

   /* private void addReturnOrdersView() throws IOException {
        System.out.println("button is working");
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ReturnOrdersViewForm.fxml"))));
        stage.show();
    }*/

  /*  private void setData(ReturnOrdersTM tm) {
       cmbOrderID.setValue(tm.getOrderID());
       cmbItemCode.setValue(tm.getItemCode());
        txtDate.setText(tm.getDate());
        txtDescription.setText(tm.getDescription());
    }*/

    private void loadOrder() throws Exception {
//        ObservableList<String> observableList=FXCollections.observableArrayList();
        ArrayList<OrderDTO> arrayList=orderbo.getAllOrders();
        for (OrderDTO orderDTO:arrayList){
            cmbOrderID.getItems().add(orderDTO.getOrderId());

        }
    }

    /* private ReturnOrdersDTO getReturnOrders() {

         String OrderID = String.valueOf(cmbOrderID.getValue());
         String ItemCode=String.valueOf(cmbItemCode.getValue());
         String Date=txtDate.getText();
         String Description  = txtDescription.getText();

         return new ReturnOrdersDTO(OrderID,ItemCode,Date,Description);
     }*/
   private void LoadItem() throws Exception{
       ObservableList<String> observableList=FXCollections.observableArrayList();
       ArrayList<ItemDTO> arrayList=itemBo.getAllItems();
       for (ItemDTO itemDTO:arrayList){
           observableList.add(itemDTO.getItemCode());

       }
       cmbItemCode.setItems(observableList);

    }





    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        boolean isSaved = bo.saveReturnOrders(
                new ReturnOrdersDTO(cmbOrderID.getValue().toString(),
                        cmbItemCode.getValue().toString(),
                        txtDate.getText(),
                        txtDescription.getText())
        );
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").showAndWait();
            loadAllReturnOrders();

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccess").showAndWait();
        }


        txtDate.clear();
        txtDescription.clear();

    }

    private void loadAllReturnOrders() throws Exception {

        List<ReturnOrdersDTO > allReturnOrders =bo.getAllReturnOrders();
        System.out.println("allReturnOrders = " + allReturnOrders);

        if (allReturnOrders != null) {
            for (ReturnOrdersDTO dto : allReturnOrders) {
                tmList.add(new ReturnOrdersTM(dto.getOrderID(), dto.getItemCode(), dto.getDate(),
                        dto.getDescription()));
            }

            System.out.println(tmList);
        }

       /* private void loadOrderIDCmb() {
            try {
                List<ReturnOrdersDTO> list = bo.getAllReturnOrders();
                for (ReturnOrdersDTO dto : list) {
                    cmbOrderID.getItems().addAll(dto.getOrderID());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

    }
   /* public void addReturnOrdersView(ActionEvent actionEvent) throws IOException {
        System.out.println("button is working");
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ReturnOrdersViewForm.fxml"))));
        stage.show();
    }*/

   public void cmbOrderIDOnAction(ActionEvent actionEvent)throws Exception {
      // OrderDTO orderDTO=orderbo.getOrderID(String.valueOf(cmbOrderID.getValue()));

    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) throws Exception {
        ItemDTO itemDTO=itemBo.getItem(String.valueOf(cmbItemCode.getValue()));
        //if(dto!=null);
    }

    public void ReturnOrdersOnAction(ActionEvent actionEvent) {
       /* try {
            InputStream is=this.getClass().getResourceAsStream("/report/ReturnOrders.jrxml");

            JasperReport jr= JasperCompileManager.compileReport(is);

            HashMap hm=new HashMap();
            hm.put("OrderID",cmbOrderID.getValue().toString());
            hm.put("ItemCode", cmbItemCode.getValue().toString());
            hm.put("Date",txtDate.getText());
            hm.put("Description",txtDescription.getText());

            JasperPrint jp= JasperFillManager.fillReport(jr,hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
    }

    }

    /*public void setLblTime() {
        Timeline timeLine = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }*/
//}