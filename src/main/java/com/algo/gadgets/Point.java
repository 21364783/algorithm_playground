package com.algo.gadgets;

import java.util.Comparator;

/**
 * ʵ����һЩ��ĳ��ò�����ʵ��Ϊdouble���͵�
 * ˳�㸴ϰC&C�ӿڵ�ʹ�÷���
 * ����ʱ��: 2011-4-11
 * @author Destiny
 *	ʵ�ʵ����ͣ�����Integer��Double��
 * @param <T>
 */
public class Point implements Comparable<Point>
{
	private double x;
	private double y;
	
	private static Comparator<Point> comparator;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public Point add(double x, double y)
	{
		this.x += x;
		this.y += y;
		
		return this;
	}
	
	public Point add(Point p)
	{
		this.x += p.getX();
		this.y += p.getY();
		
		return this;
	}

	@Override
	public int compareTo(Point o)
	{
//		System.out.println(this);
//		System.out.println(o);
		
		return Point.comparator.compare(this, o);
	}

	@Override
	public String toString()
	{
		return "X = " + this.x + "; Y = " + this.y;
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public static void setComparator(Comparator<Point> comparator)
	{
		Point.comparator = comparator;
	}
	
}
