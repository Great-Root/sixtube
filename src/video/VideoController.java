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
		System.out.println("플레이");
		service.playProc();
	}
	public void pauseProc() {
		System.out.println("일시정지");
		service.pauseProc();
	}
	public void stopProc() {
		System.out.println("정지");
		service.stopProc();
	}
	public void volumnProc() {
		System.out.println("볼륨");
		service.volumnProc();
	}
	public void plusProc() {
		System.out.println("10초 후");
		service.plusProc();
	}
	public void minusProc () {
		System.out.println("10초 전");
		service.minusProc();
	}
	public void setVideo(Parent root, String vpath) {
		service.setRoot(root);
		System.out.println(vpath);
		service.setVideo(vpath);
	}
	
	
	
	
}
