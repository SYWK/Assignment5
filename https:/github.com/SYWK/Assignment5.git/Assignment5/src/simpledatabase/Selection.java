package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.from = child.from;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		Tuple record = child.next();	//create record with child.next()
		
		//while record is not empty
		while(record != null)
		{
			//if record's table is equal to whereTablePredicate
			if(from.equals(whereTablePredicate))
			{
				int recordSize = record.getAttributeList().size();	//create integer with size of attributeList of record
				//for loop recordSize times
				for(int i=0; i<recordSize; i++)
				{
					//if record's ith attributeName equal to whereAttributePredicate
					if(record.getAttributeName(i).equals(whereAttributePredicate))
					{
						//if record's ith attributeValue equal to whereValuePredicate
						if(record.getAttributeValue(i).equals(whereValuePredicate)){
							return record;	//return record
						}
					}
				}
			}
			else
			{
				return record;
			}
			record = child.next();
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}