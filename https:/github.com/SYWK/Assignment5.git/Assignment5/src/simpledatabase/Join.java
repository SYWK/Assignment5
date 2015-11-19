package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		
		Tuple tupleLeft = leftChild.next();	//create tuple with leftChild.next()
		Tuple tupleRight = rightChild.next();	//create tuple with rightChild.next()
		
		//while tupleLeft is no empty, add it to tuples1 then tupleLeft assign as leftChild.next()
        while(tupleLeft != null) {
            tuples1.add(tupleLeft);
            tupleLeft = leftChild.next();
        }

        //if tupleRight is not empty
        if(tupleRight != null) {
        	//for loop size of tuples times
            for(int i = 0; i < tuples1.size(); i++) {
            	//if value of tupleRight's 2nd attribute from attributeList equal to value of 1st attribute of ith tuple from tuples1
                if(tupleRight.getAttributeList().get(2).attributeValue.equals(tuples1.get(i).getAttributeList().get(0).attributeValue)) {
                    tupleRight.getAttributeList().addAll(tuples1.get(i).getAttributeList());	//add all attributes from attributeList of ith tuple from tuples1 to tupleRight's attributeList
                }
            }
            return tupleRight;	//return the joint tupleRight
        }
        return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}