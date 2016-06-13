
public class Utilityklasse<T> implements GFI<T> {
ObjectSizeFetcher sizer = new ObjectSizeFetcher();
	
	@Override
	public boolean compare(Object _1, Object _2) {

//if(ObjectSizeFetcher.getObjectSize(_1)<ObjectSizeFetcher.getObjectSize(_2)){
		comparee testy = (comparee) _1;
		comparee testy2 = (comparee) _2;
		if((testy.value())>(testy2.value())){
	return true;
}else{
	return false;	
}
		

	}
	
	
	public Object betterElement(Object _1, Object _2){
		if(compare(_1,_2)){
			return _1;
		}else{
			return _2;
		}
	}



	
}
