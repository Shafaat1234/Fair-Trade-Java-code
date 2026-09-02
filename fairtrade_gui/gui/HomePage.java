package gui;
import entity.*;
import javax.swing.*;//JFrame, JButton, ...
import java.awt.*;//Font, Color
import java.awt.event.*;
import java.io.*;
public class HomePage extends JFrame implements ActionListener, FairTrade{
	Font font12 = new Font("Cambria",Font.BOLD,12);
	Font font15 = new Font("Cambria",Font.BOLD,15);
	Font font20 = new Font("Cambria",Font.BOLD,20);

	static final String DATA_FILE = "fairtrade_data.txt";

	JTabbedPane tabs;

	//------------- Vendor Tab -------------
	JLabel vendorSubTitle;
	JLabel vendorTypeLabel, slotLabel, vendorIDLabel, contactNameLabel, companyNameLabel, passwordLabel;
	JLabel stallNoLabel, sizeLabel, pavilionLabel, locationLabel, locationTypeLabel;

	JComboBox<String> vendorTypeCombo;
	JTextField slotField, vendorIDField, contactNameField, companyNameField;
	JPasswordField passwordField;
	JTextField stallNoField, sizeField, pavilionField, locationField, locationTypeField;

	JButton registerVendorButton, updateVendorButton, removeVendorButton, getVendorButton, loginVendorButton;
	JTextArea vendorScreen;

	Vendor vendors[] = new Vendor[100];

	//------------- Stall Tab -------------
	JLabel stallSubTitle;
	JLabel stallSlotLabel, stallNoLabel2, stallSizeLabel, stallLocationTypeLabel, allocateSlotLabel;

	JTextField stallSlotField, stallNoField2, stallSizeField, stallLocationTypeField, allocateSlotField;

	JButton registerStallButton, updateStallButton, removeStallButton, getStallButton, allocateButton, availableButton, occupiedButton;
	JTextArea stallScreen;

	Stall stalls[] = new Stall[100];

	//------------- Event Tab -------------
	JLabel eventSubTitle, eventStatusLabel, countLabel, totalRentLabel;
	JButton openEventButton, closeEventButton, refreshCountButton, totalRentButton, saveButton, loadButton;
	boolean eventOpen = false;

	public HomePage(){
		super("Fair Trade Manager");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(200,240,240));

		JLabel title = new JLabel("Fair Trade Manager");
		title.setBounds(10,5,400,30);
		title.setFont(font20);
		this.add(title);

		tabs = new JTabbedPane();
		tabs.setBounds(10,40,760,520);
		tabs.setFont(font15);
		this.add(tabs);

		JPanel vendorPanel = new JPanel();
		vendorPanel.setLayout(null);
		vendorPanel.setBackground(new Color(200,240,240));
		buildVendorTab(vendorPanel);
		tabs.addTab("Vendors", vendorPanel);

		JPanel stallPanel = new JPanel();
		stallPanel.setLayout(null);
		stallPanel.setBackground(new Color(200,240,240));
		buildStallTab(stallPanel);
		tabs.addTab("Stalls", stallPanel);

		JPanel eventPanel = new JPanel();
		eventPanel.setLayout(null);
		eventPanel.setBackground(new Color(200,240,240));
		buildEventTab(eventPanel);
		tabs.addTab("Event Control", eventPanel);

		this.setVisible(true);
	}

	//---------------------------------------------------------------
	// TAB BUILDERS
	//---------------------------------------------------------------

	void buildVendorTab(JPanel panel){
		int x=10, y=0, w=150, h=28, vGap = h+8;

		vendorSubTitle = creatLabel(panel,"Register / Update a Vendor Stall",x,y,w*3,h);

		vendorTypeLabel = creatLabel(panel,"Stall Type",x,y+=vGap,w,h);
		vendorTypeCombo = new JComboBox<String>(new String[]{"General Stall","Food Court Stall"});
		vendorTypeCombo.setBounds(x+w,y,w,h);
		vendorTypeCombo.setFont(font15);
		vendorTypeCombo.addActionListener(this);
		panel.add(vendorTypeCombo);

		slotLabel = creatLabel(panel,"Slot No.",x,y+=vGap,w,h);
		slotField = creatField(panel,"",x+w,y,w,h);

		vendorIDLabel = creatLabel(panel,"Vendor ID",x,y+=vGap,w,h);
		vendorIDField = creatField(panel,"",x+w,y,w,h);

		contactNameLabel = creatLabel(panel,"Contact Name",x,y+=vGap,w,h);
		contactNameField = creatField(panel,"",x+w,y,w,h);

		companyNameLabel = creatLabel(panel,"Company Name",x,y+=vGap,w,h);
		companyNameField = creatField(panel,"",x+w,y,w,h);

		passwordLabel = creatLabel(panel,"Password",x,y+=vGap,w,h);
		passwordField = new JPasswordField();
		passwordField.setBounds(x+w,y,w,h);
		passwordField.setFont(font15);
		panel.add(passwordField);

		stallNoLabel = creatLabel(panel,"Stall Number",x,y+=vGap,w,h);
		stallNoField = creatField(panel,"",x+w,y,w,h);

		sizeLabel = creatLabel(panel,"Size",x,y+=vGap,w,h);
		sizeField = creatField(panel,"",x+w,y,w,h);

		pavilionLabel = creatLabel(panel,"Pavilion Block",x,y,w,h);
		pavilionField = creatField(panel,"",x+w,y,w,h);

		locationLabel = creatLabel(panel,"Location",x,y+=vGap,w,h);
		locationField = creatField(panel,"",x+w,y,w,h);

		locationTypeLabel = creatLabel(panel,"Location Type",x,y+=vGap,w,h);
		locationTypeField = creatField(panel,"",x+w,y,w,h);

		registerVendorButton = creatButton(panel,"Register",x,y+=vGap,w-5,h);
		registerVendorButton.setBackground(new Color(114,250,174));

		loginVendorButton = creatButton(panel,"Login",x+w+5,y,w-5,h);
		loginVendorButton.setBackground(new Color(255,220,120));

		updateVendorButton = creatButton(panel,"Update",x,y+=vGap,w-5,h);
		updateVendorButton.setBackground(Color.BLUE);
		updateVendorButton.setForeground(Color.WHITE);

		removeVendorButton = creatButton(panel,"Remove",x+w+5,y,w-5,h);
		removeVendorButton.setBackground(Color.RED);
		removeVendorButton.setForeground(Color.WHITE);

		getVendorButton = creatButton(panel,"Get (View One)",x,y+=vGap,w*2,h);
		getVendorButton.setBackground(new Color(180,200,255));

		vendorScreen = new JTextArea();
		vendorScreen.setFont(font12);
		JScrollPane jsp = new JScrollPane(vendorScreen);
		jsp.setBounds(x+w*2+20,0,320,y+vGap);
		panel.add(jsp);

		//hide the Food Court-only fields until that type is chosen
		pavilionLabel.setVisible(false);
		pavilionField.setVisible(false);
		locationLabel.setVisible(false);
		locationField.setVisible(false);

		updateVendorScreen();
	}

	void buildStallTab(JPanel panel){
		int x=10, y=0, w=150, h=28, vGap = h+8;

		stallSubTitle = creatLabel(panel,"Register a Stall (Fair Trade Event)",x,y,w*3,h);

		stallSlotLabel = creatLabel(panel,"Slot No. (update/remove)",x,y+=vGap,w,h);
		stallSlotField = creatField(panel,"",x+w,y,w,h);

		stallNoLabel2 = creatLabel(panel,"Stall Number",x,y+=vGap,w,h);
		stallNoField2 = creatField(panel,"",x+w,y,w,h);

		stallSizeLabel = creatLabel(panel,"Size",x,y+=vGap,w,h);
		stallSizeField = creatField(panel,"",x+w,y,w,h);

		stallLocationTypeLabel = creatLabel(panel,"Location Type",x,y+=vGap,w,h);
		stallLocationTypeField = creatField(panel,"",x+w,y,w,h);

		registerStallButton = creatButton(panel,"Register (auto slot)",x,y+=vGap,w*2,h);
		registerStallButton.setBackground(new Color(114,250,174));

		updateStallButton = creatButton(panel,"Update",x,y+=vGap,w-5,h);
		updateStallButton.setBackground(Color.BLUE);
		updateStallButton.setForeground(Color.WHITE);

		removeStallButton = creatButton(panel,"Remove",x+w+5,y,w-5,h);
		removeStallButton.setBackground(Color.RED);
		removeStallButton.setForeground(Color.WHITE);

		getStallButton = creatButton(panel,"Get (View One)",x,y+=vGap,w*2,h);
		getStallButton.setBackground(new Color(180,200,255));

		allocateSlotLabel = creatLabel(panel,"Vendor Slot",x,y+=vGap,w,h);
		allocateSlotField = creatField(panel,"",x+w,y,w,h);

		allocateButton = creatButton(panel,"Allocate to Vendor",x,y+=vGap,w*2,h);
		allocateButton.setBackground(new Color(180,200,255));

		availableButton = creatButton(panel,"Mark Available",x,y+=vGap,w-5,h);
		availableButton.setBackground(new Color(114,250,174));

		occupiedButton = creatButton(panel,"Mark Occupied",x+w+5,y,w-5,h);
		occupiedButton.setBackground(new Color(255,180,180));

		stallScreen = new JTextArea();
		stallScreen.setFont(font12);
		JScrollPane jsp = new JScrollPane(stallScreen);
		jsp.setBounds(x+w*2+20,0,320,y+vGap);
		panel.add(jsp);

		updateStallScreen();
	}

	void buildEventTab(JPanel panel){
		int x=10, y=0, w=200, h=30, vGap = h+10;

		eventSubTitle = creatLabel(panel,"Fair Trade Event Control",x,y,w*2,h);

		eventStatusLabel = creatLabel(panel,"Event Status: CLOSED",x,y+=vGap,w*2,h);
		eventStatusLabel.setFont(font20);

		openEventButton = creatButton(panel,"Open Event",x,y+=vGap,w-5,h);
		openEventButton.setBackground(new Color(114,250,174));

		closeEventButton = creatButton(panel,"Close Event",x+w+5,y,w-5,h);
		closeEventButton.setBackground(Color.RED);
		closeEventButton.setForeground(Color.WHITE);

		countLabel = creatLabel(panel,"",x,y+=vGap,w*2,h*2);
		countLabel.setFont(font15);

		refreshCountButton = creatButton(panel,"Refresh Counts",x,y+=vGap*2,w-5,h);
		refreshCountButton.setBackground(new Color(180,200,255));

		totalRentButton = creatButton(panel,"Calculate Total Rent",x+w+5,y,w-5,h);
		totalRentButton.setBackground(new Color(180,200,255));

		totalRentLabel = creatLabel(panel,"",x,y+=vGap,w*2,h);
		totalRentLabel.setFont(font15);

		saveButton = creatButton(panel,"Save to File",x,y+=vGap,w-5,h);
		saveButton.setBackground(new Color(114,250,174));

		loadButton = creatButton(panel,"Load from File",x+w+5,y,w-5,h);
		loadButton.setBackground(new Color(255,220,120));

		updateCountLabel();
	}

	//---------------------------------------------------------------
	// HELPERS (same style as the Team Manager sample)
	//---------------------------------------------------------------

	JLabel creatLabel(Container parent, String text, int x, int y, int w, int h){
		JLabel component = new JLabel(text);
		component.setBounds(x,y,w,h);//x,y,w,h
		component.setFont(font15);
		parent.add(component);
		return component;
	}

	JTextField creatField(Container parent, String text, int x, int y, int w, int h){
		JTextField component = new JTextField(text);
		component.setBounds(x,y,w,h);//x,y,w,h
		component.setFont(font15);
		parent.add(component);
		return component;
	}

	JButton creatButton(Container parent, String text, int x, int y, int w, int h){
		JButton component = new JButton(text);
		component.setBounds(x,y,w,h);//x,y,w,h
		component.setFont(font15);
		component.addActionListener(this);
		parent.add(component);
		return component;
	}

	void showError(String msg){
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	void showInfo(String msg){
		JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	//---------------------------------------------------------------
	// SCREEN REFRESH
	//---------------------------------------------------------------

	void updateVendorScreen(){
		String allVendorData = "";
		for(int i=0;i<vendors.length;i++){
			if(vendors[i]!=null){
				//polymorphism: getPaymentInfo() is called through the Vendor
				//reference but runs each subclass's own override
				allVendorData += i+". "+vendors[i].getPaymentInfo();
			}
		}
		vendorScreen.setText(allVendorData);
	}

	void updateStallScreen(){
		String allStallData = "";
		for(int i=0;i<stalls.length;i++){
			if(stalls[i]!=null){
				allStallData += i+". "+stalls[i].getPaymentInfo();
			}
		}
		stallScreen.setText(allStallData);
	}

	void updateCountLabel(){
		int vendorTotal = 0;
		int stallTotal = 0;
		for(Vendor v : vendors) if(v != null) vendorTotal++;
		for(Stall s : stalls) if(s != null) stallTotal++;
		countLabel.setText("<html>Total Vendors Registered: "+vendorTotal
			+"<br>Total Stalls Registered: "+stallTotal+"</html>");
	}

	// demonstrates polymorphism through the Payable interface: Vendor and Stall
	// are unrelated class trees, but both implement Payable, so this loop doesn't
	// need to know or care which concrete type it's adding up
	double calculateTotalRent(){
		double total = 0;
		for(Payable p : vendors){
			if(p != null) total += p.calculateRent();
		}
		for(Payable p : stalls){
			if(p != null) total += p.calculateRent();
		}
		return total;
	}

	//---------------------------------------------------------------
	// FairTrade INTERFACE
	//---------------------------------------------------------------

	public void openEvent(){
		eventOpen = true;
		eventStatusLabel.setText("Event Status: OPEN");
		System.out.println("Fair Trade event is now OPEN");
	}

	public void closeEvent(){
		eventOpen = false;
		eventStatusLabel.setText("Event Status: CLOSED");
		System.out.println("Fair Trade event is now CLOSED");
	}

	public void registerNewStall(Stall stall) throws NoAvailableSlotException{
		for(int i=0;i<stalls.length;i++){
			if(stalls[i]==null){
				stalls[i] = stall;
				System.out.println("Stall registered by Fair Trade at slot "+i);
				showInfo("Stall registered at slot "+i);
				return;
			}
		}
		throw new NoAvailableSlotException("No empty slot available to register a new stall");
	}

	//---------------------------------------------------------------
	// FILE I/O
	//---------------------------------------------------------------

	void saveToFile(){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))){
			for(int i=0;i<vendors.length;i++){
				if(vendors[i] instanceof FoodCourtStall){
					FoodCourtStall f = (FoodCourtStall)vendors[i];
					bw.write("VENDOR,FOODCOURT,"+i+","+f.getVendorID()+","+f.getContactName()+","
						+f.getCompanyName()+","+f.getPassword()+","+f.getStallNumber()+","+f.getPavilion()+","
						+f.getLocation()+","+f.getLocationType());
					bw.newLine();
				}
				else if(vendors[i] instanceof GeneralStall){
					GeneralStall g = (GeneralStall)vendors[i];
					bw.write("VENDOR,GENERAL,"+i+","+g.getVendorID()+","+g.getContactName()+","
						+g.getCompanyName()+","+g.getPassword()+","+g.getStallNumber()+","+g.getSize()+","+g.getLocationType());
					bw.newLine();
				}
			}
			for(int i=0;i<stalls.length;i++){
				if(stalls[i]!=null){
					Stall s = stalls[i];
					bw.write("STALL,"+i+","+s.getStallNumber()+","+s.getSize()+","
						+s.getLocationType()+","+s.isAvailable());
					bw.newLine();
				}
			}
			showInfo("Data saved to "+DATA_FILE);
		}
		catch(IOException ex){
			showError("Could not save file: "+ex.getMessage());
		}
	}

	void loadFromFile(){
		File file = new File(DATA_FILE);
		if(!file.exists()){
			showError("No saved file found ("+DATA_FILE+")");
			return;
		}
		int loaded = 0, skipped = 0;
		// Replace the current in-memory data with the saved data.
		// This prevents duplicate/stale records when Load is clicked more than once.
		java.util.Arrays.fill(vendors, null);
		java.util.Arrays.fill(stalls, null);
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line = br.readLine()) != null){
				if(line.trim().isEmpty()) continue;
				String[] p = line.split(",");
				try{
					if(p[0].equals("VENDOR")){
						int slot = Integer.parseInt(p[2]);
						if(p[1].equals("FOODCOURT")){
							vendors[slot] = new FoodCourtStall(p[3],p[4],p[5],p[6],
								Integer.parseInt(p[7]), Integer.parseInt(p[8]), p[9], p[10]);
						}
						else{
							vendors[slot] = new GeneralStall(p[3],p[4],p[5],p[6],
								Integer.parseInt(p[7]), Integer.parseInt(p[8]), p[9]);
						}
						loaded++;
					}
					else if(p[0].equals("STALL")){
						int slot = Integer.parseInt(p[1]);
						Stall s = new Stall(Integer.parseInt(p[2]), Integer.parseInt(p[3]), p[4]);
						s.updateAvailability(Boolean.parseBoolean(p[5]));
						stalls[slot] = s;
						loaded++;
					}
				}
				catch(InvalidDataException | NumberFormatException | ArrayIndexOutOfBoundsException ex){
					System.out.println("Skipped a corrupted line in "+DATA_FILE+": "+ex.getMessage());
					skipped++;
				}
			}
			updateVendorScreen();
			updateStallScreen();
			updateCountLabel();
			showInfo("Loaded "+loaded+" record(s)"+(skipped>0 ? (", skipped "+skipped+" bad line(s)") : ""));
		}
		catch(IOException ex){
			showError("Could not read file: "+ex.getMessage());
		}
	}

	//---------------------------------------------------------------
	// ACTIONS
	//---------------------------------------------------------------

	public void actionPerformed(ActionEvent e){

		//------------- Vendor combo toggling -------------
		if(vendorTypeCombo == e.getSource()){
			boolean foodCourt = "Food Court Stall".equals(vendorTypeCombo.getSelectedItem());
			sizeLabel.setVisible(!foodCourt);
			sizeField.setVisible(!foodCourt);
			pavilionLabel.setVisible(foodCourt);
			pavilionField.setVisible(foodCourt);
			locationLabel.setVisible(foodCourt);
			locationField.setVisible(foodCourt);
		}

		//------------- Vendor tab -------------
		else if(registerVendorButton == e.getSource()){
			try{
				int slot = Integer.parseInt(slotField.getText());
				String vendorID = vendorIDField.getText();
				String contactName = contactNameField.getText();
				String companyName = companyNameField.getText();
				String password = new String(passwordField.getPassword());
				int stallNumber = Integer.parseInt(stallNoField.getText());
				String locationType = locationTypeField.getText();

				if("Food Court Stall".equals(vendorTypeCombo.getSelectedItem())){
					int pavilion = Integer.parseInt(pavilionField.getText());
					String location = locationField.getText();
					vendors[slot] = new FoodCourtStall(vendorID, contactName, companyName, password,
						stallNumber, pavilion, location, locationType);
				}
				else{
					int size = Integer.parseInt(sizeField.getText());
					vendors[slot] = new GeneralStall(vendorID, contactName, companyName, password,
						stallNumber, size, locationType);
				}
				vendors[slot].register();
				updateVendorScreen();
				updateCountLabel();
			}
			catch(NumberFormatException ex){
				showError("Slot, Stall Number and Size/Pavilion must be valid numbers.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(vendors.length-1)+".");
			}
			catch(InvalidDataException ex){
				showError(ex.getMessage());
			}
		}
		else if(updateVendorButton == e.getSource()){
			try{
				int slot = Integer.parseInt(slotField.getText());
				if(vendors[slot]==null){
					throw new RecordNotFoundException("No vendor found at slot "+slot);
				}
				vendors[slot].setContactName(contactNameField.getText());
				vendors[slot].setCompanyName(companyNameField.getText());
				updateVendorScreen();
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(vendors.length-1)+".");
			}
			catch(RecordNotFoundException | InvalidDataException ex){
				showError(ex.getMessage());
			}
		}
		else if(removeVendorButton == e.getSource()){
			try{
				int slot = Integer.parseInt(slotField.getText());
				if(vendors[slot]==null){
					throw new RecordNotFoundException("No vendor found at slot "+slot);
				}
				vendors[slot] = null;
				updateVendorScreen();
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(vendors.length-1)+".");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}
		else if(loginVendorButton == e.getSource()){
			try{
				int slot = Integer.parseInt(slotField.getText());
				if(vendors[slot]==null){
					throw new RecordNotFoundException("No vendor found at slot "+slot);
				}
				String password = new String(passwordField.getPassword());
				vendors[slot].login(password);
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(vendors.length-1)+".");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}
		else if(getVendorButton == e.getSource()){
			try{
				int slot = Integer.parseInt(slotField.getText());
				if(vendors[slot]==null){
					throw new RecordNotFoundException("No vendor found at slot "+slot);
				}
				//polymorphism: getPaymentInfo() dispatches to whichever subclass is actually stored
				showInfo(vendors[slot].getPaymentInfo());
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(vendors.length-1)+".");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}

		//------------- Stall tab -------------
		else if(registerStallButton == e.getSource()){
			try{
				int stallNumber = Integer.parseInt(stallNoField2.getText());
				int size = Integer.parseInt(stallSizeField.getText());
				String locationType = stallLocationTypeField.getText();

				Stall stall = new Stall(stallNumber, size, locationType);
				registerNewStall(stall);//FairTrade interface method finds an open slot itself
				updateStallScreen();
				updateCountLabel();
			}
			catch(NumberFormatException ex){
				showError("Stall Number and Size must be valid numbers.");
			}
			catch(InvalidDataException | NoAvailableSlotException ex){
				showError(ex.getMessage());
			}
		}
		else if(updateStallButton == e.getSource()){
			try{
				int slot = Integer.parseInt(stallSlotField.getText());
				if(stalls[slot]==null){
					throw new RecordNotFoundException("No stall found at slot "+slot);
				}
				stalls[slot].setSize(Integer.parseInt(stallSizeField.getText()));
				stalls[slot].setLocationType(stallLocationTypeField.getText());
				updateStallScreen();
			}
			catch(NumberFormatException ex){
				showError("Slot and Size must be valid numbers.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(stalls.length-1)+".");
			}
			catch(RecordNotFoundException | InvalidDataException ex){
				showError(ex.getMessage());
			}
		}
		else if(removeStallButton == e.getSource()){
			try{
				int slot = Integer.parseInt(stallSlotField.getText());
				if(stalls[slot]==null){
					throw new RecordNotFoundException("No stall found at slot "+slot);
				}
				stalls[slot] = null;
				updateStallScreen();
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(stalls.length-1)+".");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}
		else if(getStallButton == e.getSource()){
			try{
				int slot = Integer.parseInt(stallSlotField.getText());
				if(stalls[slot]==null){
					throw new RecordNotFoundException("No stall found at slot "+slot);
				}
				showInfo(stalls[slot].getPaymentInfo());
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number must be between 0 and "+(stalls.length-1)+".");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}
		else if(allocateButton == e.getSource()){
			try{
				int slot = Integer.parseInt(stallSlotField.getText());
				int vendorSlot = Integer.parseInt(allocateSlotField.getText());
				if(stalls[slot]==null){
					throw new RecordNotFoundException("No stall found at slot "+slot);
				}
				if(vendors[vendorSlot]==null){
					throw new RecordNotFoundException("No vendor found at slot "+vendorSlot);
				}
				stalls[slot].allocateToVendor(vendors[vendorSlot]);
			}
			catch(NumberFormatException ex){
				showError("Both slot numbers must be valid.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("A slot number is out of range.");
			}
			catch(RecordNotFoundException | InvalidDataException ex){
				showError(ex.getMessage());
			}
		}
		else if(availableButton == e.getSource()){
			try{
				int slot = Integer.parseInt(stallSlotField.getText());
				if(stalls[slot]==null){
					throw new RecordNotFoundException("No stall found at slot "+slot);
				}
				stalls[slot].updateAvailability(true);
				updateStallScreen();
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number is out of range.");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}
		else if(occupiedButton == e.getSource()){
			try{
				int slot = Integer.parseInt(stallSlotField.getText());
				if(stalls[slot]==null){
					throw new RecordNotFoundException("No stall found at slot "+slot);
				}
				stalls[slot].updateAvailability(false);
				updateStallScreen();
			}
			catch(NumberFormatException ex){
				showError("Slot must be a valid number.");
			}
			catch(ArrayIndexOutOfBoundsException ex){
				showError("Slot number is out of range.");
			}
			catch(RecordNotFoundException ex){
				showError(ex.getMessage());
			}
		}

		//------------- Event tab -------------
		else if(openEventButton == e.getSource()){
			openEvent();
		}
		else if(closeEventButton == e.getSource()){
			closeEvent();
		}
		else if(refreshCountButton == e.getSource()){
			updateCountLabel();
		}
		else if(totalRentButton == e.getSource()){
			totalRentLabel.setText("Total Rent Due: "+calculateTotalRent());
		}
		else if(saveButton == e.getSource()){
			saveToFile();
		}
		else if(loadButton == e.getSource()){
			loadFromFile();
		}
	}

}
