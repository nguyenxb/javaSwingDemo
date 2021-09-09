package viewer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.Clazz;
import bean.Course;
import bean.Student;

@SuppressWarnings("serial")
public class AddCourseDialog extends JDialog implements ActionListener,MouseListener,KeyListener{
	/**
	 * 添加选课信息对话框:
	 * */
	private JFrame frame;
	// 创建行式盒对象
	private Box boxH,controlBox,titleBox;
	// 创建列式盒对象
	private Box boxV1,boxV2;
	private Box boxV;
	
	
	private JTextField stuIDField;
	private JLabel stuIDLabel;
	
	private JTextField stuNameField;
	private JLabel stuNameLabel;
	
	private JLabel clazzIDLabel;
	private JTextField clazzIDField;
	
	private JLabel clazzNameLabel;
	private JTextField clazzNameField;
	
	private JLabel clazzCreditLabel;
	private JTextField clazzCreditField;
	
	private JLabel scoreLabel;
	private JTextField scoreField;
	private JButton submit;
	private JButton cancel;
	 
	private Course course = null;
	 
	private ViewerDao viewerDao = new ViewerDao();
	private String[] Fieldtext;
	private DefaultTableModel model;
	
	public AddCourseDialog(JFrame frame, DefaultTableModel model) {
		
		super(frame,"添加选课信息");
		
		setLayout(new FlowLayout());
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕尺寸
		int screenWidth = screenSize.width; // 屏幕宽度
		int screenHight = screenSize.height; // 屏幕高度
		// 创建窗口
		setBounds(0, 0, 600, 600);
		int windowWitdth = getWidth(); // 窗口宽度
		int windowHight = getHeight(); // 窗口高度
		setLocation(screenWidth/2-windowWitdth/2,screenHight/2 - windowHight/2);
		
		setVisible(true);
		this.frame = frame;
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		boxH = Box.createHorizontalBox();
		boxV1 = Box.createVerticalBox();
		boxV2 = Box.createVerticalBox();
		
		stuIDLabel = new JLabel("学号:");
		stuNameLabel = new JLabel("姓名:");
		clazzIDLabel = new JLabel("课程编号:");
		clazzNameLabel = new JLabel("课程名称:");
		clazzCreditLabel = new JLabel("课程学分:");
		scoreLabel = new JLabel("成绩:");
		
		
		stuIDField = new JTextField("1234",20);
		stuNameField = new JTextField("张三",20);
		clazzIDField = new JTextField("0001",20);
		clazzNameField = new JTextField("数据结构与算法",20);
		clazzCreditField = new JTextField("6",20);
		scoreField = new JTextField("99",20);
		
		
		
		Font font = new Font(null, 0, 20);
		
		stuIDLabel.setFont(font);
		stuNameLabel.setFont(font);
		clazzIDLabel.setFont(font);
		clazzNameLabel.setFont(font);
		clazzCreditLabel.setFont(font);
		scoreLabel.setFont(font);
		
		stuIDField.setFont(font);
		stuNameField.setFont(font);
		clazzIDField.setFont(font);
		clazzNameField.setFont(font);
		clazzCreditField.setFont(font);
		scoreField.setFont(font);
		
		submit = new JButton("提交");
		cancel = new JButton("取消");
		
		controlBox = Box.createHorizontalBox();
		controlBox.add(cancel);
		controlBox.add(Box.createHorizontalStrut(100));
		controlBox.add(submit);
		
		boxV1.add(Box.createGlue());
		boxV1.add(stuIDLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(stuNameLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(clazzIDLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(clazzNameLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(clazzCreditLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(scoreLabel);
		boxV1.add(Box.createGlue());
		
		
		boxV2.add(stuIDField);
		boxV2.add(stuNameField);
		boxV2.add(clazzIDField);
		boxV2.add(clazzNameField);
		boxV2.add(clazzCreditField);
		boxV2.add(scoreField);
		
		
		boxH.add(boxV1);
		boxH.add(boxV2);
		
		boxV = Box.createVerticalBox();
		titleBox = Box.createHorizontalBox();
		JLabel title = new  JLabel("添加选课信息");
		title.setFont(new Font(null, 0, 30));
		titleBox.add(title);
		boxV.add(titleBox);
		boxV.add(Box.createVerticalStrut(50));
		boxV.add(boxH);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(controlBox);
		add(boxV);
		
		stuIDField.addMouseListener(this);
		stuNameField.addMouseListener(this);
		clazzIDField.addMouseListener(this);
		clazzNameField.addMouseListener(this);
		clazzCreditField.addMouseListener(this);
		scoreField.addMouseListener(this);
		
		stuIDField.addKeyListener(this);
		stuNameField.addKeyListener(this);
		clazzIDField.addKeyListener(this);
		clazzNameField.addKeyListener(this);
		clazzCreditField.addKeyListener(this);
		scoreField.addKeyListener(this);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			course = new Course();
			
			String stuID = stuIDField.getText().trim();
			String stuName = stuNameField.getText().trim();
			String clazzID = clazzIDField.getText().trim();
			String clazzName = clazzNameField.getText().trim();
			String clazzCredit = clazzCreditField.getText().trim();
			String score = scoreField.getText().trim();
			
			course.setStuID(stuID);
			course.setStuName(stuName);
			course.setClazzID(clazzID);
			course.setClazzName(clazzName);
			course.setClazzCredit(clazzCredit);
			course.setScore(score);
			
			if(course.checkStuID() && 
					course.checkStuName() &&
					course.checkClazzID() &&
					course.checkClazzName() &&
					course.checkClazzCredit() && 
					course.checkScore()) {
				Student student = new Student();
				Clazz clazz = new Clazz();
				
				student.setId(course.getStuID());
				clazz.setId(course.getClazzID());
				
				List<Student> stuList = viewerDao.getStuInfoByStuID(student);
				List<Clazz> clazzList = viewerDao.getClazzInfoByclazzID(clazz);
				
				if (!stuList.isEmpty() && !clazzList.isEmpty()) {
					
					List<Course> courseList =  viewerDao.addCourse(course);
					if(!courseList.isEmpty()) {
						StringBuilder str = new StringBuilder();
						for (int i = 0; i < courseList.size(); i++) {
							str.append("选课信息 "+(i+1)+":");
							str.append(courseList.get(i).toString());
						}
						JOptionPane.showMessageDialog(frame, "已存在"+str);
					}else {
						model.addRow(new Object[] {
								course.getStuID(),
								course.getStuName(),
								course.getClazzID(),
								course.getClazzName(),
								course.getClazzCredit(),
								course.getScore(),
						});
						StringBuilder str = new StringBuilder();
						str.append("选课信息 :");
						str.append(course.toString());
						JOptionPane.showMessageDialog(frame, "成功添加"+str);
						setVisible(false);
					}
				}else {
					if(stuList.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "未找到学生信息 :"+student.toString());
					}
					if(clazzList.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "未找到课程信息 :"+clazz.toString());
					}
				}
				
				
				
			}else {
				if (!course.checkStuID()) {
					JOptionPane.showMessageDialog(frame, "错误:学号非数字格式或长度大于4(示例:1234)");
				}else if (!course.checkStuName()) {
					JOptionPane.showMessageDialog(frame, "错误:姓名长度大于4(示例: 张三)");
				}else if (!course.checkClazzID() ) {
					JOptionPane.showMessageDialog(frame, "错误:课程编号非数字格式或长度大于4(示例: 0001)");
				}else if (!course.checkClazzName()) {
					JOptionPane.showMessageDialog(frame, "错误:姓名长度大于50(示例: 数据结构与算法)");
				}else if(!course.checkClazzCredit()){
					JOptionPane.showMessageDialog(frame, "错误:课程学分非数字格式或长度大于2(示例: [1-10])");
				}else if (!course.checkScore()) {
					JOptionPane.showMessageDialog(frame, "错误:成绩非数字格式或长度大于3(示例: [0-100])");
				}else {
					JOptionPane.showMessageDialog(frame, "错误:未知错误 ");
				}
			}
			
		}else if (e.getSource() == cancel) {
			setVisible(false);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() <= 2) {
			if (e.getSource() == stuIDField) {
				Fieldtext[0] = stuIDField.getText().trim();
				stuIDField.setText("");
			}else if (e.getSource() == stuNameField) {
				Fieldtext[1] = stuNameField.getText().trim();
				stuNameField.setText("");
			}else if (e.getSource() == clazzIDField) {
				Fieldtext[2] = clazzIDField.getText().trim();
				clazzIDField.setText("");
			}else if (e.getSource() == clazzNameField) {
				Fieldtext[3] = clazzNameField.getText().trim();
				clazzNameField.setText("");
			}else if (e.getSource() == clazzCreditField) {
				Fieldtext[4] = clazzCreditField.getText().trim();
				clazzCreditField.setText("");
			}else if (e.getSource() == scoreField) {
			Fieldtext[5] = scoreField.getText().trim();
			scoreField.setText("");
			}
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
		Fieldtext = new String[6];
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == stuIDField) {
			String stuID = stuIDField.getText().trim();
			if (stuID.equals(Fieldtext[0]) || stuID.equals("")) {
				stuIDField.setText(Fieldtext[0]);
			}
		}
		if (e.getSource() == stuNameField) {
				String stuName = stuNameField.getText().trim();
				if (stuName.equals(Fieldtext[1]) || stuName.equals("")) {
					stuNameField.setText(Fieldtext[1]);
				}
		}
		if (e.getSource() == clazzIDField) {
				String clazzID = clazzIDField.getText().trim();
				if (clazzID.equals(Fieldtext[2]) || clazzID.equals("")) {
					clazzIDField.setText(Fieldtext[2]);
				}
		}
		if (e.getSource() == clazzNameField) {
				String clazzName = clazzNameField.getText().trim();
				if (clazzName.equals(Fieldtext[3]) || clazzName.equals("")) {
					clazzNameField.setText(Fieldtext[3]);
				}
		}
		if (e.getSource() == clazzCreditField) {
				String clazzCredit = clazzCreditField.getText().trim();
				if (clazzCredit.equals(Fieldtext[4]) || clazzCredit.equals("")) {
					clazzCreditField.setText(Fieldtext[4]);
				}
		}
		if (e.getSource() == scoreField) {
			String score = scoreField.getText().trim();
			if (score.equals(Fieldtext[5]) || score.equals("")) {
				scoreField.setText(Fieldtext[5]);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == stuIDField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String stuID = stuIDField.getText().trim();
				Fieldtext[0] = stuID;
			}
		}
		if (e.getSource() == stuNameField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String stuName = stuNameField.getText().trim();
				Fieldtext[1] = stuName;
			}
		}
		if (e.getSource() == clazzIDField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String clazzID = clazzIDField.getText().trim();
				Fieldtext[2] = clazzID;
			}
		}
		if (e.getSource() == clazzNameField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String clazzName = clazzNameField.getText().trim();
				Fieldtext[3] = clazzName;
			}
		}
		if (e.getSource() == clazzCreditField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String clazzCredit = clazzCreditField.getText().trim();
				Fieldtext[4] = clazzCredit;
			}
		}
		if (e.getSource() == scoreField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String score = scoreField.getText().trim();
				Fieldtext[5] = score;
			}
		}
	}
	
}
