package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple = child.next();		//create a tuple with return of child.next()
		//return nothing if can't get tuple from child
		if (tuple == null){
			return null;
		}
		
		int tupleSize = tuple.getAttributeList().size();	//create integer with size of attributeList of tuple

		//if tuple is no empty
		if (tuple != null){
			//for loop tupleSize times
			for(int i=0; i < tupleSize;i++){
				//if attribute name of ith attribute of tuple's attributeList equal to attributePredicate
				if(tuple.getAttributeList().get(i).getAttributeName().equals(attributePredicate)){
	    			newAttributeList.clear();	//empty newAttributeList
	    			Attribute attribute = tuple.getAttributeList().get(i);	//create attribute with getting ith attribute from tuple's attributeList
	    			newAttributeList.add(attribute);	//add attribute into newAttributeList
	    			tuple = new Tuple(newAttributeList);	//define tuple with newAttributeList
	    			return tuple;
					
					
				}
			}
				
		}
		return null;
		
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}