package video.service;

import java.util.ArrayList;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.BoundsAccessor;

import database.video.VideoDAO;
import database.video.VideoDAOImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.effect.Effect;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.VideoDTO;
import video.VideoStage;
import model.CommentDTO;

public class VideoServiceImpl implements VideoService {

	VideoDAO dao = new VideoDAOImpl();
	Parent root;
	BorderPane main;
	MediaPlayer videoPlayer;
	MediaView videoView;
	Button btnSlow,btnFast;
	VBox icons;
	Button btnPlay,btnPause,btnStop,btnPlus,btnMinus;
	Slider volBar, playBar;
	Label volicon,volmute;
	
	

	VideoStage vs = new VideoStage();
	
	public void setRoot(Parent root) {
		this.root = root;
		btnSlow = (Button)root.lookup("#btnSlow");
		btnFast = (Button)root.lookup("#btnFast");
		videoView = (MediaView)root.lookup("#fxMediaView");
		btnPlay = (Button)root.lookup("#btnPlay");
		btnPause = (Button)root.lookup("#btnPause");
		btnStop = (Button)root.lookup("#btnStop");
		btnPlus = (Button)root.lookup("#btnPlus");
		btnMinus = (Button)root.lookup("#btnMinus");
		icons = (VBox)root.lookup("#icons");
		volicon = (Label)root.lookup("#volicon");
		volmute = (Label)root.lookup("#volmute");
		volBar = (Slider)root.lookup("#slider");
		playBar =(Slider)root.lookup("#slider1");
		main = (BorderPane)root.lookup("#mediaViewPane");
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
		volBar.setValue(10.0);
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
		videoPlayer.setVolume(volBar.getValue()/100.0);
		playBar.requestFocus();
	}
	@Override
	public void volumnClickProc() {
		videoPlayer.setVolume(volBar.getValue()/100.0);
		playBar.requestFocus();
	}
	@Override
	public void volPlus() {
		double vol = videoPlayer.getVolume();
		if(vol <= 1.0) {
			videoPlayer.setVolume(vol + 0.05);
		}else {
			videoPlayer.setVolume(1.0);
		}
	}
	@Override
	public void volminus() {
		double vol = videoPlayer.getVolume();
		if(vol >= 0) {
			videoPlayer.setVolume(vol - 0.05);
		}else {
			videoPlayer.setVolume(0);
		}
	}
	@Override
	public void timeDragProc() {
		videoPlayer.seek(Duration.seconds(playBar.getValue()*videoPlayer.getTotalDuration().toSeconds()/100));
	}
	@Override
	public void timePressProc() {
		videoPlayer.seek(Duration.seconds(playBar.getValue()*videoPlayer.getTotalDuration().toSeconds()/100));
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
	public void setVolIcon(double vol) {
		String icon = (vol > 50) ? "🔊" : vol == 0 ? "🔈" : "🔉";
		volicon.setText(icon);
	}
	@Override
	public void videoMute() {
		boolean mute = videoPlayer.isMute() ? false : true;
		videoPlayer.setMute(mute);
	}
	@Override
	public void setVideo(String mediaName) {
		
		//비디오 버튼들 포커스 이동 막는 코드
		btnPlay.setFocusTraversable(false);
		btnPause.setFocusTraversable(false);
		btnStop.setFocusTraversable(false);
		btnPlus.setFocusTraversable(false);
		btnMinus.setFocusTraversable(false);
		btnSlow.setFocusTraversable(false);
		btnFast.setFocusTraversable(false);
		volBar.setFocusTraversable(false);
		
		Media media = new Media(getClass().getResource("../"+mediaName).toString());
		videoPlayer = new MediaPlayer(media);
		videoView.setMediaPlayer(videoPlayer);
		
		//비디오 플레이어 리스너 코드
		volBar.valueProperty().addListener((ob,oldV,newV) -> {
			setVolIcon((double)newV);
		});
		
		videoPlayer.muteProperty().addListener((ob,oldV,newV)->{
			if(newV) {
				volicon.setDisable(true);
				volicon.setVisible(false);
				volmute.setDisable(false);
				volmute.setVisible(true);
			}else {
				volicon.setDisable(false);
				volicon.setVisible(true);
				volmute.setDisable(true);
				volmute.setVisible(false);
			}
		});
		
		videoPlayer.volumeProperty().addListener((ob,oldV,newV)->{
			int vol = (int)(newV.doubleValue() * 100);
			vol = vol >= 100 ? 100 : vol <= 0 ? 0 : vol; 
			volBar.setValue(vol);
			String style = String.format("-fx-background-color: linear-gradient(to right, white %d%%, #969696 %d%%);",
					vol, vol);
			((StackPane)volBar.lookup(".track")).setStyle(style);
		});
		
		playBar.valueProperty().addListener((ob,oldV,newV) -> {
			double newVal = newV.doubleValue();
			String style = String.format("-fx-background-color: linear-gradient(to right, #d10000 %f%%, #969696 %f%%);",
					newVal, newVal);
			((StackPane)playBar.lookup(".track")).setStyle(style);
		});
		playBar.setOnMouseEntered((arg0)->{
			(playBar.lookup(".thumb")).setVisible(true);
		});
		playBar.setOnMouseExited((arg0)->{
			(playBar.lookup(".thumb")).setVisible(false);
		});
		
		videoPlayer.currentTimeProperty().addListener((obj,oldValue,newValue)->{
			playBar.setValue(newValue.toSeconds()*100/ videoPlayer.getTotalDuration().toSeconds());
		});
		
		
		//동영상 기능 코드
		videoPlayer.setOnReady(()->{
			(playBar.lookup(".thumb")).setVisible(false);
			(playBar.lookup(".thumb")).setStyle("-fx-background-color: #d10000;");
			//실행시 바로 비디오 실행
			videoPlayer.setVolume(0.5);
			setVolIcon(0.5);
			videoPlayer.play();
			//버튼 비활성화 여부
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnPlay.setVisible(true);
			btnPause.setVisible(false);
			btnStop.setDisable(true);
			btnPlus.setDisable(true);
			btnMinus.setDisable(true);
			btnSlow.setDisable(true);
			btnFast.setDisable(true);
			
		});
		
		videoPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnPlay.setVisible(false);
			btnPause.setVisible(true);
			btnStop.setDisable(false);
			btnPlus.setDisable(false);
			btnMinus.setDisable(false);
			btnSlow.setDisable(false);
			btnFast.setDisable(false);
		});
		videoPlayer.setOnPaused(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnPlay.setVisible(true);
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
			btnPlay.setVisible(true);
			btnPause.setVisible(false);
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
			btnPause.setDisable(true);
			btnPlay.setVisible(true);
			btnPause.setVisible(false);
			btnStop.setDisable(true);
			btnPlus.setDisable(true);
			btnMinus.setDisable(true);
			btnSlow.setDisable(true);
			btnFast.setDisable(true);
		});
			
	}

	@Override
	public void iconDisVisible() {
		icons.setStyle("-fx-opacity : 0");
	}
	@Override
	public void iconVisible() {
		icons.setStyle("-fx-opacity : 1");
	}
    
}

