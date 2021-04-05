package video;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.VideoDTO;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoController implements Initializable {
	Parent root;
	VideoStage vs;
	VideoService service;
	Stage stage;
	boolean isPlay = true;
	Thread timer;
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	public void setRoot(Parent root) {
		this.root = root;
		service.setRoot(root);
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
	public void setVideo(ImageView iv) {
		service.setVideo(iv);
	}
	
	public void setOnMousePressed(MouseEvent e) {
		stage = (Stage) root.getScene().getWindow();
		xOffset = e.getSceneX();
		yOffset = e.getSceneY();
	}
	
	public void setOnMouseDragged(MouseEvent e) {
		if(!stage.isFullScreen()) {
			stage = (Stage) root.getScene().getWindow();
			stage.setX(e.getScreenX() - xOffset);
			stage.setY(e.getScreenY() - yOffset - 30);
		}
	}
	
	public Thread getThread() {
		return new Thread(()->{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.iconDisVisible();
		});
	}
	
	public void setOnMouseEntered() {
		service.iconVisible();
		timer = getThread();
		timer.start();
	}
	
	public void setOnMouseExited() {
		service.iconDisVisible();
		if(timer.isAlive()) {
			timer.stop();
		}
	}
	
	public void setOnMouseMoved() {
		service.iconVisible();
		if(timer.isAlive()) {
			timer.stop();
		}
		timer = getThread();
		timer.start();
	}
	
	public void setOnMouseClicked(MouseEvent event) {
		if(event.getClickCount()==2) {
			stageFullScreen();
		}
	}
	
	public void stageFullScreen() {
		stage = (Stage) root.getScene().getWindow();
		boolean isFull = stage.isFullScreen() ? false : true;
		stage.setFullScreen(isFull);
	}
	
	public void muteProc() {
		service.videoMute();
	}
	
	public void setOnKeyPressed(KeyEvent e) {
		setOnMouseMoved();
		switch (e.getCode()) {
		case SPACE:
			if(isPlay) {
				service.pauseProc();
				isPlay = false;
			}else {
				service.playProc();
				isPlay = true;
			}
			break;
			
		case RIGHT:
			service.plusProc();
			break;
		case LEFT:
			service.minusProc();
			break;
		case UP:
			service.volPlus();
			break;
		case DOWN:
			service.volminus();
			break;
		case ENTER:
			stageFullScreen();
		default:
			break;

		}
		
	}
			
}
