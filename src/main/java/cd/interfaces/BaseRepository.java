package cd.interfaces;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by coskun.deniz on 08.06.2015.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
  void delete(T deleted);

  List<T> findAll();

  Optional<T> findOne(ID id);

  T save(T persisted);


  //  extends Repository<Person,Long>

//  String findTitleById(Long id);
//
//  Optional<String> findTitleById(Long id);
//
//  Person findById(Long id);
//
//  Optional<Person> findById(Long id);
//
//
//  List<Person> findByTitle(String title);
//
//  Stream<Person> findByTitle(String title);



//  @Async
//  Future<String> findTitleById(Long id);
//
//  @Async
//  Future<Optional<String>> findTitleById(Long id);
//
//  @Async
//  Future<Person> findById(Long id);
//
//  @Async
//  Future<Optional<Person>> findById(Long id);
//
//  @Async
//  Future<List<Person>> findByTitle(String title);
//
//  @Async
//  Future<Stream<Person>> findByTitle(String title);


//query li örnek
//public Optional<Todo> findByTitleAndDescription(String title, String description);
//
//  @Query("SELECT t FROM Todo t where t.title = ?1 AND t.description = ?2")
//  public Optional<Todo> findByTitleAndDescription(String title, String description);
//
//  @Query(value = "SELECT * FROM todos t where t.title = ?0 AND t.description = ?1",
//      nativeQuery=true
//  )
//  public Optional<Todo> findByTitleAndDescription(String title, String description);

  //named param örnek
//  @Query("SELECT t FROM Todo t where t.title = :title AND t.description = :description")
//  public Optional<Todo> findByTitleAndDescription(@Param("title") String title,
//                                                  @Param("description") String description);
//
//  @Query(
//      value = "SELECT * FROM todos t where t.title = :title AND t.description = :description",
//      nativeQuery=true
//  )
//  public Optional<Todo> findByTitleAndDescription(@Param("title") String title,
//                                                  @Param("description") String description);

}
