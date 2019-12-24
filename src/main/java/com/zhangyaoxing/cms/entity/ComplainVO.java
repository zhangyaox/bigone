package com.zhangyaoxing.cms.entity;

public class ComplainVO extends Complain {
	private Integer startNum;
	private Integer endNum;
	private String startTime;
	private String endTime;
	public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getEndNum() {
		return endNum;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public ComplainVO(Integer startNum, Integer endNum, String startTime, String endTime) {
		super();
		this.startNum = startNum;
		this.endNum = endNum;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
}
