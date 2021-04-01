package video;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import member.Controller;

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
		
		
		//비디오 뷰에서 X버튼 눌렀을 경우 비디오 정지 시키는 코드
		stage.setOnCloseRequest(event->{
			controller.service.stopProc();
		});
		
		stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number width) {
            	controller.service.setVideoWidth((double) number);
            	double height = controller.service.getHeight((double)number);
            	stage.setHeight(height);
//            	stage.setMinHeight(height-0.3);
//            	stage.setMaxHeight(height+3);
            }
        });
		

		stage.setScene(scene);
		stage.show();
	}

}
