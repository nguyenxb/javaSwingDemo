package viewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.Clazz;
import bean.Course;
import bean.Student;

public class BtnListener implements ActionListener ,MouseListener,KeyListener,ItemListener{
	/**
	 * 按钮监听类，封装按钮事件
	 * */
	private ViewerDao viewerDao = new ViewerDao();
	// 按钮，及文本框
	private JButton addBtn = null;
	private JButton delBtn = null;
	private JButton selBtn = null;
	private JTextField inputText = null;
	private JFrame frame = null;
	// 存储文本框的信息
	private String text = null;
	
	// 文本框文字
	private final String stuText = "学号(例:1234),输入'#'返回全部信息"; 
	private final String clazzText = "课程编号(例:0001),输入'#'返回全部信息";
	private final String courseText = "[学号-课程编号](例:1234-0001),输入'#'返回全部信息";

	public JTextField getInputText() {
		return inputText;
	}



	public void setInputText(JTextField inputText) {
		this.inputText = inputText;
	}


	// 滚动面板
	private JScrollPane scrollPane = null;
	// 选择盒
	private JComboBox<String> selBox = null;
	// 数据模板
	private DefaultTableModel model = null;
	// 数据表格
	private JTable table = null;
	
	
	
	public JButton getAddBtn() {
		return addBtn;
	}



	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}



	public JButton getDelBtn() {
		return delBtn;
	}



	public void setDelBtn(JButton delBtn) {
		this.delBtn = delBtn;
	}


	public JButton getSelBtn() {
		return selBtn;
	}



	public void setSelBtn(JButton selBtn) {
		this.selBtn = selBtn;
	}



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}



	public JScrollPane getScrollPane() {
		return scrollPane;
	}



	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}



	public JComboBox<String> getSelBox() {
		return selBox;
	}



	public void setSelBox(JComboBox<String> selBox) {
		this.selBox = selBox;
	}



	public DefaultTableModel getModel() {
		return model;
	}



	public void setModel(DefaultTableModel model) {
		this.model = model;
	}



	public JTable getTable() {
		return table;
	}



	public void setTable(JTable table) {
		this.table = table;
	}
	// 添加按钮的监听方法
	private void add() {
//			System.out.println("添加学生");
//			System.out.println("添加学生信息");
			if (selBox.getSelectedItem()==selBox.getItemAt(0)) {
				new AddStuDialog(frame,model);
				
			}else if(selBox.getSelectedItem()==selBox.getItemAt(1)){
//				System.out.println("添加选课信息");
				new AddClazzDialog(frame,model);
			}else if (selBox.getSelectedItem()==selBox.getItemAt(2)) {
//				System.out.println("添加选课信息");
				new AddCourseDialog(frame,model);
			}
		}
	// 查询按钮的监听方法
	private void sel() {
		
		// 查询学生信息
		if (selBox.getSelectedItem()==selBox.getItemAt(0)) {
//			System.out.println("查询学生信息");
			// 将原来的数据表格面板删除
			frame.remove(scrollPane);
			// 重置数据表格面板
			if(inputText.getText().trim().equals(stuText) || 
					inputText.getText().trim().equals("#") ||
					inputText.getText().trim().equals("")) {
				model = viewerDao.getModelOfStuInfos();// 添加数据模板
			}else {
				Student student = new Student();
				student.setId(inputText.getText().trim());
				if (student.checkID()) {
					model = viewerDao.getModelOfStuInfoByID(student);
					if (model.getRowCount() == 0) {
						model = viewerDao.getModelOfStuInfos();
						JOptionPane.showMessageDialog(frame, "未找到学生信息"+student.toString());
					}
				}else {
					model = viewerDao.getModelOfStuInfos();
					JOptionPane.showMessageDialog(frame, "错误：学号非数字格式或长度大于4(示例: 0001)");
				}
			}
			table = new JTable(model);
			// 设置表格模板
			viewerDao.setModelOfStuInfos(table);
			scrollPane = new JScrollPane(table);
		}
		
		// 查询课程信息
		if (selBox.getSelectedItem()==selBox.getItemAt(1)) {
			// 将原来的数据表格面板删除
			frame.remove(scrollPane);
			// 重置数据表格面板
			
			if(inputText.getText().trim().equals(clazzText) ||
					inputText.getText().equals("#") ||
					inputText.getText().trim().equals("")) {
				model = viewerDao.getModelOfClazzInfos();
			}else {
				Clazz clazz = new Clazz();
				clazz.setId(inputText.getText().trim());
				if (clazz.checkClazzID()) {
					model = viewerDao.getModelOfClazzInfoByID(clazz);
					if (model.getRowCount() == 0) {
						model = viewerDao.getModelOfClazzInfos();
						JOptionPane.showMessageDialog(frame, "未找到课程信息"+clazz.toString());
					}
				}else {
					model = viewerDao.getModelOfClazzInfos();
					JOptionPane.showMessageDialog(frame, "错误：课程编号非数字格式或长度大于4(示例: 0001)");
				}
			}
			
			table = new JTable(model);
			viewerDao.setModelOfClazzInfos(table);
			scrollPane = new JScrollPane(table);
		}
		
		// 查询选课信息
		if (selBox.getSelectedItem()==selBox.getItemAt(2)) {
		// 将原来的数据表格面板删除
		frame.remove(scrollPane);
		// 重置数据表格面板
		// 当文本框没有输入的时候就查询所有数据，有输入的时候就按照学号,课程号查
		if(inputText.getText().trim().equals(courseText) ||
				inputText.getText().equals("#") ||
				inputText.getText().trim().equals("")) {
			model = viewerDao.getModelOfCourseInfos();
		}else {
			Course course = new Course();
			String strs = inputText.getText().trim();
			if(strs.split("-*\\s*-+\\s*-*").length == 2) {
				String[] str = strs.split("-*\\s*-+\\s*-*");
				course.setStuID(str[0]);
				course.setClazzID(str[1]);
				if (course.checkClazzID() && course.checkStuID()) {
					model = viewerDao.getModelOfCourseInfoByID(course);
					if (model.getRowCount() == 0) {
						model = viewerDao.getModelOfCourseInfos();
						JOptionPane.showMessageDialog(frame, "未找到课程信息"+course.toString());
					}
				}else {
					model = viewerDao.getModelOfCourseInfos();
					if (!course.checkStuID()) {
						JOptionPane.showMessageDialog(frame, "错误:学号非数字格式或长度大于4(示例:1234)");
					}else if (!course.checkClazzID() ) {
						JOptionPane.showMessageDialog(frame, "错误:课程编号非数字格式或长度大于4(示例: 1234-0001)");
					}
				}
			}else {
				JOptionPane.showMessageDialog(frame, "错误:查询条件格式错误(示例: 0001)");
			}
			
		}
		table = new JTable(model);
		viewerDao.setModelOfCourseInfos(table);
		scrollPane = new JScrollPane(table);
		}
		// 将数据表格面板加入的窗口中
		frame.add(scrollPane,BorderLayout.CENTER);
		// 重新加载数据表格面板，并显示
		frame.repaint();
		frame.setVisible(true);
		
		
	}
	// 删除按钮的监听方法
	private void del() {

			if (selBox.getSelectedItem()==selBox.getItemAt(0)) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Student student = new Student();
					String stuID = (String) model.getValueAt(row, 0);
					student.setId(stuID);
					viewerDao.delStuInfo(student);
					model.removeRow(row);
				}else {
					JOptionPane.showMessageDialog(frame, "请选中一条记录");
				}
//				System.out.println("删除学生信息");
			}
			if (selBox.getSelectedItem()==selBox.getItemAt(1)) {
//				System.out.println("删除选课信息");
				int row = table.getSelectedRow();
				if (row != -1) {
					Clazz clazz = new Clazz();
					String stuID = (String) model.getValueAt(row, 0);
					clazz.setId(stuID);
					viewerDao.delClazzInfo(clazz);
					model.removeRow(row);
				}else {
					JOptionPane.showMessageDialog(frame, "请选中一条记录");
				}
			}
			if (selBox.getSelectedItem()==selBox.getItemAt(2)) {
//				System.out.println("删除选课信息");
				int row = table.getSelectedRow();
				if (row != -1) {
					Course course = new Course();
					String stuID = (String) model.getValueAt(row, 0);
					String ClazzID = (String)model.getValueAt(row, 2);
					course.setStuID(stuID);
					course.setClazzID(ClazzID);
					viewerDao.delCourseInfo(course);
					model.removeRow(row);
				}else {
					JOptionPane.showMessageDialog(frame, "请选中一条记录");
				}
			}
		}
		
	



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addBtn) {
			add(); // 添加事件
		}
		if(e.getSource() == selBtn) {
			sel();// 查询事件
		}
		if(e.getSource() == delBtn) {
			del();// 删除事件
			
		}
	}



	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// 当输入文本框后直接回车 就调用查询方法
		if(e.getSource() == inputText) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				sel();
			}
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// 鼠标点击 清空文本框
		if(e.getSource() == inputText) {
			inputText.setText("");
		}		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// 鼠标进入 创建字符串对象，并且获取文本框的内容
//		，并且去除该两边的空格字符
		text = new String();
		text = inputText.getText().trim();
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// 鼠标退出文本框时，如果没有在文本框中键入内容
		// 则自动尝试恢复上一次的文本数据，
		if (e.getSource() == inputText) {
			String tmpText = inputText.getText().trim();
			if (text.equals(tmpText) || tmpText.equals("")) {
				inputText.setText(text);
			}
		}
		
	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		if(selBox.getSelectedItem()==selBox.getItemAt(0)) {
			text = stuText;
			inputText.setText(text);
		}
		if (selBox.getSelectedItem() == selBox.getItemAt(1)) {
			text = clazzText;
			inputText.setText(text);
		}
		if(selBox.getSelectedItem() == selBox.getItemAt(2)) {
			text = courseText;
			inputText.setText(text);
		}
	}

}
