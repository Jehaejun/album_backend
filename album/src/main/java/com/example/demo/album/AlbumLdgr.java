package com.example.demo.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.demo.user.User;
import com.example.demo.user.UserKey;

import lombok.Data;

@Data
@Entity
@Table(name="ALBUM_LDGR")
public class AlbumLdgr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long SEQ;
	
	// 연관 관계 맵핑
	//@MapsId("userId")
    @ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", updatable = false, nullable = false)
    private User user;

    @Column(name = "TITLE", length = 50, nullable = false)
	private String title;
    
	@Column(name = "CONTENTS", length = 1000, nullable = false)
	private String contents;
	
	@Column(name = "IMG_PATH", length = 200, nullable = false)
	private String imgPath;

	
	/*
	 * @Builder public AlbumLdgr(String regName, String note, String imgName) {
	 * this.regName = regName; this.note = note; this.imgName = imgName; }
	 */
}
