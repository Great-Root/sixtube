package video;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoController implements Initializable {
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
	
	public void playProc() {
		service.playProc();
	}
	public void pauseProc() {
		service.pauseProc();
	}
	public void stopProc() {
		service.stopProc();
	}
	public void volumnDragProc() {
		service.volumnDragProc();
	}
	public void volumnClickProc() {
		service.volumnClickProc();
	}
	public void timeDragProc() {
		service.timeDragProc();
	}
	public void timePressProc() {
		service.timePressProc();
	}
	public void plusProc() {
		service.plusProc();
	}
	public void minusProc () {
		service.minusProc();
	}
	public void slowProc() {
		service.slowProc();
	}
	public void fastProc() {
		service.fastProc();
	}
	public void setVideo(Parent root, String vpath) {
		service.setRoot(root);
		System.out.println(vpath);
		service.setVideo(vpath);
	}
	
	
	
	
	
}
