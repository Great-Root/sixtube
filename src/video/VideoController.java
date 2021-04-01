package video;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoController implements Initializable {
	Parent root;
	VideoStage vs;
	VideoService service;
	Stage stage;
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	public void setRoot(Parent root) {
		this.root = root;
		service.setRoot(root);
		stage = (Stage) root.getScene().getWindow();
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
	public void setVideo(String vpath) {
		service.setVideo(vpath);
	}
	
	public void setOnMousePressed(MouseEvent e) {
		xOffset = e.getSceneX();
		yOffset = e.getSceneY();
	}
	
	public void setOnMouseDragged(MouseEvent e) {
		stage = (Stage) root.getScene().getWindow();
		stage.setX(e.getScreenX() - xOffset);
		stage.setY(e.getScreenY() - yOffset - 30);
	}
	
	
	public void setOnMouseEntered() {
		service.iconVisible();
	}
	
	public void setOnMouseExited() {
		service.iconDisVisible();
	}
	
}
