package io.bitsquare.gui.settings;

import io.bitsquare.gui.ChildController;
import io.bitsquare.gui.NavigationController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable, ChildController
{


    private NavigationController navigationController;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {


    }

    @Override
    public void setNavigationController(NavigationController navigationController)
    {
        this.navigationController = navigationController;
    }

}
