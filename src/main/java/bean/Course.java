package bean;

// 选课信息对象
public class Course {
	/**
	 * 选课信息对象
	 * */
	private String stuID ;
	private String stuName;
	private String clazzID;
	private String clazzName;
	private String clazzCredit;
	private String score;
	public Course() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("[ ");
		if(this.stuID != null) {
			str.append("学号:"+stuID);
		}
		if(this.stuName != null) {
			str.append(" 姓名:"+stuName);
		}
		if(this.clazzID != null) {
			str.append(" 课程编号:"+clazzID);
		}
		if (this.clazzName != null) {
			str.append(" 课程名称:"+clazzName);
		}
		if (this.clazzCredit != null) {
			str.append(" 课程学分:"+clazzCredit);
		}
		if(this.score != null) {
			str.append(" 成绩:" + score);
		}
		str.append("]\n");
		return str.toString();
	}
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getClazzID() {
		return clazzID;
	}
	public void setClazzID(String clazzID) {
		this.clazzID = clazzID;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getClazzCredit() {
		return clazzCredit;
	}
	public void setClazzCredit(String clazzCredit) {
		this.clazzCredit = clazzCredit;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public boolean checkStuID() {
		if (this.stuID.length() <= 4) {
			if (isNumber(this.stuID)) {
				return true;
			}
		}
		
		return false;
	}
	public boolean checkStuName() {
		return this.stuName.length() <= 4 ? true:false;
	}
	public boolean checkClazzID() {
		if (this.clazzID.length() <= 4) {
			if (isNumber(this.clazzID)) {
				return true;
			}
		}
		
		return false;
	}
	public boolean checkClazzName() {
		return this.clazzName.length() <= 50 ? true:false;
	}
	public boolean checkClazzCredit() {
			if (this.clazzCredit.length() <= 2) {
				if (isNumber(this.clazzCredit)) {
					int clazzCredit = Integer.valueOf(this.clazzCredit);
					if (clazzCredit >=1 && clazzCredit <= 10) {
						return true;
					}
				}
			}
			
			return false;
	}
	public boolean checkScore() {
		if (this.score.length() <= 2) {
			if (isNumber(this.score)) {
				int score = Integer.valueOf(this.score);
				if (score >=0 && score <= 100) {
					return true;
				}
			}
		}
		
		return false;
	}
	private boolean isNumber(String s) {
		try {
			return Integer.valueOf(s) != null;
		} catch (Exception e) {
			System.out.println("数字格式错误");
			return false;
		}
	}
}
