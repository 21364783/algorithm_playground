package com.algo.chap2;

import java.util.ArrayList;

import com.algo.sorts.QuickSort;

/**
 * �������⣬��һ�����������г��������Ǹ�Ԫ���Լ����ĳ��ִ���
 * ����ʱ��: 2011-4-5
 * @author Destiny
 *
 */
public class Mode
{
	/**
	 * �洢����Ҫ�������
	 */
	private ArrayList<Integer> mode = new ArrayList<Integer>();
	
	/**
	 * �洢�Ѿ����й�scan��Ԫ�أ���ֹ�ظ�ɨ��
	 */
	private ArrayList<Integer> record = new ArrayList<Integer>();
	
	/**
	 * ��¼�����ĳ��ִ���
	 */
	private int num = 1;
	
	//����������Ա������ʹ�û��ڵݹ�Ľⷨʱ��Ҫ�õ�
	private int scanStart;
	private int scanEnd;
	
	public Mode()
	{
		
	}
	
	public Mode(int scanStart, int scanEnd)
	{
		this.scanEnd = scanEnd;
		this.scanStart = scanStart;
	}
	
	/**
	 * �������ĵݹ�ⷨ���������ĳ��ִ�����������arr���ȵ�һ��ʱ��Ч�ʽϸߣ�����Ч�ʵ���
	 * ��ʱʹ���������ͨ����-ɨ���㷨����
	 * ��startȡ0��endȡarr.length - 1ʱ������ȫ������
	 * ���������������ĳ�Ա������
	 * @param arr	�����������飬����
	 * @param start ������ʼ������
	 * @param end ��������������
	 */
	public void getMode(int[] arr, int start, int end)
	{
		//�ݹ���ֹ����
		if(start == end)
		{
			//������պ�ʱ���������һ������
			int num = this.searchForOccurrence(arr, arr[start]);
			this.handleNumOfOccurrence(num, arr[start]);
			return;
		}
			
		//���������Ѿ���������ˣ�û��ʹ��randomizePartition
		int pivotIndex = QuickSort.partition(arr, start, end);
		
		int numOfOccurrence;
		numOfOccurrence = this.searchForOccurrence(arr, arr[pivotIndex]);
		this.handleNumOfOccurrence(numOfOccurrence, arr[pivotIndex]);
		
		//pivotԪ����������Ԫ�ظ���
		int leftSize = pivotIndex - start;
		//pivotԪ���ұ������Ԫ�ظ���
		int rightSize = end - pivotIndex;
		
		//ֻ������������Ԫ�ظ������ڻ��ߵ��ڵ�ǰ����������ʱ�������������еݹ����
		if(leftSize >= this.num)
			this.getMode(arr, start, pivotIndex - 1);
		
		//ֻ��զ�ұ������Ԫ�ظ������ڻ��ߵ��ڵ�ǰ����������ʱ�����ұ�������еݹ����
		if(rightSize >= this.num)
			this.getMode(arr, pivotIndex + 1, end);
	}

	/**
	 * ������arr������Ԫ��i���ֵĴ����������ж�i�Ƿ��Ѿ�������
	 * ������й���������ֱ�ӷ���-1����������������ҽ�i���뵽��������list��
	 * @param arr ������������
	 * @param i ��Ҫ������ֵ
	 * @param start ������ʼ����
	 * @param end ������������
	 * @return Ԫ��i��arr�г��ֵĴ���
	 */
	private int searchForOccurrence(int[] arr, int i)
	{
		//�жϼ������Ƿ����Ԫ��i���������i��˵��Ԫ���Ѿ���ɨ�����
		if (!this.record.contains(i))
		{
			//list�в�����Ԫ��i����Ԫ��i���뵽record��
			record.add(i);
			
			int numOfOccurrence = 0;
			for (int j = this.scanStart; j <= this.scanEnd; j++)
			{
				if (i == arr[j])
					numOfOccurrence++;
			}
			return numOfOccurrence;
		}
		//����������Ѿ�������Ԫ��i��ֱ�ӷ���-1
		else
			return -1;
	}
	
	private void handleNumOfOccurrence(int numOfOccurrence, int value)
	{
		if(numOfOccurrence >= this.num)
		{
			if(numOfOccurrence == this.num)
				//����������������ͬ�������������Ҳ���뵽mode������
				this.mode.add(value);
			else
			{
				//������˸����numOfOccurrence����ô��mode������գ��������������뵽mode������
				this.mode.clear();
				this.mode.add(value);
				this.num = numOfOccurrence;
			}
		}
	}
	
	/**
	 * ��ͨ������-ɨ���㷨��������Ԫ�ظ����ܴ󣬵���������������Сʱ������Ч��
	 * @param arr ������������
	 * @param start �����ɨ�����ʼ����λ��
	 * @param end �����ɨ�����ֹ����λ��
	 */
	public void sortAndScan(int[] arr, int start, int end)
	{
		//���ȶ�ָ��������������п���
		if(start != end)
			QuickSort.quickSort(arr, start, end);
		
		int tempCount = 1;
		for (int i = start; i <= end; i++)
		{
			while(arr[i] == arr[i + 1])
			{
				tempCount++;
				if(i == end - 1)
					break;
				i++;
			}
			if(tempCount >= this.num)
			{
				if(tempCount == this.num)
					this.mode.add(arr[i]);
				else
				{
					this.mode.clear();
					this.mode.add(arr[i]);
					this.num = tempCount;
				}
			}
			
			//һ���ǵý�tempCount��������
			tempCount = 1;
		}
	}


	public ArrayList<Integer> getMode()
	{
		return mode;
	}


	public int getNum()
	{
		return num;
	}
}
