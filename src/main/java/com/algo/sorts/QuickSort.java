package com.algo.sorts;

public class QuickSort
{
	/**
	 * �Ը���������arr���ƶ�����������п���
	 * @param arr ��������
	 * @param start ������ʼ����
	 * @param end �������������
	 */
	public static void quickSort(int[] arr, int start, int end)
	{
		if(start < end)
		{
			int pivotIndex = partition(arr, start, end);
			quickSort(arr, start, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, end);
		}
	}
	
	/**
	 * �Ը���������arr���ƶ�����������п��ţ���֮ͬ�����ڣ����������ѡһ��pivot
	 * @param arr ��������
	 * @param start ������ʼ����
	 * @param end �������������
	 */
	public static void quickSortRandomPivot(int[] arr, int start, int end)
	{
		if(start < end)
		{
			randomPivot(arr, start, end);	//�����������ѡpivot�Ĳ���
			int pivotIndex = partition(arr, start, end);
			quickSort(arr, start, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, end);
		}
	}
	
	/**
	 * ��arr�д�start��end���������ڵ�Ԫ�������ѡһ����Ϊpivot
	 * ֮�������ó�Ϊpublic������Ϊ������ʱ��ѡ���㷨�л��ʹ�õ�
	 * @param arr ������������
	 * @param start �������俪ʼλ��
	 * @param end �����������λ��
	 */
	public static void randomPivot(int[] arr, int start, int end)
	{
		int randomIndex = start + (int)(Math.random() * (end - start) + 0.5);
		swap(arr, start, randomIndex);
	}
	
	/**
	 * ��arr��start - endλ���ϵ�Ԫ�ظ���pivot���л��֣�pivot�������ϵĵ�һ��Ԫ�أ���arr[start]
	 * ֮�������ó�Ϊpublic������Ϊ������ʱ��ѡ���㷨�л��ʹ�õ�
	 * @param arr ������������
	 * @param start �������俪ʼλ�ã�Ҳ��pivotԪ�����ڵ�����
	 * @param end �����������λ��
	 * @return ����pivot�����arr�е�����λ��
	 */
	public static int partition(int[] arr, int start, int end)
	{
		int i = start;
		int j = end + 1;
		int pivot = arr[start];
		
//		System.out.println("Pivot Element: " + pivot);
		//��arr�б�pivotС��Ԫ���ƶ����������
		//��arr�б�pivot���Ԫ���ƶ����ұ�����
		while(true)
		{
			//������Կ�����descend���������ascend����
			while(arr[++i] <pivot && i < end);	//���������pivot��ͬ��ֵ�����ջ����pivot���ұߣ���
			while(arr[--j] > pivot && j > start);
			if(i >= j)
				break;	//������λi�������ߵ���jʱ����ֹѭ��
			swap(arr, i, j);
		}
		
		//��pivot��j����Ϊ��Ԫ�ؽ���λ��
		arr[start] = arr[j];
		arr[j] = pivot;
		
		return j;	//����pivotԪ�����ڵ�index
	}

	private static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
