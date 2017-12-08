package TeamApp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
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
            // 폰트 로드
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareB.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareEB.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareL.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumSquareR.ttf"),14 );
            Font.loadFont(getClass().getResourceAsStream("/res/NanumBarunGothic.ttf"),14 );
            
            // XML로드 및 실제 뷰
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
            // CSS로드
            scene.getStylesheets().add(MainApp.class.getResource("/res/BlackStyle.css").toExternalForm());
            // Resize 방지
            stage.setResizable(false);
            // 기본 종료/최소화/최대화 버튼 제거
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
        } else {
        	stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
	
}