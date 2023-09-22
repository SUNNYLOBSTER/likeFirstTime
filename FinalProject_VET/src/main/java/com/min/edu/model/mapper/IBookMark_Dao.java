package com.min.edu.model.mapper;

import java.util.List;

import com.min.edu.vo.BookMark_VO;

public interface IBookMark_Dao {
	
	public int countBookmark(String bm_usersid);
	
	public List<BookMark_VO> selectAllBookmark(String bm_usersid);
	
	public int insertNewBookmark(BookMark_VO bvo);
	
	public int deleteBookmark(BookMark_VO bvo);

}
