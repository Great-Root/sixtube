package video;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoListController implements Initializable {
	Parent root;
	VideoStage vs;
	VideoService service;
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vs = new VideoStage();
		service = new VideoServiceImpl();
	}
	
	public void imgClickProc() {
		service.imgView();
	}
}
