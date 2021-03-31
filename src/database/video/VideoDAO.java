package database.video;


import java.util.ArrayList;

import model.CommentDTO;
import model.VideoDTO;

public interface VideoDAO {

	//비디오 리스트 가져오기
	public ArrayList<VideoDTO> getVideoList();
	
	//선택한 비디오 가져오기
	public VideoDTO getVideo(int vnum);
	
	
	//좋아요 기능
	public void addLikes(int vnum);
	
	
	//조회수 기능
	public void addViews(int vnum);
	
	//댓글 추가 기능
	public void contentUpload(CommentDTO dto);

	//데이터베이스에서 Comment정보 가져오기
	public ArrayList<CommentDTO> getCommentList(int vnum);

	public void commentsDelete(int cnum);

	public boolean commentsRevise(CommentDTO dto);
	
}
