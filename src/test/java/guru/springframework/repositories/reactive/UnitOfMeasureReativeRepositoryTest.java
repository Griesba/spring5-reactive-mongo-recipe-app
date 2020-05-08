package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReativeRepositoryTest {

    @Autowired
    UnitOfMeasureReativeRepository unitOfMeasureReativeRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReativeRepository.deleteAll();
    }

    @Test
    public void testSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("CM");

        UnitOfMeasure savedUom = unitOfMeasureReativeRepository.save(unitOfMeasure).block();

        assertNotNull(savedUom);
        assertEquals(1, unitOfMeasureReativeRepository.count().block().intValue());
        assertEquals("CM", savedUom.getDescription());
    }
}