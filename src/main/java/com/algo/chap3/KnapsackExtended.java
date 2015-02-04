package com.algo.chap3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algo.gadgets.Point;

/**
 * ʹ�����н��ܵĻ�����Ծ����㷨����ⱳ������
 * ����֧��weight��value��double������ֵ�����
 * ����ʱ��: 2011-4-11
 * @author Destiny
 *
 */
public class KnapsackExtended
{
	private double[] weight;
	private double[] value;
	private int num;
	private double maxWeight;
	
	/**
	 * ���ڱ�������ȡ������1����ȡ��0����ȡ
	 */
	private int[] result;
	
	private List<Point> weightAndValue;
	
	/**
	 * ������Ծ�����Ϣ
	 * �൱����ʽ����ʾ�е�P[i]
	 */
	private List<Point> keyPoints;
	
	/**
	 * ������ʱ�����Ϣ
	 * �൱����ʽ����ʾ�е�Q[i]
	 */
	private List<Point> tempPoints;
	
	/**
	 * �����ܼ�ֵ
	 */
	private double totalValue;
	
	public KnapsackExtended(int num, double maxWeight, double[] weight, double[] value) throws Exception
	{
		if(num != weight.length || num != value.length)
			throw new Exception("��Ʒ�ĸ�����weight�����Ԫ�ظ������value�����Ԫ�ظ�����һ�£�");
		
		//���г�ʼ���Ĳ���
		this.weight = weight;
		this.value = value;
		this.num = num;
		this.maxWeight = maxWeight;
		
		//��ʼ��matrix�Լ�result
		result = new int[num + 1];
		
		//��ʼ����Ծ���List
		this.keyPoints = new ArrayList<Point>();
		keyPoints.add(new Point(0, 0));
		
		//��Weight�Լ�Value��Ϣ����Point��Ϣ��������List��
		this.initWeightAndValueList();
	}
	
	public void run()
	{
		this.createKeyPoints();
		
		//TEST
		for (int i = 0; i < keyPoints.size(); i++)
		{
			System.out.println("X = " + keyPoints.get(i).getX() + "; Y = " + keyPoints.get(i).getY());
		}
	}
	
	private void createKeyPoints()
	{
		for(int i = this.num - 1; i >= 0; i--)
		{
			tempPoints = this.addListByPoint(keyPoints, this.weightAndValue.get(i));	//��Ӧ����һ��Point�Ĺ��˹��ܣ�x����maxWeight�Ĳ��ܱ���
			this.getUnionList(keyPoints, tempPoints);	
			this.deleteControlledPoint(keyPoints);
		}
	}

	/**
	 * ʹ��weight�����Լ�value��������ʼ��ArrayList
	 */
	private void initWeightAndValueList()
	{
		this.weightAndValue = new ArrayList<Point>();
		
		for(int i = 0; i < num; i++)
		{
			weightAndValue.add(new Point(this.weight[i], this.value[i]));
		}
	}

	/**
	 * �Բ����е�����List�󲢼�
	 * @param list1
	 * @param list2
	 */
	private void getUnionList(List<Point> list1, List<Point> list2)
	{
		list1.removeAll(list2);
		list1.addAll(list2);
	}
	
	/**
	 * ɾ��һ��δ�����ArrayList�е��ܿص�
	 * @param points
	 */
	private void deleteControlledPoint(List<Point> pointsList)
	{
		if (pointsList.size() > 1)
		{
			Collections.sort(pointsList);
		}
		
		//��index����0�ĵط���ʼ�жϣ��������⣡
		//�������ķ��ؽ��
		/**
		 * X = 0.0; Y = 0.0
			X = 2.0; Y = 3.0
			X = 2.0; Y = 6.0
			X = 4.0; Y = 9.0
			X = 6.0; Y = 12.0
			X = 8.0; Y = 15.0
			�����е�X = 2.0; Y = 3.0���Բ���Ҫ�������ܿص�
		 */
		for (int i = 0; i < pointsList.size() - 1;)
		{
			if((pointsList.get(i).getX() < pointsList.get(i + 1).getX() || 
					Math.abs(pointsList.get(i).getX() - pointsList.get(i + 1).getX()) <= 0.000001) && 
					(pointsList.get(i).getY() > pointsList.get(i + 1).getY() ||
					Math.abs(pointsList.get(i).getY() - pointsList.get(i + 1).getY()) <= 0.000001))
			{
				pointsList.remove(i + 1);
				continue;
			}
			//��Ҫɾ��ǰ��ע���в���Ҫ��ĵ㣬�ж�ǰ���X��ͬ������ǰ�ߵ�YС��ʵ����Ҳ���ܿص�
			else if((Math.abs(pointsList.get(i).getX() - pointsList.get(i + 1).getX()) <= 0.000001) && 
					(pointsList.get(i).getY() < pointsList.get(i + 1).getY() ||
					Math.abs(pointsList.get(i).getY() - pointsList.get(i + 1).getY()) <= 0.000001))
			{
				pointsList.remove(i);
				continue;
			}
			i++;
		}
		
//		for(int i = pointsList.size() - 1; i >= 1; i--)
//		{
//			if((pointsList.get(i).getX() > pointsList.get(i - 1).getX() || 
//					Math.abs(pointsList.get(i).getX() - pointsList.get(i - 1).getX()) <= 0.000001) && 
//					(pointsList.get(i).getY() < pointsList.get(i - 1).getY() ||
//					Math.abs(pointsList.get(i).getY() - pointsList.get(i - 1).getY()) <= 0.000001))
//			{
//				pointsList.remove(i);
//			}
//		}
	}
	
	/**
	 * 
	 * @param points
	 * @param p
	 * @return
	 */
	private List<Point> addListByPoint(List<Point> points, Point p)
	{
		//��Ҫ��points������clone���õ�һ���µ�List�󣬶��µĽ��в��������أ�
		
		List<Point> temp = new ArrayList<Point>();
		
		for (int i = 0; i < points.size(); i++)
		{
			Point candidate = new Point(points.get(i)).add(p);
			if(candidate.getX() <= 10)
				temp.add(candidate);
		}
		
		return temp;
	}
}

