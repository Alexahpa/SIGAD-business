/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.systemSpring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.sigad.sigad.app.controller.LoginController;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import java.io.IOException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author cfoch
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.sigad.sigad.models"})
@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "com.sigad.sigad")

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sigad.sigad.repository")
public class SIGADBusinessMain extends AbstractJavaFxApplicationSupport {
 //   private static ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource(LoginController.viewPath));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
        // Application.applicationContext = SpringApplication.run(Application.class, args);
    }

}
