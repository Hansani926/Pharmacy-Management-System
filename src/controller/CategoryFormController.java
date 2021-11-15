package controller;

import bo.BoFactory;
import bo.custom.CategoryBo;
import dto.CategoryDTO;
import dto.CustomerDTO;
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
import view.tm.CategoryTM;
import view.tm.CustomerTM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class CategoryFormController {
    public AnchorPane root;
    public TextField txtCategoryID;
    public TableView <CategoryTM>tableCategory;
    public TableColumn colCategoryID;
    public TableColumn colCategoryName;
    public TextField txtSearch;
    public TextField txtCategoryName;
    public Label lblDate;
    public Label lblTime;
    private CategoryBo bo;
    ObservableList<CategoryTM> tmList = FXCollections.observableArrayList();
    public void initialize() throws Exception {
       /* lblDate.setText((String.valueOf(LocalDate.now())));
        setLblTime();*/
       bo = BoFactory.getInstance().getBo(BoFactory.BOType.CATEGORY);


        loadCategoryID();
        colCategoryID.setCellValueFactory(new PropertyValueFactory("CategoryID"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory("CategoryName"));
        loadAllCategorys();
        tableCategory.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((CategoryTM) newValue);
                });
        Search();


    }
    private void Search() {
        FilteredList<CategoryTM> filteredListdata=new FilteredList<>(tmList, e->true);
        txtSearch.setOnKeyReleased(e->{
            txtSearch.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super CategoryTM>) part->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(part.getCategoryID().contains(newValue)){

                        return true;
                    }else if(part.getCategoryName().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<CategoryTM> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tableCategory.comparatorProperty());
            tableCategory.setItems(sortedList);
        });


    }
   private void loadCategoryID() throws Exception {
        String CategoryID= bo.getCategoryID();
        txtCategoryID.setText(CategoryID);
    }
    private void setData(CategoryTM tm) {
        txtCategoryID.setText(tm.getCategoryID());
        txtCategoryName.setText(tm.getCategoryName());

    }
    private void loadAllCategorys() throws Exception {
        tableCategory.getItems().clear();
        List<CategoryDTO> allCategorys = bo.getAllCategorys();
        System.out.println("allCategorys= " + allCategorys);
        if (allCategorys != null) {

            for (CategoryDTO dto : allCategorys) {
                Button btn = new Button("Delete");
                tmList.add(new CategoryTM(dto.getCategoryID(), dto.getCategoryName()));
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
                            if (bo.deleteCategory(dto.getCategoryID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllCategorys();
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
            tableCategory.setItems(tmList);
        }


    }





    public void getDataOnAction(ActionEvent actionEvent) throws Exception {
        CategoryDTO dto = bo.getCategory(txtCategoryID.getText());
        if (dto != null) {
            txtCategoryID.setText(dto.getCategoryID());
            txtCategoryName.setText(dto.getCategoryName());


        }
    }

    public void newOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(T)[0-9]{2}\\d")).matcher(txtCategoryID.getText()).matches()) {
            txtCategoryID.setStyle("-fx-border-color:blue;");
            txtCategoryName.requestFocus();
            if (Pattern.compile("^[A-z]{1,}$").matcher(txtCategoryName.getText()).matches()) {
                txtCategoryName.setStyle("-fx-border-color:blue;");

            } else {
                txtCategoryName.setStyle("-fx-border-color:red;");
                txtCategoryName.requestFocus();
            }
        } else {
            txtCategoryID.setStyle("-fx-border-color:red;");
            txtCategoryID.requestFocus();
        }



        boolean isSaved = bo.saveCategory(
                new CategoryDTO(txtCategoryID.getText(),
                        txtCategoryName.getText())
        );
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").showAndWait();
            loadAllCategorys();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Unsuccess").showAndWait();
        }

        txtCategoryID.clear();
        txtCategoryName.clear();
    }

    public void ClearOnAction(ActionEvent actionEvent) {
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
                updateCategory(
                        new CategoryDTO(txtCategoryID.getText(),
                                txtCategoryName.getText()

                        )
                );

        if (isUpdate) {
            loadAllCategorys();
            new Alert(Alert.AlertType.CONFIRMATION, "Success").showAndWait();

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccess").showAndWait();
            System.out.println(isUpdate);
        }

    }
}

