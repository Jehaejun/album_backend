package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.album.AlbumLdgr;
import com.example.demo.album.AlbumRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;


@RestController
public class AlbumController {
	
	@Autowired
	private AlbumRepository repository;
	//private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value="/")
	@Transactional
	public String index() throws Exception{

		
		return "ㅎㅇㅎㅇ";
	}
	
	@GetMapping(value="/api/album")
	public ResponseEntity<List<AlbumLdgr>> select(){
		List<AlbumLdgr> albums = repository.findAll(Sort.by(Direction.DESC, "SEQ")); 
		return new ResponseEntity<List<AlbumLdgr>>(albums, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/albumInsert")
	public int commonetInsert(@RequestParam(value = "name", required=false) String name
							, @RequestParam(value = "memo", required=false) String memo
							, @RequestParam(value = "filePath", required=false) String filePath
							, @RequestParam(value = "nickName", required=false) String nickName
							, @RequestParam(value = "userId", required=false) String userId){
		
		
		AlbumLdgr albumLdgr = new AlbumLdgr();
		albumLdgr.setTitle(name);
		albumLdgr.setContents(memo);
		albumLdgr.setImgPath(filePath);
		
		User user = new User();
		user.setUserId(userId);
		albumLdgr.setUser(user);
		
		System.out.println("~~~~~~~~~~`" + nickName + " / " + userId);
		
		repository.save(albumLdgr);
		
		return 1;
	}

	
	@PostMapping(value="/api/imgUpload")
	public Map<String, String> imgUpload(@RequestParam("file") MultipartFile uploadFile, @RequestParam(value = "name", required=false) String name){
		System.out.println(uploadFile.getContentType() + "-" + uploadFile.getOriginalFilename() + "-" + uploadFile.getName() + "-" + name);
		
		String tempPath = "C:/Apache24/htdocs/images/album/";
		String imgName = UUID.randomUUID().toString() + ".jpg";
		
		// 원본파일 저장
		File tmp = new File(tempPath + imgName);
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tmp));
            stream.write(uploadFile.getBytes());
            stream.close();
            
		} catch (IOException e) {
			System.out.println("Error while copying." + e);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("filePath", imgName);
		return map;
	}

	

	@PostMapping(value="/join")
	@Transactional
	public void join(@RequestBody User user) {

		userRepository.save(user);
	}
}
