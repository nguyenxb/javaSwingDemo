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

import bean.Student;

@SuppressWarnings("serial")
public class AddStuDialog extends JDialog implements ActionListener, MouseListener,KeyListener{
	/**
	 * 添加学生信息对话框:
	 * */
	// 学生对象
	private Student student =null ;
	
	private JTextField stuIDField;
	private JLabel stuIDLabel;
	
	private JTextField stuNameField;
	private JLabel stuNameLabel;
	
	private JTextField stuSexField;
	private JLabel stuSexLabel;
	
	private JTextField stuBirthdayField;
	private JLabel stuBirthdayLabel;
	
	private JTextField stuAddressField;
	private JLabel stuAddressLabel;
	
	private JButton submit,cancel;
	
	// 横式盒
	private Box boxH,titleBox,controlBox;
	// 列式盒
	private Box boxV,boxV1,boxV2;
	
	private ViewerDao viewerDao = new ViewerDao();
	
	private JFrame frame;
	
	private String[] Fieldtext = null;

	private DefaultTableModel model;
	
	
	public AddStuDialog(JFrame frame, DefaultTableModel model) {
		super(frame,"添加学生信息");
		
		setLayout(new FlowLayout());
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕尺寸
		int screenWidth = screenSize.width; // 屏幕宽度
		int screenHight = screenSize.height; // 屏幕高度
		// 设置窗口大小
		setBounds(0, 0, 600, 600);
		int windowWitdth = getWidth(); // 窗口宽度
		int windowHight = getHeight(); // 窗口高度
		setLocation(screenWidth/2-windowWitdth/2,screenHight/2 - windowHight/2);
		
		setVisible(true);
		this.frame = frame;
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		stuIDLabel = new JLabel("学号:");
		stuNameLabel = new JLabel("姓名:");
		stuSexLabel = new JLabel("性别:");
		stuBirthdayLabel = new JLabel("出生日期:");
		stuAddressLabel = new JLabel("家庭地址:");
		
		stuIDField = new JTextField("1234",20);
		stuNameField = new JTextField("xxx",20);
		stuSexField = new JTextField("男",20);
		stuBirthdayField = new JTextField("xxxx-xx-xx",20);
		stuAddressField = new JTextField("xxx市xxx区xxx镇",20);
		
		// 设置字体
		Font font = new Font(null, 0, 20);
		
		stuIDField.setFont(font);
		stuNameField.setFont(font);
		stuSexField.setFont(font);
		stuBirthdayField.setFont(font);
		stuAddressField.setFont(font);
		
		stuIDLabel.setFont(font);
		stuNameLabel.setFont(font);
		stuSexLabel.setFont(font);
		stuBirthdayLabel.setFont(font);
		stuAddressLabel.setFont(font);
		
		// 提交取消按钮
		submit = new JButton("提交");
		cancel = new JButton("取消");
		
		// 创建横式盒，
		controlBox = Box.createHorizontalBox();
		boxH = Box.createHorizontalBox();
		// 列式盒
		boxV1 = Box.createVerticalBox();
		boxV2 = Box.createVerticalBox();
		boxV = Box.createVerticalBox();
		
		// 添加取消与提交按钮，并在中间扩充两个按钮的距离
		controlBox.add(cancel);
		controlBox.add(Box.createHorizontalStrut(100));
		controlBox.add(submit);
		
		// 扩充组件的位置与文本框统一水平，列盒1添加文本标签
		boxV1.add(Box.createGlue());
		boxV1.add(stuIDLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(stuNameLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(stuSexLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(stuBirthdayLabel);
		boxV1.add(Box.createGlue());
		boxV1.add(stuAddressLabel);
		boxV1.add(Box.createGlue());
		
		// 列和2添加文本框
		boxV2.add(stuIDField);
		boxV2.add(stuNameField);
		boxV2.add(stuSexField);
		boxV2.add(stuBirthdayField);
		boxV2.add(stuAddressField);
		
		// 将两个列盒添加到一个横盒中
		boxH.add(boxV1);
		boxH.add(boxV2);
		
		// 添加存放标题的横盒子
		titleBox = Box.createHorizontalBox();
		JLabel title = new  JLabel("添加学生信息");
		title.setFont(new Font(null, 0, 30));
		titleBox.add(title);
		
		boxV.add(titleBox);
		boxV.add(Box.createVerticalStrut(50));
		boxV.add(boxH);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(controlBox);
		add(boxV);
		
		// 添加监听器
		stuIDField.addMouseListener(this);
		stuNameField.addMouseListener(this);
		stuSexField.addMouseListener(this);
		stuBirthdayField.addMouseListener(this);
		stuAddressField.addMouseListener(this);
		
		stuIDField.addKeyListener(this);
		stuNameField.addKeyListener(this);
		stuSexField.addKeyListener(this);
		stuBirthdayField.addKeyListener(this);
		stuAddressField.addKeyListener(this);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 提交操作
		if (e.getSource() == submit) {
			student = new Student();
			
			String stuID = stuIDField.getText().trim();
			String stuName = stuNameField.getText().trim();
			String stuSex = stuSexField.getText().trim();
			String stuBirthday = stuBirthdayField.getText().trim();
			String stuAddress = stuAddressField.getText().trim();
			
			student.setId(stuID);
			student.setName(stuName);
			student.setSex(stuSex);
			student.setBirthday(stuBirthday);
			student.setAddress(stuAddress);
			
			if(student.checkID() && 
				student.checkName() &&
				student.checkSex() &&
				student.checkDate()) {
				List<Student> list =  viewerDao.addStudent(student);
				if(!list.isEmpty()) {
					StringBuilder str = new StringBuilder();
					for (int i = 0; i < list.size(); i++) {
						str.append("学生信息 "+(i+1)+":");
						str.append(list.get(i).toString());
					}
					JOptionPane.showMessageDialog(frame, "已存在"+str);
				}else {
					model.addRow(new Object[] {
							student.getId(),
							student.getName(),
							student.getSex(),
							student.getBirthday(),
							student.getAddress(),
					});
					StringBuilder str = new StringBuilder();
						str.append("学生信息 :");
						str.append(student.toString());
					JOptionPane.showMessageDialog(frame, "成功添加"+str);
					setVisible(false);
				}
			}else {
				if (!student.checkID()) {
				System.out.println("id长度过长");
				JOptionPane.showMessageDialog(frame, "错误：学号非数字格式或长度大于4(示例: 0001)");
				}else if (!student.checkName()) {
					System.out.println("姓名长度过长");
					JOptionPane.showMessageDialog(frame, "错误： 姓名长度大于4(示例: 张三)");
				}else if (!student.checkSex() ) {
					System.out.println("性别长度过长");
					JOptionPane.showMessageDialog(frame, "错误： 性别输入错误或长度大于2(示例: 男|女)");
				}else if (!student.checkDate()) {
					System.out.println("日期输入错误");
					JOptionPane.showMessageDialog(frame, "错误： 日期格式错误(示例: 2021-01-01)");
				}else {
					System.out.println("未知错误");
					JOptionPane.showMessageDialog(frame, "未知错误");
				}
			}
			
		}
		if (e.getSource() == cancel) {
			setVisible(false);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == stuIDField) {
			Fieldtext[0] = stuIDField.getText().trim();
			stuIDField.setText("");
		}else if (e.getSource() == stuNameField) {
			Fieldtext[1] = stuNameField.getText().trim();
			stuNameField.setText("");
		}else if (e.getSource() == stuSexField) {
			Fieldtext[2] = stuSexField.getText().trim();
			stuSexField.setText("");
		}else if (e.getSource() == stuBirthdayField) {
			Fieldtext[3] = stuBirthdayField.getText().trim();
			stuBirthdayField.setText("");
		}else if (e.getSource() == stuAddressField) {
			Fieldtext[4] = stuAddressField.getText().trim();
			stuAddressField.setText("");
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
		Fieldtext = new String[5];
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == stuIDField) {
			String id = stuIDField.getText().trim();
			if (id.equals(Fieldtext[0]) || id.equals("")) {
				stuIDField.setText(Fieldtext[0]);
			}
		}
		if (e.getSource() == stuNameField) {
				String name = stuNameField.getText().trim();
				if (name.equals(Fieldtext[1]) || name.equals("")) {
					stuNameField.setText(Fieldtext[1]);
				}
		}
		if (e.getSource() == stuSexField) {
				String sex = stuSexField.getText().trim();
				if (sex.equals(Fieldtext[2]) || sex.equals("")) {
					stuSexField.setText(Fieldtext[2]);
				}
		}
		if (e.getSource() == stuBirthdayField) {
				String birthday = stuBirthdayField.getText().trim();
				if (birthday.equals(Fieldtext[3]) || birthday.equals("")) {
					stuBirthdayField.setText(Fieldtext[3]);
				}
		}
		if (e.getSource() == stuAddressField) {
				String address = stuAddressField.getText().trim();
				if (address.equals(Fieldtext[4]) || address.equals("")) {
					stuAddressField.setText(Fieldtext[4]);
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
				String id = stuIDField.getText().trim();
				Fieldtext[0] = id;
			}
		}
		if (e.getSource() == stuNameField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String name = stuNameField.getText().trim();
				Fieldtext[1] = name;
			}
		}
		if (e.getSource() == stuSexField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String name = stuSexField.getText().trim();
				Fieldtext[2] = name;
			}
		}
		if (e.getSource() == stuBirthdayField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String name = stuBirthdayField.getText().trim();
				Fieldtext[3] = name;
			}
		}
		if (e.getSource() == stuAddressField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String address = stuAddressField.getText().trim();
				Fieldtext[4] = address;
			}
		}
	}
}
