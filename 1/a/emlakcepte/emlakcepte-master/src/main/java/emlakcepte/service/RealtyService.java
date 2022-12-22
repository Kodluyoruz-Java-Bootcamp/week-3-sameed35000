package emlakcepte.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import emlakcepte.dao.RealtyDao;
import emlakcepte.model.EstateType;
import emlakcepte.model.Realty;
import emlakcepte.model.RealtyType;
import emlakcepte.model.Status;
import emlakcepte.model.User;
import emlakcepte.model.UserType;

@Service
public class RealtyService {

	private static RealtyDao realtyDao = new RealtyDao();

	@Autowired // injection
	private UserService userService;

	@PostMapping("/realty")
	public static ResponseEntity<Realty> createRealty(@RequestBody Realty realty) {
		// Bireysel kullanıcılar sadece Konut tipinde ilan yayınlayabilir
		if (!EstateType.HOUSE.equals(realty.getEstateType())) {
			throw new IllegalArgumentException("Bireysel kullanıcılar sadece Konut tipinde ilan yayınlayabilir.");
		}
		// Bireysel kullanıcılar en fazla 3 ilan yayınlayabilir
		List<Realty> userRealties = getAllByUserName(realty.getUser());
		if (userRealties.size() >= 3) {
			throw new IllegalArgumentException("Bireysel kullanıcılar en fazla 3 ilan yayınlayabilir.");
		}

		// Yeni ilanı veritabanına kaydet
		realtyDao.saveRealty(realty);
		return ResponseEntity.ok(realty);
	}

	public static List<Realty> getAll() {
		return realtyDao.findAll();
	}

	public void printAll(List<Realty> realtList) {
		realtList.forEach(realty -> System.out.println(realty));
	}

	public void getAllByProvince(String province) {

		getAll().stream().filter(realty -> realty.getProvince().equals(province))
				// .count();
				.forEach(realty -> System.out.println(realty));

	}

	public static List<Realty> getAllByUserName(User user) {
		return getAll().stream().filter(realty -> realty.getUser().getMail().equals(user.getMail())).toList();
	}

	public List<Realty> getActiveRealtyByUserName(User user) {

		return getAll().stream().filter(realty -> realty.getUser().getName().equals(user.getName()))
				.filter(realty -> RealtyType.ACTIVE.equals(realty.getRealtyStatus())).toList();

	}

	// Şehir ve ilçe bazlı ilan arama
	@GetMapping("/realty/search")
	public ResponseEntity<List<Realty>> searchRealties(@RequestParam("province") String province,
			@RequestParam("district") String district) {
		// İlanları arama işlemi
		List<Realty> realties = getAll().stream()
				// İlanları filtreleme işlemi (il ve ilçe bilgilerine göre)
				.filter(realty -> realty.getProvince().equalsIgnoreCase(province)
						&& realty.getDistrict().equalsIgnoreCase(district))
				// İlanları bir listeye toplama işlemi
				.collect(Collectors.toList());
		return ResponseEntity.ok(realties);
	}

	Map<String, List<Realty>> cityRealtyMap = new HashMap<>();

	// İstanbul, Ankara, İzmir şehirlerindeki ilanların sayısı
	int istanbulRealtyCount = cityRealtyMap.get("Istanbul").size();
	int ankaraRealtyCount = cityRealtyMap.get("Ankara").size();
	int izmirRealtyCount = cityRealtyMap.get("Izmir").size();

	public void showcaseProvince(String province) {
		getAll().stream().filter(realty -> realty.getProvince().equals(province)).limit(10)
				.forEach(System.out::println);

	}

	@GetMapping("/realty/province/{province}")
	public ResponseEntity<List<Realty>> getRealtiesByProvince(@PathVariable("province") String province) {
		// Şehirlere göre ilanları gruplamak için bir harita oluştur
		Map<String, List<Realty>> provinceRealtyMap = new HashMap<>();

		// Tüm ilanları getir
		List<Realty> allRealties = RealtyService.getAll();

		// İlanları gez
		for (Realty realty : allRealties) {
			// İlanın bulunduğu şehir adını al
			String currProvince = realty.getProvince();

			// Eğer şehir haritada yoksa, haritaya yeni bir anahtar ekle
			if (!provinceRealtyMap.containsKey(currProvince)) {
				provinceRealtyMap.put(currProvince, new ArrayList<>());
			}

			// İlanı şehirle ilişkili listeye ekle
			provinceRealtyMap.get(currProvince).add(realty);
		}

		// İstenen şehir için ilanların listesini döndür
		return ResponseEntity.ok(provinceRealtyMap.get(province));
	}

	@GetMapping("/realty/province/{province}")
	public ResponseEntity<Integer> getNumberOfRealtiesInProvince(@PathVariable("province") String province) {
		// Tüm gayrimenkul ilanlarını alma işlemi
		List<Realty> realties = getAll().stream()
				// Verilen ile ait olan ilanları içeren yeni bir akış oluşturma işlemi
				.filter(realty -> realty.getProvince().equals(province))
				// İlanları bir listeye toplama işlemi
				.collect(Collectors.toList());
		int count = realties.size();

		return ResponseEntity.ok(count);
	}

	@GetMapping("/realty/province/{province}/for-sale/house")
	public ResponseEntity<Integer> getNumberOfHousesForSaleInProvince(@PathVariable("province") String province) {
		// Verilen il için printNumberOfHousesForSaleInProvince() yöntemini kullanarak
		// satılık evlerin sayısını bul
		List<Realty> realties = getAll().stream().filter(realty -> realty.getProvince().equals(province))
				.filter(realty -> realty.getRentalStatus().equals(Status.FORSALE))
				.filter(realty -> realty.getEstateType().equals(EstateType.HOUSE)).collect(Collectors.toList());
		int count = realties.size();

		// Verilen il için satılık evlerin sayısını yanıt olarak döndür
		return ResponseEntity.ok(count);
	}

}
