package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private String attributeLine;
	private String dataTypeLine;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		String tupleLine = null;	//create a string with nothing
		
		try{
			//get the attribute name and type of data
			if (getAttribute == false){
				attributeLine = br.readLine();	//read first line of the file and put into attributeLine
				dataTypeLine = br.readLine();	//read next line of the file and put into dataTypeLine
				getAttribute = true;	//set getAttribute to true
			}
			
			tupleLine = br.readLine();	//get the value, read next line of the file and put into tupleLine
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
		 
		//if there are no value in the file, return nothing
		if(tupleLine == null){
			return null;
		}
		
		
		//create a new tuple and set the name, type, value, which get from the input file
		tuple = new Tuple(attributeLine, dataTypeLine, tupleLine);
		tuple.setAttributeName();
		tuple.setAttributeType();
		tuple.setAttributeValue();
		return tuple;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}