package common.dal.dao;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import test.MockSimpleEntity;
import test.TestStandUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class BaseCrudDaoTest {
    @Test
    public void createInstance_shouldInitializeClass() throws NoSuchFieldException, IllegalAccessException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        // act
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        Field actualEntityField = dao.getClass().getSuperclass().getDeclaredField("entityClass");
        actualEntityField.setAccessible(true);
        Object actualEntityValue = actualEntityField.get(dao);
        Field actualDaoHelperField = dao.getClass().getSuperclass().getDeclaredField("daoHelper");
        actualDaoHelperField.setAccessible(true);
        Object actualDaoHelperValue = actualDaoHelperField.get(dao);
        // assert
        Assertions.assertNotNull(actualEntityField);
        Assertions.assertNotNull(actualEntityValue);
        Assertions.assertNotNull(actualDaoHelperField);
        Assertions.assertNotNull(actualDaoHelperValue);
        Assertions.assertSame(MockSimpleEntity.class, actualEntityValue);
    }

    @Test
    public void findAsync_entityExists_shouldReturnInstance() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        MockSimpleEntity expected = TestStandUtils.buildSimpleInstance();
        Mockito.doReturn(expected)
                .when(em)
                .find(ArgumentMatchers.eq(MockSimpleEntity.class), ArgumentMatchers.any());
        // act
        Future<MockSimpleEntity> actual = dao.findAsync(1L);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertSame(expected, actual.get());
    }

    @Test
    public void findAsync_nullParam_shouldReturnNull() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        Mockito.doReturn(null)
                .when(em)
                .find(ArgumentMatchers.eq(MockSimpleEntity.class), ArgumentMatchers.any());
        // act
        Future<MockSimpleEntity> actual = dao.findAsync(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNull(actual.get());
    }

    @Test
    public void findByKeyAsync_entityExists_shouldReturnInstance() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        MockSimpleEntity expected = TestStandUtils.buildSimpleInstance();
        Mockito.doReturn(expected)
                .when(em)
                .find(ArgumentMatchers.eq(MockSimpleEntity.class), ArgumentMatchers.any());
        // act
        Future<MockSimpleEntity> actual = dao.findByKeyAsync(1L);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertSame(expected, actual.get());
    }

    @Test
    public void findByKeyAsync_nullParam_shouldReturnNull() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        Mockito.doReturn(null)
                .when(em)
                .find(ArgumentMatchers.eq(MockSimpleEntity.class), ArgumentMatchers.any());
        // act
        Future<MockSimpleEntity> actual = dao.findByKeyAsync(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNull(actual.get());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void listAllAsync_entitiesExists_shouldReturnList() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        TypedQuery<MockSimpleEntity> tq = Mockito.mock(TypedQuery.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        List<MockSimpleEntity> expected = TestStandUtils.buildInstanceList();
        Mockito.doReturn(tq)
                .when(em)
                .createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(MockSimpleEntity.class));
        Mockito.doReturn(expected)
                .when(tq)
                .getResultList();
        // act
        Future<List<MockSimpleEntity>> actual = dao.listAllAsync();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual.get(), Matchers.containsInAnyOrder(expected.toArray()));
    }
}