package com.haigeek.querydsl;

import com.haigeek.querydsl.config.AppTestConfig;
import com.haigeek.querydsl.model.dto.UserPermissionDTO;
import com.haigeek.querydsl.model.entity.*;
import com.haigeek.querydsl.repository.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.deploy.util.SystemUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.util.SystemUtil;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppTestConfig.class })
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class QueryDslJpaDemoApplicationTests {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	RolePermissionRepository rolePermissionRepository;
	@Autowired
	JPAQueryFactory queryFactory;

	@Test
	public void contextLoads() {
	}

	/**
	 * 数据准备
	 */
	@Test
	public void preSaveUser(){
		User user=new User();
		user.setUserName("jerry");
		user.setName("jerry");
		user.setPassword("123456");
		user.setAge(11);
		user.setRegisterDate(new Date());
		user.setState(0);
		user.setSalt("salt");
		userRepository.save(user);
		User user1=new User();
		user1.setUserName("jack");
		user1.setName("jack");
		user1.setPassword("123456");
		user1.setAge(12);
		user1.setRegisterDate(new Date());
		user1.setState(0);
		user1.setSalt("salt");
		userRepository.save(user1);
		User user2=new User();
		user2.setUserName("tom");
		user2.setName("tom");
		user2.setPassword("123456");
		user2.setAge(5);
		user2.setRegisterDate(new Date());
		user2.setState(0);
		user2.setSalt("salt");
		userRepository.save(user2);
		User user3=new User();
		user3.setUserName("jack");
		user3.setName("tony");
		user3.setPassword("123456");
		user3.setAge(13);
		user3.setRegisterDate(new Date());
		user3.setState(0);
		user3.setSalt("salt");
		userRepository.save(user3);
		User user4=new User();
		user4.setUserName("jam");
		user4.setName("jam");
		user4.setPassword("123456");
		user4.setAge(20);
		user4.setRegisterDate(new Date());
		user4.setState(0);
		user4.setSalt("salt");
		userRepository.save(user4);

	}

	@Test
	public void preSaveRole(){
		Role role=new Role();
		role.setRole("管理员");
		role.setDescription("管理员");
		roleRepository.save(role);

	}

	@Test
	public void preSavePermission(){
		Permission permission=new Permission();
		permission.setPermission("read");
		permission.setDescription("读");
		permissionRepository.save(permission);
	}

	@Test
	public void preSaveUserRole(){
		UserRole userRole=new UserRole();
		userRole.setUserId(2);
		userRole.setRoleId(4);
		userRoleRepository.save(userRole);
	}

	@Test
	public void preSaveRolePermission(){
		RolePermission rolePermission=new RolePermission();
		rolePermission.setRoleId(4);
		rolePermission.setPermissionId(5);
		rolePermissionRepository.save(rolePermission);
	}
	
	@Test
	public void queryFactoryUpdate(){
		logger.info("使用queryFactory执行update动作开始。");
		QUser qUser = QUser.user;
		long affectedNum = queryFactory.update(qUser).set(qUser.name, "tom1").where(qUser.name.eq("tom")).execute();
		logger.info("本次操作影响了"+affectedNum+"行。");
	}

	@Test
	public void queryFactoryDelete(){
		logger.info("使用queryFactory执行delete动作开始。");
		QUser qUser = QUser.user;
		long affectedNum = queryFactory.delete(qUser).where(qUser.name.eq("tom1")).execute();
		logger.info("本次操作影响了"+affectedNum+"行。");
	}

	@Test
	public void queryFactorySelect(){
		QUser qUser=QUser.user;
		QRole qRole=QRole.role1;
		QUserRole qUserRole=QUserRole.userRole;
		QRolePermission qRolePermission=QRolePermission.rolePermission;
		QPermission qPermission=QPermission.permission1;
		//查询字段-select()
		List<Integer> roleId=queryFactory.select(qUserRole.roleId).from(qUserRole).fetch();
		logger.info("单字段查询"+roleId);
		//查询实体-selectFrom()
		List<User> userList = queryFactory.selectFrom(qUser).fetch();
		logger.info("实体查询");
		userList.forEach(System.out::println);
		//多表查询(left join)
		List<Role> leftJoinRole = queryFactory.selectFrom(qRole)
				.leftJoin(qUserRole).on(qRole.id.eq(qUserRole.roleId))
				.where(qUserRole.userId.eq(2)).fetch();
		logger.info("多表查询");
		leftJoinRole.forEach(System.out::println);

		//查询并将结果封装至dto中
		List<UserPermissionDTO> dtoList = queryFactory.select(Projections.constructor(UserPermissionDTO.class,qUser.id,qUser.name,qPermission.id,qPermission.permission))
				.from(qPermission,qUser)
				.leftJoin(qRolePermission).on(qPermission.id.eq(qRolePermission.permissionId))
				.leftJoin(qUserRole).on(qUserRole.roleId.eq(qRolePermission.roleId))
				.leftJoin(qUser).on(qUser.id.eq(qUserRole.userId))
				.where(qUser.id.eq(2)).fetch();
		dtoList.forEach(System.out::println);
		//多表查询-selectDistinct()
		List<String> distinctNameList = queryFactory.selectDistinct(qUser.name).from(qUser).fetch();
		logger.info("多表查询");
		distinctNameList.forEach(System.out::println);
		//获取首个查询结果-fetchFirst()
		User firstUser = queryFactory.selectFrom(qUser).fetchFirst();
		logger.info("获取首个查询结果"+firstUser);
		//获取唯一查询结果-fetchOne()
		//当fetchOne()根据查询条件从数据库中查询到多条匹配数据时，会抛`NonUniqueResultException`。
		User anotherFirstUser = queryFactory.selectFrom(qUser).where(qUser.name.eq("tom")).fetchOne();
		logger.info("获取唯一查询结果"+anotherFirstUser);
		//查询条件示例
		List<User> userConditionList = queryFactory.selectFrom(qUser)
				//like示例
				.where(qUser.name.like('%'+"Jack"+'%')
						//contain示例
						.and(qUser.salt.contains("sa"))
						//equal示例
						.and(qUser.state.eq(0))
						//between
						.and(qUser.age.between(1, 10)))
				.fetch();
		logger.info("查询条件示例");
		userConditionList.forEach(System.out::println);

		//复杂条件组合示例，适用于查询条件有多个，对于条件需要判断的场景
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUser.salt.contains("sa"));
		BooleanBuilder builder2 = new BooleanBuilder();
		builder2.or(qUser.state.eq(0));
		builder2.or(qUser.age.eq(11));
		builder.and(builder2);
		List<User> userComplexConditionList = queryFactory.selectFrom(qUser).where(builder).fetch();
		logger.info("复杂条件组合示例");
		userComplexConditionList.forEach(System.out::println);

		//聚合函数-avg()
		Double averageAge = queryFactory.select(qUser.age.avg()).from(qUser).fetchOne();
		logger.info("聚合函数-avg()"+averageAge);
		//聚合函数-concat()
		String concat = queryFactory.select(qUser.name.concat(qUser.userName)).from(qUser).fetchFirst();
		logger.info("聚合函数-concat()"+concat);
		//聚合函数-date_format()
		String date = queryFactory.select(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qUser.registerDate)).from(qUser).fetchFirst();
		logger.info("聚合函数-date_format()"+date);
		//子查询
		List<Role> roleList = queryFactory.selectFrom(qRole).where(qRole.id.in(JPAExpressions.select(qUserRole.roleId).from(qUserRole))).fetch();
		logger.info("子查询");
		roleList.forEach(System.out::println);
		//排序
		List<User> orderList = queryFactory.selectFrom(qUser).orderBy(qUser.age.asc()).fetch();
		logger.info("排序");
		orderList.forEach(System.out::println);
	}

	@Test
	public void springDataSelect(){
		QUser qUser = QUser.user;
		//简单查询示例
		Iterable<User> iterable = userRepository.findAll(qUser.salt.eq("salt"));
		iterable.forEach(user -> System.out.println("姓名为"+user.getName()));
		//使用BooleanBuilder
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUser.salt.contains("0"));
		builder.and(qUser.name.eq("jack"));
		Iterable<User> iterable2 = userRepository.findAll(builder);
		iterable2.forEach(user -> System.out.println(user.getName()));

		//查询一条数据
		Optional<User> user = userRepository.findOne(qUser.name.eq("jack"));
		System.out.println(user.get().getName());

		//查询多条数据带排序
		//实现1
		OrderSpecifier<Integer> order = new OrderSpecifier<>(Order.DESC, qUser.id);
		Iterable<User> iterable3 = userRepository.findAll(qUser.state.eq(0),order);
		iterable3.forEach(user1 -> System.out.println(user1.getId()+user1.getName()));
		//实现2
		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "salt"));
		Iterable<User> iterable4=userRepository.findAll(qUser.state.eq(0), sort);
		iterable4.forEach(user1 -> System.out.println(user1.getId()+user1.getName()));
	}

	@Test
	public void QueryDSLPage(){
		QUser qUser = QUser.user;
		//写法一
		JPAQuery<User> query = queryFactory.selectFrom(qUser).orderBy(qUser.age.asc());
		long total = query.fetchCount();//hfetchCount的时候上面的orderBy不会被执行
		logger.info("total:"+total);
		List<User> list0= query.offset(2).limit(5).fetch();
		logger.info("data:");
		list0.forEach(System.out::println);
		//写法二
		QueryResults<User> results = queryFactory.selectFrom(qUser).orderBy(qUser.age.asc()).offset(2).limit(5).fetchResults();
		List<User> list = results.getResults();
		logger.info("data:");
		list.forEach(System.out::println);
		logger.debug("total:"+results.getTotal());
		logger.debug("limit:"+results.getLimit());
		logger.debug("offset:"+results.getOffset());
	}

	@Test
	public void template(){
		//使用template自定义指定的组合与数据
		QUser qUser = QUser.user;
		//使用booleanTemplate充当where子句或where子句的一部分
		List<User> list = queryFactory.selectFrom(qUser).where(Expressions.booleanTemplate("{0} = \'jack\'", qUser.name)).fetch();
		logger.info("使用booleanTemplate查询jack");
		list.forEach(System.out::println);
		//上面的写法，当booleanTemplate中需要用到多个占位时
		List<User> list1 = queryFactory.selectFrom(qUser).where(Expressions.booleanTemplate("{0} = \'jack\' and {1} = 11", qUser.name,qUser.age)).fetch();
		logger.info("使用booleanTemplate查询name为jack，age为11");
		list1.forEach(System.out::println);
		//使用stringTemplate充当查询语句的某一部分
		String date = queryFactory.select(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qUser.registerDate)).from(qUser).fetchFirst();
		logger.info("stringTemplate获取date"+date);
		//在where子句中使用stringTemplate
		Integer id = queryFactory.select(qUser.id).from(qUser).where(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qUser.registerDate).eq("2020-03-02")).fetchFirst();
		logger.info("stringTemplate获取id"+id);

	}
}
