package com.snaildev;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.snaildev.dao.IUserDao;
import com.snaildev.model.UserModel;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.sql.SQLException;

public class IBatisTest {
    private static SqlMapClient sqlMapClient;

    @BeforeClass
    public static void setUpClass() {
        String[] configLocations = new String[]{
                "classpath:applicationContext-resources.xml",
                "classpath:applicationContext-ibatis.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
        sqlMapClient = ctx.getBean(SqlMapClient.class);
    }

    @Before
    public void setUp() throws SQLException {
        sqlMapClient.update("UserSQL.createTable");
    }

    @After
    public void tearDown() throws SQLException {
        sqlMapClient.update("UserSQL.dropTable");
    }

    @Test
    public void testFirst() throws SQLException {
        UserModel model = new UserModel();
        model.setName("t4st");
        SqlMapSession session = null;
        try {
            session = sqlMapClient.openSession();
            beginTransaction(session);
            session.insert("UserSQL.insert", model);
            commitTransaction(session);
        } catch (SQLException e) {
            rollbackTransacrion(session);
            throw e;
        } finally {
            closeSession(session);
        }
    }

    private void closeSession(SqlMapSession session) {
        session.close();
    }

    private void rollbackTransacrion(SqlMapSession session) throws SQLException {
        if (session != null) {
            session.endTransaction();
        }
    }

    private void commitTransaction(SqlMapSession session) throws SQLException {
        session.commitTransaction();
    }

    private void beginTransaction(SqlMapSession session) throws SQLException {
        session.startTransaction();
    }

    @Test
    public void testSqlMapClientTemplate() {
        SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
        final UserModel model = new UserModel();
        model.setName("xiaoming");
        sqlMapClientTemplate.insert("UserSQL.insert", model);

        sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor sqlMapExecutor) throws SQLException {
                sqlMapExecutor.insert("UserSQL.insert", model);
                return null;
            }
        });
    }

    @Test
    public void testBestPractice() {
        String[] configLocations = new String[]{
                "classpath:applicationContext-resources.xml",
                "classpath:applicationContext-ibatis.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
        IUserDao userDao = ctx.getBean(IUserDao.class);
        UserModel model = new UserModel();
        model.setName("test");
        userDao.save(model);
        Assert.assertEquals(1, userDao.countAll());
    }

    @Test
    public void testMybatisBestPractice(){
        String[] configLocations = new String[]{
                "classpath:applicationContext-resources.xml",
                "classpath:applicationContext-mybatis.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
        IUserDao userDao = ctx.getBean(IUserDao.class);
        UserModel model = new UserModel();
        model.setName("test");
        userDao.save(model);
        Assert.assertEquals(1, userDao.countAll());
    }
}
