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
	public void volumnDragProc() {
		System.out.println("볼륨 드래그");
		service.volumnDragProc();
	}
	public void volumnClickProc() {
		System.out.println("볼륨 클릭");
		service.volumnClickProc();
	}
	public void timeDragProc() {
		System.out.println("영상시간 드래그");
		service.timeDragProc();
	}
	public void timeClickProc() {
		System.out.println("영상시간 클릭");
		service.timeClickProc();
	}
	public void plusProc() {
		System.out.println("10초 후");
		service.plusProc();
	}
	public void minusProc () {
		System.out.println("10초 전");
		service.minusProc();
	}
	public void slowProc() {
		System.out.println("0.5배속");
		service.slowProc();
	}
	public void fastProc() {
		System.out.println("2배속");
		service.fastProc();
	}
	public void setVideo(Parent root, String vpath) {
		service.setRoot(root);
		System.out.println(vpath);
		service.setVideo(vpath);
	}
	
	
	
	
}
