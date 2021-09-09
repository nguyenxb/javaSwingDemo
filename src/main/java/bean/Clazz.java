package bean;

// 课程信息对象
public class Clazz {
	/**
	 * 课程信息对象
	 * */
	private String id;
	private String name;
	private String credit;
	public Clazz() {
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
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		if(this.id != null) {
			str.append(" 课程编号:"+id);
		}
		if (this.name != null) {
			str.append(" 课程名称:"+name);
		}
		if (this.credit != null) {
			str.append(" 课程学分:"+credit);
		}
		str.append(" ]\n");
		return str.toString();
	}
	public boolean checkClazzID() {
		if (this.id.length() <= 4) {
			if (isNumber(this.id)) {
				return true;
			}
		}
		
		return false;
	}
	public boolean checkClazzName() {
		return this.name.length() <= 50 ? true:false;
	}
	public boolean checkClazzCredit() {
			if (this.credit.length() <= 2) {
				if (isNumber(this.credit)) {
					int clazzCredit = Integer.valueOf(this.credit);
					if (clazzCredit >=1 && clazzCredit <= 10) {
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
