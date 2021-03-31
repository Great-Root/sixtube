package video.service;

import javafx.scene.Parent;
import java.util.ArrayList;
import model.VideoDTO;
import model.CommentDTO;

public interface VideoService {
	
	public void setRoot(Parent root);

	public void getVideo(String vpath);

	ArrayList<VideoDTO> getVideoList();
	//재생,일시정지,정지,볼륨, 10초후, 10초전
	public void playProc();
	public void pauseProc();
	public void stopProc();
	public void volumnProc();
	public void plusProc();
	public void minusProc();
	public void setVideo(String mediaName);
	

	//CommentDAO Comments 넘겨주고
	void sendComments(CommentDTO dto);

	//CommentDAO로 CommentList 넘겨줌
	ArrayList<CommentDTO> getCommentList(int vnum);
	
	//revise
	boolean commentsRevise(CommentDTO dto);
	
	//delete
	void commentsDelete(int cnum);
}