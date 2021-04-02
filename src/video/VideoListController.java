package video;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import member.Controller;
import member.ModifyMain;
import member.service.MemberServiceImpl;
import model.CommentDTO;
import model.VideoDTO;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoListController implements Initializable {
	
	static Parent root, reviseRoot;
	VideoStage vs;
	VideoService service;
	ModifyMain mom;//

	@FXML Button btnShow;//
	@FXML Button btnHide;//
	@FXML AnchorPane paneSlide;//
	MemberServiceImpl ms;
	TextField tf;
	
	Button btnCompl;
	
	CommentDTO seletedDTO;
	
	public String inputValue;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	
	public void setReviseRoot(Parent reviseRoot) {
		this.reviseRoot = reviseRoot;
		btnCompl = (Button)reviseRoot.lookup("#btnCompl");
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vs = new VideoStage();
		service = new VideoServiceImpl();
		mom=new ModifyMain();
		
	}
	
	//TableView내에 Data Mouse 클릭 액션으로 연결
	public void click(MouseEvent e) {
		TableView<CommentDTO> tw = (TableView<CommentDTO>) e.getSource();
		
		
		//선택한 tableview의 정보를 전부 selectedDTO에 저장.
		seletedDTO = tw.getSelectionModel().getSelectedItem();
		paneSlide.setTranslateX(-140);//
		btnShow.setVisible(true);//
		btnHide.setVisible(false);//
		
		if(seletedDTO != null) {
			//TableView 에 클릭 액션이 된 줄의 source를 tw 변수에 저장.
			btnRevDisable();
			btnDelDisable();
			//클릭 된 tableview에 수정버튼 활성화
			((Button)root.lookup("#btnRev"+seletedDTO.getVnum())).setDisable(false);
			
			//클릭 된 tableview에 삭제버튼 활성화
			((Button)root.lookup("#btnDel"+seletedDTO.getVnum())).setDisable(false);
			
		} 
	}
	
	public void btnRevDisable() {
		//수정 button 전부 비활성화
		for(int i = 0; i < 3; i++) {
			((Button)root.lookup("#btnRev"+i)).setDisable(true);
		}
	}
	
	public void btnDelDisable() {
		//삭제 button 전부 비활성화
				for(int i = 0; i < 3; i++) {
					((Button)root.lookup("#btnDel"+i)).setDisable(true);
				}
	}
	
	//revisecontent.fxml에서의 수정완료 버튼.
	public void CompleteProc() {
		seletedDTO.setContent(new SimpleStringProperty(tf.getText()));
		String n = seletedDTO.getContent();
		if(n.length() < 50) {
			
			service.commentsRevise(seletedDTO);
			Controller.cs.exit(reviseRoot);
			Controller.cs.alert("수정되었습니다.");
			setListView();
		}else {
			Controller.cs.alert("입력 가능한 글자 수는 최대 50글자입니다. \n현재 입력한 글자 수는 : " + n.length()+ "입니다");
		}
	}
	
	public void reviseEnter() {
		seletedDTO.setContent(new SimpleStringProperty(tf.getText()));
		String n = seletedDTO.getContent();
		if(n.length() < 50) {
			
			service.commentsRevise(seletedDTO);
			Controller.cs.exit(reviseRoot);
			Controller.cs.alert("수정되었습니다.");
			setListView();
		}else {
			Controller.cs.alert("입력 가능한 글자 수는 최대 50글자입니다. \n현재 입력한 글자 수는 : " + n.length()+ "입니다");
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
		}
	}
	public void imgClickProc(MouseEvent e) {
		if(e.getClickCount()>1) {
			service.getVideo(((ImageView)e.getSource()).getId());
		}
	}
	
	//TextField의 내용을 Enter Action을 통해서 데이터 넘기는 메서드
	public void onEnter(ActionEvent e) {   
		//e.getSource(); 메서드를 통해서 textField내의 특정 컨테이너의 속성을 가져온다. 
		TextField tf = (TextField)e.getSource();
		
		//textField에 삽입한 값을 String 형태로 저장.
		inputValue = tf.getText(); 
		
		if(inputValue.length() < 50) {
			//입력 값 삭제
			tf.clear();
			
			//TextField내의 id값을 String id 변수에 저장.
			String id =((TextField)e.getSource()).getId();
			
			//String id의 마지막 값을 char ch변수에 저장
			char ch = id.charAt(id.length()-1);
			
			//Character.getNumericValue(); 메서드를 통해서 char형을 int형(vnum변수)으로 변환.
			int vnum = Character.getNumericValue(ch);
			
			//dto (cnum,usrId,content,vnum) , 만든 정보를 dto 변수에 저장.
			CommentDTO dto = new CommentDTO(0, Controller.lu.getUserId(),inputValue,vnum);
			
			service.sendComments(dto);
			setListView();
			
		}else {
			Controller.cs.alert("입력 가능한 글자 수는 최대 50글자입니다. \n현재 입력한 글자 수는 : " + inputValue.length()+ "입니다");
//			Controller.cs.alert("현재 입력한 글자 수는 : " + inputValue.length() + "입니다");
		}
		
		}
	
	
	//onEnter() 메서드에서 넘어온 데이터(cnum,userId,content,vnum)를 tableView에 보여지도록 하는 메서드
	public void setListView() {
		
		for(int i = 0 ; i <3;i++) {
			
		TableView<CommentDTO> fxTable = (TableView<CommentDTO>)root.lookup("#fxTable"+i);
		//setPromptText() 메소드를 각 테이블의 textField에 넣어주는 코드
		((TextField)root.lookup("#fxComments"+i)).setPromptText("Comments 입력하세요");
		//tableview의 정보를 가져와서 list 변수에 저장.
		ObservableList<CommentDTO> list = fxTable.getItems();
		//넣고 난 후 clear()
		list.clear();
		//arrayList에 tableview의 번호를 저장.(vnum)
		ArrayList<CommentDTO> arr = service.getCommentList(i);
		
		
		for(CommentDTO data : arr) { 
			list.add(data);
		}
		
		fxTable.setItems(list);
		
		}
		
		btnRevDisable();
		btnDelDisable();
		
	}
	
	public void changeCont(CommentDTO seleteddto) {
		
		//CommentDTO seletedDTO 변수에 인자 값인 seleteddto와 동일하게 맞춰준다.
		this.seletedDTO = seleteddto;
		
		//revisecontent.fxml에서의 textField의  값을
		tf = (TextField) reviseRoot.lookup("#changeCont");
		//videolist.fxml 에 있는 tableview에 content 값을 setTest로 받아서 tf에 저장.
		tf.setText(seleteddto.getContent());
		
		
		
	}
	
	
	//수정 버튼 클릭
	public void reviseProc() {
		
		
		
		//로그인 한 id와 현재 tableview에 있는 id와 비교
		if(Controller.lu.getUserId().equals(seletedDTO.getUserId())) {
			
			//true면 videoStage에 정보를 가지고 넘겨준다.
			vs.showContentView(seletedDTO);
		}else {
			Controller.cs.alert("작성자만 수정이 가능합니다.");
		}
		
	}
	
	//삭제 버튼 클릭
	public void deleteProc() { 
		 
		//로그인 당시의 userId와 현재 TableView Column의 Id값을 비교
		if(Controller.lu.getUserId().equals(seletedDTO.getUserId())) {
			//true면 service class의 commentsDelete();메서드로 click();메서드에서 저장되었던 selectedNum(cnum)값을 넣어서 넘겨준다.
			service.commentsDelete(seletedDTO.getCnum());
			Controller.cs.alert("삭제되었습니다.");
			setListView();
			System.out.println("삭제버튼 클릭");
		}else {
			Controller.cs.alert("작성자만 삭제가 가능합니다.");
		}
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
		try {
			mom.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void btnEntered() {
		btnCompl.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 25; -fx-border-width: 3");
		
	}
	
	public void btnExited() {
		btnCompl.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 25;");
	}
	
	
	
}
