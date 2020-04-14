package suites;


import categoriesMarker.GetTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import petstore.test.PetStoreTest;
import petstore.test.PetStoreWithParametrs;

@RunWith(Categories.class)
@Categories.IncludeCategory(GetTests.class)
@Suite.SuiteClasses({PetStoreWithParametrs.class})
public class GetTestSuite {
}
