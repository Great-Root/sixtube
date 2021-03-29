package video;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class VideoController implements Initializable {
	Parent root;
	VideoStage vs;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vs = new VideoStage();
	}
	
	public void playProc() {
		System.out.println("플레이");
	}
	public void pauseProc() {
		System.out.println("일시정지");
	}
	public void stopProc() {
		System.out.println("정지");
	}
	public void volumnProc() {
		System.out.println("볼륨");
	}
	
	
	
	
}
