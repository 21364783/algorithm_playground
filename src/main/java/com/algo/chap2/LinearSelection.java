package com.algo.chap2;

import com.algo.sorts.QuickSort;

/**
 * ����ѡ���㷨��������arr��ѡ���kС��Ԫ�أ�����λ��Ԫ��Ҳ����
 * ����ʱ��: 2011-4-5
 * @author Destiny
 *
 */
public class LinearSelection
{
	/**
	 * �����ѡ���㷨��������arr��������������ѡ��selectС��Ԫ��
	 * ʹ��QuickSort�е�partition�������л��֣�Ȼ���������������еݹ�
	 * @param arr ������������
	 * @param start �������俪ʼλ��
	 * @param end �����������λ��
	 * @param select ��selectС��Ԫ��
	 * @return �����ڵĵ�selectС��Ԫ��
	 */
	public static int randomizedSelect(int[] arr, int start, int end, int select)
	{
		//�ݹ��㷨����ֹ����
		if(start == end)
			return arr[start];
		
		QuickSort.randomPivot(arr, start, end);	//��ѡ�������һ��pivot���ŵ�startλ��
		int pivotIndex = QuickSort.partition(arr, start, end);	//���partition������pivot��index
		
		//pivot��������Ԫ�ظ���
		int num = pivotIndex - start + 1;
		
		//�ж���Ҫѡ���Ԫ����������
		if(select <= num)	//��ʾ��Ҫѡ���Ԫ�����������
			return randomizedSelect(arr, start, pivotIndex, select);
		else
			return randomizedSelect(arr, pivotIndex + 1, end, select - num);
	}
	
	/**
	 * ѡ���㷨��������arr��������������ѡ��selectС��Ԫ�ء������鳤�ȴ���һ����ֵʱ�������Ż��㷨
	 * ��n��Ԫ��5��5����Ϊһ�飬�����ֵ�����Ϊn�飬��������һ���鲻��5��Ԫ���⡣��ÿһ�����λ��
	 * ���ν����������ǰn��λ�á��ҵ���n��Ԫ���е���λ��������λ������λ�����������X��Ϊpivot����
	 * �������partition���������ϵ�Ŀ����ʹpartition�����������Ԫ�ظ�������ƽ�⡣
	 * Ȼ��ʹ���ж���Ҫ�ҵ�Ԫ�����������������Ұ���������еݹ����
	 * @param arr ������������
	 * @param start �������俪ʼλ��
	 * @param end �����������λ��
	 * @param select ��selectС��Ԫ�أ�ע��ʹ�õ������ - Based on 1���������� - Based on 0
	 * @return �����ڵĵ�selectС��Ԫ�ص� ��������������������������������
	 */
	public static int optimizedSelect(int[] arr, int start, int end, int select)
	{
		int threshold = 75;
		//ʵ�ʴ����ҵ�Ԫ�ظ���
		int actualLength = end - start + 1;
		
		//���select�ĺϷ���
		if(select < 1 || select > end - start + 1)
		{
			System.out.println("���Ϸ���selectֵ��");
			return -1;
		}
		
		if(actualLength <= threshold)
		{
			//���ʵ��Ԫ�ظ���С����ֵ����ôֱ�ӽ��п���
			QuickSort.quickSort(arr, start, end);
			return start + select - 1;
		}
		
		//���Ԫ�ظ�����������ֵ
		for(int i = 0; i < (end - start - 4) / 5; i++)
			handleSubArray(i, start, arr);
		
		//�ҵ���λ������λ��������
		int allMedianIndex = optimizedSelect(arr, start, start + (end - start - 4) / 5, (end - start - 4) / 10 + 1);
		//����λ������λ������startλ��
		int temp = arr[start];
		arr[start] = arr[allMedianIndex];
		arr[allMedianIndex] = temp;
		
		//������λ������λ������partition����
		int pivotIndex = QuickSort.partition(arr, start, end);
		
		//������������Ԫ�ظ���
		int num = pivotIndex - start + 1;
		
		//���еݹ����
		if(select <= num)	
			return optimizedSelect(arr, start, pivotIndex,  select); //��ʾ��select��Ԫ�����������
		else
			return optimizedSelect(arr, pivotIndex + 1, end, select - num);
	}

	/**
	 * �Գ���Ϊ5����������д����ҵ���λ�������н���
	 * @param i ����������
	 * @param start ��ʼ������������ָarr������
	 * @param arr �������������
	 */
	private static void handleSubArray(int i, int start, int[] arr)
	{
		//��ÿһ���������������
		QuickSort.quickSort(arr, start + i * 5, start + i * 5 + 4);
		//��Ԫ�ص���λ��
		int median = arr[start + i * 5 + 2];
		//����Ԫ�ص�swap�������������е�median�������е�ǰ(end - start - 4) / 5��Ԫ�ؽ��н���
		int temp = arr[start + i];
		arr[start + i] = median;
		arr[start + i * 5 + 2] = temp;
	}
}
