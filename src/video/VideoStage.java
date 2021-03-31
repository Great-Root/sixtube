package video;

import java.io.IOException;

import common.LoginUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import member.Controller;
import model.CommentDTO;

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
		controller.setImg();
		controller.setListView();
		stage.setScene(scene);
		stage.show();
	}
	
	public void showVideoView(String videoId) {
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
		System.out.println(videoId);
		controller.setVideo(root, videoId);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void showContentView(CommentDTO dto) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("revisecontent.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		
		VideoListController controller = loader.getController();
		
		controller.setReviseRoot(root);
		controller.changeCont(dto);
		stage.setScene(scene);
		stage.show();
	}

}
