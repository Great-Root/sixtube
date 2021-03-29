package video.service;

import java.util.ArrayList;

import model.CommentDTO;

public interface VideoService {

	void imgView();

	//CommentDAO Comments 넘겨주고
	void sendComments(CommentDTO dto);

	//CommentDAO로 CommentList 넘겨줌
	ArrayList<CommentDTO> getCommentList(int vnum);
	
	//revise
	void commentsRevise(int num);
	//delete
	void commentsDelete(int cnum);
}