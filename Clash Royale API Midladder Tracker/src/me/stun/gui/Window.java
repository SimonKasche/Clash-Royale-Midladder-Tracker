package me.stun.gui;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartPanel;
import org.json.simple.parser.ParseException;

import me.stun.data.DeckData;
import me.stun.list.ListUpdater;
import me.stun.repair.RepairManager;
import me.stun.thread.ProgressbarWorker;
import me.stun.thread.UpdateWorker;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window instance;

	public String[][] matches;
	public String playerTag;

	public static String[][] staticMatches;
	public static Container cp;

	public static JButton editPlayerTag;
	public static JButton update;
	public static JButton apiKey;
	public static JButton oldWindow;
	public static JButton bSearch;
	public static JButton consoletoggle;
	public static JTextField searchbar;
	public static JLabel output;
	public static JLabel searchBarTitle;
	public static JTextField searchbarPlayerTag;
	public static JLabel outputPlayerTag;
	public static JLabel searchBarTitlePlayerTag;
	public static JButton playerTagSearchButton;
	public static JButton downloadMoreData;
	public static JButton pauseDownload;
	public static JButton stopDownload;
	public static JButton listRepair;
	public static JButton toggleTheme;

	public static JLabel spellTitle;
	public static JLabel winConditionTitle;
	public static JLabel splashTitle;
	public static JLabel matchesCounter;
	public static JLabel totalGames;
	public static JLabel recorded;
	public static JLabel downloadOutput;
	public static Thread downloadingThread;
	public static boolean darkMode = true;

	public static int chartState = 0; // 0-totalUsage 1-usageTrophies 2-usageTime
	public static JButton totalUsageButton;
	public static JButton cupsUsageButton;
	public static JButton timeUsageButton;

	public static JComboBox<String> cardMenu;
	public static JProgressBar progressbar = new JProgressBar();
	public static JProgressBar downloadProgressBar;
	public static ProgressbarWorker worker = new ProgressbarWorker();
	public static UpdateWorker updateWorker;
	public static RepairManager repairWorker = new RepairManager();
	public static JPanel cupChartContainer = new JPanel();
	public static JPanel timeChartContainer = new JPanel();

	public static JCheckBox averageBox = new JCheckBox();
	public static JCheckBox rawDataBox = new JCheckBox();
	public static JCheckBox smoothBox = new JCheckBox();
	public static JCheckBox densityBox = new JCheckBox();

	public static ListUpdater listUpdater;

	public static Color menudark = new Color(0x212326);
	public static Color dark = new Color(0x2c2f33);
	public static Color hoverColor = new Color(0x737373);
	public static Color hoverColor2 = new Color(0x363a3f);
	public static Font font = new Font("Helvetica", Font.PLAIN, 40);

	public Window(String playertag) throws IOException {

		super();

		this.instance = this;
		this.playerTag = playertag;

		if (playertag == null) {
			this.matches = me.stun.io.ArrayBuilder.readPreviousMatchesArray();
			staticMatches = this.matches;
		} else {
			this.matches = me.stun.data.DataProcessor.getMatchesPerPlayer(staticMatches, playertag);
		}

		String[][] accessableMatches = this.matches;

		me.stun.startup.StartupImage.progressbar.setValue(35);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 1280;
		int frameHeight = 770;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Midladder Tracker");
		setResizable(false);
		setLayout(null);

		cp = getContentPane();
		cp.setLayout(null);

		JPanel menu = new JPanel();
		menu.setSize(300, 770);
		menu.setBackground(dark);

		JPanel line = new JPanel();
		line.setLocation(300, 0);
		line.setSize(1, 770);
		line.setBackground(Color.BLACK);
		cp.add(line);

		JPanel background = new JPanel();
		background.setLocation(301, 0);
		background.setSize(979, 770);
		background.setBackground(menudark);

		progressbar = new JProgressBar();
		progressbar.setBounds(301, 0, frameWidth - 317, 10);
		progressbar.setValue(0);
		progressbar.setForeground(Color.GREEN);
		progressbar.setVisible(false);
		cp.add(progressbar);

		downloadProgressBar = new JProgressBar();
		downloadProgressBar.setBounds(0, 400, 299, 10);
		downloadProgressBar.setValue(0);
		downloadProgressBar.setForeground(Color.GREEN);
		downloadProgressBar.setVisible(false);
		cp.add(downloadProgressBar);

		JLabel title = new JLabel();
		title.setText("Midladder Tracker");
		title.setFont(font);
		title.setForeground(Color.WHITE);
		title.setLocation(300, 000);
		title.setSize(50, 300);
		background.add(title);

		editPlayerTag = new JButton("Edit PlayerTags");
		editPlayerTag.setBackground(dark);
		editPlayerTag.setHorizontalAlignment(SwingConstants.CENTER);
		editPlayerTag.setVerticalAlignment(SwingConstants.CENTER);
		editPlayerTag.setForeground(Color.WHITE);
		editPlayerTag.setBounds(0, 100, 300, 50);
		editPlayerTag.setBorder(BorderFactory.createEmptyBorder());
		editPlayerTag.setFocusPainted(false);
		editPlayerTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "src/PlayerTag.txt");
				try {
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		editPlayerTag.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				editPlayerTag.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				editPlayerTag.setBackground(dark);
			}
		});

		cp.add(editPlayerTag);

		apiKey = new JButton("Change Api Key");
		apiKey.setBackground(dark);
		apiKey.setHorizontalAlignment(SwingConstants.CENTER);
		apiKey.setVerticalAlignment(SwingConstants.CENTER);
		apiKey.setForeground(Color.WHITE);
		apiKey.setBounds(0, 50, 300, 50);
		apiKey.setBorder(BorderFactory.createEmptyBorder());
		apiKey.setFocusPainted(false);
		apiKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "src/ApiKey.txt");
				try {
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		apiKey.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				apiKey.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				apiKey.setBackground(dark);
			}
		});

		cp.add(apiKey);

		oldWindow = new JButton("Midladder Card GUI");
		oldWindow.setBackground(dark);
		oldWindow.setHorizontalAlignment(SwingConstants.CENTER);
		oldWindow.setVerticalAlignment(SwingConstants.CENTER);
		oldWindow.setForeground(Color.WHITE);
		oldWindow.setBounds(0, 0, 300, 50);
		oldWindow.setBorder(BorderFactory.createEmptyBorder());
		oldWindow.setFocusPainted(false);
		oldWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					new me.stun.gui.OldWindow(matches, playerTag);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		oldWindow.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				oldWindow.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				oldWindow.setBackground(dark);
			}
		});

		cp.add(oldWindow);

		update = new JButton("Reload");
		update.setBackground(dark);
		update.setHorizontalAlignment(SwingConstants.CENTER);
		update.setVerticalAlignment(SwingConstants.CENTER);
		update.setForeground(Color.WHITE);
		update.setBounds(0, 150, 300, 50);
		update.setBorder(BorderFactory.createEmptyBorder());
		update.setFocusPainted(false);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				Thread test = new Thread(new Runnable() {

					@Override
					public void run() {

						try {
							me.stun.startup.Console.windowInstance.dispose();
							me.stun.mainFile.main(null);
						} catch (IOException | ParseException | InterruptedException e) {
							e.printStackTrace();
						}
						;

					}
				});
				test.start();
			}
		});

		update.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				update.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				update.setBackground(dark);
			}
		});
		cp.add(update);

		searchbar = new JTextField();
		searchbar.setBounds(30, 650, 241, 30);
		searchbar.setBackground(menudark);
		searchbar.setForeground(Color.WHITE);
		searchbar.setText("");
		searchbar.setHorizontalAlignment(SwingConstants.LEFT);
		cp.add(searchbar);

		output = new JLabel();
		output.setBounds(30, 680, 241, 30);
		output.setForeground(Color.WHITE);
		output.setText("");
		output.setHorizontalAlignment(SwingConstants.LEFT);
		cp.add(output);

		searchBarTitle = new JLabel();
		searchBarTitle.setBounds(30, 620, 241, 30);
		searchBarTitle.setForeground(Color.WHITE);
		searchBarTitle.setText("Search Card Percentage");
		searchBarTitle.setHorizontalAlignment(SwingConstants.LEFT);
		cp.add(searchBarTitle);

		searchbarPlayerTag = new JTextField();
		searchbarPlayerTag.setBounds(30, 570, 160, 30);
		searchbarPlayerTag.setBackground(menudark);
		searchbarPlayerTag.setForeground(Color.WHITE);
		searchbarPlayerTag.setText("");
		searchbarPlayerTag.setHorizontalAlignment(SwingConstants.LEFT);
		cp.add(searchbarPlayerTag);

		outputPlayerTag = new JLabel();
		outputPlayerTag.setBounds(30, 600, 241, 30);
		outputPlayerTag.setForeground(Color.WHITE);
		outputPlayerTag.setText("");
		outputPlayerTag.setHorizontalAlignment(SwingConstants.LEFT);
		cp.add(outputPlayerTag);

		searchBarTitlePlayerTag = new JLabel();
		searchBarTitlePlayerTag.setBounds(30, 540, 241, 30);
		searchBarTitlePlayerTag.setForeground(Color.WHITE);
		searchBarTitlePlayerTag.setText("Search Player Tag");
		searchBarTitlePlayerTag.setHorizontalAlignment(SwingConstants.LEFT);
		cp.add(searchBarTitlePlayerTag);

		playerTagSearchButton = new JButton("Search");
		playerTagSearchButton.setBackground(dark);
		playerTagSearchButton.setHorizontalAlignment(SwingConstants.CENTER);
		playerTagSearchButton.setVerticalAlignment(SwingConstants.CENTER);
		playerTagSearchButton.setForeground(Color.WHITE);
		playerTagSearchButton.setBounds(190, 570, 110, 30);
		playerTagSearchButton.setBorder(BorderFactory.createEmptyBorder());
		playerTagSearchButton.setFocusPainted(false);
		playerTagSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					new me.stun.startup.StartupImage();
					me.stun.startup.StartupImage.progressbar.setValue(0);
					new me.stun.gui.Window(searchbarPlayerTag.getText());
					me.stun.startup.StartupImage.instance.dispose();
					
				} catch (Exception e) {
					
					output.setText("tag not found or valid");
					me.stun.startup.StartupImage.instance.dispose();
					System.out.println("\nerror: " + e.toString());
					
				}

			}
		});

		playerTagSearchButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				playerTagSearchButton.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				playerTagSearchButton.setBackground(dark);
			}
		});

		cp.add(playerTagSearchButton);

		consoletoggle = new JButton("Show Console");
		consoletoggle.setBackground(dark);
		consoletoggle.setHorizontalAlignment(SwingConstants.CENTER);
		consoletoggle.setVerticalAlignment(SwingConstants.CENTER);
		consoletoggle.setForeground(Color.WHITE);
		consoletoggle.setBounds(0, 200, 300, 50);
		consoletoggle.setBorder(BorderFactory.createEmptyBorder());
		consoletoggle.setFocusPainted(false);
		consoletoggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (consoletoggle.getText().equals("Show Console")) {

					consoletoggle.setText("Hide Console");
					me.stun.startup.Console.windowInstance.setVisible(true);

				} else {

					consoletoggle.setText("Show Console");
					me.stun.startup.Console.windowInstance.setVisible(false);

				}

			}
		});
		consoletoggle.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				consoletoggle.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				consoletoggle.setBackground(dark);
			}
		});
		cp.add(consoletoggle);

		listRepair = new JButton("Repair List");
		listRepair.setBackground(dark);
		listRepair.setHorizontalAlignment(SwingConstants.CENTER);
		listRepair.setVerticalAlignment(SwingConstants.CENTER);
		listRepair.setForeground(Color.WHITE);
		listRepair.setBounds(0, 300, 300, 50);
		listRepair.setBorder(BorderFactory.createEmptyBorder());
		listRepair.setFocusPainted(false);
		listRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				// repair code here
				if (me.stun.list.ListUpdater.running == false) {
					repairWorker.start();
				} else {
					output.setText("wait until download is finished..");
				}

			}
		});

		listRepair.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				listRepair.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				listRepair.setBackground(dark);
			}
		});

		cp.add(listRepair);
		cp.add(playerTagSearchButton);

		toggleTheme = new JButton("Light Theme (broken)");
		toggleTheme.setBackground(dark);
		toggleTheme.setHorizontalAlignment(SwingConstants.CENTER);
		toggleTheme.setVerticalAlignment(SwingConstants.CENTER);
		toggleTheme.setForeground(Color.WHITE);
		toggleTheme.setBounds(0, 350, 300, 50);
		toggleTheme.setBorder(BorderFactory.createEmptyBorder());
		toggleTheme.setFocusPainted(false);
		toggleTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (darkMode == true) {

					darkMode = false;

					dark = new Color(0xebebeb);
					menudark = Color.WHITE;
					hoverColor2 = Color.WHITE;

				} else {

					darkMode = true;

					dark = new Color(0x2c2f33);
					menudark = new Color(0x212326);
					hoverColor2 = new Color(0x363a3f);

				}

				revalidate();
				repaint();

			}
		});
		toggleTheme.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				toggleTheme.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				toggleTheme.setBackground(dark);
			}
		});
		cp.add(toggleTheme);

		// Download More Data
		// ------------------------------------------------------------------------------------------------------------------------

		listUpdater = new ListUpdater(accessableMatches);
		downloadMoreData = new JButton("Update Matches");
		downloadMoreData.setBackground(dark);
		downloadMoreData.setHorizontalAlignment(SwingConstants.CENTER);
		downloadMoreData.setVerticalAlignment(SwingConstants.CENTER);
		downloadMoreData.setForeground(Color.WHITE);
		downloadMoreData.setBounds(0, 250, 300, 50);
		downloadMoreData.setBorder(BorderFactory.createEmptyBorder());
		downloadMoreData.setFocusPainted(false);
		downloadMoreData.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				synchronized (listUpdater) {

					if (me.stun.list.ListUpdater.running == false) {
						// start downloading
						downloadMoreData.setVisible(false);
						pauseDownload.setVisible(true);
						stopDownload.setVisible(true);

						downloadOutput.setText("downloading..");

						listUpdater.start();

					}
				}

			}
		});
		downloadMoreData.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				downloadMoreData.setBackground(hoverColor2);
			}

			public void mouseExited(MouseEvent evt) {
				downloadMoreData.setBackground(dark);
			}
		});

		cp.add(downloadMoreData);

		pauseDownload = new JButton("Pause Download");
		pauseDownload.setBackground(dark);
		pauseDownload.setHorizontalAlignment(SwingConstants.CENTER);
		pauseDownload.setVerticalAlignment(SwingConstants.CENTER);
		pauseDownload.setForeground(Color.WHITE);
		pauseDownload.setBounds(0, 250, 150, 50);
		pauseDownload.setBorder(BorderFactory.createEmptyBorder());
		pauseDownload.setFocusPainted(false);
		pauseDownload.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				synchronized (listUpdater) {

					if (me.stun.list.Update.wait == false) {
						pauseDownload.setText("Resume Download");
						me.stun.list.Update.wait = true;

					} else {

						pauseDownload.setText("Pause Download");
						listUpdater.notify();
						me.stun.list.Update.wait = false;

					}

				}

			}
		});
		pauseDownload.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				pauseDownload.setBackground(new Color(0x363a3f));
			}

			public void mouseExited(MouseEvent evt) {
				pauseDownload.setBackground(dark);
			}
		});
		pauseDownload.setVisible(false);
		cp.add(pauseDownload);

		stopDownload = new JButton("Stop Download");
		stopDownload.setBackground(dark);
		stopDownload.setHorizontalAlignment(SwingConstants.CENTER);
		stopDownload.setVerticalAlignment(SwingConstants.CENTER);
		stopDownload.setForeground(Color.WHITE);
		stopDownload.setBounds(150, 250, 150, 50);
		stopDownload.setBorder(BorderFactory.createEmptyBorder());
		stopDownload.setFocusPainted(false);
		stopDownload.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				me.stun.list.ListUpdater.stop = true;

			}
		});
		stopDownload.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				stopDownload.setBackground(new Color(0x363a3f));
			}

			public void mouseExited(MouseEvent evt) {
				stopDownload.setBackground(dark);
			}
		});
		stopDownload.setVisible(false);
		cp.add(stopDownload);

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------

		me.stun.startup.StartupImage.progressbar.setValue(35);

		downloadOutput = new JLabel();
		downloadOutput.setBounds(0, 300, 300, 20);
		downloadOutput.setForeground(Color.WHITE);
		downloadOutput.setText("");
		downloadOutput.setHorizontalAlignment(SwingConstants.CENTER);
		cp.add(downloadOutput);

		spellTitle = new JLabel();
		spellTitle.setText("Spells / Deck");
		spellTitle.setForeground(Color.WHITE);
		spellTitle.setSize(200, 30);
		spellTitle.setLocation(433, 500);
		cp.add(spellTitle);

		winConditionTitle = new JLabel();
		winConditionTitle.setText("Win Conditions / Deck");
		winConditionTitle.setForeground(Color.WHITE);
		winConditionTitle.setSize(200, 30);
		winConditionTitle.setLocation(647, 500);
		cp.add(winConditionTitle);

		splashTitle = new JLabel();
		splashTitle.setText("Splash Cards / Deck");
		splashTitle.setForeground(Color.WHITE);
		splashTitle.setSize(200, 30);
		splashTitle.setLocation(893, 500);
		cp.add(splashTitle);

		matchesCounter = new JLabel();
		matchesCounter.setText(matches.length + "");
		matchesCounter.setForeground(Color.WHITE);
		matchesCounter.setFont(font);
		matchesCounter.setSize(200, 60);
		matchesCounter.setLocation(1090, 590);
		matchesCounter.setAlignmentX(CENTER_ALIGNMENT);
		cp.add(matchesCounter);

		totalGames = new JLabel();
		totalGames.setText("total matches");
		totalGames.setForeground(Color.WHITE);
		totalGames.setSize(200, 60);
		totalGames.setLocation(1123, 615);
		cp.add(totalGames);

		recorded = new JLabel();
		recorded.setText("recorded");
		recorded.setForeground(Color.WHITE);
		recorded.setSize(200, 60);
		recorded.setLocation(1134, 625);
		cp.add(recorded);

		System.out.println("building spells dial plot..");

		ChartPanel spells = me.stun.chart.SpellsDial.buildDialPlot(this.matches, 0, 4, 1);
		spells.setBackground(dark);
		spells.setLocation(370, 530);
		spells.setSize(200, 200);
		cp.add(spells);

		System.out.println("building win condition dial plot..");

		ChartPanel winConditions = me.stun.chart.WinConditionDial.buildDialPlot(this.matches, 0, 3, 1);
		winConditions.setBackground(dark);
		winConditions.setLocation(610, 530);
		winConditions.setSize(200, 200);
		cp.add(winConditions);

		System.out.println("building splash dial plot..");

		ChartPanel splash = me.stun.chart.SplashDial.buildDialPlot(this.matches, 0, 6, 1);
		splash.setBackground(dark);
		splash.setLocation(850, 530);
		splash.setSize(200, 200);
		cp.add(splash);

		JPanel cover = new JPanel();
		cover.setSize(980, 10);
		cover.setLocation(300, 100);
		cover.setBackground(menudark);
		cp.add(cover);

		bSearch = new JButton();
		bSearch.setBounds(290, 1000, 75, 25);
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				float percentage = me.stun.data.DataProcessor.getPercentage(accessableMatches, searchbar.getText());
				if (percentage == 0) {
					output.setText("card not found");
				} else {
					output.setText(percentage + "%");
				}

			}
		});
		bSearch.setVisible(true);
		cp.add(bSearch);
		getRootPane().setDefaultButton(bSearch);

		// main charts

		me.stun.startup.StartupImage.progressbar.setValue(40);
		System.out.print("calculating bar chart data..\t");

		JPanel barChartContainer = new JPanel();
		barChartContainer.setSize(920, 400);
		barChartContainer.setLocation(315, 100);
		DeckData.getCardUsage(this.matches);
		barChartContainer.add(me.stun.chart.BarChart.getBarChart("", playerTag, this.matches));
		cp.add(barChartContainer);
		System.out.print("done\n");

		me.stun.startup.StartupImage.progressbar.setValue(75);
		System.out.print("calculating cups data..\t\t");
		cupChartContainer = new JPanel();
		cupChartContainer.setBackground(menudark);
		cupChartContainer.setSize(920, 400);
		cupChartContainer.setLocation(315, 100);
		ChartPanel cupsChartPanel = me.stun.chart.CupsChart.getLineChart("", playerTag, this.matches);
		me.stun.thread.UpdateWorker.addCupsActionListener(cupsChartPanel);
		cupChartContainer.add(cupsChartPanel);
		cupChartContainer.setVisible(false);
		cp.add(cupChartContainer);
		System.out.print("done");

		me.stun.startup.StartupImage.progressbar.setValue(90);
		System.out.print("\ncalculating time chart data..\t");
		timeChartContainer = new JPanel();
		timeChartContainer.setSize(920, 400);
		timeChartContainer.setLocation(315, 100);
		timeChartContainer.setBackground(menudark);
		ChartPanel timeChartPanel = me.stun.chart.TimeChart.buildPlot(this.matches);
		me.stun.thread.UpdateWorker.addTimeActionListener(timeChartPanel);
		timeChartContainer.add(timeChartPanel);
		timeChartContainer.setVisible(false);
		cp.add(timeChartContainer);
		System.out.print("done");

		me.stun.startup.StartupImage.progressbar.setValue(100);

		// ----------------------------------------------------------------------------------select
		// chart buttons

		totalUsageButton = new JButton("Total Usage");
		totalUsageButton.setBackground(dark);
		totalUsageButton.setHorizontalAlignment(SwingConstants.CENTER);
		totalUsageButton.setVerticalAlignment(SwingConstants.CENTER);
		totalUsageButton.setForeground(Color.WHITE);
		totalUsageButton.setBounds(400, 70, 110, 30);
		totalUsageButton.setBorder(BorderFactory.createEmptyBorder());
		totalUsageButton.setFocusPainted(false);
		totalUsageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chartState = 0;
				cupsUsageButton.setBackground(menudark);
				timeUsageButton.setBackground(menudark);

				barChartContainer.setVisible(true);
				timeChartContainer.setVisible(false);
				cupChartContainer.setVisible(false);
				cardMenu.setVisible(false);
			}
		});

		totalUsageButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				totalUsageButton.setBackground(hoverColor);
			}

			public void mouseExited(MouseEvent evt) {
				if (chartState == 0) {
					totalUsageButton.setBackground(dark);
				} else {
					totalUsageButton.setBackground(menudark);
				}

			}
		});
		cp.add(totalUsageButton);

		cupsUsageButton = new JButton("Cups / Usage");
		cupsUsageButton.setBackground(menudark);
		cupsUsageButton.setHorizontalAlignment(SwingConstants.CENTER);
		cupsUsageButton.setVerticalAlignment(SwingConstants.CENTER);
		cupsUsageButton.setForeground(Color.WHITE);
		cupsUsageButton.setBounds(510, 70, 110, 30);
		cupsUsageButton.setBorder(BorderFactory.createEmptyBorder());
		cupsUsageButton.setFocusPainted(false);
		cupsUsageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chartState = 1;
				timeUsageButton.setBackground(menudark);
				totalUsageButton.setBackground(menudark);

				barChartContainer.setVisible(false);
				timeChartContainer.setVisible(false);
				cupChartContainer.setVisible(true);
				cardMenu.setVisible(true);
			}
		});

		cupsUsageButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cupsUsageButton.setBackground(hoverColor);
			}

			public void mouseExited(MouseEvent evt) {
				if (chartState == 1) {
					cupsUsageButton.setBackground(dark);
				} else {
					cupsUsageButton.setBackground(menudark);
				}
			}
		});
		cp.add(cupsUsageButton);

		timeUsageButton = new JButton("Time / Usage");
		timeUsageButton.setBackground(menudark);
		timeUsageButton.setHorizontalAlignment(SwingConstants.CENTER);
		timeUsageButton.setVerticalAlignment(SwingConstants.CENTER);
		timeUsageButton.setForeground(Color.WHITE);
		timeUsageButton.setBounds(620, 70, 110, 30);
		timeUsageButton.setBorder(BorderFactory.createEmptyBorder());
		timeUsageButton.setFocusPainted(false);
		timeUsageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (me.stun.chart.TimeChart.build == false) {
					// timeChartContainer.add(me.stun.chart.TimeChart.buildPlot());
					me.stun.chart.TimeChart.build = true;
				}

				chartState = 2;
				cupsUsageButton.setBackground(menudark);
				totalUsageButton.setBackground(menudark);

				barChartContainer.setVisible(false);
				timeChartContainer.setVisible(true);
				cupChartContainer.setVisible(false);
				cardMenu.setVisible(true);
			}
		});

		timeUsageButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				timeUsageButton.setBackground(hoverColor);
			}

			public void mouseExited(MouseEvent evt) {
				if (chartState == 2) {
					timeUsageButton.setBackground(dark);
				} else {
					timeUsageButton.setBackground(menudark);
				}
			}
		});
		cp.add(timeUsageButton);

		// ------------------------------------------------------------------------------------------------------------

		Arrays.sort(me.stun.data.DeckData.cards);
		String[] cardOptions = me.stun.data.DeckData.cards;
		cardMenu = new JComboBox<String>(cardOptions);
		cardMenu.setBounds(1054, 70, 170, 30);
		cardMenu.setForeground(Color.WHITE);
		cardMenu.setBackground(dark);
		cardMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				worker = new ProgressbarWorker();
				worker.start();

				me.stun.chart.TimeChart.displayedCard = (String) cardMenu.getSelectedItem();
				me.stun.chart.CupsChart.displayedCard = (String) cardMenu.getSelectedItem();

				updateWorker = new UpdateWorker(accessableMatches);
				updateWorker.start();

			}

		});
		cardMenu.setVisible(false);
		cp.add(cardMenu);

		// ------------------------------------------------------------------------------------------------------------

		cp.add(background);
		cp.add(menu);

		setVisible(true);

		invalidate();
		validate();

	}

}
