import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;


public class change_IDF extends JFrame{
	
	// Input variables
	static JTextField file, value, weather, batch, xMouse, yMouse, xRunMouse, yRunMouse, waitTime;
	public static String fileName, zoneName, parameterName, valueName, weatherName, BatchFile, x, y, xRun, yRun, wait;
	public static JLabel label = new JLabel("Status"), xOpenlabel = new JLabel("x Open"), yOpenlabel = new JLabel("y Open"), xRunlabel = new JLabel("x Run"), yRunlabel = new JLabel("y Run"), showValue = new JLabel("_"), waitLabel = new JLabel("Wait Time");
	public static ArrayList<String> zonesList = new ArrayList<String>(), parametersList = new ArrayList<String>(), valuesList = new ArrayList<String>();
	public static ArrayList<String> tempZones = new ArrayList<String>(), tempParameters = new ArrayList<String>(), tempValues = new ArrayList<String>(), previousZonesList = new ArrayList<String>();
	public static ArrayList<Integer> iterationsList = new ArrayList<Integer>();
	public static int iterations = 0;
	private static int iter;
	private static boolean flag; 
	private static int internalCounter;
	

	
	public static JProgressBar progressBar;
	// Create Swing Worker
	private static class MySwingWorker extends SwingWorker<String, Double>{
		private int iters;
		
	    private MySwingWorker( JProgressBar aProgressBar, int iter ) {
	    	progressBar = aProgressBar;
	    	iters  = iter;
	    	
	    }
		@Override
		protected String doInBackground() throws Exception {
			progressBar.setMinimum(0);
	    	progressBar.setMaximum(iterationsList.size());
			int maxNumber = 1;
	        for( int i = 0; i < maxNumber; i++ ){
		        Thread.sleep( 2000 );//simulate long running process
		        double factor = ((double)(i+1) / maxNumber);
		        System.out.println("Intermediate results ready");
		        publish( factor );//publish the progress
		        progressBar.setValue(iter);
		        progressBar.setMaximum(iterationsList.size());
	        }
	        return "Finished";
		}
		
		
		@Override
	    protected void process( List<Double> aDoubles ) {
	      //update the percentage of the progress bar that is done
	      int amount = progressBar.getMaximum() - progressBar.getMinimum();
		  progressBar.setValue(iter);
	    }
	}

    public static MySwingWorker worker = new MySwingWorker(progressBar,iterationsList.size());
	
	public static Map<String, JLabel> zoneMap = new LinkedHashMap<String, JLabel>(), zoneNamesMap = new LinkedHashMap<String, JLabel>();
	
	public static void main(String[] args) {
			
			
			
			
		 	// Create frame
		    JFrame frame = new JFrame("myTool: Easy building simulation configuration");
		    frame.getContentPane().setLayout(new FlowLayout());
		    
		    // Create input fields
		    file = new JTextField("ASHRAE9012016_SchoolPrimary_Denver.idf",30);
		    weather = new JTextField("Denver-Aurora-Buckley.AFB_CO_USA.epw",30);
		    value = new JTextField("New Value",10);
		    batch = new JTextField("C:\\EnergyPlusV9-1-0\\energyplus");
		    xMouse = new JTextField("450",7);
		    yMouse = new JTextField("225",7);
		    xRunMouse = new JTextField("260",7);
		    yRunMouse = new JTextField("50",7);
		    waitTime = new JTextField("2000000",10);
		    
		    String[] zoneList = new String[]{"Corner_Class_1_Pod_1","Corner_Class_2_Pod_1","Corner_Class_1_Pod_2","Corner_Class_2_Pod_2","Corner_Class_1_Pod_3","Corner_Class_2_Pod_3",
		    		"Multi_Class_1_Pod_1","Multi_Class_2_Pod_1","Multi_Class_1_Pod_2","Multi_Class_2_Pod_2","Multi_Class_1_Pod_3","Multi_Class_2_Pod_3",
		    		"Corridor_Pod_1","Corridor_Pod_2","Corridor_Pod_3","Main_Corridor","Mech","Computer_Class","Library_Media_Center","Offices","Lobby","Cafeteria","Kitchen","Gym","Bath"}; 
		    Arrays.sort(zoneList);
		    
		    for (String i : zoneList)  {
		        JLabel tempLabel = new JLabel("");
		        zoneMap.put(i,(tempLabel));
		    }
		    for (String i : zoneList)  {
		        JLabel tempLabel = new JLabel(i);
		        zoneNamesMap.put(i,(tempLabel));
		    }
		    
		    ///////////////////////////
		    
		    JComboBox<String> zones = new JComboBox<>(zoneList);
		    
		    String[] parameterList = new String[]{"U","A","UA","m_sa","C_coil_air","C_coil_water","mc_max","BeginMonth","EndMonth","BeginDay","EndDay","NumberOfDays(BCVTB)",
		    		"A_br_z","A_br_sa","A_br_c","Gamma_z","Gamma_sa","Gamma_c","Conductivity","Zone_projection_bound","SupplyAir_projection_bound","Coil_projection_bound",
		    		"n_bar","nSA_bar","nC_bar","nH_bar","n_nei","Threshold_A","Threshold_e_bar","Threshold_Qbar",
		    		"Threshold_A_c_C","Threshold_e_c_C_bar","Threshold_A_c_H","Threshold_e_c_H_bar",
		    		"Closed_Loop_Poles","Simulation number"}; 
		    JComboBox<String> parameters = new JComboBox<>(parameterList);
		    
		    // Put input fields in frame
		    GridBagConstraints c = new GridBagConstraints();
	        c.gridx = 0;//set the x location of the grid for the next component
	        c.gridy = 0;//set the y location of the grid for the next component
	        
	        // Input files panel
	        JPanel filesPanel = new JPanel(new GridBagLayout());
	        c.anchor=GridBagConstraints.WEST;//left align components after this point
	        filesPanel.add(batch, c);
	        c.gridx = 0;
	        c.gridy = 1;
	        filesPanel.add(file, c);
	        c.gridx = 1;
	        c.gridy = 1;
	        filesPanel.add(weather, c);
	        c.gridx = 1;
	        
	        // Parameters panel
	        JPanel fieldsPanel = new JPanel(new GridBagLayout());
	        
	        c.gridx = 0;
	        c.gridy = 0;
	        fieldsPanel.add(zones, c);
	        c.gridx = 1;
	        fieldsPanel.add(parameters, c);
	        c.gridx = 2;
	        c.insets = new Insets(0,5,0,0);
	        fieldsPanel.add(value, c);
	        
	        // Configuration panel
	        JPanel confPanel = new JPanel(new GridBagLayout());
	        c.insets = new Insets(5,0,0,0);
	        confPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	        c.gridx = 0;
	        c.gridy = 0;
	        confPanel.add(xOpenlabel, c);
	        c.gridx = 1;
	        confPanel.add(yOpenlabel, c);
	        c.gridx = 2;
	        confPanel.add(xRunlabel, c);
	        c.gridx = 3;
	        confPanel.add(yRunlabel, c);
	        c.gridx = 4;
	        confPanel.add(waitLabel, c);
	        c.gridx = 0;
	        c.gridy = 1;
	        confPanel.add(xMouse, c);
	        c.gridx = 1;
	        confPanel.add(yMouse, c);
	        c.gridx = 2;
	        confPanel.add(xRunMouse, c);
	        c.gridx = 3;
	        confPanel.add(yRunMouse, c);
	        c.gridx = 4;
	        confPanel.add(waitTime, c);
	       
	        

	        // Buttons 
	        
		 // Button to change parameter
		    JButton okButton = new JButton("Change Parameter");
		    okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					fileName = file.getText();
					zoneName = zones.getSelectedItem().toString();
					parameterName = parameters.getSelectedItem().toString();
					valueName = value.getText();
					ChangeParameter(fileName,zoneName,parameterName,valueName);
				}
		    	
		    });
					
		 // Button to show current parameter value
		    JButton showButton = new JButton("Show Current Value");
		    showButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//					System.out.println("111");
//					Timer timer = new Timer(5000, showButton.getAction());
//					System.out.println("222");
//				    timer.start();
//				    try {
//				      Thread.sleep(5000);
//				      System.out.println("444");
//				    } catch (InterruptedException e1) {
//				    	System.out.println(e.toString());
//				    }
//				    //timer.stop();
//				    
//				    System.out.println("333");
		
					
		        	fileName = file.getText();
					zoneName = zones.getSelectedItem().toString();
					parameterName = parameters.getSelectedItem().toString();
					ShowParameter(fileName,zoneName,parameterName,label);
				}
		    	
		    });
		    
		 // Button to show all current parameter values for all zones
		    JButton showAllButton = new JButton("Show Values for All Zones");
	        showAllButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					fileName = file.getText();
					parameterName = parameters.getSelectedItem().toString();
					for (Entry<String, JLabel> entry : zoneMap.entrySet()) {
						ShowParameter(fileName,entry.getKey(),parameterName,entry.getValue());
				    }
					for (String i : zoneList)  {
				        zoneNamesMap.get(i).setText(i);;
				    }
					
				}
		    	
		    });
	        
	     // Button to show all current parameter values of zone
		    JButton showZoneButton = new JButton("Show All Values of zone");
	        showZoneButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					fileName = file.getText();
					zoneName = zones.getSelectedItem().toString();
					
					for (Entry<String, JLabel> entry : zoneNamesMap.entrySet()) {
						entry.getValue().setText("");;
				    }
					for (Entry<String, JLabel> entry : zoneMap.entrySet()) {
						entry.getValue().setText("");;
				    }
					zoneNamesMap.get("Corner_Class_1_Pod_1").setText(zoneName);
					ShowParameter(fileName,zoneName,"U",zoneNamesMap.get("Corner_Class_2_Pod_1"));
					ShowParameter(fileName,zoneName,"A",zoneNamesMap.get("Corner_Class_1_Pod_2"));
					ShowParameter(fileName,zoneName,"m_sa",zoneNamesMap.get("Corner_Class_2_Pod_2"));
					
				}
		    	
		    });
		    
		 // Button to update JSON
		    JButton jsonButton = new JButton("Update JSON");
		    jsonButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						updateJSON();
					} catch (Exception e2) {
						label.setText("No simulation json");
						return;
					}
					try {
						Runtime.getRuntime().exec("taskkill /F /IM energyplus.exe");
					} catch (IOException  e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		    	
		    });
		    
		 // Button delete files
		    JButton deleteButton = new JButton("Delete Files");
		    deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					fileName = file.getText();
					deleteFiles.deleteAll(fileName);
					label.setText("Files deleted");
				}
		    	
		    });
		    
		 // Button call BCVTB
		    JButton callBCVTB = new JButton("Open BCVTB");
		    callBCVTB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					x = xMouse.getText();
					y = yMouse.getText();
					fileName = file.getText();
					callBCVTB(x, y, fileName);
					label.setText("Open BCVTB");
				}
		    	
		    });
		    
		 // Button run BCVTB  x = 260 and y = 50 for Yiorgos_USC
		    JButton runBCVTB = new JButton("Run Full Simulation");
		    runBCVTB.addActionListener(new ActionListener() {
		    	
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						updateJSON();
					} catch (Exception e2) {
						label.setText("No simulation json");
						return;
					}
					try { 
			            Thread.sleep(2000); 
			        } 
			        catch (InterruptedException e1) 
			        { 
			            //
			        } 
//					try {
//						Runtime.getRuntime().exec("taskkill /F /IM energyplus.exe");
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					deleteFiles.deleteAll(fileName);
					x = xMouse.getText();
					y = yMouse.getText();
					fileName = file.getText();
					x = xRunMouse.getText();
					y = yRunMouse.getText();
					fileName = file.getText();
					runBCVTB(x, y);
					label.setText("Run BCVTB");
				}
		    	
		    });
		    
		 // Button run Full Simulation  x Run = 260 and y Run = 50 for Yiorgos_USC
		    JButton runSim = new JButton("Open and Run");
		    runSim.addActionListener(new ActionListener() {
		    	
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						updateJSON();
					} catch (Exception e2) {
						label.setText("No simulation json");
						return;
					}
					try { 
			            Thread.sleep(2000); 
			        } 
			        catch (InterruptedException e1) 
			        { 
			            //
			        } 
//					try {
//						Runtime.getRuntime().exec("taskkill /F /IM energyplus.exe");
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					deleteFiles.deleteAll(fileName);
					x = xMouse.getText();
					y = yMouse.getText();
					fileName = file.getText();
					callBCVTB(x, y, fileName);
					try { 
			            Thread.sleep(2000); 
			        } 
			        catch (InterruptedException e1) 
			        { 
			            //
			        } 
					x = xRunMouse.getText();
					y = yRunMouse.getText();
					fileName = file.getText();
					runBCVTB(x, y);
					label.setText("Run BCVTB");
				}
		    	
		    });
		    
		    
		 // Button store values for multiple simulations  x Run = 260 and y Run = 50 for Yiorgos_USC
		    JButton multiStore = new JButton("Store values");
		    multiStore.addActionListener(new ActionListener() {
		    	
				@Override
				public void actionPerformed(ActionEvent e) {
					PrintWriter pw = null;
					if (parameters.getSelectedItem().toString()!="Simulation number") {
						valueName = value.getText();
						zonesList.add(zones.getSelectedItem().toString());
						parametersList.add(parameters.getSelectedItem().toString());
						valuesList.add(valueName);
						iterations += 1;
						showValue.setText("Value stored");
						System.out.println("Zones " + zonesList.toString());
						System.out.println("Parameters " + parametersList.toString());
						System.out.println("Values " + valuesList.toString());
						System.out.println("Iterations " + iterationsList.toString());
						
						tempZones.add(zones.getSelectedItem().toString());
						tempParameters.add(parameters.getSelectedItem().toString());
						tempValues.add(valueName);
						
						try {
							pw = new PrintWriter(new FileOutputStream("Multiple_Simulations_Log" + Integer.toString(iterationsList.size()+1) + ".csv"));
							pw.println(tempZones);
							pw.println(tempParameters);
							pw.println(tempValues);
							pw.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
					else {
						iterationsList.add(iterations);
						iterations = 0;
						label.setText("Iterations: " + Integer.toString(iterationsList.size()));
						showValue.setText(" ");
						tempZones.clear();
						tempParameters.clear();
						tempValues.clear();
						System.out.println("Zones " + zonesList.toString());
						System.out.println("Parameters " + parametersList.toString());
						System.out.println("Values " + valuesList.toString());
						System.out.println("Iterations " + iterationsList.toString());
					}
					
					
					
				}
		    	
		    }); 
		    
		 // Button empty store stack
		    JButton emptyStore = new JButton("Empty stack");
		    emptyStore.addActionListener(new ActionListener() {
		    	
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						zonesList.clear();
						parametersList.clear();
						valuesList.clear();
						iterations = 0;
						iterationsList.clear();
						changeParameters.changeDirc(0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Already empty");
					}
				}
		    	
		    }); 
		    
		    
		 // Button run Full Simulation multiple times  x Run = 260 and y Run = 50 for Yiorgos_USC
		    JButton multiRunSim = new JButton("Run Multiple Times");
		    multiRunSim.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					

					String soundName = "alarm.wav";    
					AudioInputStream audioInputStream = null;
					try {
						audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					} catch (UnsupportedAudioFileException | IOException e2) {
						// TODO Auto-generated catch block
						System.out.println(e2.toString());
					}
					Clip clip = null;
					try {
						clip = AudioSystem.getClip();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.toString());
					}
					try {
						clip.open(audioInputStream);
					} catch (LineUnavailableException | IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.toString());
					}
					
					worker.execute();
					x = xMouse.getText();
					y = yMouse.getText();
					xRun = xRunMouse.getText();
					yRun = yRunMouse.getText();
					fileName = file.getText();
					zoneName = zones.getSelectedItem().toString();
					parameterName = parameters.getSelectedItem().toString();
					wait = waitTime.getText();
					
					File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
					String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
					Path report = Paths.get(pathTemp,"\\ePlus\\",file.getText().replace(".idf", "Table.htm"));
					Path reportAd = Paths.get(pathTemp,"\\ePlusAd\\",file.getText().replace(".idf", "Table.htm"));
					Path reportCSV = Paths.get(pathTemp,"\\ePlus\\",file.getText().replace(".idf", "Table.csv"));
					Path reportAdCSV = Paths.get(pathTemp,"\\ePlusAd\\",file.getText().replace(".idf", "Table.csv"));
					
					for(iter = 1; iter < iterationsList.size()+1; iter ++) {
						label.setText("Simulation No: " + Integer.toString(iter));
						
						System.out.println("Iteration : " + Integer.toString(iter));
						System.out.println("Iteration : " + Integer.toString(iterationsList.get(iter-1)));						

						int prevZones = ArrayListSum(iterationsList, 0, iter-1); 
						System.out.println("where to start : " + prevZones);
						for(int ind = 0; ind < iterationsList.get(iter-1); ind ++) {
						
							ChangeParameter(fileName,zonesList.get(ind+prevZones),parametersList.get(ind+prevZones),valuesList.get(ind+prevZones));
							System.out.println("Iteration : " + Integer.toString(iter));
							System.out.println("Ind : " + Integer.toString(ind));
							System.out.println(fileName);
							System.out.println(zonesList.get(ind+prevZones));
							System.out.println(parametersList.get(ind+prevZones));
							System.out.println(valuesList.get(ind+prevZones));
						}	
						
						try { 
				            Thread.sleep(1000); 
				        } 
				        catch (InterruptedException e1) 
				        { 
				            //
				        } 
						try {
							updateJSON();
						} catch (Exception e2) {
							label.setText("No simulation json");
							return;
						}
						
						try { 
				            Thread.sleep(500); 
				        } 
				        catch (InterruptedException e1) 
				        { 
				            //
				        } 
						deleteFiles.deleteAll(fileName);
						try { 
				            Thread.sleep(1000); 
				        } 
				        catch (InterruptedException e1) 
				        { 
				            //
				        } 
						changeParameters.changeDirc(iter);
						try { 
				            Thread.sleep(1000); 
				        } 
				        catch (InterruptedException e1) 
				        { 
				            //
				        } 
						
						x = xRunMouse.getText();
						y = yRunMouse.getText();
						runBCVTB(xRun, yRun);
						label.setText("Run BCVTB");
						try { 
				            Thread.sleep(Long.parseLong(wait)); 
				        } 
				        catch (InterruptedException e1) 
				        { 
				            //
				        }
						clip.start();
						try { 
						    Thread.sleep(5000); 
						} 
						catch (InterruptedException e1) 
						{ 
						    //
						} 
						clip.stop();
						try { 
						    Thread.sleep(30000); 
						} 
						catch (InterruptedException e1) 
						{ 
						    //
						} 
						try {
							boolean flag = true;
							String line;
							Process p;
							while (flag) {
								flag = false;
								p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
							    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
							    while ((line = input.readLine()) != null) {
							    	try {
										if (input.readLine().contains("energyplus.exe")) {
											Thread.sleep(60000);
											flag = true;
											break;
										}
									} catch (Exception e1) {
									} 
							    	
							    }
							    
							    input.close();
									
							}
						    
						} catch (Exception err) {
						    err.printStackTrace();
						}
						
						String repTarg = pathTemp+"\\Results_"+Integer.toString(iter)+"\\"+file.getText().replace(".idf", "Table.htm");
						Path reportTarget = Paths.get(repTarg);
						String repTargAd = pathTemp+"\\Results_"+Integer.toString(iter)+"\\"+file.getText().replace(".idf", "TableAd.htm");
						Path reportTargetAd = Paths.get(repTargAd);
						String repTargCSV = pathTemp+"\\Results_"+Integer.toString(iter)+"\\"+file.getText().replace(".idf", "Table.csv");
						Path reportTargetCSV = Paths.get(repTargCSV);
						String repTargAdCSV = pathTemp+"\\Results_"+Integer.toString(iter)+"\\"+file.getText().replace(".idf", "TableAd.csv");
						Path reportTargetAdCSV = Paths.get(repTargAdCSV);
						try {
							Files.copy(report, reportTarget, REPLACE_EXISTING);
						} catch (IOException e1) {
							System.out.println(e1.toString());
						}
						try {
							Files.copy(reportAd, reportTargetAd, REPLACE_EXISTING);
						} catch (IOException e1) {
							System.out.println(e1.toString());
						}
						try {
							Files.copy(reportCSV, reportTargetCSV, REPLACE_EXISTING);
						} catch (IOException e1) {
							System.out.println(e1.toString());
						}
						try {
							Files.copy(reportAdCSV, reportTargetAdCSV, REPLACE_EXISTING);
						} catch (IOException e1) {
							System.out.println(e1.toString());
						}
//						repTarg.replace("htm", "csv");
//						reportTarget = Paths.get(repTarg);
//						repTargAd.replace("htm", "csv");
//						reportTargetAd = Paths.get(repTargAd);
//						try {
//							Files.copy(reportCSV, reportTarget, REPLACE_EXISTING);
//						} catch (IOException e1) {
//							System.out.println(e1.toString());
//						}
//						try {
//							Files.copy(reportAdCSV, reportTargetAd, REPLACE_EXISTING);
//						} catch (IOException e1) {
//							System.out.println(e1.toString());
//						}
						
						clip.start();
						try { 
						    Thread.sleep(5000); 
						} 
						catch (InterruptedException e1) 
						{ 
						    //
						} 
						clip.stop();
						try {
							Runtime.getRuntime().exec("taskkill /F /IM energyplus.exe");
						} catch (IOException  e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
				}
		    	
		    });
	        

		    // Order Buttons
	        // Buttons panel
	        JPanel buttonsPanel = new JPanel(new GridBagLayout());
	        c.insets = new Insets(0,0,5,5);
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridx = 0;//set the x location of the grid for the next component
	        c.gridy = 0;//set the y location of the grid for the next component
	        buttonsPanel.add(showButton, c);
	        c.gridx = 1;
	        c.gridy = 0;
	        buttonsPanel.add(showZoneButton, c);
	        c.gridx = 2;
	        c.gridy = 0;
	        buttonsPanel.add(showAllButton, c);
	        c.gridx = 0;
	        c.gridy = 1;
		    buttonsPanel.add(okButton, c);
		    c.gridx = 1;
	        c.gridy = 1;
		    buttonsPanel.add(jsonButton, c);
		    c.gridx = 2;
	        c.gridy = 1;
	        buttonsPanel.add(deleteButton, c);
		    c.gridx = 0;
	        c.gridy = 2;
	        buttonsPanel.add(runBCVTB, c);
		    c.gridx = 1;
	        c.gridy = 2;
	        buttonsPanel.add(callBCVTB, c);
		    c.gridx = 2;
	        c.gridy = 2;
	        buttonsPanel.add(runSim, c);
		    c.gridx = 3;
	        c.gridy = 0;
	        buttonsPanel.add(multiStore, c);
		    c.gridx = 3;
	        c.gridy = 1;
	        buttonsPanel.add(emptyStore, c);
		    c.gridx = 3;
	        c.gridy = 2;
	        buttonsPanel.add(multiRunSim, c);
	        
	        
	        c.fill = GridBagConstraints.NONE;
	        
//		    c.gridx = 1;
//	        c.gridy = 4;
//	        
//		    c.gridx = 0;
//	        c.gridy = 1;
//	        

		    
		    
		    // Extra Labels
	        JPanel statusPanel = new JPanel(new GridBagLayout());
	        statusPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	        statusPanel.setPreferredSize(new Dimension(700, 50));
	        c.ipadx = 650;
	        c.gridx = 0;//set the x location of the grid for the next component
	        c.gridy = 0;//set the y location of the grid for the next component
	        statusPanel.add(label, c);
	        c.gridy = 1;
	        statusPanel.add(showValue, c);
	        c.ipadx = 0;
	        
	        
	        JPanel labelPanel = new JPanel(new GridBagLayout());
	        labelPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	        c.anchor=GridBagConstraints.WEST;//left align components after this point
	        int loc = 0;
	        c.insets = new Insets(0,5,0,5);
	        for (Entry<String, JLabel> entry : zoneNamesMap.entrySet()) {
		    	c.gridy = loc;//change the y location
		    	labelPanel.add(entry.getValue(), c);
		    	loc++;
		    }
	        loc = 0;
		    for (Entry<String, JLabel> entry : zoneMap.entrySet()) {
		    	c.gridx = 1;
		    	c.gridy = loc;//change the y location
		    	labelPanel.add(entry.getValue(), c);
		    	loc++;
		    }
		    c.insets = new Insets(10,0,0,0);
		    JPanel panel = new JPanel(new GridBagLayout());
		    c.gridx = 0;
	        c.gridy = 0;
	        panel.add(filesPanel,c);
		    c.gridx = 0;
	        c.gridy = 1;
	        panel.add(fieldsPanel,c);
	        c.gridy = 2;
	        panel.add(confPanel,c);
	        c.gridy = 3;
	        panel.add(buttonsPanel,c);
	        c.gridy = 4;
	        panel.add(statusPanel,c);
	        c.gridy = 5;
		    panel.add(labelPanel,c);
		    c.gridy = 6;
		    
		    
		    progressBar = new JProgressBar(  );
		    progressBar.setMinimum( 0 );
		    progressBar.setMaximum( 100 );

		    
		    
		    panel.add(progressBar,c);
		    
		    frame.getContentPane().add(panel);
		    
		    // Frame position
		    frame.setBounds(500, 50, 730, 810);
//		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		  }
	

		  
	protected static void ShowParameter(String file, String zone, String parameter, JLabel label_zone) {
		// TODO Auto-generated method stub
		// Find path
				File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
				String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
				Path path = Paths.get(pathTemp, "ePlus\\" + file);
				
				
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
				} 
				catch (MalformedInputException e2) {
					label.setText("Wrong character encoder");
					return;
				}
				catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No file content");
//					return;
				} 
				
				int zone_number = 0;
				switch (zone) {
					case "Bath":
						zone_number = 1;
						break;
					case "Cafeteria":
						zone_number = 2;
						break;
					case "Computer_Class":
						zone_number = 3;
						break;
					case "Corner_Class_1_Pod_1":
						zone_number = 4;
						break;
					case "Corner_Class_1_Pod_2":
						zone_number = 5;
						break;
					case "Corner_Class_1_Pod_3":
						zone_number = 6;
						break;
					case "Corner_Class_2_Pod_1":
						zone_number = 7;
						break;
					case "Corner_Class_2_Pod_2":
						zone_number = 8;
						break;
					case "Corner_Class_2_Pod_3":
						zone_number = 9;
						break;
					case "Corridor_Pod_1":
						zone_number = 10;
						break;
					case "Corridor_Pod_2":
						zone_number = 11;
						break;
					case "Corridor_Pod_3":
						zone_number = 12;
						break;
					case "Gym":
						zone_number = 13;
						break;
					case "Kitchen":
						zone_number = 14;
						break;
					case "Library_Media_Center":
						zone_number = 15;
						break;
					case "Lobby":
						zone_number = 16;
						break;
					case "Main_Corridor":
						zone_number = 17;
						break;
					case "Mech":
						zone_number = 18;
						break;
					case "Multi_Class_1_Pod_1":
						zone_number = 19;
						break;
					case "Multi_Class_1_Pod_2":
						zone_number = 20;
						break;
					case "Multi_Class_1_Pod_3":
						zone_number = 21;
						break;
					case "Multi_Class_2_Pod_1":
						zone_number = 22;
						break;
					case "Multi_Class_2_Pod_2":
						zone_number = 23;
						break;
					case "Multi_Class_2_Pod_3":
						zone_number = 24;
						break;	
					case "Offices":
						zone_number = 25;
						break;	
				}
				
				// TODO Auto-generated method stub
				switch (parameter){
					case "U":
						showParameters.showU(file, zone, fileContent, path, label_zone);
						break;
					case "A":
						showParameters.showA(file, zone, fileContent, path, label_zone);
						break;
					case "UA":
						showParameters.showUA(file, zone, fileContent, path, label_zone);
						break;
					case "m_sa":
						showParameters.showMsa(file, zone, fileContent, path, label_zone);
						break;
					case "mc_max":
						showParameters.showMcMax(pathTemp, label_zone);
						break;
					case "BeginMonth":
						showParameters.showBeginMonth(file, zone, fileContent, path);
						break;
					case "EndMonth":
						showParameters.showEndMonth(file, zone, fileContent, path);
						break;
					case "BeginDay":
						showParameters.showBeginDay(file, zone, fileContent, path);
						break;
					case "EndDay":
						showParameters.showEndDay(file, zone, fileContent, path);
						break;
					case "NumberOfDays(BCVTB)":
						showParameters.showDateRange(pathTemp);
						break;
					case "A_br_z":
						showParameters.showAbrZ(pathTemp, label_zone);
						break;	
					case "A_br_sa":
						showParameters.showAbrSA(pathTemp, label_zone);
						break;	
					case "A_br_c":
						showParameters.showAbrC(pathTemp, label_zone);
						break;	
					case "Gamma_z":
						showParameters.showGammaZ(pathTemp, label_zone);
						break;
					case "Gamma_sa":
						showParameters.showGammaSA(pathTemp, label_zone);
						break;
					case "Gamma_c":
						showParameters.showGammaC(pathTemp, label_zone);
						break;
					case "Conductivity":
						showParameters.showConductivity(file, zone, fileContent, path, label_zone);
						break;	
					case "Zone_projection_bound":
						showParameters.showProjZ(pathTemp, label_zone);
						break;
					case "SupplyAir_projection_bound":
						showParameters.showProjSA(pathTemp, label_zone);
						break;
					case "Coil_projection_bound":
						showParameters.showProjC(pathTemp, label_zone);
						break;
					case "n_bar":
						showParameters.showNbar(pathTemp, label_zone);
						break;	
					case "nSA_bar":
						showParameters.showNsabar(pathTemp, label_zone);
						break;
					case "nC_bar":
						showParameters.showNCbar(pathTemp, label_zone);
						break;	
					case "nH_bar":
						showParameters.showNHbar(pathTemp, label_zone);
						break;	
					case "n_nei":
						showParameters.showNnei(pathTemp, label_zone);
						break;
					case "Threshold_A":
						showParameters.showThresA(pathTemp, label_zone);
						break;		
					case "Threshold_e_bar":
						showParameters.showThresEbar(pathTemp, label_zone);
						break;	
					case "Threshold_Qbar":
						showParameters.showThresQbar(pathTemp, label_zone);
						break;
					case "Threshold_A_c_C":
						showParameters.showThresAcC(pathTemp, label_zone);
						break;	
					case "Threshold_e_c_C_bar":
						showParameters.showThresEcCbar(pathTemp, label_zone);
						break;	
					case "Threshold_A_c_H":
						showParameters.showThresAcH(pathTemp, label_zone);
						break;
					case "Threshold_e_c_H_bar":
						showParameters.showThresEcHbar(pathTemp, label_zone);
						break;	
					case "Closed_Loop_Poles":
						showParameters.showPoles(value, zone_number, pathTemp);
						break;	
					default:
						label.setText("Invalid Parameter");
				}
	}


	protected static void updateJSON() {
		// TODO Auto-generated method stub
		BatchFile = batch.getText();
		fileName = file.getText();
		weatherName = weather.getText();
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
		pathTemp = pathTemp.replace("\\bin", "");
		String pathTempAd = pathTemp;
		pathTemp = pathTemp.concat("\\ePlus");
		pathTempAd = pathTempAd.concat("\\ePlusAd");
		Path path = Paths.get(pathTemp, fileName);
		Path weatherPath = Paths.get(pathTemp, weatherName);
		Path pathAd = Paths.get(pathTempAd, fileName);
		Path weatherPathAd = Paths.get(pathTempAd, weatherName);
		flag = true;
		internalCounter = 0;
		System.out.println(Integer.toString(internalCounter));
		while(flag && internalCounter < 10) {
			try {
				runSimulation(path.toString(), weatherPath.toString(), BatchFile);
				Path json = Paths.get(pathTemp.replace("\\ePlus", ""),fileName.replace("idf", "epJSON"));
				Path jsonTarget = Paths.get(pathTemp, fileName.replace("idf", "epJSON"));
				Files.copy(json, jsonTarget, REPLACE_EXISTING);
				runSimulation(pathAd.toString(), weatherPathAd.toString(), BatchFile);
				Path jsonAd = Paths.get(pathTempAd.replace("\\ePlusAd", ""),fileName.replace("idf", "epJSON"));
				Path jsonTargetAd = Paths.get(pathTempAd, fileName.replace("idf", "epJSON"));
				Files.move(jsonAd, jsonTargetAd, REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				label.setText("No simulation");
				internalCounter += 1;
				continue;
			}
			flag = false;
		}
		if (flag == true) {
			return;
		}
			
		
	}
	


	protected static void runBCVTB(String x2, String y2) {
		// TODO Auto-generated method stub
		run_BCVTB.run_idf(x,y);
		
	}


	protected static void callBCVTB(String x, String y, String fileName) {
		// Method to open BCVTB
		if (Desktop.isDesktopSupported()) {
			 try {
			     Desktop desktop = Desktop.getDesktop();
			     File myFile = new File("C:\\BCVTB\\bin\\BCVTB.jar");
			     desktop.open(myFile);
			     } catch (IOException ex) {
			    	 label.setText("BCVTB not found");
			     }
			 }
        try { 
            Thread.sleep(3000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
        run_BCVTB.open_idf(x,y, fileName);
        
    } 
	
		

//	// Method to call Matlab and check Theorem 1
//	protected static void matlabCall() {
//		// TODO Auto-generated method stub
//	}

	// Method that changes the parameter
	protected static void ChangeParameter(String file, String zone, String parameter, String value) {
		

		// Find path
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
		Path path = Paths.get(pathTemp, "ePlus\\" + file);
		Path pathAd = Paths.get(pathTemp, "ePlusAd\\" + file);
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} 
		catch (MalformedInputException e2) {
			label.setText("Wrong character encoder");
			return;
		}
		catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No file content");
//			return;
		} 
		

		int zone_number = 0;
		switch (zone) {
			case "Bath":
				zone_number = 1;
				break;
			case "Cafeteria":
				zone_number = 2;
				break;
			case "Computer_Class":
				zone_number = 3;
				break;
			case "Corner_Class_1_Pod_1":
				zone_number = 4;
				break;
			case "Corner_Class_1_Pod_2":
				zone_number = 5;
				break;
			case "Corner_Class_1_Pod_3":
				zone_number = 6;
				break;
			case "Corner_Class_2_Pod_1":
				zone_number = 7;
				break;
			case "Corner_Class_2_Pod_2":
				zone_number = 8;
				break;
			case "Corner_Class_2_Pod_3":
				zone_number = 9;
				break;
			case "Corridor_Pod_1":
				zone_number = 10;
				break;
			case "Corridor_Pod_2":
				zone_number = 11;
				break;
			case "Corridor_Pod_3":
				zone_number = 12;
				break;
			case "Gym":
				zone_number = 13;
				break;
			case "Kitchen":
				zone_number = 14;
				break;
			case "Library_Media_Center":
				zone_number = 15;
				break;
			case "Lobby":
				zone_number = 16;
				break;
			case "Main_Corridor":
				zone_number = 17;
				break;
			case "Mech":
				zone_number = 18;
				break;
			case "Multi_Class_1_Pod_1":
				zone_number = 19;
				break;
			case "Multi_Class_1_Pod_2":
				zone_number = 20;
				break;
			case "Multi_Class_1_Pod_3":
				zone_number = 21;
				break;
			case "Multi_Class_2_Pod_1":
				zone_number = 22;
				break;
			case "Multi_Class_2_Pod_2":
				zone_number = 23;
				break;
			case "Multi_Class_2_Pod_3":
				zone_number = 24;
				break;	
			case "Offices":
				zone_number = 25;
				break;	
		}

		// TODO Auto-generated method stub
		switch (parameter){
			case "U":
				changeParameters.changeU(file, zone, value, fileContent, path);
				changeParameters.changeU(file, zone, value, fileContent, pathAd);
				break;
			case "A":
				changeParameters.changeA(file, zone, value, fileContent, path);
				changeParameters.changeA(file, zone, value, fileContent, pathAd);
				break;
			case "UA":
				changeParameters.changeUA(file, zone, value, fileContent, path);
				changeParameters.changeUA(file, zone, value, fileContent, pathAd);
				break;	
			case "C_coil_air":
				changeParameters.change_CcoilAir(file, zone, value, fileContent, path);
				changeParameters.change_CcoilAir(file, zone, value, fileContent, pathAd);
				break;	
			case "C_coil_water":
				changeParameters.change_CcoilWater(file, zone, value, fileContent, path);
				changeParameters.change_CcoilWater(file, zone, value, fileContent, pathAd);
				break;	
			case "m_sa":
				changeParameters.changeMsa(file, zone, value, fileContent, path);
				changeParameters.changeMsa(file, zone, value, fileContent, pathAd);
				break;
			case "mc_max":
				changeParameters.changeMcMax(value, pathTemp);
				changeParameters.changeMcMax(value, pathTemp);
				break;	
			case "BeginMonth":
				changeParameters.changeBeginMonth(file, zone, value, fileContent, path);
				changeParameters.changeBeginMonth(file, zone, value, fileContent, pathAd);
				break;
			case "EndMonth":
				changeParameters.changeEndMonth(file, zone, value, fileContent, path);
				changeParameters.changeEndMonth(file, zone, value, fileContent, pathAd);
				break;
			case "BeginDay":
				changeParameters.changeBeginDay(file, zone, value, fileContent, path);
				changeParameters.changeBeginDay(file, zone, value, fileContent, pathAd);
				break;
			case "EndDay":
				changeParameters.changeEndDay(file, zone, value, fileContent, path);
				changeParameters.changeEndDay(file, zone, value, fileContent, pathAd);
				break;
			case "NumberOfDays(BCVTB)":
				changeParameters.changeDateRange(value, pathTemp);
				break;
			case "A_br_z":
				changeParameters.changeAbrZ(value, pathTemp);
				break;
			case "A_br_sa":
				changeParameters.changeAbrSA(value, pathTemp);
				break;
			case "A_br_c":
				changeParameters.changeAbrC(value, pathTemp);
				break;
			case "Gamma_z":
				changeParameters.changeGammaZ(value, pathTemp);
				break;
			case "Gamma_sa":
				changeParameters.changeGammaSA(value, pathTemp);
				break;
			case "Gamma_c":
				changeParameters.changeGammaC(value, pathTemp);
				break;
			case "Conductivity":
				changeParameters.changeConductivity(file, zone, value, fileContent, path);
				changeParameters.changeConductivity(file, zone, value, fileContent, pathAd);
				break;
			case "Zone_projection_bound":
				changeParameters.changeProjZ(value, pathTemp);
				break;	
			case "SupplyAir_projection_bound":
				changeParameters.changeProjSA(value, pathTemp);
				break;
			case "Coil_projection_bound":
				changeParameters.changeProjC(value, pathTemp);
				break;
			case "n_bar":
				changeParameters.changeNbar(value, pathTemp);
				break;	
			case "nSA_bar":
				changeParameters.changeNsabar(value, pathTemp);
				break;
			case "nC_bar":
				changeParameters.changeNCbar(value, pathTemp);
				break;	
			case "nH_bar":
				changeParameters.changeNHbar(value, pathTemp);
				break;	
			case "n_nei":
				changeParameters.changeNnei(value, pathTemp);
				break;
			case "Threshold_A":
				changeParameters.changeThresA(value, pathTemp);
				break;		
			case "Threshold_e_bar":
				changeParameters.changeThresEbar(value, pathTemp);
				break;	
			case "Threshold_Qbar":
				changeParameters.changeThresQbar(value, pathTemp);
				break;
			case "Threshold_A_c_C":
				changeParameters.changeThresAcC(value, pathTemp);
				break;	
			case "Threshold_e_c_C_bar":
				changeParameters.changeThresEcCbar(value, pathTemp);
				break;	
			case "Threshold_A_c_H":
				changeParameters.changeThresAcH(value, pathTemp);
				break;
			case "Threshold_e_c_H_bar":
				changeParameters.changeThresEcHbar(value, pathTemp);
				break;
			case "Closed_Loop_Poles":
				System.out.println("sfsfs");
				changeParameters.changePoles(value, zone_number, pathTemp);
				break;	


			default:
				label.setText("Invalid Parameter");
		}
		
	}
	
	
	
	
	public static void runSimulation(String path, String weatherName, String BatchFile) throws IOException {
		// Run EnergyPlus Simulation
		Runtime.getRuntime().exec(BatchFile +" -w " + weatherName + " -c " + path);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		label.setText("Simulation");
		
	}

	// Method that sums the elements of an arraylist of integers for a specific range
	// "last" is the the index of the element AFTER the last one included in the sum
	public static int ArrayListSum(ArrayList<Integer> myList, int first, int last) {
		int sum = 0;
		for(int i = first; i < last; i++) {
			sum = sum + myList.get(i);
		}
		return sum;
	}
}
