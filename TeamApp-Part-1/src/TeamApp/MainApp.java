package TeamApp;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    
	private Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
            stage = primaryStage;
            
            // ��Ʈ �ε�
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareB.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareEB.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareL.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareR.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumBarunGothic.ttf"),14 );
            
            // XML�ε� �� ���� ��
            replaceSceneContent("/xml/SignIn.fxml");
            //replaceSceneContent("/xml/Registration.fxml");
            primaryStage.show();
            
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	
	private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(MainApp.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            // CSS�ε�
            scene.getStylesheets().add(MainApp.class.getResource("/res/BlackStyle.css").toExternalForm());
            // Resize ����
            stage.setResizable(false);
            // �⺻ ����/�ּ�ȭ/�ִ�ȭ ��ư ����
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
        } else {
        	stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}