package video.service;

import java.util.ArrayList;

import database.video.VideoDAO;
import database.video.VideoDAOImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import member.Controller;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.VideoDTO;
import video.VideoStage;
import model.CommentDTO;

public class VideoServiceImpl implements VideoService {

	VideoDAO dao = new VideoDAOImpl();
	Parent root;
	MediaPlayer videoPlayer;
	MediaView videoView;
	Button btnSlow,btnFast;
	AnchorPane icons;
	
	Label btnPlay,btnPause,btnStop,btnPlus,btnMinus;
	Label labelTime;
	ProgressIndicator progressIndicator;
	Slider slider, slider1;
	

	VideoStage vs = new VideoStage();
	
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void getVideo(String vpath) {
		vs.showVideoView(vpath);
	}
	@Override
	public ArrayList<VideoDTO> getVideoList() {
		return dao.getVideoList();
	}
	@Override
	public void sendComments(CommentDTO dto) {
		dao.contentUpload(dto);
	}
	@Override
	public ArrayList<CommentDTO> getCommentList(int vnum) {
		return dao.getCommentList(vnum);
	}
	@Override
	public boolean commentsRevise(CommentDTO dto) {
		return dao.commentsRevise(dto);
	}
	@Override
	public void commentsDelete(int cnum) {
		dao.commentsDelete(cnum);
	}
	@Override 
	public void playProc() {
		//플레이,볼륨
		videoPlayer.setVolume(0.1);
		slider.setValue(10.0);
		
		//재생을 누르면 0.5,2배속이 다시 1배속으로 돌아온다.
		videoPlayer.setRate(1);	
		
		//플레이
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
	public void volumnDragProc() {
		videoPlayer.setVolume(slider.getValue()/100.0);
	}
	@Override
	public void volumnClickProc() {
		videoPlayer.setVolume(slider.getValue()/100.0);
	}
	@Override
	public void timeDragProc() {
		videoPlayer.seek(Duration.seconds(slider1.getValue()*videoPlayer.getTotalDuration().toSeconds()/100));
	}
	@Override
	public void timeClickProc() {
		videoPlayer.seek(Duration.seconds(slider1.getValue()*videoPlayer.getTotalDuration().toSeconds()/100));
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
	public void slowProc() {
		videoPlayer.setRate(0.5);
	}
	@Override 
	public void fastProc() {
		videoPlayer.setRate(2);
	}
	@Override
	public void setVideo(String mediaName) {
		btnSlow = (Button)root.lookup("#btnSlow");
		btnFast = (Button)root.lookup("#btnFast");
		videoView = (MediaView)root.lookup("#fxMediaView");
		btnPlay = (Label)root.lookup("#btnPlay");
		btnPause = (Label)root.lookup("#btnPause");
		btnStop = (Label)root.lookup("#btnStop");
		btnPlus = (Label)root.lookup("#btnPlus");
		btnMinus = (Label)root.lookup("#btnMinus");
		icons = (AnchorPane)root.lookup("#icons");
		
		//labelTime =(Label)root.lookup("#labelTime");
		//progressBar =(ProgressBar)root.lookup("#progressBar");
		progressIndicator =(ProgressIndicator)root.lookup("#progressIndicator");
		slider = (Slider)root.lookup("#slider");
		slider1 =(Slider)root.lookup("#slider1");
		System.out.println(getClass().getResource(mediaName));
		System.out.println(mediaName);
		Media media = new Media(getClass().getResource("../"+mediaName).toString());
		videoPlayer = new MediaPlayer(media);
//		videoView.setFitWidth(root.getScaleX());
		videoView.setMediaPlayer(videoPlayer);
			
		videoPlayer.setOnReady(()->{
		//실행시 바로 비디오 실행
		videoPlayer.setVolume(0.1);
		slider.setValue(10.0);
				
		videoPlayer.play();
		//버튼 비활성화 여부
		btnPlay.setDisable(false);
		btnPause.setDisable(true);
		btnStop.setDisable(true);
		btnPlus.setDisable(true);
		btnMinus.setDisable(true);
		btnSlow.setDisable(true);
		btnFast.setDisable(true);
		
		videoPlayer.currentTimeProperty().addListener((obj,oldValue,newValue)->{
			double progress = 
			videoPlayer.getCurrentTime().toSeconds() / videoPlayer.getTotalDuration().toSeconds();
			//progressBar.setProgress(progress);
			progressIndicator.setProgress(progress);
			//labelTime.setText( (int)videoPlayer.getCurrentTime().toSeconds()+" / "+
					//(int)videoPlayer.getTotalDuration().toSeconds()+" sec");
			slider1.setValue(newValue.toSeconds()*100/ videoPlayer.getTotalDuration().toSeconds());
			});
		});
		
		videoPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true);
			btnPlay.setVisible(false);
			btnPause.setDisable(false);
			btnPause.setVisible(true);
			btnStop.setDisable(false);
			btnStop.setVisible(true);
			btnPlus.setDisable(false);
			btnPlus.setVisible(true);
			btnMinus.setDisable(false);
			btnSlow.setDisable(false);
			btnFast.setDisable(false);
			btnMinus.setVisible(true);
		});
		videoPlayer.setOnPaused(()->{
			btnPlay.setDisable(false);
			btnPlay.setVisible(true);
			btnPause.setDisable(true);
			btnPause.setVisible(false);
			btnStop.setDisable(false);
			btnPlus.setDisable(false);
			btnMinus.setDisable(false);
			btnSlow.setDisable(false);
			btnFast.setDisable(false);
		});
		videoPlayer.setOnEndOfMedia(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			btnPlus.setDisable(true);
			btnMinus.setDisable(true);
			btnSlow.setDisable(true);
			btnFast.setDisable(true);
				
			videoPlayer.stop();
			videoPlayer.seek(videoPlayer.getStartTime());
		});
		videoPlayer.setOnStopped(()->{
			btnPlay.setDisable(false);
			btnPlay.setVisible(true);
			btnPause.setDisable(true);
			btnPause.setVisible(false);
			btnStop.setDisable(true);
			btnStop.setVisible(false);
			btnPlus.setDisable(true);
			btnPlus.setVisible(false);
			btnMinus.setDisable(true);
			btnSlow.setDisable(true);
			btnFast.setDisable(true);
			btnMinus.setVisible(false);
		});
			
	}

	@Override
	public void setVideoWidth(double width) {
		videoView.setFitWidth(width);
	}
	public double getHeight(double width) {
		return videoView.computeAreaInScreen()/width +35;
	}
	@Override
	public void iconDisVisible() {
		icons.setVisible(false);
	}
	@Override
	public void iconVisible() {
		icons.setVisible(true);
	}

}

