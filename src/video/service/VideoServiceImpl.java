package video.service;

import database.video.VideoDAO;
import database.video.VideoDAOImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class VideoServiceImpl implements VideoService{
	VideoDAO dao = new VideoDAOImpl();
	Parent root;
	MediaPlayer videoPlayer;
	MediaView videoView;
	Button btnPlay,btnPause,btnStop,btnPlus,btnMinus;
	Label labelTime;
	ProgressBar progressBar;
	ProgressIndicator progressIndicator;
	Slider slider;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void imgView() {
		System.out.println("서비스에서 실행됩니다");
	}
	@Override 
	public void playProc() {
		//플레이
		videoPlayer.setVolume(0.1);
		slider.setValue(10.0);
			
		videoPlayer.play();
	}
	@Override
	public void pauseProc() {
		videoPlayer.pause();
	}
	@Override 
	public void stopProc() {
		videoPlayer.stop();
	}
	@Override
	public void volumnProc() {
		videoPlayer.setVolume(slider.getValue()/100.0);
	}
	@Override
	public void plusProc() {
		videoPlayer.seek(videoPlayer.getCurrentTime().add(Duration.seconds(10)));
	}
	@Override
	public void minusProc() {
		videoPlayer.seek(videoPlayer.getCurrentTime().add(Duration.seconds(-10)));
	}
	@Override
	public void setVideo(Parent root, String mediaName) {
		setVideoController(root);
		Media media = new Media(getClass().getResource(mediaName).toString());
		videoPlayer = new MediaPlayer(media);
		videoView.setMediaPlayer(videoPlayer);
			
		videoPlayer.setOnReady(()->{
		//버튼 비활성화 여부
		btnPlay.setDisable(false);
		btnPause.setDisable(true);
		btnStop.setDisable(true);
		btnPlus.setDisable(true);
		btnMinus.setDisable(true);
		
		/*videoPlayer.currentTimeProperty().addListener((obj,oldValue,newValue)->{
			double progress = 
			videoPlayer.getCurrentTime().toSeconds() / videoPlayer.getTotalDuration().toSeconds();
			progressBar.setProgress(progress);
			progressIndicator.setProgress(progress);
			labelTime.setText( (int)videoPlayer.getCurrentTime().toSeconds()+" / "+
					(int)videoPlayer.getTotalDuration().toSeconds()+" sec");
			});*/
		});
		videoPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnStop.setDisable(false);
			btnPlus.setDisable(false);
			btnMinus.setDisable(false);
		});
		videoPlayer.setOnPaused(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(false);
			btnPlus.setDisable(false);
			btnMinus.setDisable(false);
		});
		videoPlayer.setOnEndOfMedia(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			btnPlus.setDisable(true);
			btnMinus.setDisable(true);
				
			videoPlayer.stop();
			videoPlayer.seek(videoPlayer.getStartTime());
		});
		videoPlayer.setOnStopped(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			btnPlus.setDisable(true);
			btnMinus.setDisable(true);
		});
			
	}

	private void setVideoController(Parent root) {
		videoView = (MediaView)root.lookup("#fxMediaView");
		btnPlay = (Button)root.lookup("#btnPlay");
		btnPause = (Button)root.lookup("#btnPause");
		btnStop = (Button)root.lookup("#btnStop");
		btnPlus = (Button)root.lookup("#btnPlus");
		btnMinus = (Button)root.lookup("#btnMinus");
		
		
		//labelTime =(Label)root.lookup("#labelTime");
		progressBar =(ProgressBar)root.lookup("#progressBar");
		progressIndicator =(ProgressIndicator)root.lookup("#progressIndicator");
		slider = (Slider)root.lookup("#slider");
	}
}

