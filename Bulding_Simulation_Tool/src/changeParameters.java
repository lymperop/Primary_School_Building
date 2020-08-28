import java.io.BufferedReader;
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

import javax.swing.JTextField;

public class changeParameters extends change_IDF{

	public static void changeDateRange(String value, String pathTemp)  {
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
			        line = "<property name=\"endTime\" class=\"ptolemy.data.expr.Parameter\" value=\"" + value + "*24*3600\">";
			        label.setText("Date Range changed");
			    }
				fileContent.add(line);
			}
		} catch (IOException e1) {
			// Empty file check
			label.setText("Date Range not changed");
			showValue.setText(" ");
		}
		try {
			Files.write(Paths.get(pathTemp , "system.xml"), fileContent, StandardCharsets.UTF_8);
			label.setText("XML changed successfully");
			showValue.setText("NumberOfDays = " + value);
		} catch (IOException e) {
			// Didn't write XML
			label.setText("XML not changed successfully");
			showValue.setText(" ");
		}
		
	
	}
	
	public static void changeEndMonth(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change End Month
		try {
			//Change End Month value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("!- End Month")) {
			        fileContent.set(i, value +", !- End Month");
			        showValue.setText(fileContent.get(i));
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			label.setText("End Month value change was successful");
			showValue.setText("EndMonth = " + value);
		} catch (Exception e) {
			// Check 
			label.setText("End Month value didn't change");
			showValue.setText(" ");
		}
		
	}
	
	public static void changeBeginMonth(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change Begin Month
		try {
			//Change Begin Month value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("!- Begin Month")) {
			        fileContent.set(i, value +", !- Begin Month");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			label.setText("Begin Month value change was successful");
			showValue.setText("BeginMonth = " + value);
		} catch (Exception e) {
			// Check 
			label.setText("Begin Month value didn't change");
			showValue.setText(" ");
		}
		
	}
	
	public static void changeEndDay(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change Day Month
		try {
			//Change End Day value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("!- End Day of Month")) {
			        fileContent.set(i, value +", !- End Day of Month");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			label.setText("End Day value change was successful");
			showValue.setText("EndDay = " + value);
		} catch (Exception e) {
			// Check 
			label.setText("End Day value didn't change");
			showValue.setText(" ");
		}
		
	}

	public static void changeBeginDay(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change Begin Day
				try {
					//Change Begin Day value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("!- Begin Day of Month")) {
					        fileContent.set(i, value +", !- Begin Day of Month");
					        noChanges += 1;
					    }
					}
					Files.write(path, fileContent, StandardCharsets.UTF_8);
					label.setText("Begin Day value change was successful");
					showValue.setText("BeginDay = " + value);
				} catch (Exception e) {
					// Check 
					label.setText("Begin Day value didn't change");
					showValue.setText(" ");
				}
		
	}

	public static void changeU(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change U
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("Set " +zone+"_CWCoil_U =")) {
			        fileContent.set(i, "Set " + zone + "_CWCoil_U = " + value +",");
			        noChanges += 1;
			    }else if (fileContent.get(i).contains("Set " +zone+"_HWCoil_U =")){
			    	fileContent.set(i, "Set " + zone + "_HWCoil_U = " + value +",");
			    	noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			label.setText("U value change was successful");
			showValue.setText(zone + "_Coil_U = " + value);
		} catch (Exception e) {
			// Check 
			label.setText("U value didn't change");
			showValue.setText(" ");
		}
			
	}
	
	public static void changeA(String file, String zone, String value,  List<String> fileContent, Path path) {
		// ChangeA
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("Set " +zone+"_CWCoil_A =")) {
			        fileContent.set(i, "Set " + zone + "_CWCoil_A = " + value +",");
			        noChanges += 1;
			    }else if (fileContent.get(i).contains("Set " +zone+"_HWCoil_A =")){
			    	fileContent.set(i, "Set " + zone + "_HWCoil_A = " + value +",");
			    	noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			label.setText("A value change was successful");
			showValue.setText(zone + "_Coil_A = " + value);
		} catch (Exception e) {
			// Check 
			label.setText("A value didn't change");
			showValue.setText(" ");
		}
		
	}
	
	public static void changeUA(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change UA
				try {
					//Change UA value and store it
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("Set " +zone+"_CWCoil_UA =")) {
					        fileContent.set(i, "Set " + zone + "_CWCoil_UA = " + zone + "_CWCoil_U*" + zone + "_CWCoil_A*" + value +",");
					        noChanges += 1;
					    }else if (fileContent.get(i).contains("Set " +zone+"_HWCoil_UA =")){
					    	fileContent.set(i, "Set " + zone + "_HWCoil_UA = " + zone + "_HWCoil_U*" + zone + "_HWCoil_A*" + value +",");
					    	noChanges += 1;
					    }
					}
					Files.write(path, fileContent, StandardCharsets.UTF_8);
					label.setText("UA value change was successful");
					showValue.setText(zone + "_Coil_U = " + value);
				} catch (Exception e) {
					// Check 
					label.setText("UA value didn't change");
					showValue.setText(" ");
				}
	}

	public static void change_CcoilAir(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change CcoilAir
				try {
					//Change CcoilAir value and store it
					int noChanges = 0;
					boolean flag = false;

					for (int i = 1; i < fileContent.size(); i++) {
						if ( fileContent.get(i-1).equals("EnergyManagementSystem:Program,") && fileContent.get(i).contains(zone +"_H_InitSimpleCoilModel,") ) {
						flag = true;
						}

					    if (fileContent.get(i).contains("Set C_coil_air =")) {
					        fileContent.set(i, "    Set C_coil_air = " + value +",");
					        noChanges += 1;
					        flag = false;

					    }
					    
					}
					
					Files.write(path, fileContent, StandardCharsets.UTF_8);
					label.setText("CcoilAir value change was successful");
					showValue.setText(zone + "_Coil_U = " + value);
				} catch (Exception e) {
					// Check 
					label.setText("CcoilAir value didn't change");
//					showValue.setText(" ");
				}
	}
	
	public static void change_CcoilWater(String file, String zone, String value, List<String> fileContent, Path path) {
		// Change change_CcoilWater
				try {
					//Change change_CcoilWater value and store it
					int noChanges = 0;
					boolean flag = false;
					
					for (int i = 1; i < fileContent.size(); i++) {
						
						if (fileContent.get(i-1).equals("EnergyManagementSystem:Program,") && (fileContent.get(i).contains(zone +"_C_InitSimpleCoilModel,")|| fileContent.get(i).contains(zone +"_H_InitSimpleCoilModel,"))&& fileContent.get(i).contains("!- Name")) {
						flag = true;
					}
					    if (fileContent.get(i).contains("Set C_coil_water = ") && flag == true) {
					        fileContent.set(i, "    Set C_coil_water = " + value +",");
					        noChanges += 1;
					        flag = false;
					    }
					}
					Files.write(path, fileContent, StandardCharsets.UTF_8);
					label.setText("change_CcoilWater value change was successful");
					showValue.setText(zone + "_Coil_U = " + value);
				} catch (Exception e) {
					// Check 
					label.setText("change_CcoilWater value didn't change");
					showValue.setText(" ");
				}
	}
				
	
	public static void changeMsa(String file, String zone, String value, List<String> fileContent, Path path) {
		// ChangeMsa
		try {
			// Useful counters and flag
			int noChanges = 0;
			boolean flag = false;
			int heatCoolCounter = 0;
			
			
			for (int i = 1; i < fileContent.size(); i++) {

				System.out.println(fileContent.get(i).toString());
				// Change air flow in Sizing:Zone
				if (fileContent.get(i-1).equals("Sizing:Zone,") && fileContent.get(i).contains(zone +"_ZN_1_FLR_1,") && fileContent.get(i).contains("!- Zone or ZoneList Name")) {
					flag = true;
				}
				if (fileContent.get(i).contains("!- Cooling Design Air Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + ", !- Cooling Design Air Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
				} else if (fileContent.get(i).contains("!- Heating Design Air Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + ", !- Heating Design Air Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
			        if (heatCoolCounter == 2) {
			        	flag = false;
			        	heatCoolCounter = 0;
			        }
				}
				
				// Change air flow in Sizing:System and AirLoopHVAC:UnitarySystem
				if ((fileContent.get(i-1).equals("Sizing:System,") && fileContent.get(i).contains("AHU " + zone +",") && fileContent.get(i).contains("!- AirLoop Name")) ||
						(fileContent.get(i-1).equals("AirLoopHVAC:UnitarySystem,") && fileContent.get(i).contains("AHU " + zone +" Unitary System,") && fileContent.get(i).contains("!- Name"))) {
					flag = true;
				}
				if (fileContent.get(i).contains("!- Cooling Supply Air Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + ", !- Cooling Supply Air Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
				} else if (fileContent.get(i).contains("!- Heating Supply Air Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + ", !- Heating Supply Air Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
			        if (heatCoolCounter == 2) {
			        	flag = false;
			        	heatCoolCounter = 0;
			        }
				}
				
				// Change air flow in AirLoopHVAC
				if (fileContent.get(i-1).equals("AirLoopHVAC,") && fileContent.get(i).contains("AHU " + zone +",") && fileContent.get(i).contains("!- Name")) {
					flag = true;
				};
				if (fileContent.get(i).contains("!- Design Supply Air Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + ", !- Design Supply Air Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
			        if (heatCoolCounter == 1) {
			        	flag = false;
			        	heatCoolCounter = 0;
			        }
				}
				
				// Change air flow in AirTerminal:SingleDuct:Uncontrolled
				if (fileContent.get(i-1).equals("AirTerminal:SingleDuct:Uncontrolled,") && fileContent.get(i).contains(zone +" Direct Air,") && fileContent.get(i).contains("!- Name")) {
					flag = true;
				};
				if (fileContent.get(i).contains("!- Maximum Air Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + "; !- Maximum Air Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
			        if (heatCoolCounter == 1) {
			        	flag = false;
			        	heatCoolCounter = 0;
			        }
				}
				
				// Change air flow in Fan:ConstantVolume
				if (fileContent.get(i-1).equals("Fan:ConstantVolume,") && fileContent.get(i).contains("AHU "+ zone +" Supply Fan,") && fileContent.get(i).contains("!- Name")) {
					flag = true;
				};
				if (fileContent.get(i).contains("!- Maximum Flow Rate {m3/s}") && flag == true) {
					fileContent.set(i, value + ", !- Maximum Flow Rate {m3/s}");
					noChanges += 1;
			        heatCoolCounter += 1;
			        if (heatCoolCounter == 1) {
			        	flag = false;
			        	heatCoolCounter = 0;
			        }
				}
				
				// Change in EMS
				if (fileContent.get(i).contains("Set " + zone +"_CWCoil_Air_Mdot =")) {
			        fileContent.set(i, "Set " + zone + "_CWCoil_Air_Mdot = " + value + "*" + zone +"_CWCoil_Air_rho*On_Off,");
			        noChanges += 1;
			    }else if (fileContent.get(i).contains("Set " +zone +"_HWCoil_Air_Mdot =")){
			    	fileContent.set(i, "Set " + zone + "_HWCoil_Air_Mdot = " + value + "*" + zone +"_HWCoil_Air_rho*On_Off,");
			    	noChanges += 1;
			    }else if (fileContent.get(i).contains("Set Asa = -1*(" + zone +"_CWCoil_UA ")) {
			        fileContent.set(i, "Set Asa = -1*(" + zone + "_CWCoil_UA + " + value + "*" + zone +"_CWCoil_Air_rho*" + zone + "_CWCoil_Air_cp)/(C_coil_air),");
			        noChanges += 1;
			    }else if (fileContent.get(i).contains("Set Asa = -1*(" + zone +"_HWCoil_UA ")){
			    	fileContent.set(i, "Set Asa = -1*(" + zone + "_HWCoil_UA + " + value + "*" + zone +"_HWCoil_Air_rho*" + zone + "_HWCoil_Air_cp)/(C_coil_air),");
			    	noChanges += 1;
			    }else if (fileContent.get(i).toLowerCase().contains("set " + zone.toLowerCase() + "_cwcoil_inletcoeff ")) {
			        fileContent.set(i, "Set " + zone + "_CWCoil_InletCoeff  = (" + value + "*" + zone +"_CWCoil_Air_cp/" + zone + "_CWCoil_UA);");
			        noChanges += 1;
			    }else if (fileContent.get(i).toLowerCase().contains("set " + zone.toLowerCase() + "_hwcoil_inletcoeff ")){
			    	fileContent.set(i, "Set " + zone + "_HWCoil_InletCoeff  = (" + value + "*" + zone +"_HWCoil_Air_cp/" + zone + "_HWCoil_UA);");
			    	noChanges += 1;
			    }
				
				
				
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			label.setText("Msa value change was successful");
			showValue.setText(zone + "_msa_A = " + value);
		} catch (Exception e) {
			// Check 
			label.setText("Msa value didn't change");

			showValue.setText(" ");
		}
		
	}

	public static void changeMcMax(String value, String pathTemp) {

		
		// Change valve saturation bound 
		Path path = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
		
		// Find file content
		List<String> fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change saturation bound 
		try {
			
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("set_param(['controllerAd/Controller/Saturation']")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/Saturation'],'UpperLimit','" +value + "*ones(50,1)');");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Mc_bound value change was successful");
				showValue.setText("Mc upper bound = " +value);
			} else {
				label.setText("Mc_bound value didn't change");
				showValue.setText("");
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("Mc_bound value didn't change");
			showValue.setText(" ");
		}
		
		path = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		
		// Find file content
		fileContent = null;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			label.setText("No m.file content");
			return;
		}
		// Change saturation bound 
		try {
			
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("set_param(['controllerAd/Controller/Saturation']")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/Saturation'],'UpperLimit','" +value + "*ones(50,1)');");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Mc_bound value change was successful");
				showValue.setText("Mc upper bound = " +value);
			} else {
				label.setText("Mc_bound value didn't change");
				showValue.setText("");
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("Mc_bound value didn't change");
			showValue.setText(" ");
		}
	}
	
	
	public static void changeGammaZ(String value, String pathTemp) {
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
			        fileContent.set(i, "    gammaZ(:,:,i)  =  {1*abs(diag(" +value + "*round(gamZ{i},1,'significant')))};");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Gamma_z value change was successful");
				showValue.setText("    gammaZ(:,:,i)  =  {1*abs(diag(" +value + "*round(gamZ{i},1,'significant')))};");
			} else {
				label.setText("Gamma_z value didn't change");
				showValue.setText("");
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("Gamma_z value didn't change");
			showValue.setText(" ");
		}
		
	}

	public static void changeGammaSA(String value, String pathTemp) {
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
			        fileContent.set(i, "    gammaSA(:,:,i) =  {1*abs(diag(" + value + "*round(gamSA{i},1,'significant')))};");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Gamma_sa value change was successful");
				showValue.setText("    gammaSA(:,:,i) =  {1*abs(diag(" + value + "*round(gamSA{i},1,'significant')))};");
			} else {
				label.setText("Gamma_sa value didn't change");
				showValue.setText("");
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("Gamma_sa value didn't change");
			showValue.setText(" ");
		}
	}

	public static void changeGammaC(String value, String pathTemp) {
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
		// Change Gamma_C
		try {
			//Change U value and store it
			int noChanges = 0;
			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).contains("gammaC(:,:,i) = ")) {
			        fileContent.set(i, "    gammaC(:,:,i) =  {1*abs(diag(" + value + "*round(gamC{i},1,'significant')))};");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Gamma_C value change was successful");
				showValue.setText("    gammaC(:,:,i) =  {1*abs(diag(" + value + "*round(gamC{i},1,'significant')))};");
			} else {
				label.setText("Gamma_C value didn't change");
				showValue.setText("");
			}
		} catch (Exception e) {
			// Check 
			label.setText("Gamma_C value didn't change");
			showValue.setText(" ");
		}
	}

	
	public static void changeDirc(int ind) {
		// Change Folder to store results
			File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
			String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
			new File(pathTemp + "\\Results_" + ind).mkdir();
			ArrayList<Path> paths = new ArrayList<Path>();
			Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
			paths.add(path1);
			Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
			paths.add(path2);
			for(Path path : paths) {

				// Find file content
				List<String> fileContent = null;
				try {
					fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					label.setText("No m.file content");
					return;
				}
				// Change folder to store results
				try {
					int noChanges = 0;
					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).contains("dirc= [newdir,'\\Results")) {
					        fileContent.set(i, "dirc= [newdir,'\\Results_" + ind + "'];");
					        noChanges += 1;
					    }
					}
					Files.write(path, fileContent, StandardCharsets.UTF_8);
					label.setText("Folder to store resutls changed successfully");
				} catch (Exception e) {
					// Check 
					label.setText("Folder to store resutls didn't change successfully");
				}
			}
			
			
		
	}

	public static void changeProjZ(String value, String pathTemp) {
		// TODO Auto-generated method stub
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
					        fileContent.set(i, "    'UpperSaturationLimit',["+ value +"*abs(gamZ{', num2str(i) ,'})+0.0001'],'LowerSaturationLimit',['[0.001;-"+ value +"*abs(gamZ{', num2str(i) ,'}(2:end))]'],'IntegratorMethod','Integration: Backward Euler')");
					        noChanges += 1;
					    }
					}
					Files.write(path, fileContent, StandardCharsets.UTF_8);
					if (noChanges !=0) {
						label.setText("Zone projection bound change was successful");
						showValue.setText("Zone projection bound = " +value);
					} else {
						label.setText("Zone projection bound value didn't change");
						showValue.setText("");
					}
					
				} catch (Exception e) {
					// Check 
					label.setText("Zone projection bound didn't change");
					showValue.setText(" ");
				}
	}

	public static void changeProjSA(String value, String pathTemp) {
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
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['"+value+"*abs(gamSA{', num2str(i) ,'})'])");
			        noChanges += 1;
			    } else if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['[0.001;")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['[0.001;-"+value+"*abs(gamSA{', num2str(i) ,'}(2:end))]'])");
			        noChanges += 1;
			    } else if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['"+value+"*abs(gamSA{', num2str(i) ,'})'])");
			        noChanges += 1;
			    } else if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/SupplyAirAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['[0.001;-"+value+"*abs(gamSA{', num2str(i) ,'}(2:end))]'])");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Supply air projection bound change was successful");
				showValue.setText("Supply air projection bound = " +value);
			} else {
				label.setText("Supply air projection bound value didn't change");
				showValue.setText("");
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("Supply air projection bound didn't change");
			showValue.setText(" ");
		}
	}

	public static void changeProjC(String value, String pathTemp) {
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
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['"+value+"*abs(gamC{', num2str(i) ,'})'])");
			        noChanges += 1;
			    } else if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['[")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Cooling/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['[0.001;-"+value+"*abs(gamC{', num2str(i) ,'}(2:end))]'])");
			        noChanges += 1;
			    } else if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'UpperSaturationLimit',['"+value+"*abs(gamC{', num2str(i) ,'})'])");
			        noChanges += 1;
			    } else if (fileContent.get(i).contains("set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['")) {
			        fileContent.set(i, "set_param(['controllerAd/Controller/' building.zone(i).tag '_Heating/CoilAdaptiveController/Discrete Integrator/Discrete-Time Integrator'],'LowerSaturationLimit',['[0.01;-"+value+"*abs(gamC{', num2str(i) ,'}(2:end))]'])");
			        noChanges += 1;
			    }
			}
			Files.write(path, fileContent, StandardCharsets.UTF_8);
			if (noChanges !=0) {
				label.setText("Zone projection bound change was successful");
				showValue.setText("Zone projection bound = " +value);
			} else {
				label.setText("Zone projection bound value didn't change");
				showValue.setText("");
			}
			
		} catch (Exception e) {
			// Check 
			label.setText("Zone projection bound didn't change");
			showValue.setText(" ");
		}
		
	}

	public static void changeConductivity(String file, String zone, String value, List<String> fileContent, Path path) {
		// TODO Auto-generated method stub
		// ChangeA
				try {
					//Change U value and store it
					int noChanges = 0;
					double temp, temp1;
					String tempSt;
					boolean flag = false;
					
					for (int i = 0; i < fileContent.size(); i++) {
						showValue.setText(fileContent.get(i));
						
						if ( fileContent.get(i).equals("Material,") ) {
							flag = true;
						}
						
						
					    if (fileContent.get(i).contains("!- Conductivity {W/m-K}") && fileContent.get(i).contains(",") && flag == true) {
					    	temp = Double.parseDouble(value);
					    	temp1 = Double.parseDouble(fileContent.get(i).replace("!- Conductivity {W/m-K}", "").replace(",", "").trim());
					    	temp = temp*temp1;
					    	tempSt = String.valueOf(temp);
					        fileContent.set(i, tempSt + ",                     !- Conductivity {W/m-K}");
					        noChanges += 1;
					        flag = false;
					    } else if (fileContent.get(i).contains("!- Conductivity {W/m-K}") && fileContent.get(i).contains(";") && flag == true) {
					    	temp = Double.parseDouble(value);
					    	temp1 = Double.parseDouble(fileContent.get(i).replace("!- Conductivity {W/m-K}", "").replace(";", "").trim());
					    	temp = temp*temp1;
					    	tempSt = String.valueOf(temp);
					        fileContent.set(i, tempSt + ";                     !- Conductivity {W/m-K}");
					        noChanges += 1;
					        flag = false;
					    }
					    
					}
					Files.write(path, fileContent, StandardCharsets.UTF_8);
//					label.setText("A value change was successful");
//					showValue.setText("Conductivity  = X* " + value);
				} catch (Exception e) {
					// Check 
//					label.setText("Conductivity value didn't change");
					
				}
	}

	public static void changeAbrZ(String value, String pathTemp) {
		// A_br for zone
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).A_br_z =")) {
				        fileContent.set(i, "building.zone(i).A_br_z = " + value + "*building.zone(i).A_z;");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("A_br_z value change was successful");
					showValue.setText("building.zone(i).A_br_z = " + value + "*building.zone(i).A_z");
				} else {
					label.setText("A_br_z value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("A_br_z value didn't change");
				showValue.setText(" ");
			}
		}
		
		
	}

	public static void changeAbrSA(String value, String pathTemp) {
		// A_br for supply air
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).A_br_sa_C =")) {
				        fileContent.set(i, "building.zone(i).A_br_sa_C = " + value + "*building.zone(i).A_sa_C;");
				        noChanges += 1;
				    }
				    else if (fileContent.get(i).contains("building.zone(i).A_br_sa_H =")) {
				    	fileContent.set(i, "building.zone(i).A_br_sa_H = " + value + "*building.zone(i).A_sa_H;");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("A_br_sa value change was successful");
					showValue.setText("building.zone(i).A_br_sa = " + value + "*building.zone(i).A_sa");
				} else {
					label.setText("A_br_sa value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("A_br_sa value didn't change");
				showValue.setText(" ");
			}
		}
				
		
	}

	public static void changeAbrC(String value, String pathTemp) {

		// A_br for coil
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).A_br_c_C =")) {
				        fileContent.set(i, "building.zone(i).A_br_c_C = " + value + "*building.zone(i).A_c_C;");
				        noChanges += 1;
				    }
				    else if (fileContent.get(i).contains("building.zone(i).A_br_c_H =")) {
				    	fileContent.set(i, "building.zone(i).A_br_c_H = " + value + "*building.zone(i).A_c_H;");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("A_br_c value change was successful");
					showValue.setText("building.zone(i).A_br_c = " + value + "*building.zone(i).A_c");
				} else {
					label.setText("A_br_c value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("A_br_c value didn't change");
				showValue.setText(" ");
			}
		}
		
	}
	

	public static void changeNbar(String value, String pathTemp) {
		// Nbar
		Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
		Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\SystemParameters.m" );
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).n_bar=")||fileContent.get(i).contains("building.zone(i).n_bar =")) {
				        fileContent.set(i, "building.zone(i).n_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("n_bar value change was successful");
					showValue.setText("building.zone(i).n_bar =" + value);
				} else {
					label.setText("n_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("n_bar value didn't change");
				showValue.setText(" ");
			}
		}
				
		
	}

		
	public static void changeNsabar(String value, String pathTemp) {
		// Nsabar
		Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
		Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\SystemParameters.m" );
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).nSA_bar=")||fileContent.get(i).contains("building.zone(i).nSA_bar =")) {
				        fileContent.set(i, "building.zone(i).nSA_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("nSA_bar value change was successful");
					showValue.setText("building.zone(i).nSA_bar =" + value);
				} else {
					label.setText("nSA_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("nSA_bar value didn't change");
				showValue.setText(" ");
			}
		}
						
		
	}


	public static void changeNCbar(String value, String pathTemp) {
		// NCbar
		Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
		Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\SystemParameters.m" );
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).nC_bar=")||fileContent.get(i).contains("building.zone(i).nC_bar =")) {
				        fileContent.set(i, "building.zone(i).nC_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("nC_bar value change was successful");
					showValue.setText("building.zone(i).nC_bar =" + value);
				} else {
					label.setText("nC_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("nC_bar value didn't change");
				showValue.setText(" ");
			}
		}
		
	}


	public static void changeNHbar(String value, String pathTemp) {
		// NHbar
		Path path1 = Paths.get(pathTemp, "\\Simulink\\SystemParameters.m" );
		Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\SystemParameters.m" );
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).nH_bar=")||fileContent.get(i).contains("building.zone(i).nH_bar =")) {
				        fileContent.set(i, "building.zone(i).nH_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("nH_bar value change was successful");
					showValue.setText("building.zone(i).nH_bar =" + value);
				} else {
					label.setText("nH_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("nH_bar value didn't change");
				showValue.setText(" ");
			}
		}

	}


	public static void changeNnei(String value, String pathTemp) {
		// Nnei
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).n_nei(1,j)=")||fileContent.get(i).contains("building.zone(i).n_nei(1,j) =")) {
				        fileContent.set(i, "building.zone(i).n_nei(1,j) = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("n_nei(1,j) value change was successful");
					showValue.setText("building.zone(i).n_nei(1,j) =" + value);
				} else {
					label.setText("n_nei(1,j) value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("n_nei(1,j) value didn't change");
				showValue.setText(" ");
			}
		}

	}

	public static void changeThresA(String value, String pathTemp) {
		// Threshold_A
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).a=")||fileContent.get(i).contains("building.zone(i).a =")) {
				        fileContent.set(i, "building.zone(i).a = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold a value change was successful");
					showValue.setText("building.zone(i).a =" + value);
				} else {
					label.setText("Threshold a value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold a value didn't change");
				showValue.setText(" ");
			}
		}

		
	}

	public static void changeThresEbar(String value, String pathTemp) {
		// Threshold_E_bar
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).e_bar=")||fileContent.get(i).contains("building.zone(i).e_bar =")) {
				        fileContent.set(i, "building.zone(i).e_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold e_bar value change was successful");
					showValue.setText("building.zone(i).e_bar =" + value);
				} else {
					label.setText("Threshold e_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold e_bar value didn't change");
				showValue.setText(" ");
			}
		}

				
		
	}

	public static void changeThresQbar(String value, String pathTemp) {
		// Threshold_Q_bar
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).Qbar=")||fileContent.get(i).contains("building.zone(i).Qbar =")) {
				        fileContent.set(i, "building.zone(i).Qbar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold Qbar value change was successful");
					showValue.setText("building.zone(i).Qbar =" + value);
				} else {
					label.setText("Threshold Qbar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold Qbar value didn't change");
				showValue.setText(" ");
			}
		}
		
	}

	public static void changeThresAcC(String value, String pathTemp) {
		// Threshold_a_c_C
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).a_c_C=")||fileContent.get(i).contains("building.zone(i).a_c_C =")) {
				        fileContent.set(i, "building.zone(i).a_c_C = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold a_c_C value change was successful");
					showValue.setText("building.zone(i).a_c_C =" + value);
				} else {
					label.setText("Threshold a_c_C value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold a_c_C value didn't change");
				showValue.setText(" ");
			}
		}
				
		
	}

	public static void changeThresEcCbar(String value, String pathTemp) {
		// Threshold_e_c_C_bar
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).e_c_C_bar=")||fileContent.get(i).contains("building.zone(i).e_c_C_bar =")) {
				        fileContent.set(i, "building.zone(i).e_c_C_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold e_c_C_bar value change was successful");
					showValue.setText("building.zone(i).e_c_C_bar =" + value);
				} else {
					label.setText("Threshold e_c_C_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold e_c_C_bar value didn't change");
				showValue.setText(" ");
			}
		}
					
		
	}

	public static void changeThresAcH(String value, String pathTemp) {
		// Threshold_a_c_H
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).a_c_H=")||fileContent.get(i).contains("building.zone(i).a_c_H =")) {
				        fileContent.set(i, "building.zone(i).a_c_H = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold a_c_H value change was successful");
					showValue.setText("building.zone(i).a_c_H =" + value);
				} else {
					label.setText("Threshold a_c_H value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold a_c_H value didn't change");
				showValue.setText(" ");
			}
		}
				
		
	}

	public static void changeThresEcHbar(String value, String pathTemp) {
		// Threshold_e_c_H_bar
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
				int noChanges = 0;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("building.zone(i).e_c_H_bar=")||fileContent.get(i).contains("building.zone(i).e_c_H_bar =")) {
				        fileContent.set(i, "building.zone(i).e_c_H_bar = " + value +";");
				        noChanges += 1;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (noChanges !=0) {
					label.setText("Threshold e_c_H_bar value change was successful");
					showValue.setText("building.zone(i).e_c_H_bar =" + value);
				} else {
					label.setText("Threshold e_c_H_bar value didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Threshold e_c_H_bar value didn't change");
				showValue.setText(" ");
			}
		}
					
		
	}

	public static void changePoles(String value, int zone_number, String pathTemp) {
		// Change Closed loop Poles
		Path path1 = Paths.get(pathTemp, "\\Simulink\\DesignParameters.m" );
		Path path2 = Paths.get(pathTemp, "\\SimulinkAd\\DesignParameters.m" );
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(path1);
		paths.add(path2);
		System.out.println("poles");
		String[] arrOfStr = value.split(" ");
		for (String a : arrOfStr) {
			  System.out.println(a); 
		}
		if (arrOfStr.length!=4) {
			return;
		}
		
		 		
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
				boolean Change = false;
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).contains("case")&&fileContent.get(i).contains(" "+Integer.toString(zone_number)+" ")) {
				        System.out.println(fileContent.get(i).toString());
				        flag = 1;
				    }
				    if (fileContent.get(i).contains("des_char_eq")&&(flag==1)) {
				    	 System.out.println(fileContent.get(i).toString());
				    	 fileContent.set(i,"        des_char_eq = (z-"+ arrOfStr[0] +")*(z-"+ arrOfStr[1] +")*((z-"+ arrOfStr[2] +")^2+"+ arrOfStr[3] +"^2);");
				    	 flag = 0;
				    	 Change = true;
				    }
				}
				Files.write(p, fileContent, StandardCharsets.UTF_8);
				if (Change) {
					label.setText("Poles change was successful");
					showValue.setText("New poles for zone : " + zone_number + " are :  "+ arrOfStr[0] + "   "+ arrOfStr[1] + "   "+ arrOfStr[2] + "+j"+ arrOfStr[3]);
				} else {
					label.setText("Poles values didn't change");
					showValue.setText("");
				}
				
			} catch (Exception e) {
				// Check 
				label.setText("Poles values didn't change");
				showValue.setText(" ");
			}
		}
		
	}


	
}
