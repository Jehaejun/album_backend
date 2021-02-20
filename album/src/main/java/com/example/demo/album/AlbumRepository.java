package com.example.demo.album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumRepository extends JpaRepository<AlbumLdgr, Long>{
	@Modifying	// update , delete Query
	@Query(value="update AlbumLdgr set note = :#{#albumLdgr.note}, regName = :#{#albumLdgr.regName} where SEQ = 1", nativeQuery=false)
	Integer update(@Param("albumLdgr") AlbumLdgr albumLdgr);
} 