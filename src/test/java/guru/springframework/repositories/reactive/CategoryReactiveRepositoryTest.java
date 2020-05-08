package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription("Italy");
        categoryReactiveRepository.save(category).block();

        assertEquals(1, categoryReactiveRepository.count().block().intValue());
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription("Italy");
        categoryReactiveRepository.save(category).block();

        Category savedCat = categoryReactiveRepository.findByDescription("Italy").block();

        assertNotNull(savedCat);
        assertTrue("Italy".equals(savedCat.getDescription()));

    }

}