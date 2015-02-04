package com.algo.gadgets;

public class NumberHandler
{
	/**
	 * 
	 * @param number ����Ҫת����Integer
	 * @return ����number��ÿһλ���֣��洢��int������
	 */
	public static int[] convertIntToIntArray(int number)
	{	
		String ns = String.valueOf(number);
		int[] nums = new int[ns.length()];
		
		for (int i = 0; i < ns.length(); i++)
		{
			nums[i] = Integer.parseInt(ns.charAt(i) + "");
		}
		
		return nums;
	}
	
	/**
	 *����arr����������Ϊi1�Լ�����Ϊi2������Ԫ��
	 * @param arr Ԫ����������
	 * @param i1 ��һ��������Ԫ�ص�����
	 * @param i2 �ڶ���������Ԫ�ص�����
	 */
	public static void swap(int[] arr, int i1, int i2)
	{
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	/**
	 * ��number�Ľ׳�
	 * @param number ����׳˵���
	 * @return ����number�Ľ׳�
	 */
	public static long factorial(int number)
	{
		//���м򵥵���֤
		if(number < 0)
			return -1;
		if(number == 0)
			return 1;
		
		long sum = 1;
		while(number > 0)
		{
			sum *= number;
			number--;
		}
		
		return sum;
	}
}
