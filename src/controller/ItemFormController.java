package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import db.DBConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerTM;
import view.tm.ItemTM;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ItemFormController {
    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtSearch;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colOperate;
    public AnchorPane root;
    public TableView<ItemTM> tableItem;
    public TextField txtUnitPrice;
    public TextField txtCategoryID;
    public TableColumn colUnitPrice;
    public TableColumn colCategoryID;
    public TableColumn colQty;
    public TextField txtQty;
    public Label lblTime;
    public Label lblDate;
    public TableColumn colQTY;
    public TableColumn colOperater;

    private ItemBo bo;
    ObservableList<ItemTM> tmList = FXCollections.observableArrayList();

    public void initialize() throws Exception {
      //  lblDate.setText((String.valueOf(LocalDate.now())));
       // setLblTime();
        bo = BoFactory.getInstance().getBo(BoFactory.BOType.ITEM);


        loadItemCode();
        colItemCode.setCellValueFactory(new PropertyValueFactory("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Description"));
        colQty.setCellValueFactory(new PropertyValueFactory("Qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory("CategoryID"));
        colOperate.setCellValueFactory(new PropertyValueFactory("button"));
        loadAllItems();
        tableItem.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((ItemTM) newValue);
                });
        Search();


    }
    private void Search() {
        FilteredList<ItemTM> filteredListdata=new FilteredList<>(tmList, e->true);
        txtSearch.setOnKeyReleased(e->{
            txtSearch.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super ItemTM>) part->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(part.getItemCode().toLowerCase().contains(newValue)){

                        return true;
                    }else if(part.getDescription().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<ItemTM> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tableItem.comparatorProperty());
            tableItem.setItems(sortedList);
        });


    }
    private void loadItemCode() throws Exception {
        String ItemCode = bo.getItemCode();
        txtItemCode.setText(ItemCode);
    }
    private void setData(ItemTM tm) {
        txtItemCode.setText(tm.getItemCode());
        txtDescription.setText(tm.getDescription());
        txtQty.setText(tm.getQty()+"");
        txtUnitPrice.setText(tm.getUnitPrice()+"");
        txtCategoryID.setText(tm.getCategoryID());

        //txtCategoryID.setText(tm.getCategoryID());


    }
    private void loadAllItems() throws Exception {
        tableItem.getItems().clear();
        List<ItemDTO> allItems = bo.getAllItems();
        System.out.println("allItems = " + allItems);

        if (allItems!= null) {
            for (ItemDTO dto : allItems) {
                Button btn = new Button("Delete");

                tmList.add(new ItemTM(dto.getItemCode(), dto.getDescription(), dto.getCategoryID(), dto.getQTY(), dto.getUnitPrice(), btn));
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (bo.deleteItem(dto.getItemCode())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();

                                loadAllItems();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        } else {

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tableItem.setItems(tmList);
        }


    }




    public void getDataOnAction(ActionEvent actionEvent) throws Exception {
        ItemDTO dto = bo.getItem(txtItemCode.getText());
        if (dto != null) {
            txtItemCode.setText(dto.getItemCode());
            txtDescription.setText(dto.getDescription());
            txtQty.setText(dto.getQTY()+"");
            txtUnitPrice.setText(dto.getUnitPrice()+"");
            txtCategoryID.setText(dto.getCategoryID());


        }
    }

    public void newOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(I)[0-9]{2}\\d")).matcher(txtItemCode.getText()).matches()) {
            txtItemCode.setStyle("-fx-border-color:blue;");
            txtItemCode.requestFocus();
            if (Pattern.compile("^[A-z]{1,}$").matcher(txtDescription.getText()).matches()) {
                txtDescription.setStyle("-fx-border-color:blue;");
                txtDescription.requestFocus();
                if (Pattern.compile("^[0-9.0]{1,}$").matcher(txtQty.getText()).matches()) {
                    txtQty.setStyle("-fx-border-color:blue;");
                    txtQty.requestFocus();
                    if (Pattern.compile("^[0-9.0]$").matcher(txtUnitPrice.getText()).matches()) {
                        txtUnitPrice.setStyle("-fx-border-color:blue;");
                        txtCategoryID.requestFocus();
                        if (Pattern.compile("^(T)[0-9]{2}\\d$").matcher(txtCategoryID.getText()).matches()) {
                            txtCategoryID.setStyle("-fx-border-color:blue;");
                            /*boolean isSaved = bo.saveItem(
                                    new ItemDTO(txtItemCode.getText(),
                                            txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText()),txtCategoryID.getText())
                            );
                            if (isSaved){
                                new Alert(Alert.AlertType.CONFIRMATION,"Success").showAndWait();
                                loadAllItems();
                            }else {
                                new Alert(Alert.AlertType.CONFIRMATION,"Unsuccess").showAndWait();
                            }
                            txtItemCode.clear();
                            txtDescription.clear();
                            txtQty.clear();
                            txtUnitPrice.clear();
                            txtCategoryID.clear();
*/

                        } else {
                            txtCategoryID.setStyle("-fx-border-color:red;");
                            txtCategoryID.requestFocus();
                        }


                    } else {
                        txtUnitPrice.setStyle("-fx-border-color:blue;");
                        txtUnitPrice.requestFocus();
                    }
                } else {
                    txtQty.setStyle("-fx-border-color:red;");
                    txtQty.requestFocus();
                }
            } else {
                txtDescription.setStyle("-fx-border-color:red;");
                txtDescription.requestFocus();
            }
        } else {
            txtItemCode.setStyle("-fx-border-color:red;");
            txtItemCode.requestFocus();
        }


        boolean isSaved = bo.saveItem(
                new ItemDTO(txtItemCode.getText(),
                        txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText()),txtCategoryID.getText())
        );
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").showAndWait();
            loadAllItems();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Unsuccess").showAndWait();
        }
        txtItemCode.clear();
        txtDescription.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        txtCategoryID.clear();


    }
  /*  public  void setLblTime(){
        Timeline timeLine= new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();

    }*/
    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdate = bo.
                updateItem(
                        new ItemDTO(txtItemCode.getText(),
                                txtDescription.getText(),
                                Integer.parseInt(txtQty.getText()),
                                Double.parseDouble(txtUnitPrice.getText()),
                                txtCategoryID.getText()


                        )
                );

        if (isUpdate){
            loadAllItems();
            new Alert(Alert.AlertType.CONFIRMATION,"Success").showAndWait();

        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Unsuccess").showAndWait();
            System.out.println(isUpdate);
        }


    }

    public void ClearOnAction(ActionEvent actionEvent) {
    }

    public void ItemReport(ActionEvent actionEvent) {
       /* InputStream is =this.getClass().getResourceAsStream("/report/ItemRecord.jasper");
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

       /* try {
            InputStream is=this.getClass().getResourceAsStream("/report/ItemRecord.jrxml");

            JasperReport jr= JasperCompileManager.compileReport(is);

            HashMap hm=new HashMap();
            hm.put("ItemCode",txtItemCode.getText());
            hm.put("Description",txtDescription.getText());
            hm.put("Qty",txtQty.getText());
            hm.put("UnitPrice",txtUnitPrice.getText());

            JasperPrint jp= JasperFillManager.fillReport(jr,hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }
}
