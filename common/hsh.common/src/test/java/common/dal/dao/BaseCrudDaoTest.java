package common.dal.dao;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import test.MockSimpleEntity;
import test.TestStandUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Collections;
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

    @Test
    public void listFilteredAsync_nullParam_shouldReturnEmptyList() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        Future<List<MockSimpleEntity>> actual = dao.listFilteredAsync(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        MatcherAssert.assertThat(actual.get(), Matchers.empty());
    }

    @SuppressWarnings("unchecked")
    @ParameterizedTest
    @EnumSource(SortOrderEnum.class)
    public void listFilteredAsync_withParams_shouldReturnList(SortOrderEnum sortOrder) throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        FilterParams filterParams = buildFilterParams(sortOrder);
        List<MockSimpleEntity> result = TestStandUtils.buildInstanceList();
        CriteriaBuilder cb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<MockSimpleEntity> cq = Mockito.mock(CriteriaQuery.class);
        Root<MockSimpleEntity> root = Mockito.mock(Root.class);
        TypedQuery<MockSimpleEntity> tq = Mockito.mock(TypedQuery.class);
        Mockito.doReturn(cb)
                .when(em)
                .getCriteriaBuilder();
        Mockito.doReturn(cq)
                .when(cb)
                .createQuery(ArgumentMatchers.eq(MockSimpleEntity.class));
        Mockito.doReturn(root)
                .when(cq)
                .from(ArgumentMatchers.eq(MockSimpleEntity.class));
        Mockito.doReturn(tq)
                .when(em)
                .createQuery(ArgumentMatchers.any(CriteriaQuery.class));
        Mockito.doReturn(tq)
                .when(tq)
                .setFirstResult(ArgumentMatchers.anyInt());
        Mockito.doReturn(tq)
                .when(tq)
                .setMaxResults(ArgumentMatchers.anyInt());
        Mockito.doReturn(tq)
                .when(tq)
                .setLockMode(ArgumentMatchers.any(LockModeType.class));
        Mockito.doReturn(result)
                .when(tq)
                .getResultList();

        // act
        Future<List<MockSimpleEntity>> actual = dao.listFilteredAsync(filterParams);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        MatcherAssert.assertThat(actual.get(), Matchers.not(Matchers.empty()));
    }

    @SuppressWarnings("unchecked")
    @ParameterizedTest
    @ValueSource(longs = {2L})
    @NullSource
    public void existsAsync_nullOrNotExists_shouldReturnFalse(Long id) throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        TypedQuery<Long> tq = Mockito.mock(TypedQuery.class);
        Mockito.doReturn(tq).when(em).createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(Long.class));
        Mockito.doReturn(tq).when(tq).setParameter(ArgumentMatchers.anyString(), ArgumentMatchers.any(Long.class));
        Mockito.doReturn(tq).when(tq).setLockMode(ArgumentMatchers.any(LockModeType.class));
        Mockito.doReturn(tq).when(tq).setMaxResults(ArgumentMatchers.anyInt());
        Mockito.doThrow(NoResultException.class).when(tq).getSingleResult();
        // act
        Future<Boolean> actual = dao.existsAsync(id);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        Assertions.assertFalse(actual.get());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void existsAsync_throwException_shouldReturnFalse() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        TypedQuery<Long> tq = Mockito.mock(TypedQuery.class);
        Mockito.doReturn(tq).when(em).createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.eq(Long.class));
        Mockito.doReturn(tq).when(tq).setParameter(ArgumentMatchers.anyString(), ArgumentMatchers.any(Long.class));
        Mockito.doReturn(tq).when(tq).setLockMode(ArgumentMatchers.any(LockModeType.class));
        Mockito.doReturn(tq).when(tq).setMaxResults(ArgumentMatchers.anyInt());
        Mockito.doThrow(RuntimeException.class).when(tq).getSingleResult();
        // act
        Future<Boolean> actual = dao.existsAsync(1L);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        Assertions.assertFalse(actual.get());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void countAsync_useDefaults_shouldReturnRowCount() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        CriteriaBuilder cb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery<Long> cq = Mockito.mock(CriteriaQuery.class);
        TypedQuery<Long> tq = Mockito.mock(TypedQuery.class);
        Root<MockSimpleEntity> root = Mockito.mock(Root.class);
        Mockito.doReturn(cb).when(em).getCriteriaBuilder();
        Mockito.doReturn(cq).when(cb).createQuery(ArgumentMatchers.eq(Long.class));
        Mockito.doReturn(root).when(cq).from(ArgumentMatchers.eq(MockSimpleEntity.class));
        Mockito.doReturn(tq).when(em).createQuery(cq);
        Mockito.doReturn(tq).when(tq).setLockMode(ArgumentMatchers.any(LockModeType.class));
        Mockito.doReturn(2L).when(tq).getSingleResult();
        // act
        Future<Long> actual = dao.countAsync();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        Assertions.assertEquals(2L, actual.get());
    }

    @Test
    public void countAsync_nullParams_shouldReturn() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        Future<Long> actual = dao.countAsync(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        Assertions.assertEquals(0L, actual.get());
    }

    @Test
    public void createAsync_nullParam_shouldReturnNull() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        Future<MockSimpleEntity> actual = dao.createAsync(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNull(actual.get());
    }

    @Test
    public void createAsync_useInstance_shouldReturnInstance() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        Mockito.doNothing().when(em).persist(ArgumentMatchers.any(MockSimpleEntity.class));
        // act
        Future<MockSimpleEntity> actual = dao.createAsync(data);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
        Assertions.assertEquals(data, actual.get());
    }

    @Test
    public void editAsync_useNull_shouldReturnNull() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        Future<MockSimpleEntity> actual = dao.editAsync(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNull(actual.get());
    }

    @Test
    public void editAsync_useInstance_shouldReturnInstance() throws ExecutionException, InterruptedException {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        Mockito.doReturn(data).when(em).merge(ArgumentMatchers.any(MockSimpleEntity.class));
        // act
        Future<MockSimpleEntity> actual = dao.editAsync(data);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
    }

    @Test
    public void removeAsync_nullParam_shouldDoNothing() {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        dao.removeAsync(null);
        // assert
        Assertions.assertTrue(true);
    }

    @Test
    public void removeAsync_useInstance_shouldRemoveInstance() {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        Query q = Mockito.mock(Query.class);
        Mockito.doReturn(q).when(em).createQuery(ArgumentMatchers.anyString());
        Mockito.doReturn(q).when(q).setParameter(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        Mockito.doReturn(1).when(q).executeUpdate();
        // act
        dao.removeAsync(data);
        // assert
        Assertions.assertTrue(true);
    }

    @Test
    public void getEntityClass_shouldReturnEntityClass() {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        Class<?> actual = dao.getEntityClass();
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void getDaoHelper_shouldReturnDaoHelper() {
        // prepare
        EntityManager em = Mockito.mock(EntityManager.class);
        MockBaseCrudDao dao = new MockBaseCrudDao(em);
        // act
        DaoHelper<MockSimpleEntity> actual = dao.getDaoHelper();
        // assert
        Assertions.assertNotNull(actual);
    }

    private FilterParams buildFilterParams(SortOrderEnum sortOrder) {
        return new BaseFilterParams(1, 10, DaoUtils.createFilterSet(Collections.emptyMap()), sortOrder, "test");
    }
}