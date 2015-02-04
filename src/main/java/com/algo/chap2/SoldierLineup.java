package com.algo.chap2;

import com.algo.sorts.QuickSort;

/**
 * ʿ��վ�����⣬ת��Ϊ��XY���������ϵ�median������X��Ҫ���б任
 * ����ʱ��: 2011-4-7
 * @author Destiny
 *
 */
public class SoldierLineup
{
	private int[] bestLocation = new int[2];
	private int distance = 0;
	
	public void caculateLocationAndDistance(int[] locationsX, int[] locationsY)
	{
		//��X������д���
		//������Ҫע�Ⱑ��
		QuickSort.quickSort(locationsX, 0, locationsX.length - 1);
		this.preprocessX(locationsX);
		
		int indexBestX = LinearSelection.optimizedSelect(locationsX, 0, locationsX.length - 1, locationsX.length / 2 + 1);
		int indexBestY = LinearSelection.optimizedSelect(locationsY, 0, locationsY.length - 1, locationsY.length / 2 + 1);
		bestLocation[0] = locationsX[indexBestX];
		bestLocation[1] = locationsY[indexBestY];
		
		distance += this.caculateDistance(locationsX, bestLocation[0]);
		distance += this.caculateDistance(locationsY, bestLocation[1]);
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
	
	private void preprocessX(int[] locationsX)
	{
		for(int i = 0; i < locationsX.length; i++)
			locationsX[i] -= i;
	}
	
	public int[] getBestLocation()
	{
		return bestLocation;
	}
	
	public int getDistance()
	{
		return distance;
	}
}
