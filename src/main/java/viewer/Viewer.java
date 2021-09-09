package viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Viewer  {
	/**
	 *  函数入口类，封装基本的窗口组件
	 */
	// 按钮监听器
	private BtnListener btnListener = new BtnListener();
	// 从DataDao读取到数据的工具类
	private ViewerDao viewerDao = new ViewerDao();
	// 表格的模型
	private DefaultTableModel model = viewerDao.getModelOfStuInfos();
	// 带有数据的表格
	private JTable table = new JTable(model);
	
	// 窗口
	private JFrame frame;
	// 文本框标签
	private JLabel inputStuIDLabel ;
	// 文本框
	private JTextField inputStuIDField;
	// 查询,删除，添加,更新按钮
	private JButton selBtn,delBtn,addBtn;
	// 选择框，学生信息或选课信息
	private JComboBox<String> selBox;
	// 控制面板，放查询,删除，添加,更新按钮
	private JPanel ctrlPanel = null ;
	// 滚动面板，放表格数据
	private JScrollPane scrollPane;

	public Viewer() {
		// 新建窗口
		frame = new JFrame();
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕尺寸
		int screenWidth = screenSize.width; // 屏幕宽度
		int screenHight = screenSize.height; // 屏幕高度
		// 创建窗口大小
		frame.setBounds(0, 0, 700, 700);
		int windowWitdth = frame.getWidth(); // 窗口宽度
		int windowHight = frame.getHeight(); // 窗口高度
		frame.setLocation(screenWidth/2-windowWitdth/2,screenHight/2 - windowHight/2);
		frame.setTitle("ruasib");
		// 设置默认关闭窗口模式
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 初始化组件
		init();
		// 图像可视化
		frame.setVisible(true);
	}
	
	private void init() {
		inputStuIDLabel = new JLabel("学号:");
		inputStuIDField = new JTextField("学号(例:1234),输入'#'返回全部信息",30);
		selBtn = new JButton("查询");
		delBtn = new JButton("删除");
		addBtn = new JButton("添加");
		scrollPane = new JScrollPane(table);
		ctrlPanel = new JPanel();
		
		// 选择盒
		selBox = new JComboBox<String>();
		String[] items = {"学生信息","课程信息","选课信息"};
		for (String item : items) {
			selBox.addItem(item);
		}
		
		
		// 给工具对象添加操作表格对象
		viewerDao.setModelOfStuInfos(table);
		
		
		// 监听器获取要操作的组件
		btnListener.setInputText(inputStuIDField);
		btnListener.setAddBtn(addBtn);
		btnListener.setDelBtn(delBtn);
		btnListener.setSelBtn(selBtn);
		btnListener.setSelBox(selBox);
		btnListener.setScrollPane(scrollPane);
		btnListener.setTable(table);
		btnListener.setFrame(frame);
		btnListener.setModel(model);
		
		// 给文本框添加监听器，
		inputStuIDField.addKeyListener(btnListener);
		inputStuIDField.addMouseListener(btnListener);
		inputStuIDField.addActionListener(btnListener);
		
		// 给选择盒添加元素监听器
		selBox.addItemListener(btnListener);
		
		// 给查询，删除，添加按钮加监听器 
		selBtn.addActionListener(btnListener);
		delBtn.addActionListener(btnListener);
		addBtn.addActionListener(btnListener);
		
		// 将 文本标签，文本框，选择盒，查，删，增按钮添加到控制面板中
		ctrlPanel.add(inputStuIDLabel);
		ctrlPanel.add(inputStuIDField);
		ctrlPanel.add(selBox);
		ctrlPanel.add(selBtn);
		ctrlPanel.add(delBtn);
		ctrlPanel.add(addBtn);
		
		// 控制面板和滚动面板放到窗口中
		frame.add(ctrlPanel,BorderLayout.NORTH);
		frame.add(scrollPane,BorderLayout.CENTER);
	}



	public static void main(String[] args) {
//		运行程序
		new Viewer();
	}


	
}
