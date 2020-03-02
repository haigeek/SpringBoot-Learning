package com.haigeek.querydsl;

import com.haigeek.querydsl.config.AppTestConfig;
import com.haigeek.querydsl.model.entity.*;
import com.haigeek.querydsl.repository.PermissionRepository;
import com.haigeek.querydsl.repository.RoleRepository;
import com.haigeek.querydsl.repository.UserRepository;
import com.haigeek.querydsl.repository.UserRoleRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

import java.security.PublicKey;
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
	JPAQueryFactory queryFactory;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void preSaveUser(){
		User user=new User();
		user.setUserName("jack");
		user.setName("jack");
		user.setPassword("123456");
		user.setState(0);
		user.setSalt("salt");
		userRepository.save(user);
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
	public void queyFactorySelect(){
//		QMemberDomain qm = QMemberDomain.memberDomain;
//		QFavoriteInfoDomain qf= QFavoriteInfoDomain.favoriteInfoDomain;
		QUser qUser=QUser.user;
		QRole qRole=QRole.role1;
		QUserRole qUserRole=QUserRole.userRole;
		//查询字段-select()
		List<String> nameList = queryFactory.select(qUser.name).from(qUser).fetch();
		//查询实体-selectFrom()
		List<User> userList = queryFactory.selectFrom(qUser).fetch();
		List<Integer> roleId=queryFactory.select(qUserRole.roleId).from(qUserRole).fetch();
		for (Integer id:roleId
			 ) {
			System.out.println(id);
		}
		//多表查询(left join)
		List<Role> leftJoinRole = queryFactory.selectFrom(qRole).leftJoin(qUserRole).on(qRole.id.eq(qUserRole.roleId)).where(qUserRole.userId.eq(2)).fetch();
		for (Role role:leftJoinRole
			 ) {
			System.out.println(role+role.getRole());
		}
//
//		//查询并将结果封装至dto中
//		List<MemberFavoriteDto> dtoList = queryFactory.select(Projections.constructor(MemberFavoriteDto.class,qm.name,qf.favoriteStoreCode)).from(qm).leftJoin(qm.favoriteInfoDomains,qf).fetch();
//		//去重查询-selectDistinct()
//		List<String> distinctNameList = queryFactory.selectDistinct(qm.name).from(qm).fetch();
//		//获取首个查询结果-fetchFirst()
//		MemberDomain firstMember = queryFactory.selectFrom(qm).fetchFirst();
//		//获取唯一查询结果-fetchOne()
//		//当fetchOne()根据查询条件从数据库中查询到多条匹配数据时，会抛`NonUniqueResultException`。
//		MemberDomain anotherFirstMember = queryFactory.selectFrom(qm).fetchOne();
//
//		//查询条件示例
//		List<MemberDomain> memberConditionList = queryFactory.selectFrom(qm)
//				//like示例
//				.where(qm.name.like('%'+"Jack"+'%')
//						//contain示例
//						.and(qm.address.contains("厦门"))
//						//equal示例
//						.and(qm.status.eq("0013"))
//						//between
//						.and(qm.age.between(20, 30)))
//				.fetch();
//		//复杂条件组合示例
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(qm.address.contains("厦门"));
//		BooleanBuilder builder2 = new BooleanBuilder();
//		builder2.or(qm.status.eq("0013"));
//		builder2.or(qm.status.eq("0014"));
//		builder.and(builder2);
//		List<MemberDomain> memberComplexConditionList = queryFactory.selectFrom(qm).where(builder).fetch();
//

//		//聚合函数-avg()
//		Double averageAge = queryFactory.select(qm.age.avg()).from(qm).fetchOne();
//		//聚合函数-concat()
//		String concat = queryFactory.select(qm.name.concat(qm.address)).from(qm).fetchFirst();
//		//聚合函数-date_format()
//		String date = queryFactory.select(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qm.registerDate)).from(qm).fetchFirst();
//
//		//子查询
//		List<MemberDomain> subList = queryFactory.selectFrom(qm).where(qm.status.in(JPAExpressions.select(qm.status).from(qm))).fetch();
//
//		//排序
//		List<MemberDomain> orderList = queryFactory.selectFrom(qm).orderBy(qm.name.asc()).fetch();
	}

//	@SuppressWarnings("unused")
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
//
//	@SuppressWarnings("unused")
//	@Test
//	public void QueryDSL分页的写法(){
//		QMemberDomain qm = QMemberDomain.memberDomain;
//		//写法一
//		JPAQuery<MemberDomain> query = queryFactory.selectFrom(qm).orderBy(qm.age.asc());
//		long total = query.fetchCount();//hfetchCount的时候上面的orderBy不会被执行
//		List<MemberDomain> list0= query.offset(2).limit(5).fetch();
//		//写法二
//		QueryResults<MemberDomain> results = queryFactory.selectFrom(qm).orderBy(qm.age.asc()).offset(2).limit(5).fetchResults();
//		List<MemberDomain> list = results.getResults();
//		logger.debug("total:"+results.getTotal());
//		logger.debug("limit:"+results.getLimit());
//		logger.debug("offset:"+results.getOffset());
//	}
//
//	@SuppressWarnings("unused")
//	@Test
//	public void template的使用(){
//		QMemberDomain qm = QMemberDomain.memberDomain;
//		//使用booleanTemplate充当where子句或where子句的一部分
//		List<MemberDomain> list = queryFactory.selectFrom(qm).where(Expressions.booleanTemplate("{} = \"tofu\"", qm.name)).fetch();
//		//上面的写法，当booleanTemplate中需要用到多个占位时
//		List<MemberDomain> list1 = queryFactory.selectFrom(qm).where(Expressions.booleanTemplate("{0} = \"tofu\" and {1} = \"Amoy\"", qm.name,qm.address)).fetch();
//
//		//使用stringTemplate充当查询语句的某一部分
//		String date = queryFactory.select(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qm.registerDate)).from(qm).fetchFirst();
//		//在where子句中使用stringTemplate
//		String id = queryFactory.select(qm.id).from(qm).where(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qm.registerDate).eq("2018-03-19")).fetchFirst();
//
//	}
	

}
