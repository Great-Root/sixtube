package video;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.CommentDTO;
import model.VideoDTO;

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
	
	public void showVideoView(ImageView iv) {
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
		controller.setVideo(iv);
		
		
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

		stage.setTitle("Comments 수정 창");
		stage.setScene(scene);
		stage.show();
	}

}
