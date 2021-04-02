package video.service;

import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.util.Duration;

import java.util.ArrayList;
import model.VideoDTO;
import model.CommentDTO;

public interface VideoService {
	
	public void setRoot(Parent root);

	public void getVideo(String vpath);

	ArrayList<VideoDTO> getVideoList();
	//재생,일시정지,정지,볼륨드래그,볼륨클릭,10초후,10초전,0.5배속,2배속,영상시간드래그,영상시간클릭 
	public void playProc();
	public void pauseProc();
	public void stopProc();
	public void volumnDragProc();
	public void volumnClickProc();
	public void plusProc();
	public void minusProc();
	public void slowProc();
	public void fastProc();
	public void timeDragProc();
	public void timePressProc();
	public void setVideo(String mediaName);
	
	//CommentDAO Comments 넘겨주고
	void sendComments(CommentDTO dto);

	//CommentDAO로 CommentList 넘겨줌
	ArrayList<CommentDTO> getCommentList(int vnum);
	
	//revise
	void commentsRevise(int num);
	//delete
	void commentsDelete(int cnum);

}