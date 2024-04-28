package com.proj.calproj;

import com.proj.calproj.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
////////////
    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();

    }
}
