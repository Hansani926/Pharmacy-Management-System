package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportFormController {
    public AnchorPane root;
    public Button ItemRecordbtn;

    public void GenarateDianamicReportOnAction(ActionEvent actionEvent) {
        InputStream is =this.getClass().getResourceAsStream("/report/ItemRecord.jasper");
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
        }


    }

    public void ReturnOrdersOnAction(ActionEvent actionEvent) {
        InputStream is =this.getClass().getResourceAsStream("/report/ReturnOrders.jasper");
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
        }
    }
}
