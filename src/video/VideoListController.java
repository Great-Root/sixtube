package video;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.member.MemberDAO;
import database.member.MemberDAOImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import member.Controller;
import member.service.MemberServiceImpl;
import model.VideoDTO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.CommentDTO;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoListController implements Initializable {
	
	static Parent root, reviseRoot;
	VideoStage vs;
	VideoService service;
	MemberServiceImpl ms;
	TextField tf;
	
	CommentDTO seletedDTO;
	
	public String inputValue;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void setReviseRoot(Parent reviseRoot) {
		this.reviseRoot = reviseRoot;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vs = new VideoStage();
		service = new VideoServiceImpl();
		
	}
	
	public void click(MouseEvent e) { //TableView내에 Data Mouse 클릭 액션으로 연결
		
		//TableView 에 클릭 액션이 된 줄의 source를 tw 변수에 저장.
		TableView<CommentDTO> tw = (TableView<CommentDTO>) e.getSource();
		
		System.out.println(tw.getSelectionModel().getSelectedItem().getCnum());
		//CommentDTO 클래스에 TableView에서 선택한 것들 전부 seletedDTO란 변수에 저장
		seletedDTO = tw.getSelectionModel().getSelectedItem();
			
		
//		fxComments0.setText(tw.getSelectionModel().getSelectedItem().getContent());
//		fxComments1.setText(tw.getSelectionModel().getSelectedItem().getContent());
//		fxComments2.setText(tw.getSelectionModel().getSelectedItem().getContent());
	}
	
	public void CompleteProc () {
		System.out.println("수정 완료 버튼");
		System.out.println("선택한 번호에요 : "+ seletedDTO.getCnum());
//		Parent root = ((Button) e.getSource()).getParent();
//		System.out.println(root);
		if(Controller.lu.getId().equals(seletedDTO.getUserId())) {
			seletedDTO.setContent(new SimpleStringProperty(tf.getText()));
			if(service.commentsRevise(seletedDTO)) {
				Controller.cs.exit(reviseRoot);
				setListView();
			}else {
				Controller.cs.alert("수정에 실패했습니다.");
			}
		}else {
			Controller.cs.alert("작성자만 수정이 가능합니다.");
		}
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
		CommentDTO dto = new CommentDTO(0, Controller.lu.getId(),inputValue,vnum);
		
		service.sendComments(dto);
		setListView();
		}
	
	
	//onEnter() 메서드에서 넘어온 데이터(cnum,userId,content,vnum)를 tableView에 보여지도록 하는 메서드
	public void setListView() {
		
		for(int i = 0 ; i <3;i++) {
		TableView<CommentDTO> fxTable = (TableView<CommentDTO>)root.lookup("#fxTable"+i);
		((TextField)root.lookup("#fxComments"+i)).setPromptText("Comments 입력하세요");
		ObservableList<CommentDTO> list = fxTable.getItems();
		list.clear();
		ArrayList<CommentDTO> arr = service.getCommentList(i);
		
		
		for(CommentDTO data : arr) { 
			list.add(data);
		}
		
		fxTable.setItems(list);
		
		}
		
		
	}
	
	public void changeCont(CommentDTO seleteddto) {
		
		//CommentDTO seletedDTO 변수에 인자 값인 seleteddto와 동일하게 맞춰준다.
		this.seletedDTO = seleteddto;
		
		//revisecontent.fxml에서의 textField의  값을
		tf = (TextField) reviseRoot.lookup("#changeCont");
		//videolist.fxml 에 있는 tableview에 content 값을 setTest로 받아서 tf에 저장.
		tf.setText(seleteddto.getContent());
		
		
		
	}
	
	public void reviseProc() {
		System.out.println("수정버튼");
		if(Controller.lu.getId().equals(seletedDTO.getUserId())) {
			vs.showContentView(seletedDTO);
			System.out.println("수정 버튼 클릭");
		}else {
			Controller.cs.alert("작성자만 수정이 가능합니다.");
		}
		
	}
	
	public void deleteProc() { //삭제 버튼 클릭
		 
		//로그인 당시의 userId와 현재 TableView Column의 Id값을 비교
		if(Controller.lu.getId().equals(seletedDTO.getUserId())) {
			//true면 service class의 commentsDelete();메서드로 click();메서드에서 저장되었던 selectedNum(cnum)값을 넣어서 넘겨준다.
			service.commentsDelete(seletedDTO.getCnum());
			
			setListView();
			System.out.println("삭제버튼 클릭");
		}else {
			Controller.cs.alert("작성자만 삭제가 가능합니다.");
		}
	}
	
		
		
		
	}

