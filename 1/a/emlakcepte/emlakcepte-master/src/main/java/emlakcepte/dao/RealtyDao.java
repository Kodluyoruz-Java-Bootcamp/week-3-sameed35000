package emlakcepte.dao;

import java.util.ArrayList;
import java.util.List;

import emlakcepte.model.Realty;

public class RealtyDao {
	
	private static List<Realty> realtyList = new ArrayList<>();
	
	public void saveRealty(Realty realty) {
		realty.getUser().getRealtyList().add(realty);
		realtyList.add(realty);
	}
	
	public List<Realty> findAll(){
		return realtyList;
	}

}
