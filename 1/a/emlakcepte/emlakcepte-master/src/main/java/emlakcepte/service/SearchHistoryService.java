package emlakcepte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import emlakcepte.model.Search;

@Service
public class SearchHistoryService {
  private static List<Search> searchHistoryList = new ArrayList<>();

  public static void addSearchHistory(String province, String district) {
    Search searchHistory = new Search();
    searchHistory.setProvince(province);
    searchHistory.setDistrict(district);
    searchHistoryList.add(searchHistory);
  }

  public static List<Search> getSearchHistory1() {
    return searchHistoryList;
  }
  
  @PostMapping("/search-history")
	public ResponseEntity<Void> addSearchHistory(@RequestBody Search search) {
	  // Arama geçmişine ekleme işlemi
	  SearchHistoryService.addSearchHistory(search.getProvince(), search.getDistrict());
	  return ResponseEntity.ok().build();
	}

	@GetMapping("/search-history")
	public ResponseEntity<List<Search>> getSearchHistory() {
	  // Arama geçmişini döndürme işlemi
	  return ResponseEntity.ok(SearchHistoryService.getSearchHistory1());
	}

}

