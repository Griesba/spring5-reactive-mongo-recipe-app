package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll();
    }

    @Test
    public void testSave() {
        Recipe recipe = new Recipe();
        recipe.setId("uuid");
        Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

        assertEquals(1, recipeReactiveRepository.count().block().intValue());

        assertNotNull(savedRecipe);

        assertEquals("uuid", savedRecipe.getId());
    }

}