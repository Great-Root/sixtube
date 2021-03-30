package video;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import member.ModifyMain;
import model.VideoDTO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.CommentDTO;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoListController implements Initializable {
	Parent root;
	VideoStage vs;
	VideoService service;
	ModifyMain mom;//
	
	@FXML TextField fxComments0;
	@FXML TextField fxComments1;
	@FXML TextField fxComments2;
	
	@FXML TableView<CommentDTO> tableView;

	@FXML Button btnShow;//
	@FXML Button btnHide;//
	@FXML AnchorPane paneSlide;//
	
	public String inputValue;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vs = new VideoStage();
		service = new VideoServiceImpl();
		mom=new ModifyMain();//
		
		//for문 돌리고 싶은데 어떻게 해야할까?
		fxComments0.setPromptText("Comments 입력하세요");
		fxComments1.setPromptText("Comments 입력하세요");
		fxComments2.setPromptText("Comments 입력하세요");
		
		paneSlide.setTranslateX(-140);//
		btnShow.setVisible(true);//
		btnHide.setVisible(false);//
	}
	public void setImg() {
		ArrayList<VideoDTO> videoList = service.getVideoList();
		for(VideoDTO video : videoList) {
			int id = video.getVnum();
			ImageView iv = (ImageView)root.lookup("#fxImg"+id);
			Label title = (Label) root.lookup("#labelTitle"+id);
			title.setText(video.getTitle());
			iv.setImage(new Image(video.getThpath()));
			iv.setId(video.getVpath());
			System.out.println(video.toString());
		}
	}
	public void imgClickProc(MouseEvent e) {
		if(e.getClickCount()>1) {
			service.getVideo(((ImageView)e.getSource()).getId());
			System.out.println(((ImageView)e.getSource()).getId());
		}
	}
	
	//TextField의 내용을 Enter Action을 통해서 데이터 넘기는 메서드
	public void onEnter(ActionEvent e) {   
		//e.getSource(); 메서드를 통해서 textField내의 특정 컨테이너의 속성을 가져온다. 
		TextField tf = (TextField)e.getSource();
		System.out.println("tf : "+tf);
		
		//textField에 삽입한 값을 String 형태로 저장.
		inputValue = tf.getText();
		System.out.println("inputValue : "+inputValue);
		
		//입력 값 삭제
	
		tf.clear();
		//TextField내의 id값을 String id 변수에 저장.
		String id =((TextField)e.getSource()).getId();
		System.out.println("id : " +id);
		
		//String id의 마지막 값을 char ch변수에 저장
		char ch = id.charAt(id.length()-1);
//		Character.getNumericValue(); 메서드를 통해서 char형을 int형(vnum변수)으로 변환.
		int vnum = Character.getNumericValue(ch);
			
		//dto (cnum,usrId,content,vnum)
		CommentDTO dto = new CommentDTO(0,"test",inputValue,vnum);
		
		service.sendComments(dto);
		setListView();
		}
	
	
	//onEnter() 메서드에서 넘어온 데이터(cnum,userId,content,vnum)를 tableView에 보여지도록 하는 메서드
	public void setListView() {
		
		for(int i = 0 ; i <3;i++) {
		TableView<CommentDTO> fxTable = (TableView<CommentDTO>)root.lookup("#fxTable"+i);
		
		ObservableList<CommentDTO> list = fxTable.getItems();
		list.clear();
		ArrayList<CommentDTO> arr = service.getCommentList(i);
		
		
		for(CommentDTO data : arr) { 
			list.add(data);
		}
		
		fxTable.setItems(list);
		}
		
		
	}
	public void reviseProc() {
		System.out.println("수정버튼 클릭");
	}
	
	public void deleteProc() {
//		tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
		service.commentsDelete(0);
		System.out.println("삭제버튼 클릭");
	}
	
	////
	public void showSlide(MouseEvent event) {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.4));
		slide.setNode(paneSlide);
		slide.setToX(0);
		slide.play();
		paneSlide.setTranslateX(-140);
		slide.setOnFinished((ActionEvent e) -> {
			btnShow.setVisible(false);
			btnHide.setVisible(true);
		});
	}
	
	public void hideSlide(MouseEvent event) {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.4));
		slide.setNode(paneSlide);
		slide.setToX(-140);
		slide.play();
		paneSlide.setTranslateX(0);
		slide.setOnFinished((ActionEvent e) -> {
			btnShow.setVisible(true);
			btnHide.setVisible(false);
		});
	}

	public void modifyProc() {
		System.out.println("회원 정보 수정");
		try {
			mom.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	
}
