package database.video;

import java.util.ArrayList;
import java.util.List;

import model.CommentDTO;
import model.VideoDTO;

public interface VideoDAO {

	//비디오 리스트 가져오기
	public ArrayList<VideoDTO> getVideoList();
	
	//선택한 비디오 가져오기
	public VideoDTO getVideo(int vnum);
	
	//비디오 업로드 기능
	public boolean uploadVideo(VideoDTO dto);
	
	//비디오 삭제 기능
	public boolean deleteVideo(int vnum);
	
	//좋아요 기능
	public void addLikes(int vnum);
	
	//싫어요 기능
	public void addDisLikes(int vnum);
	
	//조회수 기능
	public void addViews(int vnum);
	
	//댓글 추가 기능
	public void contentUpload(CommentDTO dto);

	//데이터베이스에서 Comment정보 가져오기
	public ArrayList<CommentDTO> getCommentList(int vnum);
	
}
