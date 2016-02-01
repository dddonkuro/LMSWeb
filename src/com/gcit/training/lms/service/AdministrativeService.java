package com.gcit.training.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.entity.Author;

public class AdministrativeService {
	
	public void addAuthor(Author author) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.create(author);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}
	
	public List<Author> getAllAuthors(int pageNo, int pageSize) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			return adao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}

	public void deleteAuthor(Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.delete(author);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			return adao.readByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}

}
