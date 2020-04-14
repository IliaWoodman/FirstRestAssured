package suites;


import categoriesMarker.GetTests;
import categoriesMarker.PostTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import petstore.test.PetStoreWithParametrs;

@RunWith(Categories.class)
@Categories.IncludeCategory(PostTests.class)
@Suite.SuiteClasses({PetStoreWithParametrs.class})
public class PostTestSuite {
}
