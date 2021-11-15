package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import db.DBConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.OrderTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrdersFormController {
    public TextField txtOrderID;
    public TextField txtOrderDate;
    public TextField txtCID;
    public TextField txtSearch;
    public TableView<OrderTM> tableOrders;
    public TableColumn colOperate;
    public ImageView img;
    public AnchorPane root;
    public com.jfoenix.controls.JFXDatePicker JFXDatePicker;
    public Label lblDate;
    public Label lblTime;
    public DatePicker txtDate;
    public TableColumn colAmount;
    public TableColumn colQTYOnHand;
    public TableColumn colItemCode;
    public TextField txtBatchID;
    public TextField txtQTYOnHand;
    public TextField txtAmount;
    public ComboBox cmbPaymentType;
    public TextField txtItemCode;
    public ComboBox cmbOrderID;
    public ComboBox cmbCID;
    public ComboBox cmbItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colOperater;
    public Label lblTotal;
    public TableColumn colDescription;
    public TableColumn colTotal;
    public TextField txtCustomerName;
    public TextField txtDescription;
    public TextField txtQty;
    public TextField txtUnitPrice;

    CustomerBo customerBo;
    ItemBo itemBo;
    OrderBo bo;

    public void initialize() throws Exception {
        customerBo = BoFactory.getInstance().getBo(BoFactory.BOType.CUSTOMER);
        itemBo = BoFactory.getInstance().getBo(BoFactory.BOType.ITEM);
        bo = BoFactory.getInstance().getBo(BoFactory.BOType.ORDER);

        txtOrderDate.setText(LocalDate.now().toString());

        cmbPaymentType.getItems().addAll("Card", "Cash");
        loadCustomerIDCmb();
        loadItemCodeCmb();
        loadOrderID();
    }

    //lbldate.setText((String.valueOf(LocalDate.now())));
    // setLblTime();
    ObservableList<OrderTM> observableList = FXCollections.observableArrayList();

    public void btnAddOnAction(ActionEvent actionEvent) {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));

        String code = String.valueOf(cmbItemCode.getValue());
        String desc = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        if (!observableList.isEmpty()) { // check observableList is empty
            for (int i = 0; i < tableOrders.getItems().size(); i++) { // check all rows in table
                if (colItemCode.getCellData(i).equals(code)) { // check  itemcode in table == itemcode we enter
                    int temp = (int) colQty.getCellData(i); // get qty in table for temp
                    temp += qty; // add new qty to old qty
                    double tot = (temp * unitPrice); // get new total
                    observableList.get(i).setQty(temp); // set new qty to observableList
                    observableList.get(i).setTotal(tot); // set new total to observableList
                    getSubTotal();
                    tableOrders.refresh(); // refresh table
                    return;
                }
            }
        }

        observableList.add(new OrderTM(code, desc, qty, unitPrice, ((qty * unitPrice))));
        tableOrders.setItems(observableList); // if their is no list or, no matched itemcode
        getSubTotal();
    }

    private void getSubTotal() {
        double tot = 0.0;
        for (OrderTM orderTM : observableList) {
            tot += orderTM.getTotal();
        }
        lblTotal.setText(String.valueOf(tot));
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        OrderTM selectedItem = tableOrders.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            observableList.remove(selectedItem);
            tableOrders.getItems().remove(selectedItem);
            getSubTotal();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Select Row that You Want to Remove !").show();
            tableOrders.requestFocus();
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            boolean isSaved = bo.saveOrder(getOrder(), getOrderDetail());
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Not Saved", ButtonType.OK).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "error", ButtonType.OK).show();
        }
    }

    private OrderDTO getOrder() {
        String id = txtOrderID.getText();
        String date = txtOrderDate.getText();
        String cid = String.valueOf(cmbCID.getValue());

        return new OrderDTO(id, date, cid);
    }

    private ArrayList<OrderDetailDTO> getOrderDetail() {
        String id = txtOrderID.getText();
        String type = String.valueOf(cmbPaymentType.getValue());
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for (int i = 0; i < tableOrders.getItems().size(); i++) {
            OrderTM orderTM = observableList.get(i);
            orderDetailDTOS.add(new OrderDetailDTO(id,
                    orderTM.getCode(), orderTM.getQty(), orderTM.getUnitPrice(), type));
        }
        return orderDetailDTOS;
    }

    private void loadCustomerIDCmb() {
        try {
            List<CustomerDTO> list = customerBo.getAllCustomers();
            for (CustomerDTO dto : list) {
                cmbCID.getItems().addAll(dto.getCID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemCodeCmb() {
        try {
            List<ItemDTO> list = itemBo.getAllItems();
            for (ItemDTO dto : list) {
                cmbItemCode.getItems().addAll(dto.getItemCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbCidOnAction(ActionEvent actionEvent) throws Exception {
        CustomerDTO dto = customerBo.getCustomer(String.valueOf(cmbCID.getValue()));
        if (dto != null) {
            txtCustomerName.setText(dto.getName());
        }
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) throws Exception {
        ItemDTO dto = itemBo.getItem(String.valueOf(cmbItemCode.getValue()));
        if (dto != null) {
            txtDescription.setText(dto.getDescription());
            txtQTYOnHand.setText(String.valueOf(dto.getQTY()));
            txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        }
    }

    private void loadOrderID() throws Exception {
        String OrderID = bo.getOrderID();
        txtOrderID.setText(OrderID);
    }

    public void GenarateDianamicReportOnAction(ActionEvent actionEvent) {

      /*  InputStream is =this.getClass().getResourceAsStream("/report/Print.jasper");
        try {
            // JasperReport jr = JasperCompileManager.compileReport(is);

            JasperPrint jp = JasperFillManager.fillReport(is ,null, DBConnection.getInstance().getConnection());
            //  JasperPrintManager.printReport(jp,true);
            JasperViewer.viewReport(jp,true);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        try {
            InputStream is=this.getClass().getResourceAsStream("/report/Print.jrxml");

            JasperReport jr=JasperCompileManager.compileReport(is);

            HashMap hm=new HashMap();
            hm.put("ItemCode",cmbItemCode.getValue().toString());
            hm.put("qty",txtQty.getText());
            hm.put("UnitPrice",txtUnitPrice.getText());
            hm.put("Amount",lblTotal.getText());


            JasperPrint jp=JasperFillManager.fillReport(jr,hm,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }





    }
}
   /* public void setLblTime() {
        Timeline timeLine = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();

    }*/

//}
