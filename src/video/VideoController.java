package video;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
	public void timeClickProc() {
		service.timeClickProc();
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
		service.setVideo(vpath);
	}
	
	
	
	
}
