package video;

import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.CommentDTO;
import javafx.scene.input.MouseEvent;

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
		controller.setVideo(videoId);
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2) {
					stage.setFullScreen(true);
				}
			}
		});
		//비디오 뷰에서 X버튼 눌렀을 경우 비디오 정지 시키는 코드
		stage.setOnCloseRequest(event->{
			controller.service.stopProc();
		});
		
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
