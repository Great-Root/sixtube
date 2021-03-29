package video.service;

import javafx.scene.Parent;

public interface VideoService {

	void imgView();
	//재생,일시정지,정지,볼륨, 10초후, 10초전
	public void playProc();
	public void pauseProc();
	public void stopProc();
	public void volumnProc();
	public void plusProc();
	public void minusProc();
	public void setVideo(Parent root, String mediaName);
	
}