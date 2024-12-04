package com.tech.seoul.culture.models;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CultureDao {
	public Integer selectCultureBookPKCnt(String esntl_id);
	public Integer selectCultureBestsellerPKCnt(int seq_no);
	public List<CultureBookDto> selectCultureBook();
	public List<CultureBestsellerDto> selectCultureBestseller();
	public void insertCultureBook(CultureBookDto cultureBookDto);
	public void insertCultureBestseller(CultureBestsellerDto cultureBestsellerDto);
}
