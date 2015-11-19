package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		
		if (tuplesResult.isEmpty()) {
			
			Tuple record = child.next();	//create tuple with return from child.next()
			ArrayList<Tuple> array1 = new ArrayList<Tuple>();
			int predicateNumber;
			
			//if record is not empty add record to array them record assign to child.next()
			while (record != null) {
				array1.add(record);
				record = child.next();
			}
			
			//if array1 is empty return nothing
			if(array1.isEmpty()){
				return null;
			}
			
			
			//initial first tuple of array1 to record
			record = array1.get(0);
			
			//for loop size of tuple's attributList times
			for(predicateNumber = 0;predicateNumber <record.getAttributeList().size(); predicateNumber++){
				//if record's attribute name equal to orderPredicate, break the for loop
				if(record.getAttributeName(predicateNumber).equals(orderPredicate))
					break;
			}
			
			//while array1 is not empty
			while(!array1.isEmpty()){
				int minimum = 0;	//set minimum counter
				//for loop array1's size times
				for(int j = 0; j < array1.size();j++){
					//if string of jth tuple's predicateNumber-th attribute's value is less then that of minimum-th tuple form array1
					if(array1.get(j).getAttributeValue(predicateNumber).toString().compareTo(array1.get(minimum).getAttributeValue(predicateNumber).toString())<0)
						minimum = j;	//set minimum as j
				}
				//add minimum-th tuple from array1 to tuplesResult the remove from array1
				tuplesResult.add(array1.get(minimum));
				array1.remove(minimum);
			}
		}
		
		//return the first tuple from tuplesResult and remove it
		return tuplesResult.remove(0);
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}