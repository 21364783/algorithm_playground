package com.algo.chap2;

import com.algo.gadgets.NumberHandler;

/**
 * ͨ���ݹ�ķ����г�������������n��һ��ȫ����
 * ����ʱ��: 2011-3-30
 * @author Destiny
 *
 */
public class PermutationRecursive
{
	/**
	 * ��¼ȥȫ���е���������
	 */
	private int length;
	
	/**
	 * ���������־��������е����飬���ݽ�����Ӧ����һ��ascending������
	 */
	private int[] permutation;
	
	/**
	 * ���м�����
	 */
	private int count;
	
	public PermutationRecursive(int length, int[] permutation)
	{
		this.length = length;
		this.permutation = permutation;
	}
	
	public void getAllPermutation()
	{
		this.getPermutation(0, length - 1);
	}
	
	/**
	 * ��ָ�����������������ȫ����
	 * @param start ��ʼ����
	 * @param end ��������
	 * @return ���и���
	 */
	public void getPermutation(int start, int end)
	{
		//�ݹ��������
		if(start == end)
		{
			this.printPerm();
		}
		else
		{
			for(int i = start; i <= end; i++)
			{
				NumberHandler.swap(permutation, start, i);
				this.getPermutation(start + 1, end);
				NumberHandler.swap(permutation, start, i);
			}
		}
	}
	
	/**
	 * ��ӡ�������
	 */
	private void printPerm()
	{
		for (int temp : permutation)
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		//������+1
		this.count++;
	}
	
	/**
	 * ������������
	 */
	public void clearCount()
	{
		this.count = 0;
	}
	
	/**
	 * ��ü�������ֵ
	 * @return ��ü�������ֵ
	 */
	public int getCount()
	{
		return this.count;
	}
}
