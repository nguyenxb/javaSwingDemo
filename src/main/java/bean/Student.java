package bean;

//  学生对象
public class Student {
	/**
	 * 学生信息对象
	 * */
	private String id;
	private String name;
	private String sex;
	private String birthday;
	private String address;
	public Student() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder(" [学号:" + id);
		
		if(this.name != null) {
			str.append("  姓名:" + name);
		}
		if(this.sex != null) {
			str.append(" 性别:" + sex);
		}
		if(this.birthday != null) {
			str.append(" 出生日期:" + birthday );
		}
		if(this.address != null) {
			str.append(" 家庭地址:"+address );
		}
		str.append(" ]\n");
		return str.toString();
	}
	
	public boolean checkID() {
		if (this.id.length() <= 4) {
			if (isNumber(this.id)) {
				System.out.println(Integer.valueOf(id));
				return true;
			}
		}
		
		return false;
	}
	public boolean checkName() {
		return this.name.length() <= 4 ? true:false;
	}
	public boolean checkSex() {
		if (this.sex.length() <= 2) {
			if (this.sex.equals("男")) {
				return true;
			}else if (this.sex.equals("女")) {
				return true;
			}
		}
		return false;
	}
	public boolean checkDate() {
		if(this.birthday.split("-*\\s*-+\\s*-*").length ==3) {
			String[] date = this.birthday.split("-*\\s*-+\\s*-*");
			
			if (isYear(date[0]) && isMonth(date[1]) && isDay(date[0], date[1], date[2])) {
				int month = Integer.valueOf(date[1]);
				int day = Integer.valueOf(date[2]);
				if (month>=1 && month < 10) {
					this.birthday = date[0] + "-0"+date[1];
				}else {
					this.birthday =date[0] + "-"+date[1];
				}
				if (day >1 && day < 10) {
					this.birthday += "-0"+date[2];
				}else {
					this.birthday += "-" + date[2];
				}
		
				return true;
			}
		}
		return false;
	}
	private boolean isYear(String s) {
		if (getYear(s)!=0) {
			return true;
		}
		
		return false;
	}
	private boolean isMonth(String s) {
		int month = Integer.valueOf(s);
		if (month >=1 && month<= 12) {
			return true;
		}
		
		return false;
	}
	private int getYear(String s) {
		if (isNumber(s)) {
			int year = Integer.valueOf(s);
			if (year >=1840 && year<= 2021) {
				return year;
			}
		}
		return 0;
	}
	private boolean isDay(String yearStr,String monthStr,String dayStr) {
		if (isNumber(monthStr) && isNumber(dayStr)) {
			if (isMonth(monthStr)) {
				int month = Integer.valueOf(monthStr);
				int day = Integer.valueOf(dayStr);
				int year = getYear(yearStr);
				int monthOfday = getMonthOfday(year, month);
				if (day <= monthOfday && day >= 1) {
					return true;
				}
			}
		}
		return false;
		
	}
	private int getMonthOfday(int year,int month) {
			switch (month) {
			case 1:case 3:case 5:case 7:case 8:case 10:case 12:
				return 31;
				
			case 4:case 6:case 9:case 11:
				return 30;
			case 2:
				if (year%4 ==0 && year % 100 != 0 || year % 400 ==0) {
					return 29;
				}else {
					return 28;
				}
			}
		return 0;
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
