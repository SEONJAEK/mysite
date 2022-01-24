package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	public List<BoardVo> findAll(int page, String kwd) {
		List<BoardVo> boardList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			if(kwd==null) {
				String sql = "select b.no, b.title, b.contents, b.hit, b.g_no, b.o_no, b.depth, b.reg_date, b.user_no, u.name "
						+ "from user u join board b on b.user_no = u.no order by g_no desc, o_no asc limit " + page + " ,10 ";
				pstmt = conn.prepareStatement(sql);
			}else {
				String sql = "select b.no, b.title, b.contents, b.hit, b.g_no, b.o_no, b.depth, b.reg_date, b.user_no, u.name "
						+ "from user u join board b on b.user_no = u.no where b.title like '%"+ kwd + "%' or b.contents like '%"+kwd+ "%' "
								+ " order by g_no desc, o_no asc limit "+ page +" , 10";

				pstmt = conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				int groupNo = rs.getInt(5);
				int orderNo = rs.getInt(6);
				int depth = rs.getInt(7);
				String regDate = rs.getString(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);// 함수 이용했음

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
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return boardList;
	}
		
		

		// select BoardName
		public String findUserName(Long no) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String userName = "";

			try {
				conn = getConnection();
				String sql = "select name from user where no=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, no);
				rs = pstmt.executeQuery();
				userName = rs.getString(1);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return userName;
		}

		// select BoardView->board.no로 내용 가져오기
		public BoardVo boardView(Long no) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVo boardVo = null;
			try {
				conn = getConnection();
				String sql = "select b.title, b.contents, b.hit, b.g_no, b.o_no, b.depth, b.reg_date, b.user_no, u.name "
						+ "from user u join board b on b.user_no = u.no where b.no=?; ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, no);
				rs = pstmt.executeQuery();

				String title = rs.getString(1);
				String contents = rs.getString(2);
				int hit = rs.getInt(3);
				int g_no = rs.getInt(4);
				int o_no = rs.getInt(5);
				int depth = rs.getInt(6);
				String reg_date = rs.getString(7);
				Long user_no = rs.getLong(8);
				String user_name = rs.getString(9);

				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setHit(hit);
				boardVo.setGroupNo(g_no);
				boardVo.setOrderNo(o_no);
				boardVo.setDepth(depth);
				boardVo.setRegDate(reg_date);
				boardVo.setUserNo(user_no);
				boardVo.setUserName(user_name);

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return boardVo;
		}

		// delete
		public boolean delete(BoardVo bvo) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();

				String sql = "delete from board where no=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setLong(1, bvo.getNo());
				int count = pstmt.executeUpdate();
				result = count == 1;

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return result;
		}

		// findkwd
		public List<BoardVo> findKwd(String kwd) {
			List<BoardVo> boardList = new ArrayList<>();

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = getConnection();

				String sql = "select b.no, b.title, b.contents, b.hit, b.g_no, b.o_no, b.depth, b.reg_date, b.user_no, u.name "
						+ "from user u join board b on b.user_no = u.no where b.title like ? or b.contents like ? ";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + kwd + "%");
				pstmt.setString(2, "%" + kwd + "%");

				rs = pstmt.executeQuery();

				while (rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					int hit = rs.getInt(4);
					int groupNo = rs.getInt(5);
					int orderNo = rs.getInt(6);
					int depth = rs.getInt(7);
					String regDate = rs.getString(8);
					Long userNo = rs.getLong(9);
					String userName = rs.getString(10);// 함수 이용했음

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
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return boardList;
		}
		
			// findOne//강사님코드에 맞춘 버전
				public List<BoardVo> findOne(Long no) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List<BoardVo> boardList = new ArrayList<BoardVo>();
					try {
						conn = getConnection();

						String sql = "select b.title, b.contents, b.hit, b.g_no, b.o_no, b.depth, b.reg_date, b.user_no, u.name "
								+ "from user u join board b on b.user_no = u.no where b.no=?  ";

						pstmt = conn.prepareStatement(sql);
						pstmt.setLong(1, no);
						rs = pstmt.executeQuery();

						while (rs.next()) {

							String title = rs.getString(1);
							String contents = rs.getString(2);
							int hit = rs.getInt(3);
							int groupNo = rs.getInt(4);
							int orderNo = rs.getInt(5);
							int depth = rs.getInt(6);
							String regDate = rs.getString(7);
							Long userNo = rs.getLong(8);
							String userName = rs.getString(9);// 함수 이용했음

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

						}

					} catch (SQLException e) {
						System.out.println("error:" + e);
					} finally {
						try {
							if (rs != null) {
								rs.close();
							}
							if (pstmt != null) {
								pstmt.close();
							}
							if (conn != null) {
								conn.close();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					return boardList;
				}
			

//		// findOne//원래 선재 코드
//		public BoardVo findOne(Long no) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			BoardVo bvo = new BoardVo();
//			try {
//				conn = getConnection();
//
//				String sql = "select b.title, b.contents, b.hit, b.g_no, b.o_no, b.depth, b.reg_date, b.user_no, u.name "
//						+ "from user u join board b on b.user_no = u.no where b.no=?  ";
//
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setLong(1, no);
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//
//					String title = rs.getString(1);
//					String contents = rs.getString(2);
//					int hit = rs.getInt(3);
//					int groupNo = rs.getInt(4);
//					int orderNo = rs.getInt(5);
//					int depth = rs.getInt(6);
//					String regDate = rs.getString(7);
//					Long userNo = rs.getLong(8);
//					String userName = rs.getString(9);// 함수 이용했음
//
//					bvo.setNo(no);
//					bvo.setTitle(title);
//					bvo.setContents(contents);
//					bvo.setHit(hit);
//					bvo.setGroupNo(groupNo);
//					bvo.setOrderNo(orderNo);
//					bvo.setDepth(depth);
//					bvo.setRegDate(regDate);
//					bvo.setUserNo(userNo);
//					bvo.setUserName(userName);
//
//				}
//
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			} finally {
//				try {
//					if (rs != null) {
//						rs.close();
//					}
//					if (pstmt != null) {
//						pstmt.close();
//					}
//					if (conn != null) {
//						conn.close();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			return bvo;
//		}

		// update
		public boolean update(BoardVo vo) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();

				String sql = " update board set title=?, contents=? where no=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getNo());
				int count = pstmt.executeUpdate();
				result = count == 1;
				System.out.println(result);

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return result;
		}

		// insert
		public boolean insert(BoardVo vo) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();

				String sql = "insert into board values (null, ?, ? , 1, ifnull((select max(g_no)+1 from board as b) ,1), 1, 1, now(), ? )";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getUserNo());
				int count = pstmt.executeUpdate();
				result = count == 1;
				System.out.println(result);

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return result;
		}
		
		
		// reply update///수정해야함
			public boolean replyUpdate(BoardVo vo) {
				boolean result = false;

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();

					String sql = " update board set o_no = o_no+1 where o_no > ? and g_no = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, vo.getOrderNo());
					pstmt.setInt(2, vo.getGroupNo());
					int count = pstmt.executeUpdate();
					
					if(count>=0) {
						result = true;
						System.out.println(count+"개 reply update변경됨");
					}
					
					System.out.println(result);

				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {
					try {
						if (pstmt != null) {
							pstmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				return result;
			}
		

			// replyInsert
			public boolean replyInsert(BoardVo vo, Long user_no) {
				boolean result = false;

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();

					String sql = "insert into board values (null, ?, ? , 1, ? , ?, ?, now(), ? )";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, vo.getTitle());
					pstmt.setString(2, vo.getContents());
					pstmt.setInt(3, vo.getGroupNo());
					pstmt.setInt(4, vo.getOrderNo()+1);
					pstmt.setInt(5, vo.getDepth()+1);
					pstmt.setLong(6, user_no);
					
					int count = pstmt.executeUpdate();
					result = count == 1;
					System.out.println(result);

				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {
					try {
						if (pstmt != null) {
							pstmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				return result;
			}
			
		//count
			public int getCount() {
				int result = 0;
				ResultSet rs = null;

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					
					String sql = "select count(*) from board";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();	
					while(rs.next()) {
						result = rs.getInt(1);
					}
					
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
			
			//조회수 증가
			public boolean views(BoardVo vo) {
				boolean result = false;
			      Connection conn = null;
			      PreparedStatement pstmt = null;
			      ResultSet rs = null;
			      
			      try {
			         conn = getConnection();
			         
			         String sql = "update board set hit = hit + 1 where no = ?";
			         pstmt = conn.prepareStatement(sql);

			         pstmt.setLong(1, vo.getNo());
			         
			         result = (pstmt.executeUpdate() == 1);

			      } catch (SQLException e) {
			         System.out.print("error : " + e); 
			      }
			      
			      finally {
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
			         } catch(SQLException e) {
			            e.printStackTrace();
			         }
			      }
			      
			      return result;
			}
			
		// connection
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
