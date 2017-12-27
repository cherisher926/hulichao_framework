package com.whu.test;

import com.whu.dao.EmployeeDao;
import com.whu.entity.Employee;
import com.whu.test.spring.SpringTxTestCase;
import com.whu.ybatis.pojo.SimpleDaoPage;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hulichao on 2017/12/21
 */
public class EmployeeDaoTest extends SpringTxTestCase {
    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    int maxNum;

    @Before
    public void testGetCount() {
        maxNum = employeeDao.getCount();
        logger.info("当前数据条数 --------------------------(" + maxNum + ")");
    }

    @Test
    public void testInsert() {
        logger.info("--------testInsert--------------------------------------------------------------");

        Employee employee = new Employee();
        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        employee.setId(id);
        employee.setEmpno("200");
        employee.setName("xxs");
        employee.setBirthday(new Date());
        employee.setAge(20);
        employee.setSalary(new BigDecimal(88888));
        employeeDao.insert(employee);
    }

    @Test
    public void testUpdate() {
        logger.info("--------testUpdate--------------------------------------------------------------");

        Employee employee = new Employee();
        employee.setId("F22B3B5F371E49918B061442010F1C9A");
        employee.setName("hulichao33s");
        employee.setBirthday(new Date());
        int num = employeeDao.update(employee);
        logger.info("------update---count---" + num);
    }

    @Test
    public void testGetMap() {
        logger.info("--------testGetMap--------------------------------------------------------------");

        // 如果没有数据获取报错
        Map<String, Object> mp = employeeDao.getMap("001", "张开忠");
        if (mp != null) {
            logger.info(mp.get("id"));
            logger.info(mp.get("name"));
            logger.info(mp.get("empno"));
            logger.info(mp.get("age"));
            logger.info(mp.get("birthday"));
            logger.info(mp.get("salary"));
        }
    }

    @Test
    public void testGetEntity() {
        Employee employee = employeeDao.get("AD1024E0DAD84D2DB76A82E779F85B76");
        logger.info("testGetEntity --" + employee.getName());
    }

    @Test
    public void testListAll() {
        logger.info("--------testListAll--------------------------------------------------------------");
        Employee employee = new Employee();
        employee.setName("darren");
        employee.setAge(20);
        SimpleDaoPage<Employee> list = employeeDao.getAll(employee, 0, 10);

        for (Employee mp : list.getResults()) {
            logger.info(mp.getId());
            logger.info(mp.getName());
            logger.info(mp.getEmpno());
            logger.info(mp.getAge());
            logger.info(mp.getBirthday());
            logger.info(mp.getSalary());
        }

    }
}
