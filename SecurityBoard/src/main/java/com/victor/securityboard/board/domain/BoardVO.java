package com.victor.securityboard.board.domain;

import java.sql.Date;

import com.victor.securityboard.util.CommonVO;

public class BoardVO extends CommonVO {

   private int board_seq;
   private String user_id;
   private String board_title;
   private int board_views;
   private Date board_reg_date;
   private String board_contents;
   
   public int getBoard_seq() {
      return board_seq;
   }
   public void setBoard_seq(int board_seq) {
      this.board_seq = board_seq;
   }
   public String getUser_id() {
      return user_id;
   }
   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }
   public String getBoard_title() {
      return board_title;
   }
   public void setBoard_title(String board_title) {
      this.board_title = board_title;
   }
   public int getBoard_views() {
      return board_views;
   }
   public void setBoard_views(int board_views) {
      this.board_views = board_views;
   }
   public Date getBoard_reg_date() {
      return board_reg_date;
   }
   public void setBoard_reg_date(Date board_reg_date) {
      this.board_reg_date = board_reg_date;
   }
   public String getBoard_contents() {
      return board_contents;
   }
   public void setBoard_contents(String board_contents) {
      this.board_contents = board_contents;
   }
   
	@Override
	public String toString() {
		return "BoardVO [board_seq=" + board_seq + ", user_id=" + user_id + ", board_title=" + board_title
				+ ", board_views=" + board_views + ", board_reg_date=" + board_reg_date + ", board_contents="
				+ board_contents + ", getCurPage()=" + getCurPage() + ", getPageSize()=" + getPageSize()
				+ ", getBlockSize()=" + getBlockSize() + ", getStartNum()=" + getStartNum() + "]";
	}
   
   
}