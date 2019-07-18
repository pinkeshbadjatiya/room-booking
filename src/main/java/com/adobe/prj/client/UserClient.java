package com.adobe.prj.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.adobe.prj.cfg.AppConfig;
import com.adobe.prj.dao.LayoutDao;
import com.adobe.prj.dao.LayoutDaoJpaImpl;
import com.adobe.prj.dao.RoomDaoJpaImpl;
import com.adobe.prj.dao.UserDaoJpaImpl;
import com.adobe.prj.entity.Room;
import com.adobe.prj.service.RoomService;
import com.adobe.prj.service.UserService;

public class UserClient {

	public static void main(String[] args) {
		// This line creates a Spring container
		// ctx === this is the container
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		// We are telling the spring container to start creating objects
		ctx.register(UserDaoJpaImpl.class);
		ctx.register(UserService.class);
		ctx.register(AppConfig.class);
		ctx.register(RoomDaoJpaImpl.class);
//		ctx.register(LayoutDao.class);
		ctx.register(LayoutDaoJpaImpl.class);
		ctx.register(RoomService.class);

		// This tells the Spring to start the wiring
		ctx.refresh();
		
		RoomService service = ctx.getBean("roomService", RoomService.class);
		Room r = service.getRoom(4);
		System.out.println(r);
	}

}
