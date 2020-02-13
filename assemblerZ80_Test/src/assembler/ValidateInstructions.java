package assembler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

public class ValidateInstructions {

	private JFrame frmTemplate;
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JSplitPane splitPane1;
	private JTextArea txtLog;
	private JLabel lblLog;

	private String defaultDirectory;
	private File asmSourceFile = null;
	private Scanner scanner;
	private String sourceFileFullName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValidateInstructions window = new ValidateInstructions();
					window.frmTemplate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // try
			}// run
		});
	}// main

	private void prepareSourceFile(File asmSourceFile) {
		sourceFileFullName = asmSourceFile.getAbsolutePath();
		defaultDirectory = asmSourceFile.getParent();
		lblLog.setText(asmSourceFile.getName());
	}// prepareSourceFile

	/* Standard Stuff */

	private void updateStatus(int passed, int failed) {

		if (failed != 0) {
			lblStatus.setForeground(Color.RED);
		} else {
			lblStatus.setForeground(Color.BLACK);
		} // if
		String stat = String.format("%d Lines of Code, %d Passed, %d Failed", passed + failed, passed, failed);
		lblStatus.setText(stat);
	}

	private void doBtnOne() {
		int passed = 0;
		int failed = 0;
		updateStatus(passed, failed);

		txtLog.setText(EMPTY_STRING);
		scanner = null;
		try {
			scanner = new Scanner(asmSourceFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} //
		Matcher matcherTarget = null;
		Pattern patternTarget = Pattern.compile("(L.{2}:)|(L.{4}:)|(L.{8}:)");
		Matcher matcherPreCode = null;
		Pattern patternPreCode = Pattern.compile("[0-9]{4}:\\s[0-9A-F]{4}\\s");
		String originalLine = null;
		String asmCode, targetCode;
		int opCodeSize = 0;
		String reportLine,indicator;
		while (scanner.hasNextLine()) {
			originalLine = scanner.nextLine();
			matcherTarget = patternTarget.matcher(originalLine);
			if (matcherTarget.find()) {

				matcherPreCode = patternPreCode.matcher(originalLine);
				asmCode = matcherPreCode.replaceFirst(EMPTY_STRING);
				asmCode = asmCode.replace(SPACE, EMPTY_STRING);

				if (matcherTarget.group(1) != null) {
					opCodeSize = 2;
				} // if 2x
				if (matcherTarget.group(2) != null) {
					opCodeSize = 4;
				} // if 4x
				if (matcherTarget.group(3) != null) {
					opCodeSize = 8;
				} // if 3X

				targetCode = matcherTarget.group().substring(1, 1 + opCodeSize).toUpperCase();
				asmCode = asmCode.substring(0, opCodeSize);
				if (asmCode.equals(targetCode)) {
					passed++;
					indicator = EMPTY_STRING;
				} else {
					failed++;
					indicator = "*****";
				}
				updateStatus(passed, failed);
				
				reportLine = String.format("%-5s %-8s = %s%n", indicator,asmCode,targetCode);
				txtLog.append(reportLine);

			} // if

		} // try
		txtLog.setCaretPosition(0);
	}// doBtnOne

	private void doBtnTwo() {

	}// doBtnTwo

	private void doBtnThree() {

	}// doBtnThree

	private void doBtnFour() {
		asmSourceFile = new File(sourceFileFullName);
		prepareSourceFile(asmSourceFile);
	}// doBtnFour

	// ---------------------------------------------------------

	private void doFileNew() {

	}// doFileNew

	private void doFileOpen() {
//		JFileChooser chooserOpen = MyFileChooser.getFilePicker(defaultDirectory, "Assembler Source Code",
//				SUFFIX_LISTING); // SUFFIX_ASSEMBLER - SUFFIX_LISTING
		JFileChooser chooserOpen = new JFileChooser(defaultDirectory);	
		
		if (chooserOpen.showOpenDialog(frmTemplate) != JFileChooser.APPROVE_OPTION) {
			System.out.printf("%s%n", "You cancelled the file open");
		} else {
			// txtSource.setText("");
			// txtListing.setText("");
			asmSourceFile = chooserOpen.getSelectedFile();
			defaultDirectory = asmSourceFile.getParent();
			
			prepareSourceFile(asmSourceFile);

			// outputPathAndBase = defaultDirectory + FILE_SEPARATOR + sourceFileBase;

		} // if
	}// doFileOpen

	private void doFileSave() {

	}// doFileSave

	private void doFileSaveAs() {

	}// doFileSaveAs

	private void doFilePrint() {

	}// doFilePrint

	private void doFileExit() {
		appClose();
		System.exit(0);
	}// doFileExit

	private void appClose() {
		Preferences myPrefs = Preferences.userNodeForPackage(ValidateInstructions.class)
				.node(this.getClass().getSimpleName());
		Dimension dim = frmTemplate.getSize();
		myPrefs.putInt("Height", dim.height);
		myPrefs.putInt("Width", dim.width);
		Point point = frmTemplate.getLocation();
		myPrefs.putInt("LocX", point.x);
		myPrefs.putInt("LocY", point.y);
		myPrefs.putInt("Divider", splitPane1.getDividerLocation());

		myPrefs.put("defaultDirectory", defaultDirectory);
		myPrefs.put("LastFile", sourceFileFullName);

		myPrefs = null;

	}// appClose

	private void appInit() {
		Preferences myPrefs = Preferences.userNodeForPackage(ValidateInstructions.class)
				.node(this.getClass().getSimpleName());
		frmTemplate.setSize(myPrefs.getInt("Width", 761), myPrefs.getInt("Height", 693));
		frmTemplate.setLocation(myPrefs.getInt("LocX", 100), myPrefs.getInt("LocY", 100));
		splitPane1.setDividerLocation(myPrefs.getInt("Divider", 250));
		// txtLog.append(String.format("myPrefs.absolutePath() - %s%n", myPrefs.absolutePath()));
		defaultDirectory = myPrefs.get("defaultDirectory", DEFAULT_DIRECTORY);

		sourceFileFullName = myPrefs.get("LastFile", "");

		myPrefs = null;

	}// appInit

	public ValidateInstructions() {
		initialize();
		appInit();
	}// Constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTemplate = new JFrame();
		frmTemplate.setTitle("ValidateInstructions");
		frmTemplate.setBounds(100, 100, 450, 300);
		frmTemplate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTemplate.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				appClose();
			}
		});
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		frmTemplate.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frmTemplate.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		btnOne = new JButton("Validate");
		btnOne.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnOne = new GridBagConstraints();
		gbc_btnOne.insets = new Insets(0, 0, 0, 5);
		gbc_btnOne.gridx = 0;
		gbc_btnOne.gridy = 0;
		panel.add(btnOne, gbc_btnOne);
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doBtnOne();
			}
		});
		btnOne.setMaximumSize(new Dimension(0, 0));
		btnOne.setPreferredSize(new Dimension(100, 20));

		btnTwo = new JButton("Button 2");
		btnTwo.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnTwo = new GridBagConstraints();
		gbc_btnTwo.insets = new Insets(0, 0, 0, 5);
		gbc_btnTwo.gridx = 1;
		gbc_btnTwo.gridy = 0;
		panel.add(btnTwo, gbc_btnTwo);
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnTwo();
			}
		});
		btnTwo.setPreferredSize(new Dimension(100, 20));
		btnTwo.setMaximumSize(new Dimension(0, 0));

		btnThree = new JButton("Button 3");
		btnThree.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnThree = new GridBagConstraints();
		gbc_btnThree.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnThree.insets = new Insets(0, 0, 0, 5);
		gbc_btnThree.gridx = 2;
		gbc_btnThree.gridy = 0;
		panel.add(btnThree, gbc_btnThree);
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnThree();
			}
		});
		btnThree.setPreferredSize(new Dimension(100, 20));
		btnThree.setMaximumSize(new Dimension(0, 0));

		btnFour = new JButton("Last File Used");
		btnFour.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_btnFour = new GridBagConstraints();
		gbc_btnFour.anchor = GridBagConstraints.NORTH;
		gbc_btnFour.gridx = 3;
		gbc_btnFour.gridy = 0;
		panel.add(btnFour, gbc_btnFour);
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnFour();
			}
		});
		btnFour.setPreferredSize(new Dimension(100, 20));
		btnFour.setMaximumSize(new Dimension(0, 0));

		splitPane1 = new JSplitPane();
		GridBagConstraints gbc_splitPane1 = new GridBagConstraints();
		gbc_splitPane1.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane1.fill = GridBagConstraints.BOTH;
		gbc_splitPane1.gridx = 0;
		gbc_splitPane1.gridy = 1;
		frmTemplate.getContentPane().add(splitPane1, gbc_splitPane1);

		JPanel panelLeft = new JPanel();
		splitPane1.setLeftComponent(panelLeft);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[] { 0 };
		gbl_panelLeft.rowHeights = new int[] { 0 };
		gbl_panelLeft.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panelLeft.rowWeights = new double[] { Double.MIN_VALUE };
		panelLeft.setLayout(gbl_panelLeft);

		JPanel panelRight = new JPanel();
		splitPane1.setRightComponent(panelRight);
		GridBagLayout gbl_panelRight = new GridBagLayout();
		gbl_panelRight.columnWidths = new int[] { 0, 0 };
		gbl_panelRight.rowHeights = new int[] { 0, 0 };
		gbl_panelRight.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelRight.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelRight.setLayout(gbl_panelRight);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelRight.add(scrollPane, gbc_scrollPane);

		txtLog = new JTextArea();
		txtLog.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() > 1) {
					((JTextComponent) arg0.getComponent()).setText("");
					txtLog.setText("");
				} // if
			}// mouseClicked
		});
		scrollPane.setViewportView(txtLog);

		lblLog = new JLabel("New label");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblLog);
		splitPane1.setDividerLocation(250);

		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelStatus = new GridBagConstraints();
		gbc_panelStatus.fill = GridBagConstraints.BOTH;
		gbc_panelStatus.gridx = 0;
		gbc_panelStatus.gridy = 2;
		frmTemplate.getContentPane().add(panelStatus, gbc_panelStatus);

		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Courier New", Font.PLAIN, 16));
		panelStatus.add(lblStatus);

		JMenuBar menuBar = new JMenuBar();
		frmTemplate.setJMenuBar(menuBar);

		JMenu mnuFile = new JMenu("File");
		menuBar.add(mnuFile);

		JMenuItem mnuFileNew = new JMenuItem("New");
		mnuFileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileNew();
			}
		});
		mnuFile.add(mnuFileNew);

		JMenuItem mnuFileOpen = new JMenuItem("Open...");
		mnuFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileOpen();
			}
		});
		mnuFile.add(mnuFileOpen);

		JSeparator separator = new JSeparator();
		mnuFile.add(separator);

		JMenuItem mnuFileSave = new JMenuItem("Save...");
		mnuFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileSave();
			}
		});
		mnuFile.add(mnuFileSave);

		JMenuItem mnuFileSaveAs = new JMenuItem("Save As...");
		mnuFileSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileSaveAs();
			}
		});
		mnuFile.add(mnuFileSaveAs);

		JSeparator separator_2 = new JSeparator();
		mnuFile.add(separator_2);

		JMenuItem mnuFilePrint = new JMenuItem("Print...");
		mnuFilePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFilePrint();
			}
		});
		mnuFile.add(mnuFilePrint);

		JSeparator separator_1 = new JSeparator();
		mnuFile.add(separator_1);

		JMenuItem mnuFileExit = new JMenuItem("Exit");
		mnuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doFileExit();
			}
		});
		mnuFile.add(mnuFileExit);

	}// initialize

	private final static String EMPTY_STRING = "";
	private final static String SPACE = " ";
//	private final static String SUFFIX_LISTING = "list";
//	private final static String SUFFIX_ASSEMBLER = "asm";
	private static final String FILE_SEPARATOR = File.separator;
	private static final String DEFAULT_DIRECTORY = "." + FILE_SEPARATOR + "Code" + FILE_SEPARATOR + ".";
	private JLabel lblStatus;

}// class GUItemplate