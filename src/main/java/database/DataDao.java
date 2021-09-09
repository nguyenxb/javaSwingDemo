package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Clazz;
import bean.Course;
import bean.Student;

// 封装 从数据库读取数据的对象
public class DataDao {
	/**
	 * 封装从数据库获取数据的方法，
	 * 并且对外暴露获取数据集合方法，
	 * 将数据获取细节方法进行私有化
	 * */
	
	public List<Student> getStuInfoByStuID(Student student){
		List<Student> list = new ArrayList<Student>();
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from student_info where id=?;";
		
		try {
			PreparedStatement preStat = conn.prepareStatement(sql);
			preStat .setString(1, student.getId());
			ResultSet rs = preStat.executeQuery();
			// 获取 学生的信息
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String address = rs.getString("address");
				
				student.setId(id);
				student.setName(name);
				student.setSex(sex);
				student.setBirthday(birthday);
				student.setAddress(address);
				
				list.add(student);
			}
			DbUtil.close(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
	public List<Clazz> getClazzInfoByClazzID(Clazz clazz){
		List<Clazz> list = new ArrayList<Clazz>();
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from curriculum where id=?;";
		
		try {
			PreparedStatement preStat = conn.prepareStatement(sql);
			preStat .setString(1, clazz.getId());
			ResultSet rs = preStat.executeQuery();
			// 获取 学生的信息
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String credit = rs.getString("credit");
				
				clazz.setId(id);
				clazz.setName(name);
				clazz.setCredit(credit);
				
				list.add(clazz);
			}
			DbUtil.close(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return list;
		} catch (SQLException e) {
			
		}
		
		return null; 
	}
	
	
	public List<Course> getCourseInfoByID(Course course) {
		List<Course> list = new ArrayList<Course>();
		String sql = "SELECT stu.id '学号',stu.name '姓名',clazz.id '课程号',clazz.name '课程名称'"
				+ ",clazz.credit '课程学分',g.score '成绩'"
				+ "				FROM student_info stu "
				+ "				JOIN grade g ON g.student_id = stu.id"
				+ "				JOIN curriculum  clazz ON clazz.id=g.curriculum_id "
				+ "				where stu.id=? and clazz.id = ?;";
		Connection conn = DbUtil.getConnection();
		PreparedStatement preStat;
		try {
			preStat = conn.prepareStatement(sql);
			preStat.setString(1, course.getStuID());
			preStat.setString(2, course.getClazzID());
			ResultSet rs = preStat.executeQuery();
			while (rs.next()) {
				String stuID = rs.getString("学号");
				String stuName = rs.getString("姓名");
				String clazzID = rs.getString("课程号");
				String clazzName = rs.getString("课程名称");
				String clazzCredit = rs.getString("课程学分");
				String score = rs.getString("成绩");
				
				course.setStuID(stuID);
				course.setStuName(stuName);
				course.setClazzID(clazzID);
				course.setClazzName(clazzName);
				course.setClazzCredit(clazzCredit);
				course.setScore(score);
				
				list.add(course);
			}
			DbUtil.close(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// 添加学生信息
	public List<Student> addStuInfo(Student student){
		String sql = "insert into student_info values(?,?,?,?,?);";
		
		List<Student> list =  getStuInfoByStuID(student);
		Connection conn = DbUtil.getConnection();
		try {
			if (list.isEmpty()) {
				PreparedStatement preStat = conn.prepareStatement(sql);
				preStat.setString(1, student.getId());
				preStat.setString(2, student.getName());
				preStat.setString(3, student.getSex());
				preStat.setString(4, student.getBirthday());
				preStat.setString(5, student.getAddress());
				int rs = preStat.executeUpdate();
				System.out.println(rs);
				DbUtil.close(preStat);
				DbUtil.close(conn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 添加课程信息
	public List<Clazz> addClazzInfo(Clazz clazz) {
		String sql = "insert into curriculum values(?,?,?);";
		
		Connection conn = DbUtil.getConnection();
		List<Clazz> list =  getClazzInfoByClazzID(clazz);
		try {
			if (list.isEmpty()) {
				PreparedStatement preStat = conn.prepareStatement(sql);
				preStat.setString(1, clazz.getId());
				preStat.setString(2, clazz.getName());
				preStat.setString(3, clazz.getCredit());
				int rs = preStat.executeUpdate();
				System.out.println(rs);
				DbUtil.close(preStat);
				DbUtil.close(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 添加选课信息
	public List<Course> addCourseInfo(Course course) {
		String sql = "insert into grade values(?,?,?);";
		
		
		Connection conn = DbUtil.getConnection();
		List<Course> list =  getCourseInfoByID(course);
		try {
				if(list.isEmpty()) {
					PreparedStatement preStat = conn.prepareStatement(sql);
					preStat.setString(1, course.getStuID());
					preStat.setString(2, course.getClazzID());
					preStat.setString(3, course.getScore());
					int rs = preStat.executeUpdate();
					System.out.println(rs);
					DbUtil.close(preStat);
					DbUtil.close(conn);
				}
		} catch (SQLException e) {
		}
		return list;
	}
	 
	// 删除学生信息
	public boolean delStuInfo(Student student) {
		String sqlG = "delete from grade where student_id = ?;";
		String sqlS =  "delete from student_info where id = ?;";
		
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement preStat = conn.prepareStatement(sqlG);
			preStat.setString(1, student.getId());
			int rs = preStat.executeUpdate();
			preStat = conn.prepareStatement(sqlS);
			preStat.setString(1, student.getId());
			preStat.executeUpdate();
			System.out.println(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	// 删除 课程信息
	public boolean delClazzInfo(Clazz clazz) {
		String sqlG = "delete from grade where curriculum_id = ?;";
		String sqlS =  "delete from curriculum where id = ?;";
		
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement preStat = conn.prepareStatement(sqlG);
			preStat.setString(1, clazz.getId());
			int rs = preStat.executeUpdate();
			preStat = conn.prepareStatement(sqlS);
			preStat.setString(1, clazz.getId());
			preStat.executeUpdate();
			System.out.println(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	// 删除选课信息
	public boolean delCourseInfo(Course course) {
		String sql = "delete from grade where student_id = ? and curriculum_id = ?;";
		
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement preStat = conn.prepareStatement(sql);
			preStat.setString(1, course.getStuID());
			preStat.setString(2, course.getClazzID());
			
			int rs = preStat.executeUpdate();
			System.out.println(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	
	// 获取所有学生的信息
	public List<Student> getAllStuInfos(){
		List<Student> list = new ArrayList<Student>();
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from student_info;";
		
		try {
			PreparedStatement preStat = conn.prepareStatement(sql);
			ResultSet rs = preStat.executeQuery();
			// 获取 每个学生的信息
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String address = rs.getString("address");
				
				Student student = new Student();
				student.setId(id);
				student.setName(name);
				student.setSex(sex);
				student.setBirthday(birthday);
				student.setAddress(address);
				
				list.add(student);
			}
			DbUtil.close(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	// 获取所有课程的信息
	public List<Clazz> getAllClazzInfos(){
		List<Clazz> list = new ArrayList<Clazz>();
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from curriculum;";
		
		try {
			PreparedStatement preStat = conn.prepareStatement(sql);
			ResultSet rs = preStat.executeQuery();
			// 获取 学生的信息
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String credit = rs.getString("credit");
				
				Clazz clazz = new Clazz();
				clazz.setId(id);
				clazz.setName(name);
				clazz.setCredit(credit);
				
				list.add(clazz);
			}
			DbUtil.close(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	// 获取所有的选课信息
	public List<Course> getAllCourseInfos() {
		List<Course> list = new ArrayList<Course>();
		String sql = "SELECT stu.id '学号',stu.name '姓名',clazz.id '课程编号',clazz.name '课程名称'"
				+ ",clazz.credit '课程学分',g.score '成绩'"
				+ "				FROM student_info stu "
				+ "				JOIN grade g ON g.student_id = stu.id"
				+ "				JOIN curriculum  clazz ON clazz.id=g.curriculum_id "
				+ "				order by stu.id;";
		Connection conn = DbUtil.getConnection();
		PreparedStatement preStat;
		try {
			preStat = conn.prepareStatement(sql);
			ResultSet rs = preStat.executeQuery();
			while (rs.next()) {
				String stuID = rs.getString("学号");
				String stuName = rs.getString("姓名");
				String clazzID = rs.getString("课程编号");
				String clazzName = rs.getString("课程名称");
				String clazzCredit = rs.getString("课程学分");
				String score = rs.getString("成绩");
				
				Course course = new Course();
				course.setStuID(stuID);
				course.setStuName(stuName);
				course.setClazzID(clazzID);
				course.setClazzName(clazzName);
				course.setClazzCredit(clazzCredit);
				course.setScore(score);
				
				list.add(course);
			}
			DbUtil.close(rs);
			DbUtil.close(preStat);
			DbUtil.close(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Student> updStuInfo(Student student,String id){
		String sql = "update student_info set id=?, name=?, sex=?, birthday=?,address = ? where id = ?;";
		
		List<Student> list =  getStuInfoByStuID(student);
		Connection conn = DbUtil.getConnection();
		try {
			if (!list.isEmpty()) {
				PreparedStatement preStat = conn.prepareStatement(sql);
				preStat.setString(1, student.getId());
				preStat.setString(2, student.getName());
				preStat.setString(3, student.getSex());
				preStat.setString(4, student.getBirthday());
				preStat.setString(5, student.getAddress());
				preStat.setString(6, id);
				int rs = preStat.executeUpdate();
				System.out.println(rs);
				DbUtil.close(preStat);
				DbUtil.close(conn);
				list = getStuInfoByStuID(student);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 更新课程信息
	public List<Clazz> updClazzInfo(Clazz clazz) {
		String sql = "update curriculum set id=?, name = ?, credit = ? where ;";
		
		Connection conn = DbUtil.getConnection();
		List<Clazz> list =  getClazzInfoByClazzID(clazz);
		try {
			if (!list.isEmpty()) {
				PreparedStatement preStat = conn.prepareStatement(sql);
				preStat.setString(1, clazz.getId());
				preStat.setString(2, clazz.getName());
				preStat.setString(3, clazz.getCredit());
				int rs = preStat.executeUpdate();
				System.out.println(rs);
				DbUtil.close(preStat);
				DbUtil.close(conn);
				list = getClazzInfoByClazzID(clazz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 更新选课信息
	public List<Course> updCourseInfo(Course course) {
		String sql = "update grade set student_id = ?, set curriculum_id = ?, set score = ?;";
		
		
		Connection conn = DbUtil.getConnection();
		List<Course> list =  getCourseInfoByID(course);
		try {
				if(!list.isEmpty()) {
					PreparedStatement preStat = conn.prepareStatement(sql);
					preStat.setString(1, course.getStuID());
					preStat.setString(2, course.getClazzID());
					preStat.setString(3, course.getScore());
					int rs = preStat.executeUpdate();
					System.out.println(rs);
					DbUtil.close(preStat);
					DbUtil.close(conn);
					list = getCourseInfoByID(course);
				}
		} catch (SQLException e) {
		}
		return list;
	}
	
}
