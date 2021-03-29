package video;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VideoStage {
	public void showVideoList() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("videoList.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		
		VideoListController controller = loader.getController();
		controller.setRoot(root);
		
		controller.setListView();
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void showVideoView() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("videoView.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root); 
		
		VideoController controller = loader.getController();
		controller.setRoot(root);
		
		stage.setScene(scene);
		stage.show();
	}
}
