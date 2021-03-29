package video.service;

import java.util.ArrayList;

import database.video.VideoDAO;
import database.video.VideoDAOImpl;
import javafx.scene.Parent;
import model.CommentDTO;

public class VideoServiceImpl implements VideoService{
	
	VideoDAO dao = new VideoDAOImpl();
	Parent root;
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void imgView() {
		System.out.println("서비스에서 실행됩니다");
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
	public void commentsRevise(int cnum) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void commentsDelete(int cnum) {
		// TODO Auto-generated method stub
		
	}}
