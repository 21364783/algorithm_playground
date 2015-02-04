package com.algo.chap2;

/**
 * ����Ӧ�þ�����X��Y�����Ϸֱ������λ��
 * ʹ������ʱ��ѡ���㷨
 * ����ʱ��: 2011-4-5
 * @author Destiny
 *
 */
public class PostOfficeLocation
{
	/**
	 * ����������
	 */
	private int[] location = new int[2];
	
	/**
	 * ����|x1 - x2| + |y1 - y2|���ܺ�
	 * ��������ܺ�
	 */
	private int distance = 0;
	
	/**
	 * ���ȼ�����ѵ�XY���꣬����distance�����걣���ڴ���ĳ�Ա������
	 * @param x X��������
	 * @param y Y��������
	 */
	public void CaculateDistance(int[] x, int[] y)
	{
		//��������ʱ��ѡ�����Ż�����ѡ���㷨�ֱ�õ����X��Y������
		int indexX = LinearSelection.optimizedSelect(x, 0, x.length - 1, x.length / 2 + 1);
		int indexY = LinearSelection.optimizedSelect(y, 0, y.length - 1, y.length / 2 + 1);
		location[0] = x[indexX];
		location[1] = y[indexY];
		
		//�־����XY�����������ĺ�
		distance += this.caculateDistance(x, location[0]);
		distance += this.caculateDistance(y, location[1]);
	}
	
	/**
	 * ����һ�������е�ÿһ��Ԫ�ص�median�ľ�����ܺ�
	 * @param arr ������������
	 * @param median �������λ��
	 * @return ������ܺ�
	 */
	private int caculateDistance(int[] arr, int median)
	{
		int tempDistance = 0;
		
		for(int i = 0; i < arr.length; i++)
		{
			tempDistance += Math.abs(arr[i] - median);
		}
		
		return tempDistance;
	}

	public int[] getLocation()
	{
		return location;
	}

	public int getDistance()
	{
		return distance;
	}
}
