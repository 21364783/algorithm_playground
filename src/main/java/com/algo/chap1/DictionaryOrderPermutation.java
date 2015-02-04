package com.algo.chap1;

/**
 * �����ֵ��������һ�����⣬��ȫ�����йص�����
 * @author Destiny
 *
 */
public class DictionaryOrderPermutation
{
	//���Է�������
	public int[] getOrderAndNextSequence(int[] arr)
	{
		System.out.println("��ǰ���е���ţ�" + this.findPosition(arr));
		if(this.sortSequence(arr))
			return arr;
		else
		{
			System.out.println("�Ѿ��ﵽ���һ�����У�");
			return arr;
		}
	}

	/**
	 * ��number�Ľ׳�
	 * @param number ����׳˵���
	 * @return ����number�Ľ׳�
	 */
	private int factorial(int number)
	{
		//���м򵥵���֤
		if(number <= 0)
			return -1;
		
		int sum = 1;
		while(number > 0)
		{
			sum *= number;
			number--;
		}
		
		return sum;
	}
	
	private boolean sortSequence(int[] arr)
	{
		int index1, index2;
		index1 = this.findFirstSmall(arr);
		//���û���ҵ��������Ѿ��ﵽ���һ������
		if(index1 == -1)
			return false;
		index2 = this.findSmallest(arr, index1 + 1);
		this.swap(arr, index1, index2);
		this.converse(arr, index1 + 1, arr.length - 1);
		
		return true;
	}
	
	/**
	 * ��ȡ���������ڴ��ֵ������֮�µ����
	 * @param arr �����������
	 * @return �����е����
	 */
	private int findPosition(int[] arr)
	{
		int sum = 0;
		for(int i = 0; i < arr.length; i++)
		{
			int smallerNumber = this.getCount(arr, i);
			if(smallerNumber > 0)
				sum += smallerNumber * this.factorial(arr.length - i - 1);
		}
		
		return sum + 1;
	}
	
	/**
	 * �Ӵ������������Ҷ˿�ʼ�ҵ���һ��Ԫ�أ�����Ҫ���������������ֵС�����ұ�Ԫ�ص�ֵ
	 * @param arr ������������
	 * @return ��Ԫ�ص�����
	 */
	private int findFirstSmall(int[] arr)
	{
		for(int i = arr.length - 1; i > 0; i--)
		{
			if(arr[i-1] < arr[i])
				return i - 1;
		}
		//���û���ҵ�
		return -1;
	}
	
	/**
	 * ���arr[start]�ұ����б���С��Ԫ�صĸ���
	 * @param arr ������������
	 * @param start ��ʼ�����ı߽�
	 * @return ����arr[start]�ұ����б���С��Ԫ�صĸ���
	 */
	private int getCount(int[] arr, int start)
	{
		int value = arr[start];
		int count = 0;
		for(int i = start + 1; i < arr.length; i++)
			if(arr[i] < value)
				count++;
		
		return count;
	}
	
	/**
	 * ��������arr�е�ĳһ��Ԫ��arr[start - 1]���ҳ������Ҳ�����б�����������С��һ���������ظ���������
	 * @param arr ����������������
	 * @param start ��⿪ʼ������
	 * @return arr[start - 1]�Ҳ�����б�����������С��һ��������
	 */
	private int findSmallest(int[] arr, int start)
	{
		int size = arr.length;
		int value = arr[start - 1];
		int key = arr[start];
		int index = start;
		for(int i = start + 1; i < size; i++)
		{
			if(arr[i] > value && arr[i] < key)
			{
				key = arr[i];
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	 * ʵ������������Ԫ��λ�õ���
	 * @param arr ��������
	 * @param index1 ��Ҫ������Ԫ������1
	 * @param index2 ��Ҫ������Ԫ������2
	 */
	private void swap(int[] arr, int index1, int index2)
	{
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * ������arr�е�ָ�����н����������
	 * @param arr �������������������
	 * @param start ��ʼ�����������������
	 * @param end ���������������������
	 */
	private void converse(int[] arr, int start, int end)
	{
		//���Թ۲쵽�����Ǵ���࿪ʼѭ�������Ǵ��Ҳ࿪ʼѭ�����м��һ�����������Ա�ʾΪstart + end - i
		for(int i = end; i >= (end + start + 1) / 2; i--)
			swap(arr, end - i + start, i);
//		for(int i = start; i <= (end + start + 1) / 2; i++)
//			swap(arr, i, start + end - i);
	}
}
