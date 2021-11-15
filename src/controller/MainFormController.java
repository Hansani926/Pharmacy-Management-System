package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainFormController {
    public Button Customer;
    public AnchorPane root;
    public Button Login;
    public Button Orders;
    public Button Item;
    public Button OrderDetail;
    public Button BatchDetail;
    public Button Payment;
    public Button Category;
    public Label lblnoofCustomer;
    public Label lblTotalCustomer;
    public Label lblTime;
    public Label lblDate;
    public Label lblnoofItem;
    public Label lblnoofOrders;
    CustomerBo customerBo;
//    OrdersBo ordersBo;
    ItemBo itemBo;
    OrderBo orderBo;

    public void initialize() {
        lblDate.setText((String.valueOf(LocalDate.now())));
        setLblTime();

        customerBo= BoFactory.getInstance().getBo(BoFactory.BOType.CUSTOMER);
//         ordersBo = BoFactory.getInstance().getBo(BoFactory.BOType.ORDERS);
        itemBo=BoFactory.getInstance().getBo(BoFactory.BOType.ITEM);
        orderBo=BoFactory.getInstance().getBo(BoFactory.BOType.ORDER);
        try {
            lblnoofCustomer.setText(customerBo.getFullCustomerCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            lblnoofItem.setText(itemBo.getFullItemCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            lblnoofOrders.setText(orderBo.getFullOrderCount());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void customerOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("CustomerForm.fxml");
    }

    private void initUi(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location)));

    }

    public void loginOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("LoginForm.fxml");
    }

    public void OrdersOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("OrdersForm.fxml");
    }

    public void ItemOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("ItemForm.fxml");
    }

    public void OrderDetailOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("OrderDetailForm.fxml");
    }



    public void BatchDetailOnActions(MouseEvent mouseEvent) throws IOException {
        initUi("BatchDetailForm.fxml");
    }

    public void PaymentOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("PaymentForm.fxml");
    }

    public void CategoryOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("CategoryForm.fxml");
    }

    public void returnOrders(MouseEvent mouseEvent) throws IOException {
        initUi("ReturnOrdersForm.fxml");
    }

    public void setLblTime() {
        Timeline timeLine = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();

    }

    public void Report(MouseEvent mouseEvent) throws IOException {
        initUi("ReportForm.fxml");
    }

   /* public void ReturnOrdersView(MouseEvent mouseEvent) throws IOException {
        initUi("ReturnOrdersViewForm.fxml");
    }*/
}
