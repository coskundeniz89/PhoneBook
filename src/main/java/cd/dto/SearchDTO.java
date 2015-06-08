package cd.dto;

import cd.enm.SearchType;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by coskun.deniz on 08.06.2015.
 */
public class SearchDTO {

  public String getSearchTerm() {
    return searchTerm;
  }

  public void setSearchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
  }

  public SearchType getSearchType() {
    return searchType;
  }

  public void setSearchType(SearchType searchType) {
    this.searchType = searchType;
  }

  private String searchTerm;

  private SearchType searchType;

  public SearchDTO(){

  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
