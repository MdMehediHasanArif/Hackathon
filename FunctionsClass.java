package hackathon0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class FunctionsClass{
	
	private String name;
	private String Faname;
	private String Maname;
	private String DOB;
	private String adr;
	private String phn;
	private String regNo;
	private String accNum;
	private double cg;
	private String semester;
	private String year;
	double balance;
	
	private ArrayList<PersonalInfoClass> listPerson= new ArrayList<>();
	private ArrayList<AcademicInfoClass> listAcademy= new ArrayList<>();
	private ArrayList<BankInfoClass> listBank= new ArrayList<>();
	
	Scanner cin= new Scanner(System.in);
	
	public void initialize() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		File newFile= new File("personal.txt");
		
		if(newFile.length()==0) return; 
		
		ObjectInputStream PersObj= new ObjectInputStream(new FileInputStream("personal.txt"));
		ObjectInputStream BankObj= new ObjectInputStream(new FileInputStream("bankInfo.txt"));
		ObjectInputStream AcaObj= new ObjectInputStream(new FileInputStream("acaInfo.txt"));
		
		listPerson= (ArrayList<PersonalInfoClass>) PersObj.readObject();
		listBank=(ArrayList<BankInfoClass>) BankObj.readObject();
		listAcademy=(ArrayList<AcademicInfoClass>) AcaObj.readObject();
		
	}
	
	public void createAccount() 
	{
		
		boolean df=false;
		cin.nextLine();
		
		System.out.println("Enter name: ");
		name=cin.nextLine();
		
		System.out.println("Enter Date of Birth: ");
		DOB=cin.nextLine();
		
		System.out.println("Enter Address: ");
		adr=cin.nextLine();
		
		System.out.println("Enter Father's name: ");
		Faname=cin.nextLine();
		
		System.out.println("Enter Mother's name: ");
		Maname=cin.nextLine();
		
		System.out.println("Enter current year: ");
		year=cin.nextLine();
		
		System.out.println("Enter current semester: ");
		semester=cin.nextLine();
		
		System.out.println("Enter Account Number: ");
		accNum=cin.nextLine();
		
		System.out.println("Enter registration number: ");
		regNo=cin.nextLine();
		
		System.out.println("Enter CGPA: ");
		cg=cin.nextDouble();
		
		System.out.println("Enter Account Balance: ");
		balance=cin.nextDouble();
		
		PersonalInfoClass persona= new PersonalInfoClass(name,Faname,Maname,adr,DOB,regNo);
		AcademicInfoClass aca= new AcademicInfoClass(regNo,semester,year,cg);
		BankInfoClass ban=new BankInfoClass(accNum,persona,balance);
		
		
			listPerson.add(persona);
			listAcademy.add(aca);
			listBank.add(ban);
			writer(listPerson,listAcademy,listBank);
			
	}
	
	
	public void writer(ArrayList<PersonalInfoClass> listPerson,ArrayList<AcademicInfoClass> listAcademy,ArrayList<BankInfoClass> listBank) {
		
		FileOutputStream fout = null;
	    ObjectOutputStream object_out_for_serializable = null;
	        
	    try {
	            fout = new FileOutputStream("persnal.txt");
	            object_out_for_serializable = new ObjectOutputStream(fout);
	            
	            object_out_for_serializable.writeObject(listPerson);
	            object_out_for_serializable.flush();
	            
	            fout = null;
	    	    object_out_for_serializable = null;
	    	    
	    	    fout = new FileOutputStream("acaInfo.txt");
	            object_out_for_serializable = new ObjectOutputStream(fout);
	            
	            object_out_for_serializable.writeObject(listBank);
	            object_out_for_serializable.flush();
	            
	            fout = null;
	    	    object_out_for_serializable = null;
	    	    
	    	    fout = new FileOutputStream("bankInfo.txt");
	            object_out_for_serializable = new ObjectOutputStream(fout);
	            
	            object_out_for_serializable.writeObject(listBank);
	            object_out_for_serializable.flush();
	                    
	            System.out.println("Entry added Successfully\n");
	    	} 
	    
	    catch (FileNotFoundException ex) 
	    {
	            System.out.println("FileOutputStream in " + ex.toString());
	    } 
	    catch (IOException ex) 
	    {
	            System.out.println("ObjectOutputStream in " + ex.toString());
	    } 
	}
	
	
	
	
	public int search (String s)
	{	
		for(int i=0;i<listBank.size();i++)
		{	
			String s4 = listBank.get(i).getAccNum();
			if(s4.equals(s))
			{	
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<PersonalInfoClass> getAccounts() {
		return listPerson;
	}
	
	
	public void beforeLogIn()
	{
		Scanner cin = new Scanner (System.in);
		
		System.out.print("\nEnter account number: ");
				
		String s3=cin.nextLine();
			
		int i = search(s3);
		
		//if(i!=-1) listPerson.get(i).afterLogIn();
		//else System.out.println("\nACCOUNT NOT FOUND!\n");
	}
	
	public void afterLogIn()
	{
		
		
	}
	
	
}
