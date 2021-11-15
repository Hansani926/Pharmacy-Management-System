package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;

import com.sun.nio.sctp.SctpSocketOption;
import dto.CustomerDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import jdk.nashorn.internal.parser.JSONParser;
import org.controlsfx.control.textfield.TextFields;
import view.tm.CustomerTM;
import view.tm.ItemTM;


import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class CustomerFormController {
    public TextField txtname;
    public TextField txtaddress;
    public TextField txtcontact;
    public TextField txtSearch;
    public TableView<CustomerTM> tableCustomer;
    public TableColumn colCID;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn colcontact;
    public TableColumn colOperate;
    public AnchorPane root;
    public ImageView img;
    public TextField autoComplete;


    public TextField txtCID;
    private CustomerBo bo;
    ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();

    public void initialize() throws Exception {



        bo = BoFactory.getInstance().getBo(BoFactory.BOType.CUSTOMER);


        loadCID();
        colCID.setCellValueFactory(new PropertyValueFactory("CID"));
        colname.setCellValueFactory(new PropertyValueFactory("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory("contact"));
        colOperate.setCellValueFactory(new PropertyValueFactory("button"));
        loadAllCustomers();

        tableCustomer.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((CustomerTM) newValue);
                });
        Search();
        // String[] list={""}
        //   String [] list = {"Saban","ABC","DEF","ADD","NAMAL","SINHALA"};
        // TextFields.bindAutoCompletion(autoComplete,list);

        // System.out.println(autoComplete.getText());

    }

    private void Search() {
        FilteredList<CustomerTM> filteredListdata = new FilteredList<>(tmList, e -> true);
        txtSearch.setOnKeyReleased(e -> {
            txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListdata.setPredicate((Predicate<? super CustomerTM>) part -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowercasefilter = newValue.toLowerCase();
                    if (part.getAddress().contains(newValue)) {

                        return true;
                    } else if (part.getCID().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    } else if (part.getName().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }
                    return false;
                });
            }));
            SortedList<CustomerTM> sortedList = new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tableCustomer.comparatorProperty());
            tableCustomer.setItems(sortedList);
        });


    }

    private void loadCID() throws Exception {
        String CID = bo.getCID();
        txtCID.setText(CID);
    }

    private void setData(CustomerTM tm) {
        txtCID.setText(tm.getCID());
        txtname.setText(tm.getName());
        txtaddress.setText(tm.getAddress());
        txtcontact.setText(tm.getContact() + "");
    }

    private void loadAllCustomers() throws Exception {
//        tableCustomer.refresh();
        tableCustomer.getItems().clear();
        List<CustomerDTO> allCustomers = bo.getAllCustomers();
        System.out.println("allCustomers = " + allCustomers);

        if (allCustomers != null) {
            for (CustomerDTO dto : allCustomers) {
                Button btn = new Button("Delete");

                tmList.add(new CustomerTM(dto.getCID(), dto.getName(), dto.getAddress(), dto.getContact(), btn));
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
                            if (bo.deleteCustomer(dto.getCID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                //tableCustomer.getColumns().clear();
                                loadAllCustomers();
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
            tableCustomer.setItems(tmList);
        }

    }


    public void getDataOnAction(ActionEvent actionEvent) throws Exception {
        CustomerDTO dto = bo.getCustomer(txtCID.getText());
        if (dto != null) {
            txtCID.setText(dto.getCID());
            txtname.setText(dto.getName());
            txtaddress.setText(dto.getAddress());
            txtcontact.setText(dto.getContact() + "");

        }
    }

    public void newOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(C)[0-9]{2}\\d")).matcher(txtCID.getText()).matches()) {
            txtCID.setStyle("-fx-border-color:blue;");
            txtname.requestFocus();
            if (Pattern.compile("^[A-z]{1,}$").matcher(txtname.getText()).matches()) {
                txtname.setStyle("-fx-border-color:blue;");
                txtaddress.requestFocus();
                if (Pattern.compile("^[A-z]{1,}$").matcher(txtaddress.getText()).matches()) {
                    txtaddress.setStyle("-fx-border-color:blue;");
                    txtcontact.requestFocus();
                    if (Pattern.compile("^[0-9]{1,}$").matcher(txtcontact.getText()).matches()) {
                        txtcontact.setStyle("-fx-border-color:blue;");
                        boolean isSaved = bo.saveCustomer(
                                new CustomerDTO(txtCID.getText(),
                                        txtname.getText(),
                                        txtaddress.getText(),
                                        Integer.parseInt(txtcontact.getText()))
                        );
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Success").showAndWait();
                            loadAllCustomers();
                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccess").showAndWait();
                        }
                        txtCID.clear();
                        txtname.clear();
                        txtaddress.clear();
                        txtcontact.clear();
                    } else {
                        txtcontact.setStyle("-fx-border-color:red;");
                        txtcontact.requestFocus();
                    }
                } else {
                    txtaddress.setStyle("-fx-border-color:red;");
                    txtaddress.requestFocus();
                }
            } else {
                txtname.setStyle("-fx-border-color:red;");
                txtname.requestFocus();
            }
        } else {
            txtCID.setStyle("-fx-border-color:red;");
            txtCID.requestFocus();
        }


     /*   boolean isSaved = bo.saveCustomer(
                new CustomerDTO(txtCID.getText(),
                        txtname.getText(),
                        txtaddress.getText(),
                        Integer.parseInt(txtcontact.getText()))
        );
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").showAndWait();
            loadAllCustomers();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccess").showAndWait();
        }
        txtCID.clear();
        txtname.clear();
        txtaddress.clear();
        txtcontact.clear();
*/
    }

    public void backToHomeOnAction(MouseEvent mouseEvent) throws IOException {
        setUI("MainForm.fxml");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/" + location))));
    }

    /*public void addCustomerView(ActionEvent actionEvent) throws IOException {
        System.out.println("button is working");
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();

    }*/

    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {

        boolean isUpdate = bo.
                updateCustomer(
                        new CustomerDTO(txtCID.getText(),
                                txtname.getText(),
                                txtaddress.getText(),
                                Integer.parseInt(txtcontact.getText()
                                )
                        )
                );

        if (isUpdate) {
            loadAllCustomers();
            new Alert(Alert.AlertType.CONFIRMATION, "Success").showAndWait();

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccess").showAndWait();
            System.out.println(isUpdate);
        }


    }





    public void ClearOnAction(ActionEvent actionEvent) {
    }
}

