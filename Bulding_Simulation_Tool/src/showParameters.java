import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class showParameters extends change_IDF{

	public static void showDateRange(String pathTemp)  {
		// Change Date Range in BCVTB
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(pathTemp + "\\system.xml")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			label.setText("XML not read");
			return;
		}
		String line;
		ArrayList<String> fileContent = new ArrayList<String>();
		try {
			while((line=br.readLine())!= null){
				if (line.contains("<property name=\"endTime\" class=\"ptolemy.data.expr.Parameter\" value=")) {
					label.setText("");
			        showValue.setText(line);
			    }
			}
		} catch (IOException e1) {
			// Empty file check
			label.setText("Date Range not found");
		}
		
		
	
	}
	
	public static void showEndMonth(String file, String zone, List<String> fileContent, Path path) {
		// Change End Month
		try {
			//Change End Month value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("!- End Month")) {
			    	label.setText("");
			    	showValue.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("End Month value not found");
		}
		
	}
	
	public static void showBeginMonth(String file, String zone, List<String> fileContent, Path path) {
		// Change Begin Month
		try {
			//Change Begin Month value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("!- Begin Month")) {
			    	label.setText("");
			    	showValue.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Begin Month value not found");
		}
		
	}
	
	public static void showEndDay(String file, String zone, List<String> fileContent, Path path) {
		// Change Day Month
		try {
			//Change End Day value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("!- End Day of Month")) {
			    	label.setText("");
			    	showValue.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("End Day value not found");
		}
		
	}

	public static void showBeginDay(String file, String zone, List<String> fileContent, Path path) {
		// Change Begin Day
				try {
					//Change Begin Day value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("!- Begin Day of Month")) {
					    	label.setText("");
					    	showValue.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Begin Day value not found");
				}
		
	}

	public static void showU(String file, String zone, List<String> fileContent, Path path, JLabel label_zone) {
		// Change U
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("Set " +zone+"_CWCoil_U =")) {
			    	if (label_zone == label) {
			    		label.setText(fileContent.get(i));
			    	} else {
			    		label.setText(" ");
				    	showValue.setText(" ");
				    	String[] line = fileContent.get(i).trim().split(" ");
				    	for (String str : line) {
				    		if (isNumeric(str.substring(0,str.length()-1))) {
				    			label_zone.setText("    U = " + str.substring(0,str.length()-1));
				    			return;
				    		}
				    		
				    	}
			    	}
			    }else if (fileContent.get(i).contains("Set " +zone+"_HWCoil_U =") && label_zone == label){
			    	showValue.setText(fileContent.get(i));
			    }
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("U value not found");
		}
			
	}
	
	public static void showA(String file, String zone, List<String> fileContent, Path path, JLabel label_zone) {
		// ChangeA
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("Set " +zone+"_CWCoil_A =")) {
			    	if (label_zone == label) {
			    		label.setText(fileContent.get(i));
			    	} else {
			    		label.setText(" ");
				    	showValue.setText(" ");
				    	String[] line = fileContent.get(i).trim().split(" ");
				    	for (String str : line) {
				    		if (isNumeric(str.substring(0,str.length()-1))) {
				    			label_zone.setText("    A = " + str.substring(0,str.length()-1));
				    			return;
				    		}
				    		
				    	}
				    	
			    	}
			    }else if (fileContent.get(i).contains("Set " +zone+"_HWCoil_A =") && label_zone == label){
			    	showValue.setText(fileContent.get(i));
			    }
			    
			}
		} catch (Exception e) {
			// Check 
			label.setText("A value not found");
		}
		
	}

	public static void showUA(String file, String zone, List<String> fileContent, Path path, JLabel label_zone) {
		// Change UA
		try {
			//Change UA value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("Set " +zone+"_CWCoil_UA =")) {
			    	if (label_zone == label) {
			    		label.setText(fileContent.get(i));
			    	} else {
			    		label.setText(" ");
				    	showValue.setText(" ");
				    	String[] line = fileContent.get(i).trim().split(" ");
				    	for (String str : line) {
				    		if (isNumeric(str.substring(0,str.length()-1))) {
				    			label_zone.setText("    UA = x " + str.substring(0,str.length()-1));
				    			return;
				    		}
				    		
				    	}
			    	}
			    }else if (fileContent.get(i).contains("Set " +zone+"_HWCoil_UA =") && label_zone == label){
			    	showValue.setText(fileContent.get(i));
			    }
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("UA value not found");
		}
			
	}

	public static void showMsa(String file, String zone, List<String> fileContent, Path path, JLabel label_zone) {
		// ChangeMsa
		try {
			// Useful counters and flag
			int noChanges = 0;
			boolean flag = false;
			int heatCoolCounter = 0;
			
			
			for (int i = 1; i < fileContent.size(); i++) {

				// Change air flow in AirLoopHVAC
				if (fileContent.get(i-1).equals("AirLoopHVAC,") && fileContent.get(i).contains("AHU " + zone +",") && fileContent.get(i).contains("!- Name")) {
					flag = true;
				};
				if (fileContent.get(i).contains("!- Design Supply Air Flow Rate {m3/s}") && flag == true) {
					label.setText(" ");
					showValue.setText(" ");
					label_zone.setText("    m_sa = " + fileContent.get(i).trim().split(" ")[0].substring(0,fileContent.get(i).trim().split(" ")[0].length()-1));
					flag = false;
				}	
			}
		}
		catch (Exception e) {
			// Check 
			label.setText("Msa value not found");
		}
		
	}
	
	public static void showMcMax(String pathTemp, JLabel label_zone) {
		// Show upper bound for valve
		Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change Gamma_z
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("set_param(['controllerAd/Controller/Saturation']")) {
			    	label.setText(" ");
					showValue.setText(" ");
					label_zone.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Upper bound value for mc not found");
		}
		
	}

	public static void showGammaZ(String pathTemp, JLabel label_zone) {
		// Change adaptive gain gamma for zone
		Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change Gamma_z
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("gammaZ(:,:,i)  =")) {
			    	label.setText(" ");
					showValue.setText(" ");
					label_zone.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Gamma_z value not found");
		}
		
	}

	public static void showGammaSA(String pathTemp, JLabel label_zone) {
		// Change adaptive gain gamma for supply air
		Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change Gamma_z
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("gammaSA(:,:,i) = ")) {
			    	label.setText(" ");
					showValue.setText(" ");
					label_zone.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Gamma_sa value not found");
		}
	}

	public static void showGammaC(String pathTemp, JLabel label_zone) {
		// Change adaptive gain gamma for coils
		Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change Gamma_z
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("gammaC(:,:,i) = ")) {
			    	label.setText(" ");
					showValue.setText(" ");
					label_zone.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Gamma_c value not found");
		}
	}

	static JLabel fromString(String str) {
	    XMLDecoder d = new XMLDecoder(new ByteArrayInputStream(str.getBytes()));
	    JLabel label = (JLabel) d.readObject();
	    d.close();
	    return label;
	}
	
	String toString(JLabel jl) {
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  XMLEncoder e = new XMLEncoder(baos);
		  e.writeObject(jl);
		  e.close();
		  return new String(baos.toByteArray());
	}

	public static void ShowAll(String fileName, String key, String parameterName) {
		// TODO Auto-generated method stub
		
		
	}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}

	public static void showProjZ(String pathTemp, JLabel label_zone) {
		// Change adaptive gain gamma for zone
				Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
				
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("*abs(gamZ{', num2str(i) ,'})+0.0001'],'LowerSaturationLimit',['[0.001;")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Zone projection bound not found");
				}
	}

	public static void showProjSA(String pathTemp, JLabel label_zone) {
		// Change adaptive gain gamma for zone
		Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change Gamma_z
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['")) {
			    	label.setText(" ");
					showValue.setText(" ");
					label_zone.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Supply air projection bound not found");
		}
		
	}

	public static void showProjC(String pathTemp, JLabel label_zone) {
		// Change adaptive gain gamma for zone
		Path path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change Gamma_z
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['")) {
			    	label.setText(" ");
					showValue.setText(" ");
					label_zone.setText(fileContent.get(i));
			    }
			}
		} catch (Exception e) {
			// Check 
			label.setText("Coil projection bound not found");
		}
		
	}

	public static void showConductivity(String file, String zone, List<String> fileContent, Path path,
			JLabel label_zone) {
		// TODO Auto-generated method stub
		
	}	
	
	public static void showAbrZ(String pathTemp, JLabel label_zone) {
		// Change A_br for zone
		
		Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(path1);
		
		for (Path p : paths) {
			// Find file content
			List<String> fileContent = null;
			try {
				fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				label.setText("No m.file content");
				return;
			}
			// Change Gamma_z
			try {
				//Change U value and store it
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).A_br_z =")) {
				    	label.setText(" ");
						showValue.setText(" ");
						label_zone.setText(fileContent.get(i));
				    }
				}
			} catch (Exception e) {
				// Check 
				label.setText("A_br_z value not found");
			}
		}
		
			
	}

	public static void showAbrSA(String pathTemp, JLabel label_zone) {
		// Change A_br for zone

		Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(path1);
		
		for (Path p : paths) {
			// Find file content
			List<String> fileContent = null;
			try {
				fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				label.setText("No m.file content");
				return;
			}
			// Change Gamma_z
			try {
				//Change U value and store it
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).A_br_sa_C =")) {
				    	label.setText(" ");
						showValue.setText(" ");
						label_zone.setText(fileContent.get(i));
				    }
				}
			} catch (Exception e) {
				// Check 
				label.setText("A_br_z value not found");
			}
		}
		
	}

	public static void showAbrC(String pathTemp, JLabel label_zone) {
		// Change A_br for zone
		
		Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(path1);
		
		for (Path p : paths) {
			// Find file content
			List<String> fileContent = null;
			try {
				fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				label.setText("No m.file content");
				return;
			}
			// Change Gamma_z
			try {
				//Change U value and store it
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).A_br_c_C =")) {
				    	label.setText(" ");
						showValue.setText(" ");
						label_zone.setText(fileContent.get(i));
				    }
				}
			} catch (Exception e) {
				// Check 
				label.setText("A_br_z value not found");
			}
		}
		
	}
	

	public static void showNbar(String pathTemp, JLabel label_zone) {
	// Change Nbar for zone
		
		Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(path1);
		
		for (Path p : paths) {
			// Find file content
			List<String> fileContent = null;
			try {
				fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				label.setText("No m.file content");
				return;
			}
			// Change Gamma_z
			try {
				//Change U value and store it
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).n_bar =")||fileContent.get(i).contains("building.zone(i).n_bar=")) {
				    	label.setText(" ");
						showValue.setText(" ");
						label_zone.setText(fileContent.get(i));
				    }
				}
			} catch (Exception e) {
				// Check 
				label.setText("n_bar value not found");
			}
		}
		
	}


	public static void showNsabar(String pathTemp, JLabel label_zone) {
		// Change nSA_bar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).nSA_bar =")||fileContent.get(i).contains("building.zone(i).nSA_bar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("nSA_bar value not found");
				}
			}
			
		}

	public static void showNCbar(String pathTemp, JLabel label_zone) {
		// Change nC_bar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).nC_bar =")||fileContent.get(i).contains("building.zone(i).nC_bar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("nC_bar value not found");
				}
			}
			
		}

	public static void showNHbar(String pathTemp, JLabel label_zone) {
		// Change nH_bar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).nH_bar =")||fileContent.get(i).contains("building.zone(i).nH_bar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("nH_bar value not found");
				}
			}
			
		}

	public static void showNnei(String pathTemp, JLabel label_zone) {
		// Change n_nei for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).n_nei(1,j) =")||fileContent.get(i).contains("building.zone(i).n_nei(1,j)=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("n_nei(1,j) value not found");
				}
			}
			
		}

	public static void showThresA(String pathTemp, JLabel label_zone) {
		// Change Threshold_A for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).a =")||fileContent.get(i).contains("building.zone(i).a=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_A value not found");
				}
			}
			
		}

	public static void showThresEbar(String pathTemp, JLabel label_zone) {
		// Change Threshold_e_bar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).e_bar =")||fileContent.get(i).contains("building.zone(i).e_bar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_e_bar value not found");
				}
			}
			
		}

	public static void showThresQbar(String pathTemp, JLabel label_zone) {
		// Change Threshold_Qbar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).Qbar =")||fileContent.get(i).contains("building.zone(i).Qbar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_Qbar value not found");
				}
			}
			
		}

	public static void showThresAcC(String pathTemp, JLabel label_zone) {
		// Change Threshold_A_c_C for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).a_c_C =")||fileContent.get(i).contains("building.zone(i).a_c_C=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_A_c_C value not found");
				}
			}
			
		}

	public static void showThresEcCbar(String pathTemp, JLabel label_zone) {
		// Change Threshold_e_c_C_bar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).e_c_C_bar =")||fileContent.get(i).contains("building.zone(i).e_c_C_bar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_e_c_C_bar value not found");
				}
			}
			
		}

	public static void showThresAcH(String pathTemp, JLabel label_zone) {
		// Change Threshold_A_c_H for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).a_c_H =")||fileContent.get(i).contains("building.zone(i).a_c_H=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_A_c_H value not found");
				}
			}
			
		}

	public static void showThresEcHbar(String pathTemp, JLabel label_zone) {
		// Change Threshold_e_c_H_bar for zone
			
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			ArrayList<Path> paths = new ArrayList<Path>();
			paths.add(path1);
			
			for (Path p : paths) {
				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change Gamma_z
				try {
					//Change U value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("building.zone(i).e_c_H_bar =")||fileContent.get(i).contains("building.zone(i).e_c_H_bar=")) {
					    	label.setText(" ");
							showValue.setText(" ");
							label_zone.setText(fileContent.get(i));
					    }
					}
				} catch (Exception e) {
					// Check 
					label.setText("Threshold_e_c_H_bar value not found");
				}
			}
			
		}

	public static void showPoles(JTextField value, int zone_number, String pathTemp) {
		// Change Closed loop Poles
		Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
		Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(path1);
		paths.add(path2);
		
		 		
		for (Path p : paths) {
			// Find file content
			List<String> fileContent = null;
			try {
				fileContent = new ArrayList<>(Files.readAllLines(p));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				label.setText("No m.file content");
				return;
			}
			// Change Gamma_z
			try {
				//Change U value and store it
				int flag = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("case")&&fileContent.get(i).contains(" "+Integer.toString(zone_number)+" ")) {
				        System.out.println(fileContent.get(i).toString());
				        flag = 1;
				    }
				    if (fileContent.get(i).contains("des_char_eq")&&(flag==1)) {
				    	 System.out.println(fileContent.get(i).toString());
				    	label.setText("");
				    	showValue.setText(fileContent.get(i));
				    	 flag = 0;
				    }
				}
			
			} catch (Exception e) {
				// Check 
				label.setText("Poles values didn't change");
				showValue.setText(" ");
			}
		}
				
		
	}

	
	
	
}