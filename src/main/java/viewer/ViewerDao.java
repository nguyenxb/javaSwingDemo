package viewer;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import bean.Clazz;
import bean.Course;
import bean.Student;
import database.DataDao;

// 封装 表格数据的对象
public class ViewerDao {
	/**
	 * 封装 从后端获取到数据的方法，只获取到jtable的模组与数据，
	 * 已经各个按钮的响应方法
	 * 无需知道获取数据细节
	 * 从DataDao类中获取到的数据
	 * */
	private String[] columnNamesOfStudent = {"学号","姓名","性别","出生日期","家庭地址"};
	private String[] columnNamesOfCourse = {"学号","姓名","课程编号","课程名称","课程学分","成绩"};
	private String[] columnNamesOfClazz = {"课程编号","课程名称","课程学分"};
	// 封装操作数据库的对象
	private DataDao dataDao = new DataDao();
	
	// 通过学号获取学生信息
	public List<Student> getStuInfoByStuID (Student student) {
		return dataDao.getStuInfoByStuID(student);
		
	}
	// 通过课程号获取课程信息
	public List<Clazz> getClazzInfoByclazzID (Clazz clazz) {
		return dataDao.getClazzInfoByClazzID(clazz);
		
	}
	// 通过学号和课程号获取选课信息
	public List<Course> getCourseInfoByID (Course course) {
		return dataDao.getCourseInfoByID(course);
		
	}
	// 添加学生信息
	public List<Student> addStudent(Student student) {
		return dataDao.addStuInfo(student);
	}
	// 添加课程信息
	public List<Clazz> addClazz(Clazz clazz){
		return dataDao.addClazzInfo(clazz);
	}
	// 添加选课信息
	public List<Course> addCourse(Course course){
		return dataDao.addCourseInfo(course);
	}
	// 获取学生信息的数据集
	public  DefaultTableModel getModelOfStuInfos() {
		List<Student> list = dataDao.getAllStuInfos();
		DefaultTableModel model = getModelOfStuInfos(list);
		return model;
	}
	// 获取选课信息的数据集
	public  DefaultTableModel getModelOfCourseInfos() {
		List<Course> list = dataDao.getAllCourseInfos();
		DefaultTableModel model = getModelOfCourseInfos(list);
		return model;
	}
	// 获取课程信息的数据集
	public  DefaultTableModel getModelOfClazzInfos() {
		List<Clazz> list = dataDao.getAllClazzInfos();
		DefaultTableModel model = getModelOfClazzInfos(list);
		return model;
	}
	// 设置选课信息的模板
	public void setModelOfCourseInfos(JTable table) {

		// 设置列居中对齐
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				table.add(renderer);
				table.getColumn("学号").setCellRenderer(renderer);
				table.getColumn("姓名").setCellRenderer(renderer);
				table.getColumn("课程编号").setCellRenderer(renderer);
				table.getColumn("课程名称").setCellRenderer(renderer);
				table.getColumn("课程学分").setCellRenderer(renderer);
				table.getColumn("成绩").setCellRenderer(renderer);
				
				// 设置表格宽，以及拖动的最大宽度和最小宽度
				DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
				// 学号
				columnModel.getColumn(0).setPreferredWidth(40);
				columnModel.getColumn(0).setMinWidth(40);
				columnModel.getColumn(0).setMaxWidth(80);
				
				// 姓名
				// 默认宽度
				columnModel.getColumn(1).setPreferredWidth(60);
				// 最小宽度
				columnModel.getColumn(1).setMinWidth(60);
				//最大宽度
				columnModel.getColumn(1).setMaxWidth(80);
				
				// 课程编号
				// 默认宽度
				columnModel.getColumn(2).setPreferredWidth(60);
				// 最小宽度
				columnModel.getColumn(2).setMinWidth(60);
				//最大宽度
				columnModel.getColumn(2).setMaxWidth(100);
				
				// 课程名称
				// 默认宽度
				columnModel.getColumn(3).setPreferredWidth(150);
				// 最小宽度
				columnModel.getColumn(3).setMinWidth(150);
				//最大宽度
				columnModel.getColumn(3).setMaxWidth(200);
				
				// 课程学分
				// 默认宽度
				columnModel.getColumn(4).setPreferredWidth(60);
				// 最小宽度
				columnModel.getColumn(4).setMinWidth(60);
				//最大宽度
				columnModel.getColumn(4).setMaxWidth(100);
				
				table.setRowHeight(30);
	}
	// 设置课程信息的模板
	public void setModelOfClazzInfos(JTable table) {
		// 设置列居中对齐
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.add(renderer);
		table.getColumn("课程编号").setCellRenderer(renderer);
		table.getColumn("课程名称").setCellRenderer(renderer);
		table.getColumn("课程学分").setCellRenderer(renderer);
		
		// 设置表格宽，以及拖动的最大宽度和最小宽度
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		// 课程编号
		// 默认宽度
		columnModel.getColumn(0).setPreferredWidth(100);
		// 最小宽度
		columnModel.getColumn(0).setMinWidth(100);
		//最大宽度
		columnModel.getColumn(0).setMaxWidth(200);
		
		// 课程名称
		// 默认宽度
		columnModel.getColumn(1).setPreferredWidth(300);
		// 最小宽度
		columnModel.getColumn(1).setMinWidth(300);
		//最大宽度
		columnModel.getColumn(1).setMaxWidth(400);
		
		table.setRowHeight(30);
	}
	// 设置学生信息的模板
	public void setModelOfStuInfos(JTable table) {
		// 设置列居中对齐
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.add(renderer);
		table.getColumn("学号").setCellRenderer(renderer);
		table.getColumn("姓名").setCellRenderer(renderer);
		table.getColumn("性别").setCellRenderer(renderer);
		table.getColumn("出生日期").setCellRenderer(renderer);
		table.getColumn("家庭地址").setCellRenderer(renderer);
		
		// 设置表格宽，以及拖动的最大宽度和最小宽度
		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		// 学号
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(0).setMinWidth(40);
		columnModel.getColumn(0).setMaxWidth(80);
		
		// 姓名
		// 默认宽度
		columnModel.getColumn(1).setPreferredWidth(50);
		// 最小宽度
		columnModel.getColumn(1).setMinWidth(50);
		//最大宽度
		columnModel.getColumn(1).setMaxWidth(80);
		
		// 性别
		// 默认宽度
		columnModel.getColumn(2).setPreferredWidth(50);
		// 最小宽度
		columnModel.getColumn(2).setMinWidth(50);
		//最大宽度
		columnModel.getColumn(2).setMaxWidth(80);
		
		// 出生日期
		// 默认宽度
		columnModel.getColumn(3).setPreferredWidth(100);
		// 最小宽度
		columnModel.getColumn(3).setMinWidth(100);
		//最大宽度
		columnModel.getColumn(3).setMaxWidth(200);
		
		table.setRowHeight(30);
	}
	
	
	
	// 获取表格模型
	private  DefaultTableModel getModelOfStuInfos(List<Student> list) {
		DefaultTableModel model = new DefaultTableModel(columnNamesOfStudent,0);
		if(list!=null) {
			for (Student student : list) {
				model.addRow(new Object[] {
						student.getId(),
						student.getName(),
						student.getSex(),
						student.getBirthday(),
						student.getAddress(),
				});
			}
		}
		return model;
	}
	private DefaultTableModel getModelOfCourseInfos(List<Course> list) {
		DefaultTableModel model = new DefaultTableModel(columnNamesOfCourse,0);
		if(list!=null) {
			for (Course course : list) {
				model.addRow(new Object[] {
					course.getStuID(),
					course.getStuName(),
					course.getClazzID(),
					course.getClazzName(),
					course.getClazzCredit(),
					course.getScore()
				});
			}
		}
		return model;
	}

	private DefaultTableModel getModelOfClazzInfos(List<Clazz> list) {
		DefaultTableModel model = new DefaultTableModel(columnNamesOfClazz,0);
		if(list!=null) {
			for (Clazz clazz : list) {
				model.addRow(new Object[] {
					clazz.getId(),
					clazz.getName(),
					clazz.getCredit()
				});
			}
		}
		return model;
	}

	// 删除操作
	public void delStuInfo(Student student) {
		dataDao.delStuInfo(student);
	}

	public void delClazzInfo(Clazz clazz) {
		dataDao.delClazzInfo(clazz);
		
	}

	public void delCourseInfo(Course course) {
		dataDao.delCourseInfo(course);
	}
//	通过学号获取到数据库中的学生数据
	public DefaultTableModel getModelOfStuInfoByID(Student student) {
		List<Student> list = dataDao.getStuInfoByStuID(student);
		DefaultTableModel model = getModelOfStuInfos(list);
		return model;
	}
	public DefaultTableModel getModelOfClazzInfoByID(Clazz clazz) {
		List<Clazz> list = dataDao.getClazzInfoByClazzID(clazz);
		DefaultTableModel model = getModelOfClazzInfos(list);
		return model;
	}
	public DefaultTableModel getModelOfCourseInfoByID(Course course) {
		List<Course> list = dataDao.getCourseInfoByID(course);
		DefaultTableModel model = getModelOfCourseInfos(list);
		return model;
	}


}
