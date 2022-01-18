package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.GuestbookVo;

public class BoardDao {
	public List<BoardVo> findAll() {
		List<BoardVo> boardList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select no, title, contents, hit, g_no, o_no, depth, reg_date, user_no from board";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				int groupNo = rs.getInt(5);
				int orderNo = rs.getInt(6);
				int depth = rs.getInt(7);
				String regDate = rs.getString(8);
				Long userNo = rs.getLong(9);
				String userName = findUserName(userNo);//함수 이용했음
				System.out.println(userName);
				
				BoardVo bvo = new BoardVo();
				bvo.setNo(no);
				bvo.setTitle(title);
				bvo.setContents(contents);
				bvo.setHit(hit);
				bvo.setGroupNo(groupNo);
				bvo.setOrderNo(orderNo);
				bvo.setDepth(depth);
				bvo.setRegDate(regDate);
				bvo.setUserNo(userNo);
				bvo.setUserName(userName);
				
				boardList.add(bvo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return boardList;
	}
	
	//select BoardName
	public String findUserName(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userName="";
		
		try {
			conn = getConnection();
			String sql =
				"select name from user where no=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			userName = rs.getString(1);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userName;
	}
	
	//select BoardView->board.no로 내용 가져오기
		public BoardVo boardView(Long no) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVo boardVo = null;
			try {
				conn = getConnection();
				String sql =
					"select title, contents, hit, g_no, o_no, depth, reg_date, user_no from user where no=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, no);
				rs = pstmt.executeQuery();
				
				String title = rs.getString(1);
				String contents = rs.getString(2);
				int hit = rs.getInt(3);
				int g_no = rs.getInt(4);
				int o_no = rs.getInt(5);
				String depth = rs.getString(6);
				String reg_date = rs.getString(7);
				String user_no = rs.getString(8);
				
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setHit(hit);
				boardVo.setGroupNo(g_no);
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return boardVo;
		}
	
	//delete
	public boolean delete(BoardVo bvo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					"delete from board where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, bvo.getNo());
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;		
	}
	
	
	//connection
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		return conn;
	}	
}
