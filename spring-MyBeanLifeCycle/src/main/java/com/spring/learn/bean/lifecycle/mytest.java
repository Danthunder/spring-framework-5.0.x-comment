package com.spring.learn.bean.lifecycle;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-21
 **/
public class mytest {

	public static void main(String[] args) {
		String[] a = new String[] {"1,2,3","a,b,c","4,2,a"};

		List<List<String>> b = new ArrayList<>();
		List<String> tmp1 = new ArrayList<>();
		tmp1.add("1");
		tmp1.add("2");
		tmp1.add("3");
		List<String> tmp2 = new ArrayList<>();
		tmp2.add("a");
		tmp2.add("b");
		tmp2.add("c");
		List<String> tmp3 = new ArrayList<>();
		tmp3.add("4");
		tmp3.add("2");
		tmp3.add("a");
		b.add(tmp1);
		b.add(tmp2);
		b.add(tmp3);

		System.out.println("String[]:" + GraphLayout.parseInstance(a).totalSize());
		System.out.println("List<List<String>>:" + GraphLayout.parseInstance(b).totalSize());

		System.out.println("String[]:" + ClassLayout.parseInstance(a).instanceSize());
		System.out.println("List<List<String>>:" + ClassLayout.parseInstance(b).instanceSize());
	}
}
